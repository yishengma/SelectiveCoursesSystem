package servlet.student;

import Utils.JsonUtil;
import bean.Student;
import com.alibaba.fastjson.JSON;

import constant.Constant;
import db.DataBaseHelper;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
//@POST
/*
{
        "age": 20,
        "name": "马大生",
        "no": 4650,
        "school": "广东工业大学",
        "sex": "男"
    }
 */
//http://localhost:8080/web/student/update
@WebServlet(name = "StudentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Student student = JSON.toJavaObject(JsonUtil.getParm(req), Student.class);
        try {
            DataBaseHelper.getInstance().update("UPDATE student SET "
                    + "Sname = " + "'" + student.getName() + "' ,"
                    + "Sage = " + "'" + student.getAge() + "' ,"
                    + "Ssex = " + "'" + student.getSex() + "' ,"
                    + "Sschool = " + "'" + student.getSchool() + "' "
                    + " WHERE Sno = " + "'" + student.getNo() + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonUtil.response(resp,"更新成功！","更新成功！","更新失败！", Constant.STUDENT);
    }



}
