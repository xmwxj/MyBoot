from decorator.timer import timer
from loguru import logger
from utils.svn import SvnUtils
from dotenv import load_dotenv
from utils.icexs import switch_icexs_version
import sys,os,json

@timer
def main(module:str):
    logger.info(f"开始启动模块：{module}")
    # 从环境变量读取 SVN 配置
    svn_username = os.getenv('SVN_USERNAME', '')
    svn_password = os.getenv('SVN_PASSWORD', '')
    upf_env_json_url = os.getenv('UPF_ENV_JSON_URL', '')
    upf_env_local_path = os.getenv('UPF_ENV_LOCAL_PATH', '')
    # 创建 SVN 工具实例
    svn_client = SvnUtils(username=svn_username,password=svn_password)
    # 导出upfenv.json
    success = svn_client.export(upf_env_json_url, upf_env_local_path)
    if not success:
        logger.error("upfenv.json文件导出失败，无法继续处理")
        return 1

    #读取upfenv.json
    with open(upf_env_local_path, 'r') as f:
        upf_env = json.load(f)
    logger.success("'upfenv.json' 文件读取成功")

    # 获取 list_of_envs 并转换为列表
    module_list = upf_env.get('list_of_envs', '').split()
    if module not in module_list:
        logger.error(f"模块 '{module}' 不在已知模块列表中")
        return 1
    else:
        module_info = upf_env.get('project').get(module)
        logger.info(f"模块 '{module}' 的启动信息：\n{json.dumps(module_info,indent=2)}")
    # 切换icexs版本
    icexs_version = module_info.get('expected_icexs_ver')
    switch_icexs_version(icexs_version)

if __name__ == '__main__':
    #加载环境变量
    load_dotenv('.env.prod')
    main(sys.argv[1])