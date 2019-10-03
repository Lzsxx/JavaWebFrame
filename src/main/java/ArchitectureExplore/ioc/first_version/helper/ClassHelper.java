package ArchitectureExplore.ioc.first_version.helper;

import ArchitectureExplore.ioc.first_version.annotation.MyController;
import ArchitectureExplore.ioc.first_version.annotation.MyService;
import ArchitectureExplore.ioc.first_version.util.ClassUtil;

import java.lang.annotation.Annotation;
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

    /**
     * 获取应用包名下某个父类或接口的所有子类或实现类
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for (Class<?> cls : CLASS_SET){
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
