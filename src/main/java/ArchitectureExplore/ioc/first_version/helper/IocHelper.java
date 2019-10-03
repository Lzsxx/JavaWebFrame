package ArchitectureExplore.ioc.first_version.helper;

import ArchitectureExplore.ioc.first_version.annotation.MyAutowired;
import ArchitectureExplore.ioc.first_version.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public class IocHelper {
    // 这是一个静态块，当IocHelper被加载时，下面的内容就会被执行
    static {
        // 获取所有的Bean类与Bean实例之间的映射关系
        try {
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            if ( beanMap != null && !beanMap.isEmpty()){
                // 遍历整个Map
                for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                    // 取出每一个Class类与相应的对象实例
                    Class<?> beanClass = beanEntry.getKey();
                    Object beanInstance = beanEntry.getValue();

                    // 获取Class类所定义的所有成员变量，查看是否带有Autowired注解
                    Field[] beanFields = beanClass.getDeclaredFields();
                    if (beanFields != null && beanFields.length > 0){
                        // 如果成员变量数组非空，就遍历
                        for (Field beanField : beanFields) {
                            // 如果当前变量上有Autowired注解
                            if (beanField.isAnnotationPresent(MyAutowired.class)) {
                                Class<?> beanFieldClass = beanField.getType();  // 获取这个变量的数据类型，按理说数据类型可以是8种基本类型，
                                // 或者是自己定义的类，在这里，拥有autowire注解的一般都是自己定义的类，
                                // 所以紧接着需要取得这个类的实例，进行注入
                                Object beanFieldInstance = beanMap.get(beanFieldClass);
                                if (beanFieldInstance != null) {
                                    // 通过反射初始化BeanField的值
                                    // beanInstance是Controller的实例，beanField是声明的变量helloService，beanFieldInstance是通过容器取得的helloService类型的实例
                                    // 通过setField进行依赖注入
                                    ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
