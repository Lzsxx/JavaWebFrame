package ArchitectureExplore.aop;

import ArchitectureExplore.aop.interfaces.MyProxy;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: myJavaWebFrame
 * @author: lzs
 * @date:: 2019-10-02 17:34
 */
public class ProxyChain {

    private final Class<?> targetClass;     /* 目标类 */
    private final Object targetObject;      /* 目标对象 */
    private final Method targetMethod;      /* 目标方法 */
    private final MethodProxy methodProxy;  /* 方法代理 */
    private final Object[] methodParams;    /* 方法参数 */

    private List<MyProxy> proxyList = new ArrayList<MyProxy>();     /* 代理列表 */
    private int proxyIndex = 0;             /* 代理索引 */

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<MyProxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()) {
            methodResult = proxyList.get(proxyIndex).doProxy(this);
            proxyIndex++;
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
