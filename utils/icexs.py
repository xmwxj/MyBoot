import os
from loguru import logger

def switch_icexs_version(icexs_version: str):
    """切换 ICEXS 版本"""
    base_dir = os.getenv('base_dir', '')
    target_path = os.path.join(base_dir, icexs_version)
    if not os.path.exists(target_path):
        logger.error(f"icexs版本 '{icexs_version}' 不存在")
        return
    else:
        logger.info(f"切换icexs版本为 '{icexs_version}'")
        os.system(f"cd {base_dir} &&  rm current && ln -s {target_path} current")