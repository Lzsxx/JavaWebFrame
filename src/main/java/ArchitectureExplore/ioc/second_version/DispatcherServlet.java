package ArchitectureExplore.ioc.second_version;

import ArchitectureExplore.ioc.first_version.bean.Handler;
import ArchitectureExplore.ioc.first_version.bean.Request;
import ArchitectureExplore.ioc.second_version.helper.CreateInstanceHelper;
import ArchitectureExplore.ioc.second_version.helper.RequestMappingHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(value = "/")
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Init");
        InitLoad.initial();
    }
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 首先，上面的URL会拦截所有的请求，然后在这里要提取出请求的方法和地址，取得对应的Handler，
        // 再通过对应Handler里的cls取得控制器的实例对象，通过实例对象调用对应的方法，执行相应的操作
        // 依赖注入是在控制器调用服务类时默默使用到的功能
        System.out.println("Call a Service");

        String reqMethod = req.getMethod();
        String url = req.getContextPath();
        Request request = new Request(reqMethod, url);
        Map<Request, Handler> map = RequestMappingHelper.getMap();
        Handler handler = map.get(request);
        if (handler != null) {
            // 获取控制器对象和方法
            Class<?> cls = handler.getControllerClass();
            Method method = handler.getActionMethod();
            Object controlelrInstance = CreateInstanceHelper.getMap().get(cls);
            String result = "";
            try {
                method.setAccessible(true);
                result = (String) method.invoke(controlelrInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            // 将返回的字符串写回web前端
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter writer = res.getWriter();
            writer.write(result);
            writer.flush();
            writer.close();
        }

    }


}
