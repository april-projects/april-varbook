# 使用 mobaijun 提供的最新版本 JRE 镜像
FROM mobaijun/jre21:latest

# 设置维护者标签（替代已弃用的 MAINTAINER 指令）
LABEL maintainer="mobaijun8@163.com"

# 设置时区和语言环境变量
# TZ：设置为亚洲/上海
# LANG、LANGUAGE 和 LC_ALL：设置为 en_US.UTF-8 以确保一致的字符编码
ENV TZ 'Asia/Shanghai'
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

# 设置时区并更新时区配置
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建应用程序目录
WORKDIR /app

# 定义 JAR 文件和依赖库目录参数
ARG JAR_FILE=april-varbook-backend/target/april-varbook.jar
ARG LIB_DIR=april-varbook-backend/target/lib

# 将 JAR 文件和依赖库目录添加到 /app 目录
ADD ${JAR_FILE} /app/app.jar
ADD ${LIB_DIR} /app/lib/

# 暴露 9090 端口供应用程序使用
EXPOSE 9090

# 设置启动命令和 JVM 参数
# JVM 参数包括：
# -Xmx1024m：最大堆内存
# -Xms512m：初始堆内存
# -Dfile.encoding：设置文件编码
# -Dsun.stdout.encoding：设置标准输出编码
# -Dsun.stderr.encoding：设置标准错误输出编码
# -Dloader.path：指定外部依赖库路径
# -jar：指定运行的 JAR 文件
ENTRYPOINT ["java", "-Xmx1024m", "-Xms512m", \
           "-Dfile.encoding=UTF-8", \
           "-Dsun.stdout.encoding=UTF-8", \
           "-Dsun.stderr.encoding=UTF-8", \
           "-Dloader.path=/app/lib", \
           "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", \
           "-jar", "/app/app.jar"]
