#!/bin/bash
# run.sh - MyBoot 一键运行脚本
# 用法：./run.sh <module_name>
# 示例：./run.sh paynet_pcs

set -e

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
GRAY='\033[90m'
NC='\033[0m' # No Color

# 检查参数数量
if [ $# -lt 1 ]; then
    echo -e "${RED}========================================${NC}"
    echo -e "${RED}  MyBoot 运行脚本${NC}"
    echo -e "${RED}========================================${NC}"
    echo ""
    echo -e "${CYAN}用法:${NC}"
    echo -e "  $0 <module_name>"
    echo ""
    echo -e "${CYAN}示例:${NC}"
    echo -e "  $0 paynet_pcs"
    echo -e "  $0 hsbc"
    echo -e "  $0 baml"
    echo ""
    exit 1
fi

MODULE=$1

# 检查虚拟环境
if [ ! -d ".venv" ]; then
    echo -e "${RED}✗ 虚拟环境不存在${NC}"
    echo -e "${YELLOW}提示：请先运行部署脚本：./deploy.sh${NC}"
    exit 1
fi

# 激活虚拟环境
echo -e "${CYAN}→ 激活虚拟环境...${NC}"
source .venv/bin/activate

# 设置默认环境为生产环境
export ENVIRONMENT=production
ENV_FILE=".env.prod"

# 检查配置文件
if [ ! -f "$ENV_FILE" ]; then
    echo -e "${RED}✗ 错误：环境配置文件不存在：$ENV_FILE${NC}"
    echo -e "${YELLOW}提示：${NC}"
    echo -e "  1. 复制模板：cp .env.example .env.prod"
    echo -e "  2. 编辑配置：vi .env.prod"
    echo -e "  3. 重新运行：$0 $MODULE"
    deactivate
    exit 1
fi

echo -e "${GREEN}✓ 使用配置文件：$ENV_FILE${NC}"

# 显示启动信息
echo -e "${CYAN}========================================${NC}"
echo -e "${CYAN}  启动 UPF${NC}"
echo -e "${CYAN}========================================${NC}"
echo -e "${GREEN}  模块：${YELLOW}$MODULE${NC}"
echo -e "${CYAN}========================================${NC}"
echo ""

# 运行程序
python main.py $MODULE
EXIT_CODE=$?

# 退出虚拟环境
deactivate

# 检查结果
echo ""
if [ $EXIT_CODE -eq 0 ]; then
    echo -e "${GREEN}========================================${NC}"
    echo -e "${GREEN}  ✓ 程序执行成功${NC}"
    echo -e "${GREEN}========================================${NC}"
else
    echo -e "${RED}========================================${NC}"
    echo -e "${RED}  ✗ 程序执行失败 (退出码：$EXIT_CODE)${NC}"
    echo -e "${RED}========================================${NC}"
    echo ""
    echo -e "${YELLOW}提示：查看日志获取详细信息${NC}"
    if [ -d "logs" ]; then
        LATEST_LOG=$(ls -t logs/*.log 2>/dev/null | head -n1)
        if [ -n "$LATEST_LOG" ]; then
            echo -e "${CYAN}最新日志：$LATEST_LOG${NC}"
            echo -e "${GRAY}查看最后 50 行：tail -n 50 $LATEST_LOG${NC}"
        fi
    fi
fi

exit $EXIT_CODE
