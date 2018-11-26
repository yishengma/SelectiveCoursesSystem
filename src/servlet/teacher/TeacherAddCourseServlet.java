package servlet.teacher;


import Utils.JsonUtil;
import db.DataBaseHelper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "TeacherAddCourseServlet",urlPatterns = "/teacher/course")
public class TeacherAddCourseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String tno = req.getParameter("tno");
        String cno = req.getParameter("cno");
        try {
            DataBaseHelper.getInstance().insert("INSERT INTO teach (Tno,Cno) " +
                    "value (" + tno + "," + cno + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonUtil.response(resp,"添加成功！","添加成功！","添加失败！");
    }
}
