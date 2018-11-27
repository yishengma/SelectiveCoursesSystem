package servlet.manager;

import Utils.JsonUtil;
import constant.Constant;
import db.DataBaseHelper;




import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//@GET //http://localhost:8080/web/manager/delete?type=student&account=4651
@WebServlet(name = "ManagerDeleteServlet", urlPatterns = "/manager/delete")
public class ManagerDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
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
        JsonUtil.response(resp, "删除成功！", "删除成功！", "删除失败！",Constant.MANAGER);

    }
}
