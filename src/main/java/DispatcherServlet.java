
import bean.Handler;
import helper.BeanHelper;
import helper.ControllerHelper;
import util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 请求转发器
 *
 * @author huangyong
 * @since 1.0.0
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 统一初始化相关类
        HelperLoader.init();
    }
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("In Service: receive a call");
        try {
            // 从原始HttpServletRequest对象中获取请求方法与请求路径
            String requestMethod = request.getMethod().toUpperCase();
            String requestPath = request.getRequestURI();
            // 根据路径和方法，从request与Handler的map中获取handler
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
                // 获取Controller的Class对象，再根据这个对象，获取真正的对象实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);

                // 调用URL对应的处理方法
                Method actionMethod = handler.getActionMethod();
                Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);

                // 将返回的字符串写入到HttpServletResponse对象中
                writeResponse(response, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeResponse(HttpServletResponse response, Object result) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write((String) result);
        writer.flush();
        writer.close();
    }
}
