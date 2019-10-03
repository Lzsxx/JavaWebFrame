package ArchitectureExplore.ioc.second_version;

import ArchitectureExplore.ioc.second_version.helper.AutowiredHelper;
import ArchitectureExplore.ioc.second_version.helper.RequestMappingHelper;

public class InitLoad {
    public static void initial(){
        Class<?>[] classes = {AutowiredHelper.class,
                RequestMappingHelper.class};

        for (Class<?> cls : classes) {
            try {
                Class.forName(cls.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
