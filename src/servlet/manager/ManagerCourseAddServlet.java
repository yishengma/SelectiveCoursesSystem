package servlet.manager;

import Utils.JsonUtil;
import bean.Course;
import com.alibaba.fastjson.JSON;
import constant.Constant;
import db.DataBaseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ManagerCourseAddServlet", urlPatterns = "/manager/course/add")
public class ManagerCourseAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = JSON.toJavaObject(JsonUtil.getParm(req), Course.class);
        try {
            DataBaseHelper.getInstance().insert("INSERT INTO Course " +
                    "(Cno, Cname, Cschool)" + " value (" +
                    course.getNo() + "," +
                    "'" + course.getName() + "'" + "," +
                    "'" + course.getSchool() + "'" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonUtil.response(resp, "添加成功！", "添加成功！", "添加失败！", Constant.MANAGER);

    }

}
