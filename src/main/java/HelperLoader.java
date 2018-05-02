import helper.BeanHelper;
import helper.ClassHelper;
import helper.ControllerHelper;
import helper.IocHelper;
import util.ClassUtil;


// 为了让加载更集中，可以调用这个类来进行统一加载
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
//                AopHelper.class,
                IocHelper.class,
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
}
