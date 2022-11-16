package com.sc.crawler.util;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author lihao
 * @create 2022-08-30 9:47
 * @desc
 **/
public class JavaScriptProvider<T> {

    public T loadJs(String jsName, Class<T> clazz) throws FileNotFoundException, ScriptException {
        //创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本管理器
        ScriptEngine engine = manager.getEngineByName("ecmascript");
        //获取js文件所在的目录的路径
        String path = "E:\\Code\\JavaProject\\air_crawler\\src\\main\\resources\\static\\";
        //engine.eval(new FileReader(path+jsName+".js"));
        String s = path + jsName;
        engine.eval(new FileReader(s));
        //从脚本引擎中返回一个给定接口的实现
        Invocable invocable = (Invocable) engine;
        return invocable.getInterface(clazz);
    }

    public static void main(String[] args) {


        ScriptEngineManager factory = new ScriptEngineManager();
        for (ScriptEngineFactory available : factory.getEngineFactories()) {
            System.out.println(available.getEngineName());
            // 打印脚本具体名称信息
            System.out.println(available.getNames());
        }

        try {
            JavaScriptProvider<JSMethods> jsProvider = new JavaScriptProvider<>();
            JSMethods jsMethods = jsProvider.loadJs("n.js", JSMethods.class);
            System.out.println(jsMethods.getParam("n.js", "getParam"));
        } catch (FileNotFoundException | ScriptException e) {
            e.printStackTrace();
        }
    }
}
