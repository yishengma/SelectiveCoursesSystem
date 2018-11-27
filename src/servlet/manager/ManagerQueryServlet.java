package servlet.manager;

import Utils.CloseUtil;
import Utils.JsonUtil;
import bean.Student;
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
//@GET http://localhost:8080/web/manager/query?type=teacher
@WebServlet(name = "ManagerQueryServlet", urlPatterns = "/manager/query")
public class ManagerQueryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        switch (type) {
            case Constant.STUDENT:
                ResultSet resultSet = null;
                List<Student> list = new ArrayList<>();
                try {

                    resultSet = DataBaseHelper.getInstance().query("SELECT  * FROM student");
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setNo(resultSet.getInt("Sno"));
                        student.setScore(null);
                        student.setAge(resultSet.getInt("Sage"));
                        student.setName(resultSet.getString("Sname"));
                        student.setSchool(resultSet.getString("Sschool"));
                        student.setSex(resultSet.getString("Ssex"));
                        list.add(student);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    CloseUtil.close(resultSet);
                }
                JsonUtil.response(resp, list,"查询成功！","查询失败！",Constant.MANAGER);
                break;
            case Constant.TEACHER:

                ResultSet resultSet1 = null;
                List<Teacher> list1 = new ArrayList<>();

                try {
                    resultSet1 = DataBaseHelper.getInstance().query("SELECT * FROM teacher");
                    while (resultSet1.next()) {
                        Teacher teacher = new Teacher();
                        teacher.setTel(resultSet1.getString("Ttel"));
                        teacher.setName(resultSet1.getString("Tname"));
                        teacher.setSchool(resultSet1.getString("Tschool"));
                        teacher.setSex(resultSet1.getString("Tsex"));
                        teacher.setNo(resultSet1.getInt("Tno"));
                        list1.add(teacher);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    CloseUtil.close(resultSet1);
                }
                JsonUtil.response(resp, list1,"查询成功！","查询失败！",Constant.MANAGER);
                break;
            default:
                break;
        }
    }
}
