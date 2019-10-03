package ArchitectureExplore.ioc.first_version.helper;

import ArchitectureExplore.ioc.first_version.annotation.MyRequestMapping;
import ArchitectureExplore.ioc.first_version.bean.Handler;
import ArchitectureExplore.ioc.first_version.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 用于处理带有RequestMapping注解的方法
// 首先，通过ClassHelper可以获得所有定义了Controller注解的类，通过反射取得所有带有RequestMapping注解的方法，
// 获取注解里的请求表达式，进而获取请求方法与请求路径，封装一个请求对象和处理对象，最后将Request与Handler建立一个映射关系
// 放入一个Request Map 中，并提供一个可根据请求方法与请求路径获取处理对象的方法
public class ControllerHelper {
    /**
     * @Description: Map用于存放请求与处理器的映射关系
     */
    private static final Map<Request, Handler> REQUEST_HANDLER_MAP = new HashMap<Request, Handler>();
    static {
        // 获取所有的Controller类。
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (!controllerClassSet.isEmpty()) {
            // 遍历这些Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取Controller中定义的方法，判断是否带有@RequestMapping注解
                Method[] methods = controllerClass.getDeclaredMethods();
                if (methods.length > 0) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(MyRequestMapping.class)) {
                            // 从注解中获取URL映射规则
                            MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                            String requestPath = requestMapping.value();
                            String requestMethod = "GET";
                            Request request = new Request(requestMethod, requestPath);
                            Handler handler = new Handler(controllerClass, method);
                            REQUEST_HANDLER_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    /**
    * @Description: 获取Handler
    */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return REQUEST_HANDLER_MAP.get(request);
    }
}
