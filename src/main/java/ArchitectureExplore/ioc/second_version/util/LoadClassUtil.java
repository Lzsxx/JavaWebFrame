package ArchitectureExplore.ioc.second_version.util;

import java.util.HashSet;
import java.util.Set;

// 加载类
public class LoadClassUtil {
    private static String[] classNames = {"controller.HelloController",
            "service.HelloService"};

    public static Set<Class<?>> getClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            for (String name : classNames) {
                Class<?> cls = Class.forName(name);
                classSet.add(cls);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classSet;
    }

}
