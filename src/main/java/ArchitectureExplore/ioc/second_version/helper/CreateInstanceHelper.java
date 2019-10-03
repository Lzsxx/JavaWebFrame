package ArchitectureExplore.ioc.second_version.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CreateInstanceHelper {
    private static Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
    private static Set<Class<?>> beanSet = LoadClassHelper.getBeanSet();

    static {
        for (Class<?> cls : beanSet) {
            try {
                Object obj = cls.newInstance();
                map.put(cls, obj);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<Class<?>, Object> getMap() {
        return map;
    }

    public static Set<Class<?>> getBeanSet() {
        return beanSet;
    }
}
