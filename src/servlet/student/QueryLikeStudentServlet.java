package servlet.student;

import Utils.CloseUtil;
import Utils.JsonUtil;
import bean.Student;
import constant.Constant;
import db.DataBaseHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "QueryLikeStudent" ,urlPatterns = "/student/like")
public class QueryLikeStudentServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String str = req.getParameter("str");

        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            resultSet = DataBaseHelper.getInstance().query("SELECT  * FROM student WHERE Sname Like \'%" +str + "%\'");
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
    }
}
