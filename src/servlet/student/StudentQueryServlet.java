package servlet.student;

import Utils.CloseUtil;

import bean.Course;
import bean.SelectCourse;
import bean.Teacher;
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

import static Utils.JsonUtil.response;


@WebServlet(name = "StudentQueryServlet", urlPatterns = "/student/course")
public class StudentQueryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sno = req.getParameter("sno");
        List<SelectCourse> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet  = DataBaseHelper.getInstance().query("SELECT " +
                    "selection.Sno, " +
                    "selection.Cno, " +
                    "selection.Sscore, " +
                    "course.Cno, " +
                    "course.Cname, " +
                    "course.Cschool, " +
                    "teach.Tno, " +
                    "teach.Cno, " +
                    "teacher.Tname, " +
                    "teacher.Tno, " +
                    "teacher.Tsex, " +
                    "teacher.Tschool, " +
                    "teacher.Ttel " +
                    "FROM " +
                    "selection " +
                    "INNER JOIN course ON selection.Cno = course.Cno " +
                    "INNER JOIN teach ON teach.Cno = course.Cno " +
                    "INNER JOIN teacher ON teach.Tno = teacher.Tno " +
                    "WHERE " +
                    "selection.Sno = " + sno);
            while (resultSet.next()) {
                SelectCourse selectCourse = new SelectCourse();
                Course course = new Course();
                course.setName(resultSet.getString("course.Cname"));
                course.setNo(resultSet.getInt("course.Cno"));
                course.setSchool(resultSet.getString("course.Cschool"));

                Teacher teacher = new Teacher();

                teacher.setSex(resultSet.getString("teacher.Tsex"));
                teacher.setSchool(resultSet.getString("teacher.Tschool"));
                teacher.setName(resultSet.getString("teacher.Tname"));
                teacher.setTel(resultSet.getString("teacher.Ttel"));

                Integer score = resultSet.getInt("selection.Sscore");

                selectCourse.setCourse(course);
                selectCourse.setTeacher(teacher);
                selectCourse.setScore(score);
                list.add(selectCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.close(resultSet);
        }
        response(resp, list, "成功！", "暂无选课！");

    }
}
