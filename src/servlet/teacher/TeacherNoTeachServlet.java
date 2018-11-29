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

@WebServlet(name = "TeacherNoTeachServlet", urlPatterns = "/teacher/no_teach")
public class TeacherNoTeachServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String tno = req.getParameter("tno");
        List<Course> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = DataBaseHelper.getInstance().query("SELECT  " +
                    "course.Cno,  " +
                    "course.Cname,  " +
                    "course.Cschool  " +
                    "FROM  " +
                    "course  " +
                    "WHERE Course.Cno NOT  " +
                    "IN (SELECT Cno  " +
                    "FROM teach   " +
                    "WHERE teach.Tno =" + tno +
                    ")");

            while (resultSet.next()) {
                Course course = new Course();
                course.setNo(resultSet.getInt("Cno"));
                course.setName(resultSet.getString("Cname"));
                course.setSchool(resultSet.getString("Cschool"));
                list.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(resultSet);
        }

        JsonUtil.response(resp, list, "查询成功！", "查询失败！", Constant.TEACHER);
    }
}
