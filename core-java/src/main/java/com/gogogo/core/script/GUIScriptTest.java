package com.gogogo.core.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * TODO
 *
 * @author HUZHAOYANG
 * @version 1.0
 * @date 2019/9/8 18:28
 */
public class GUIScriptTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                //获取引擎管理器
                ScriptEngineManager manager = new ScriptEngineManager();
                String language;
                if (args.length == 0) {
                    System.out.println("Available factores: ");
                    manager.getEngineFactories().forEach(e -> System.out.println(e.getEngineName()));
                    language = "nashorn";
                } else {
                    language = args[1];
                }
                //根据语言获取引擎
                final ScriptEngine engine = manager.getEngineByName(language);
                if (engine == null) {
                    System.out.println("No engine for: " + language);
                    System.exit(1);
                }
                //获取窗体
                final String frameClassName = args.length < 2 ? "com.gogogo.core.script.buttons1.ButtonFrame" : args[1];
                JFrame frame = (JFrame) Class.forName(frameClassName).newInstance();
                //加载脚本初始化
                InputStream in = frame.getClass().getResourceAsStream("init." + language);
                if (in != null) {
                    engine.eval(new InputStreamReader(in));
                }
                Map<String, Component> components = new HashMap<>();
                getComponentBindings(frame, components);
                components.forEach(engine::put);

                //加载脚本文件
                final Properties events = new Properties();
                in = frame.getClass().getResourceAsStream(language + ".properties");
                events.load(in);

                //为所有组件添加事件监听器
                events.keySet().forEach(e -> {
                    String[] s = ((String) e).split("\\.");
                    try {
                        addListener(s[0], s[1], (String) events.get(e), engine, components);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });

                frame.setTitle("ScriptTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void addListener(String beanName, String eventName, final String scriptCode, final ScriptEngine engine, Map<String, Component> components) throws Exception {
        Object bean = components.get(beanName);
        EventSetDescriptor descriptor = getEventSetDescriptor(bean, eventName);
        if (descriptor == null) {
            return;
        }
        descriptor.getAddListenerMethod().invoke(bean, Proxy.newProxyInstance(
                null,
                new Class[]{descriptor.getListenerType()},
                (proxy, method, args) -> {
                    engine.eval(scriptCode);
                    //method.invoke()
                    return null;
                }));

    }

    private static EventSetDescriptor getEventSetDescriptor(Object bean, String eventName) throws IntrospectionException {
        for (EventSetDescriptor eventSetDescriptor : Introspector.getBeanInfo(bean.getClass()).getEventSetDescriptors()) {
            if (eventSetDescriptor.getName().equals(eventName)) {
                return eventSetDescriptor;
            }
        }
        return null;
    }

    private static void getComponentBindings(Component c, Map<String, Component> nameComponentMap) {
        String name = c.getName();
        if (name != null) {
            nameComponentMap.put(name, c);
        }
        if (c instanceof Container) {
            for (Component component : ((Container) c).getComponents()) {
                getComponentBindings(component, nameComponentMap);
            }
        }

    }
}
