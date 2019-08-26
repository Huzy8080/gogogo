# Eureka 注册中心
## 实现一个注册中心
pom.xml添加以下依赖

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
        

## 启动类添加注解@EnableEurekaServer

## 配置文件

    spring.application.name=eureka-server
    server.port=8900
    eureka.instance.hostname=localhost
    eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false
