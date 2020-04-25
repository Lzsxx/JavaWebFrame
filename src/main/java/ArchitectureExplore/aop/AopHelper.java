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
            // proxyMap中，key为切面，value为被这个切面选中的类
            Map<Class<?>, Set<Class<?>>> aspectMap = createProxyMap();
            // targetMap中,key为每一个被切面选中的类，value为选中这个类的所有切面类的实例的List
            Map<Class<?>, List<MyProxy>> targetMap = createTargetMap(aspectMap);

            // 为每一个被切面选中的类创建代理对象，代理对象的callBack里设置好了调用链，一旦触发，就会先走完调用链再执行真正的方法
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
            // proxyClass为切面，targetClassSet为被这个切面选中的类
            Class<?> proxyClass = proxyEntry.getKey();
            Set<Class<?>> targetClassSet = proxyEntry.getValue();
            // 遍历每一个被切面选中的类，将切面类的实例加入到以proxyClass为key的Map中，
            // 每个类可能会被多个切面选中，而每个切面方法都要执行，所以讲切面类的实例都放在一个List中，等待后续执行
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
