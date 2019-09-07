package com.gogogo.spirnginaction.config;

import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContainerInitializer;

/**
 * DispatcherServlet配置类
 * 1.Servlet容器启动后，会在类路径中查找实现 {@link ServletContainerInitializer}接口的类
 * 2.Spring 提供了{@link SpringServletContainerInitializer}实现。该实现类会查找{@link WebApplicationInitializer}
 * 接口的实现类，也就是{@link AbstractAnnotationConfigDispatcherServletInitializer}
 *
 * @author HUZHAOYANG
 * @date 2019/8/31 18:58
 * @return
 **/
public class SpitterWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
