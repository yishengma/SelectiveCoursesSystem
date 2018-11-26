package servlet.manager;

import Utils.JsonUtil;
import constant.Constant;
import db.DataBaseHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ManagerDeleteServlet", urlPatterns = "/manager/delete")
public class ManagerDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        String account = req.getParameter("account");
        switch (type) {
            case Constant.STUDENT:
                try {
                    DataBaseHelper.getInstance().delete("DELETE  FROM student WHERE sno = '" + account + "'");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case Constant.TEACHER:
                try {
                    DataBaseHelper.getInstance().delete("DELETE  FROM teacher WHERE tno = '" + account + "'");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        JsonUtil.response(resp, "删除成功！", "删除成功！", "删除失败！");

    }
}
