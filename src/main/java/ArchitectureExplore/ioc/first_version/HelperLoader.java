package ArchitectureExplore.ioc.first_version;

import ArchitectureExplore.aop.AopHelper;
import ArchitectureExplore.ioc.first_version.helper.BeanHelper;
import ArchitectureExplore.ioc.first_version.helper.ClassHelper;
import ArchitectureExplore.ioc.first_version.helper.ControllerHelper;
import ArchitectureExplore.ioc.first_version.helper.IocHelper;
import ArchitectureExplore.ioc.first_version.util.ClassUtil;


// 为了让加载更集中，可以调用这个类来进行统一加载
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                AopHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
            System.out.println(cls.getName());
        }
//        String[] clsName = {"helper.ClassHelper",
//                "helper.BeanHelper",
//                "helper.IocHelper",
//                "helper.ControllerHelper"
//        };
//        for (String cls : clsName) {
//            ClassUtil.loadClass(cls);
//            System.out.println(cls);
//        }

    }

    public static void main(String[] args) {
        HelperLoader h = new HelperLoader();
    }
}
