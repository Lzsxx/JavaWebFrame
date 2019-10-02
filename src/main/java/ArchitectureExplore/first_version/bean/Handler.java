package ArchitectureExplore.first_version.bean;

import java.lang.reflect.Method;

public class Handler {
    /**
    * @Description: Controller类
    */
    private Class<?> controllerClass;
    /**
    * @Description: 注解的requestMapping的方法
    */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
