package com.gogogo.core.script;

import javax.script.*;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * java脚本引擎
 *
 * @author HUZHAOYANG
 * @version 1.0
 * @date 2019/9/8 16:50
 */
public class ScriptEngineExample {
    private ScriptEngine engine;

    public static void main(String[] args) throws Exception {
        ScriptEngineExample example = new ScriptEngineExample();
        example.initScriptEngine();
        //获取引擎信息
        example.getInfoFromFactory();

        //执行脚本
        example.evalByScriptString();
        example.bindingVal();
        example.invokeFunction();

    }

    private void initScriptEngine() {
        //初始化一个脚本引擎工厂
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取脚本引擎
        engine = manager.getEngineByName("nashorn");
    }

    private void evalByScriptString() throws ScriptException {
        engine.eval("n=1728");
        Object result = engine.eval("n+1");
        System.out.println(result);
    }

    private void getInfoFromFactory() {
        ScriptEngineFactory factory = engine.getFactory();
        factory.getNames().forEach(System.out::println);
    }

    private void bindingVal() throws ScriptException {
        engine.put("a", 1);
        System.out.println(engine.eval("a+1"));
    }

    /**
     * 调用脚本的函数
     *
     * @return void
     * @author HUZHAOYANG
     * @date 2019/9/8 17:10
     **/
    private void invokeFunction() throws Exception {
        Reader reader = new InputStreamReader(ScriptEngineExample.class.getResourceAsStream("ScriptExample.js"));

        engine.eval(reader);
        //调用函数
        Object result = ((Invocable) engine).invokeFunction("greet", "hello", "world");
        System.out.println(result);

        //调用对象的函数
        Object yo = engine.eval("new Greeter('Yo')");
        result = ((Invocable) engine).invokeMethod(yo, "welcome", "world");
        System.out.println(result);

        //用接口调用脚本方法
        Greeter greeter = ((Invocable) engine).getInterface(Greeter.class);
        System.out.println(greeter.welcome("Interface. "));
        //将脚本的对象匹配到JAVA接口
        Greeter greeter2 = ((Invocable) engine).getInterface(yo, Greeter.class);
        System.out.println(greeter2.welcome("Interface2."));

    }

}
