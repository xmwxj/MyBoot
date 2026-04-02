import subprocess
from loguru import logger

def boot(module_dir: str, fixture_file: str, timeout: int = 3600) -> bool:
    """
    启动测试环境并执行初始化测试

    Args:
        module_dir: 模块目录（包含 fixture 文件）
        fixture_file: fixture 文件路径（相对于 module_dir）
        timeout: 命令执行超时时间（秒），默认 1 小时

    Returns:
        bool: 是否成功

    Example:
        >>> boot('/path/to/module', 'com/aciworldwide/hub/fixtures/boot_fixture.xml')
    """
    try:
        logger.info(f"准备启动测试环境")
        logger.info(f"模块目录：{module_dir}")
        logger.info(f"Fixture 文件：{fixture_file}")

        # 构建 bt-sting 命令脚本
        bt_script = f"""load {fixture_file}
start fixture -a -w
runtest boot*
runtest Load*
status -m
quit
"""

        # 切换到模块目录并执行命令
        logger.info("开始启动...")

        process = subprocess.Popen(
            ['bt-sting', '-e', 'upp1', '-DTDE_DISABLED=true', '-DNO_PARTITION=true'],
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,  # 合并 stderr 到 stdout
            text=True,
            cwd=module_dir,
            bufsize=10  # 行缓冲
        )

        # 发送脚本内容到 stdin
        process.stdin.write(bt_script)
        process.stdin.flush()
        process.stdin.close()  # 关闭 stdin，让进程知道输入已完成
        print()  # 空行分隔
        for line in iter(process.stdout.readline, ''):
            if line:
                print(line, end='')  # 实时打印每一行
        print()  # 空行分隔

        # 等待进程完成
        process.wait(timeout=timeout)

        # 检查结果
        if process.returncode == 0:
            logger.success("✓ 测试环境启动成功")
            return True
        else:
            logger.error(f"✗ 测试环境启动失败，退出码：{process.returncode}")
            return False

    except subprocess.TimeoutExpired:
        logger.error(f"命令执行超时（{timeout}秒）")
        return False
    except FileNotFoundError:
        logger.error("未找到 bt-sting 命令，请确保已安装并配置到 PATH")
        return False
    except Exception as e:
        logger.error(f"启动过程异常：{e}")
        return False