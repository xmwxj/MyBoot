import subprocess
from loguru import logger


def run_maven_clean_install(project_dir: str, maven_args: list = None) -> bool:
    """
    执行 Maven clean install

    Args:
        project_dir: Maven 项目目录
        maven_args: 额外的 Maven 参数（可选）

    Returns:
        bool: 是否成功
    """
    try:
        # 构建 Maven 命令
        cmd = ['mvn', 'clean', 'install']

        # 添加额外参数
        if maven_args:
            cmd.extend(maven_args)

        logger.info(f"正在执行 Maven: {' '.join(cmd)}")
        logger.info(f"项目目录：{project_dir}")

        # 执行命令
        process = subprocess.Popen(
            cmd,
            cwd=project_dir,  # 指定工作目录
            stdout=subprocess.PIPE,
            stderr=subprocess.STDOUT,  # 将 stderr 合并到 stdout
            text=True,
            bufsize=20,
            encoding='utf-8'
        )

        # 实时读取并输出
        for line in process.stdout:
            print(line, end='')  # 实时打印每一行

        # 等待进程结束
        return_code = process.wait()

        if return_code == 0:
            logger.success("Maven clean install 执行成功")
            return True
        else:
            logger.error(f"Maven 执行失败，退出码：{return_code}")
            return False

    except FileNotFoundError:
        logger.error("未找到 mvn 命令，请确保已安装 Maven")
        return False
    except Exception as e:
        logger.error(f"Maven 执行异常：{e}")
        return False
