package helper;

import annotation.MyController;
import annotation.MyService;
import util.ClassUtil;

import java.util.HashSet;
import java.util.Set;

public class ClassHelper {
    private static final Set<Class<?>> CLASS_SET = ClassUtil.getClassSet();

    /**
    * @Description: 取得应用包名下的所有类
    */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
    * @Description: 取得应用包名下所有Service类
    */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(MyService.class)) { // 存在MyService注解
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * @Description: 取得应用包名下所有Controller类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(MyController.class)) { // 存在MyService注解
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * @Description: 取得应用包名下所有Bean类，（包括Service、Controller类）
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getServiceClassSet());
        beanClassSet.addAll(getControllerClassSet());
        return beanClassSet;
    }

}
