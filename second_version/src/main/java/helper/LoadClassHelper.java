package helper;

import annotation.mController;
import annotation.mService;
import util.LoadClassUtil;

import java.util.HashSet;
import java.util.Set;

public class LoadClassHelper {
    public static Set<Class<?>> getControllerSet(){
        Set<Class<?>> controllerSet = new HashSet<Class<?>>();
        Set<Class<?>> allSet = LoadClassUtil.getClassSet();
        for (Class<?> cls : allSet) {
            if (cls.isAnnotationPresent(mController.class)) {
                controllerSet.add(cls);
            }
        }
        return controllerSet;
    }
    public static Set<Class<?>> getServiceSet(){
        Set<Class<?>> serviceSet = new HashSet<Class<?>>();
        Set<Class<?>> allSet = LoadClassUtil.getClassSet();
        for (Class<?> cls : allSet) {
            if (cls.isAnnotationPresent(mService.class)) {
                serviceSet.add(cls);
            }
        }
        return serviceSet;
    }

    public static Set<Class<?>> getBeanSet(){
        Set<Class<?>> beanSet = new HashSet<Class<?>>();
        beanSet.addAll(getControllerSet());
        beanSet.addAll(getServiceSet());
        return beanSet;
    }

}
