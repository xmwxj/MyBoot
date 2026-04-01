#!/bin/bash
# deploy.sh - Linux 生产环境自动部署脚本

set -e

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

echo -e "${CYAN}========================================${NC}"
echo -e "${CYAN}  MyBoot 自动部署脚本${NC}"
echo -e "${CYAN}========================================${NC}"

# 检测 Python
if command -v python3 &> /dev/null; then
    PYTHON_VERSION=$(python3 --version)
    echo -e "${GREEN}✓ Python 已安装：$PYTHON_VERSION${NC}"
else
    echo -e "${RED}✗ 错误：未找到 Python3，请先安装${NC}"
    exit 1
fi

# 确定环境
ENVIRONMENT="${1:-production}"
if [ "$ENVIRONMENT" == "production" ]; then
    ENV_FILE=".env.prod"
    echo -e "${YELLOW}→ 部署到【生产环境】${NC}"
elif [ "$ENVIRONMENT" == "development" ]; then
    ENV_FILE=".env.dev"
    echo -e "${CYAN}→ 部署到【开发环境】${NC}"
else
    ENV_FILE=".env.$ENVIRONMENT"
    echo -e "${CYAN}→ 部署到【$ENVIRONMENT 环境】${NC}"
fi

# 检查虚拟环境
if [ ! -d ".venv" ]; then
    echo -e "${CYAN}→ 创建虚拟环境...${NC}"
    python3 -m venv .venv
    echo -e "${GREEN}✓ 虚拟环境创建成功${NC}"
else
    echo -e "${GREEN}✓ 虚拟环境已存在${NC}"
fi

# 激活虚拟环境
echo -e "${CYAN}→ 激活虚拟环境...${NC}"
source .venv/bin/activate

# 升级 pip
# echo -e "${CYAN}→ 升级 pip...${NC}"
# pip install --upgrade pip --quiet

# 安装依赖
echo -e "${CYAN}→ 安装项目依赖...${NC}"
pip install -r requirements.txt --quiet
echo -e "${GREEN}✓ 依赖安装完成${NC}"

# 检查环境配置文件
if [ ! -f "$ENV_FILE" ]; then
    echo -e "${YELLOW}⚠ 警告：$ENV_FILE 不存在${NC}"

    if [ "$ENVIRONMENT" == "production" ] && [ ! -f ".env.prod" ]; then
        echo -e "${CYAN}→ 从模板创建 .env.prod...${NC}"
        if [ -f ".env.example" ]; then
            cp .env.example .env.prod
            echo -e "${GREEN}✓ 已创建 .env.prod，请编辑配置后重新运行${NC}"
            echo -e "${YELLOW}重要：请编辑 .env.prod 文件，配置 SVN 账号、密码等信息${NC}"
            exit 0
        else
            echo -e "${RED}✗ 错误：找不到 .env.example 模板文件${NC}"
            exit 1
        fi
    fi
else
    echo -e "${GREEN}✓ 环境配置文件存在：$ENV_FILE${NC}"
fi

# 创建必要的目录
for dir in logs svn backup; do
    if [ ! -d "$dir" ]; then
        mkdir -p "$dir"
        echo -e "${GREEN}✓ 创建目录：$dir${NC}"
    fi
done

# 验证安装
echo -e "${CYAN}→ 验证安装...${NC}"
if python -c "from loguru import logger; from dotenv import load_dotenv; print('✓ 依赖验证通过')" 2>/dev/null; then
    :
else
    echo -e "${RED}✗ 依赖验证失败${NC}"
    exit 1
fi

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}  部署完成！${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo -e "${CYAN}运行命令：${NC}"
echo -e "  ${GRAY}# 生产环境${NC}"
echo -e "  source venv/bin/activate"
echo -e "  ENVIRONMENT=production python main.py <module_name>"
echo ""
echo -e "  ${GRAY}# 或一键运行：${NC}"
echo -e "  ./run.sh production <module_name>"
echo ""
echo -e "${CYAN}示例：${NC}"
echo -e "  ./run.sh production paynet_pcs"
