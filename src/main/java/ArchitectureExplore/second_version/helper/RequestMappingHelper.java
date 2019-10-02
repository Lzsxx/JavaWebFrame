package ArchitectureExplore.second_version.helper;

import ArchitectureExplore.first_version.annotation.mRequestMapping;
import ArchitectureExplore.first_version.bean.Handler;
import ArchitectureExplore.first_version.bean.Request;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 遍历每一个Controller上的方法，检查是否有RequestMapping注解，如果有，就要提取出上面的注解路径值，创建Request对象
// 再创建Handler对象，两者之间建立映射关系
public class RequestMappingHelper {
    private static Map<Request, Handler> map = new HashMap<Request, Handler>();
    private static Set<Class<?>> controllerSet = LoadClassHelper.getControllerSet();
    static {
        for (Class<?> cls : controllerSet) {
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                // 如果有那个注解了，就开始构造相应的对象，添加进map
                if (method.isAnnotationPresent(mRequestMapping.class)) {
                    mRequestMapping mRequestMapping = method.getAnnotation(mRequestMapping.class);
                    String url = mRequestMapping.value();
                    String action = "GET";
                    Request request = new Request(action, url);
                    Handler handler = new Handler(cls, method);
                    map.put(request, handler);
                }
            }
        }
    }

    public static Map<Request, Handler> getMap() {
        return map;
    }

    public static Set<Class<?>> getControllerSet() {
        return controllerSet;
    }
}
