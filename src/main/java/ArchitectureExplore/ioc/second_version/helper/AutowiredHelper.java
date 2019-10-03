package ArchitectureExplore.ioc.second_version.helper;


import ArchitectureExplore.ioc.second_version.annotation.mAutowired;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class AutowiredHelper {
    private static Set<Class<?>> beanSet = LoadClassHelper.getBeanSet();
    private static Map<Class<?>, Object> map = CreateInstanceHelper.getMap();

    static {
        for (Class<?> cls : beanSet) {
            // 遍历类上的方法，检查有没有Autowired注解
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(mAutowired.class)) {
                    // 如果有，就取得这个注解的类型，判断是否有被容器管理的实例
                    Class<?> annoType = field.getType();
                    Object objInstance = map.get(annoType);
                    // 如果有被管理的实例，在这里完成依赖注入
                    if (objInstance != null) {
                        // 要搞定清楚，是这个从map中取出的实例，被注入到被mAutowired注解的变量里去,而想要确定这个变量，还需要定位到这个变量的实例对象
                        try {
                            field.setAccessible(true);
                            field.set( map.get(cls) , objInstance);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }
}
