package ArchitectureExplore.ioc.first_version.bean;

public class Request {
    /**
     * @Description: 请求方法
     */
    private String requestMethod;

    /**
    * @Description: 请求路径
    */
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public boolean equals(Object o) {
        return true;
//        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return 33;
//        return HashCodeBuilder.reflectionHashCode(this);
    }
}
