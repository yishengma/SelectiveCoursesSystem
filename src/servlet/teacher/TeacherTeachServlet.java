package servlet.teacher;

import Utils.CloseUtil;
import Utils.JsonUtil;
import bean.Course;
import constant.Constant;
import db.DataBaseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "TeacherTeachServlet", urlPatterns = "/teacher/teach")
public class TeacherTeachServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tno = req.getParameter("tno");
        ResultSet resultSet = null;
        List<Course> courses = new ArrayList<>();
        try {
            resultSet = DataBaseHelper.getInstance().query("SELECT " +
                    "home.course.Cname, " +
                    "home.course.Cno, " +
                    "home.course.Cschool, " +
                    "home.teach.Cno, " +
                    "home.teach.Tno " +
                    "FROM " +
                    "home.course , " +
                    "home.teach " +
                    "WHERE " +
                    "home.teach.Cno = home.course.Cno AND " +
                    "home.teach.Tno = " + tno);

            while (resultSet.next()) {
                Course course = new Course();
                course.setNo(resultSet.getInt("Cno"));
                course.setName(resultSet.getString("Cname"));
                course.setSchool(resultSet.getString("Cschool"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(resultSet);
        }
        JsonUtil.response(resp, courses, "查询成功！", "查询失败！", Constant.TEACHER);
    }
}
