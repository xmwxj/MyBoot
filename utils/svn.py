import subprocess
from loguru import logger
from pathlib import Path
from typing import Optional, List, Dict


class SvnUtils:
    """SVN 工具类，封装常用的 SVN 操作"""

    def __init__(self, username: Optional[str] = None, password: Optional[str] = None):
        """
        初始化 SVN 工具类

        Args:
            username: SVN 用户名（可选）
            password: SVN 密码（可选）
        """
        self.username = username
        self.password = password
        self.base_cmd = ['svn']

        if username and password:
            self.base_cmd.extend(['--username', username, '--password', password, '--no-auth-cache'])

    def _run_svn_command(self, cmd: List[str], description: str = "SVN 操作") -> tuple[bool, str]:
        """
        运行 SVN 命令的通用方法

        Args:
            cmd: SVN 命令列表
            description: 操作描述

        Returns:
            tuple: (是否成功，输出信息)
        """
        try:
            full_cmd = self.base_cmd + cmd
            #logger.info(f"正在执行 {description}: {' '.join(full_cmd)}")
            logger.info(f"正在执行 {description}")

            result = subprocess.run(
                full_cmd,
                capture_output=True,
                text=True,
                check=True,
                encoding='utf-8'
            )

            logger.success(f"{description} 成功")
            return True, result.stdout

        except subprocess.CalledProcessError as e:
            error_msg = f"{description} 失败：{e.stderr}"
            logger.error(error_msg)
            return False, error_msg
        except FileNotFoundError:
            error_msg = "未找到 svn 命令，请确保已安装 SVN 客户端"
            logger.error(error_msg)
            return False, error_msg
        except Exception as e:
            error_msg = f"{description} 异常：{str(e)}"
            logger.error(error_msg)
            return False, error_msg

    def export(self, svn_url: str, local_path: str, force: bool = True) -> bool:
        """
        从 SVN 导出单个文件或目录

        Args:
            svn_url: SVN 文件/目录 URL
            local_path: 本地保存路径
            force: 是否强制覆盖已存在的文件

        Returns:
            bool: 是否成功
        """
        try:
            # 确保目标目录存在
            target_path = Path(local_path)
            target_path.parent.mkdir(parents=True, exist_ok=True)

            cmd = ['export', svn_url, local_path]
            if force:
                cmd.append('--force')

            success, output = self._run_svn_command(cmd, f"导出 {svn_url}")
            return success

        except Exception as e:
            logger.error(f"导出操作异常：{e}")
            return False

    def export_batch(self, files: Dict[str, str]) -> Dict[str, bool]:
        """
        批量导出多个文件

        Args:
            files: 字典 {svn_url: local_path}

        Returns:
            dict: {svn_url: 是否成功}
        """
        results = {}

        for svn_url, local_path in files.items():
            logger.info(f"处理文件：{svn_url} -> {local_path}")
            success = self.export(svn_url, local_path)
            results[svn_url] = success

        success_count = sum(results.values())
        total_count = len(results)
        logger.info(f"批量导出完成：{success_count}/{total_count} 成功")

        return results

    def checkout(self, svn_url: str, local_path: str) -> bool:
        """
        从 SVN 检出仓库或目录

        Args:
            svn_url: SVN 仓库/目录 URL
            local_path: 本地路径

        Returns:
            bool: 是否成功
        """
        try:
            # 确保父目录存在
            Path(local_path).parent.mkdir(parents=True, exist_ok=True)

            cmd = ['checkout', svn_url, local_path]
            success, output = self._run_svn_command(cmd, f"检出 {svn_url}")
            return success

        except Exception as e:
            logger.error(f"检出操作异常：{e}")
            return False

    def update(self, local_path: str, revision: Optional[str] = None) -> bool:
        """
        更新本地工作副本

        Args:
            local_path: 本地工作副本路径
            revision: 指定版本号（可选），如 'HEAD' 或具体版本号 '1234'

        Returns:
            bool: 是否成功
        """
        try:
            cmd = ['update', local_path]

            if revision:
                cmd.extend(['-r', revision])

            success, output = self._run_svn_command(cmd, f"更新 {local_path}")

            if success and output:
                logger.debug(f"更新详情：{output}")

            return success

        except Exception as e:
            logger.error(f"更新操作异常：{e}")
            return False

    def update_batch(self, paths: List[str], revision: Optional[str] = None) -> Dict[str, bool]:
        """
        批量更新多个工作副本

        Args:
            paths: 本地路径列表
            revision: 指定版本号（可选）

        Returns:
            dict: {路径：是否成功}
        """
        results = {}

        for path in paths:
            logger.info(f"更新工作副本：{path}")
            success = self.update(path, revision)
            results[path] = success

        success_count = sum(results.values())
        total_count = len(results)
        logger.info(f"批量更新完成：{success_count}/{total_count} 成功")

        return results

    def info(self, path: str) -> Optional[Dict]:
        """
        获取 SVN 信息

        Args:
            path: 文件或目录路径（可以是本地路径或 SVN URL）

        Returns:
            dict: SVN 信息，失败返回 None
        """
        try:
            cmd = ['info', '--xml', path]
            success, xml_output = self._run_svn_command(cmd, f"获取信息 {path}")

            if success:
                # 解析 XML（简单提取关键信息）
                info = {
                    'xml': xml_output,
                    'path': path
                }
                logger.debug(f"SVN 信息获取成功")
                return info
            return None

        except Exception as e:
            logger.error(f"获取信息异常：{e}")
            return None

    def status(self, local_path: str) -> Optional[str]:
        """
        查看工作状态

        Args:
            local_path: 本地工作副本路径

        Returns:
            str: 状态信息，失败返回 None
        """
        try:
            cmd = ['status', local_path]
            success, output = self._run_svn_command(cmd, f"查看状态 {local_path}")

            if success:
                return output
            return None

        except Exception as e:
            logger.error(f"查看状态异常：{e}")
            return None

    def log(self, path: str, limit: int = 10) -> Optional[str]:
        """
        查看提交日志

        Args:
            path: 文件或目录路径
            limit: 显示最近多少条日志

        Returns:
            str: 日志信息，失败返回 None
        """
        try:
            cmd = ['log', '-l', str(limit), path]
            success, output = self._run_svn_command(cmd, f"查看日志 {path}")

            if success:
                return output
            return None

        except Exception as e:
            logger.error(f"查看日志异常：{e}")
            return None

