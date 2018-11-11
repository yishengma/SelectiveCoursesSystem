package jvademo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AServlet", urlPatterns = "/login")
public class AServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码
        req.setCharacterEncoding("utf8");
        //从request对象中获取username,password
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //判断是否为管理员
        HttpSession session = req.getSession(true);
        if ("administrator".equals(username) && "123456".equals(password)) {
            //登录成功，设置session

            session.setAttribute("user", "管理员，欢迎你！");
        } else {
            session.setAttribute("user", "登录信息错误，请检查用户名或密码");
        }
        //将页面转发到欢迎页面
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/welcome.jsp");
        requestDispatcher.forward(req, resp);

    }
}
