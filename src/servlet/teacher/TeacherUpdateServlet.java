package servlet.teacher;

import Utils.JsonUtil;

import bean.Teacher;
import com.alibaba.fastjson.JSON;
import constant.Constant;
import db.DataBaseHelper;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

//http://localhost:8080/web/teacher/update
//POST
@WebServlet(name = "TeacherUpdateServlet",urlPatterns = "/teacher/update")
public class TeacherUpdateServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("UTF-8");
        Teacher teacher = JSON.toJavaObject(JsonUtil.getParm(req), Teacher.class);
        try {
            DataBaseHelper.getInstance().update("UPDATE teacher SET "
                    + "Tname = " + "'" + teacher.getName() + "' ,"
                    + "Ttel = " + "'" + teacher.getTel() + "' ,"
                    + "Tsex = " + "'" + teacher.getSex() + "' ,"
                    + "Tschool = " + "'" + teacher.getSchool() + "' "
                    + " WHERE Tno = " + "'" + teacher.getNo() + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonUtil.response(resp,"更新成功！","更新成功！","更新失败！", Constant.TEACHER);
    }

}
