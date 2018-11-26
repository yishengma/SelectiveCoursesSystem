package servlet.student;

import Utils.JsonUtil;
import db.DataBaseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "StudentSelectServlet",urlPatterns = "/student/select")
public class StudentSelectServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String sno = req.getParameter("sno");
         String cno = req.getParameter("cno");

        try {
            DataBaseHelper.getInstance().insert("INSERT INTO selection (Sno,Cno) "+
            "value (" +sno + ","+cno +")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonUtil.response(resp,"添加成功！","添加成功！","添加失败！");
    }
}
