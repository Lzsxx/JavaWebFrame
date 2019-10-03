package ArchitectureExplore.ioc.first_version.helper;


import ArchitectureExplore.ioc.first_version.util.ReflectionUtil;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 我们需要获取所有被框架管理的Bean类，此时需要调用ClassHelper类的getBeanClassSet方法，
 * 随后需要循环调用ReflcetionUtil的newInstance方法，根据类来实例化对象，最后将每次创建的对象放在一个静态的Map中
 */
public class BeanHelper {
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);     //创建该类真正的实例
            BEAN_MAP.put(beanClass, obj);   // 将实例对象放在map中，通过class对象来再次获取
        }
    }

    /**
     * @Description: 获取Bean的Map
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * @Description: 获取Bean实例
     */
    @SuppressWarnings("uncheck")
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class:" + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /**
     * 设置Bean实例
     */
    public static void setBean(Class<?> cls, Object object) {
        BEAN_MAP.put(cls, object);
    }
}
