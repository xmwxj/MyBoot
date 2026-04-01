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
        result = subprocess.run(
            cmd,
            cwd=project_dir,  # 指定工作目录
            capture_output=True,
            text=True,
            check=False,  # 不自动抛出异常
            encoding='utf-8'
        )

        # 输出日志
        if result.stdout:
            logger.debug(f"Maven 输出:\n{result.stdout}")

        if result.returncode == 0:
            logger.success("Maven clean install 执行成功")
            return True
        else:
            logger.error(f"Maven 执行失败，退出码：{result.returncode}")
            if result.stderr:
                logger.error(f"错误信息:\n{result.stderr}")
            return False

    except FileNotFoundError:
        logger.error("未找到 mvn 命令，请确保已安装 Maven")
        return False
    except Exception as e:
        logger.error(f"Maven 执行异常：{e}")
        return False
