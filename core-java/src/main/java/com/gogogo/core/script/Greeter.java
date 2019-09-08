package com.gogogo.core.script;

/**
 * 利用JAVA接口调用脚本函数
 * 需要脚本实现这个接口的所有方法
 *
 * @author HUZHAOYANG
 * @version 1.0
 * @date 2019/9/8 17:38
 */
public interface Greeter {

    String welcome(String whom);
}
