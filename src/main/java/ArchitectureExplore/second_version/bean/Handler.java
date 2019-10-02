package ArchitectureExplore.second_version.bean;

import java.lang.reflect.Method;

// Handler是方法
public class Handler {
    private Class<?> cls;
    private Method method;

    public Handler() {
    }

    public Handler(Class<?> cls, Method method) {
        this.cls = cls;
        this.method = method;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
