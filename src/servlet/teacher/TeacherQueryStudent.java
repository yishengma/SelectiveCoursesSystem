package servlet.teacher;

import Utils.CloseUtil;
import Utils.JsonUtil;
import bean.Student;
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

//@GET
@WebServlet(name = "TeacherQueryStudent",urlPatterns = "/teacher/query")
public class TeacherQueryStudent extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tno = req.getParameter("tno");
        String cno = req.getParameter("cno");
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
          resultSet =   DataBaseHelper.getInstance().query("SELECT  " +
                    "COUNT(student.Sno) total, "+
                    "student.Sno,  " +
                    "student.Sname,  " +
                    "student.Ssex,  " +
                    "student.Sage,  " +
                    "student.Sschool,  " +
                    "selection.Sno,  " +
                    "selection.Cno,  " +
                    "selection.Sscore,  " +
                    "teach.Tno,  " +
                    "teach.Cno  " +
                    "FROM  " +
                    "student  " +
                    "INNER JOIN selection ON selection.Sno = student.Sno ,  " +
                    "teach  " +
                    "WHERE  " +
                    "selection.Cno = teach.Cno AND  " +
                    "teach.Tno = "+"'"+tno+"'"+" AND  " +
                    "teach.Cno = "+"'"+cno+"'");
            while (resultSet.next()){
                Student student = new Student();
                student.setSex(resultSet.getString("student.Ssex"));
                student.setSchool(resultSet.getString("student.Sschool"));
                student.setName(resultSet.getString("student.Sname"));
                student.setAge(resultSet.getInt("student.Sage"));
                student.setScore(resultSet.getInt("selection.Sscore"));
                student.setNo(resultSet.getInt("student.Sno"));
                list.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseUtil.close(resultSet);
        }

        JsonUtil.response(resp,list,"查询成功!","查询失败！", Constant.TEACHER);
    }
}
