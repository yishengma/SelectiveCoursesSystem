package servlet.student;

import Utils.CloseUtil;

import bean.Course;
import bean.SelectCourse;
import bean.Teacher;
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

import static Utils.JsonUtil.response;


//GET //http://localhost:8080/web/student/course?type=select&sno=4650
@WebServlet(name = "StudentQueryServlet", urlPatterns = "/student/course")
public class StudentQueryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");

        if (type.equals("select")) {
            select(req, resp);
        }
        if (type.equals("unselected")) {
            unseleted(req, resp);
        }


    }

    private void unseleted(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sno = req.getParameter("sno");
        List<SelectCourse> list = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            resultSet = DataBaseHelper.getInstance().query("SELECT " +
                    "course.Cschool, " +
                    "course.Cname, " +
                    "course.Cno, " +
                    "teacher.Tno, " +
                    "teacher.Tname, " +
                    "teacher.Tsex, " +
                    "teacher.Ttel, " +
                    "teacher.Tschool, " +
                    "teach.Tno, " +
                    "teach.Cno " +
                    "FROM " +
                    "course , " +
                    "teacher " +
                    "RIGHT JOIN teach ON teach.Tno = teacher.Tno " +
                    "WHERE " +
                    "course.Cno = teach.Cno  AND course.Cno  " +
                    "IN " +
                    "        (SELECT cno " +
                    "        FROM course " +
                    "        WHERE Cno " +
                    "        NOT IN " +
                    "        ( " +
                    "        SELECT " +
                    "        selection.Cno " +
                    "        FROM " +
                    "        selection " +
                    "        WHERE " +
                    "        selection.Sno = " + sno + ")) ");


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

                selectCourse.setCourse(course);
                selectCourse.setTeacher(teacher);
                list.add(selectCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(resultSet);
        }
        response(resp, list, "成功！", "暂无选课!", Constant.STUDENT);

    }


    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sno = req.getParameter("sno");
        List<SelectCourse> list = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = DataBaseHelper.getInstance().query("SELECT " +
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
        } finally {
            CloseUtil.close(resultSet);
        }
        response(resp, list, "成功！", "暂无选课!", Constant.STUDENT);
    }


}
