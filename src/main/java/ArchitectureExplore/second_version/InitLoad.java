import ArchitectureExplore.first_version.helper.AutowiredHelper;
import ArchitectureExplore.first_version.helper.CreateInstanceHelper;
import ArchitectureExplore.first_version.helper.RequestMappingHelper;
import netscape.security.ForbiddenTargetException;

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
