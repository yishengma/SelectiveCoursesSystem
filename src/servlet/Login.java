package servlet;

import Utils.JsonUtil;
import bean.Result;
import bean.Student;
import bean.Teacher;
import com.alibaba.fastjson.JSON;
import constant.Constant;
import db.DataBaseHelper;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Utils.JsonUtil.response;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        switch (req.getParameter("type")) {
            case Constant.MANAGER:

                try {
                    managerLogin(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                break;
            case Constant.STUDENT:
                try {
                    studentLogin(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case Constant.TEACHER:
                try {
                    teacherLogin(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;

        }


    }


    private void managerLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        List<String> list = new ArrayList<>();
        ResultSet resultSet = DataBaseHelper.getInstance().query("SELECT password FROM manager WHERE account = " + account);
        while (resultSet.next()) {
            list.add(resultSet.getString("password"));
        }
        Result result = new Result();
        if (list.contains(password)) {
            result.setMsg("登录成功！");
            result.setResult(true);
            result.setT(null);
        } else {
            result.setMsg("密码错误！");
            result.setResult(false);
            result.setT(null);
        }
        resultSet.close();
        response(resp, result);

    }

    private void studentLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");

        String url = "SELECT * FROM student WHERE Sno = " + "'"+account +"'"
                + " AND Sname = " + "'"+password +"'";
        System.out.println(url);
        ResultSet resultSet = DataBaseHelper.getInstance().query(url);
        Student student = null;
        while (resultSet.next()) {
            student = new Student();
            student.setNo(resultSet.getInt("Sno"));
            student.setAge(resultSet.getInt("Sage"));
            student.setName(resultSet.getString("Sname"));
            student.setSchool(resultSet.getString("Sschool"));
            student.setSex(resultSet.getString("Ssex"));
        }

        resultSet.close();
        response(resp, student,"登录成功！","登录失败！");


    }

   // http://localhost:8080/web/login?type=teacher&account=1234&password=%E5%BC%A0%E9%93%AD
    private void teacherLogin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        ResultSet resultSet = DataBaseHelper.getInstance().query("SELECT * FROM servlet.teacher WHERE Tno = " + "'"+account +"'"
                + " AND Tname = " + "'"+password +"'");
        Teacher teacher = null;
        while (resultSet.next()) {
            teacher = new Teacher();
            teacher.setName(resultSet.getString("Tname"));
            teacher.setNo(resultSet.getInt("Tno"));
            teacher.setSchool(resultSet.getString("Tschool"));
            teacher.setSex(resultSet.getString("Tsex"));
        }

        resultSet.close();
        response(resp, teacher,"登录成功！","登录失败！");

    }



}
