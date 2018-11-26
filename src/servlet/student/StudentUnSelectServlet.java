package servlet.student;

import Utils.JsonUtil;
import db.DataBaseHelper;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StudentUnSelectServlet",urlPatterns = "/student/unselect")
public class StudentUnSelectServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sno = req.getParameter("sno");
        String cno = req.getParameter("cno");

        try {
            DataBaseHelper.getInstance().delete("DELETE FROM selection WHERE sno = " +"'"+sno+"'" +" AND " + "cno = " +"'"+cno+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonUtil.response(resp,"更新成功！","更新成功！","更新失败！");
    }
}
