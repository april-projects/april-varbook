# April VarBook

> 致敬原作者 [@uiuing/varbook](https://github.com/uiuing/varbook) ，本项目由 [mobaijun](https://github.com/mobaijun)
> 基于其优秀开源项目二次开发，功能与体验全面升级。

---

## 项目简介

April VarBook 是一个现代化的变量命名风格转换与常用命名生成工具，支持中英文智能翻译，适用于后端、前端等多语言场景。前后端分离，界面美观，交互流畅，支持 Docker 一键部署。

- **前端**：Vue3 + Element Plus + Vite
- **后端**：Spring Boot
- **特色**：变量命名风格一键转换、常用命名标签自动生成、可复制、响应式美观UI

![](./docs/Snipaste_2025-07-16_15-34-30.png)

![](./docs/Snipaste_2025-07-16_15-35-16.png)

![](./docs/Snipaste_2025-07-16_15-35-25.png)

---

## 主要功能

- 支持中英文互译，自动生成多种变量命名风格（小驼峰、大驼峰、小蛇形、大蛇形、脊柱、注释等）
- 自动生成常用命名标签（如 Controller、Service、DTO、VO、BO、PO、Util、Config、Exception 等）
- 标签可一键复制，复制后高亮提示
- 响应式布局，适配多端
- 支持 Docker 一键部署

---

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/april-projects/april-varbook.git
cd april-varbook
```

### 2. 本地开发启动

#### 后端（Spring Boot）

```bash
cd april-varbook-backend
# 配置 application.yml 数据库等信息
mvn spring-boot:run
```

#### 前端（Vue3）

```bash
cd april-varbook-frontend
npm install
npm run dev
```

访问：http://localhost:8888

---

## Docker 一键部署

### 1. 构建镜像

```bash
docker build -t april-varbook .
```

### 2. 运行容器

```bash
docker run -d -p 8888:9090 -e NIUTRANS_API_KEY=0b2c5c4d450229eea1405edb2f37b3 -e SPRING_PROFILES_ACTIVE=docker --name april-varbook april-varbook
```

### 3. docker-compose

~~~yaml
services:
  varbook:
    # 代码生成器镜像
    image: mobaijun/varbook:latest
    # 容器名称
    container_name: varbook
    # 重启策略
    restart: always
    ports:
      - "8890:9090"
    environment:
      # 环境变量配置
      - NIUTRANS_API_KEY=09b2c54d45022da9eea14025eb2f37b3
      - SPRING_PROFILES_ACTIVE=docker
    volumes:
      # 时间同步挂载，保持和宿主机一致
      - /etc/localtime:/etc/localtime:ro
    deploy:
      resources:
        limits:
          # 代码生成器 CPU 限制
          cpus: '1.0'
          # 代码生成器最大内存限制
          memory: 512M
        reservations:
          # 代码生成器 CPU 保留
          cpus: '0.5'
          # 代码生成器内存保留
          memory: 256M
~~~

---

## 获取小牛翻译 API-KEY

April VarBook 使用小牛翻译作为英文变量生成的底层服务。首次部署前请先申请小牛翻译 API-KEY。

### 申请流程

1. 访问 [小牛翻译官网](https://niutrans.com/) 并注册账号。
2. 登录后，进入左侧菜单【API应用】。
3. 在【文本API】卡片下，点击“开通服务”按钮。
4. 开通后即可在页面看到 API-KEY 和 APPID（如下图红色箭头所示）。
5. 点击复制按钮，获取你的 API-KEY。

![小牛翻译API申请示意图](./docs/Snipaste_2025-07-16_15-32-55.png)

6. 将 API-KEY 配置到环境变量 `NIUTRANS_API_KEY`，如 Docker 启动命令或 compose 文件所示。

---

## 目录结构

```
april-varbook/
├── april-varbook-backend/   # Spring Boot 后端
├── april-varbook-frontend/  # Vue3 前端
├── docker/                  # Dockerfile
├── README.md
```

---

## 致敬原作者

本项目基于 [@uiuing/varbook](https://github.com/uiuing/varbook) 二次开发，感谢原作者的开源贡献与灵感支持！

---

## 联系方式

- 作者：mobaijun
- GitHub: [https://github.com/mobaijun](https://github.com/mobaijun)
- Issues/PR 欢迎交流与反馈

---

## License

MIT
