package ArchitectureExplore.aop;

import ArchitectureExplore.aop.annotation.MyAspect;
import ArchitectureExplore.aop.interfaces.MyProxy;
import ArchitectureExplore.ioc.first_version.helper.BeanHelper;
import ArchitectureExplore.ioc.first_version.helper.ClassHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * @Description: myJavaWebFrame
 * @author: lzs
 * @date:: 2019-10-03 16:26
 */
public class AopHelper {

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<MyProxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<MyProxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<MyProxy> myProxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, myProxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            // logger.error()
        }
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassSet) {
            if (proxyClass.isAnnotationPresent(MyAspect.class)) {
                MyAspect myAspect = proxyClass.getAnnotation(MyAspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(myAspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
        return proxyMap;
    }

    private static Set<Class<?>> createTargetClassSet(MyAspect myAspect)throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = myAspect.value();
        if (annotation != null && !annotation.equals(MyAspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>, List<MyProxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception{
        Map<Class<?>, List<MyProxy>> targetMap = new HashMap<Class<?>, List<MyProxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                MyProxy proxy = (MyProxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<MyProxy> myProxyList = new ArrayList<MyProxy>();
                    myProxyList.add(proxy);
                    targetMap.put(targetClass, myProxyList);
                }
            }
        }
        return targetMap;
    }
}
