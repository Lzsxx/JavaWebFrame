package ArchitectureExplore.first_version.util;

import java.util.HashSet;
import java.util.Set;

public class ClassUtil {
//    public static void main(String[] args) {
//        Set<Class<?>> result = getClassSet();
//        System.out.println(result.size());
//    }
    /**
    * @Description: 获取类加载器
    */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();  // 取得当前线程运行时的加载器
    }

    /**
    * @Description: 加载类
    */
    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());    // 进行类的加载，也就是在JVM里生成Class对象，但还没有真正的实例对象
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return cls;
    }
    /**
     * 加载类（默认将初始化类）
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    /**
    * @Description: 取得所有动态加载的类的集合
    */
    public static Set<Class<?>> getClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        Class<?> cls = loadClass("controller.HelloController");
        classSet.add(cls);

        cls = loadClass("service.HelloService");
        classSet.add(cls);

        return classSet;
    }
}
