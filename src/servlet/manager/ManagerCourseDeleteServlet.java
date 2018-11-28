package servlet.manager;

import Utils.JsonUtil;
import bean.Course;
import bean.PostList;
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


@WebServlet(name = "ManagerCourseDeleteServlet", urlPatterns = "/manager/course/delete")
public class ManagerCourseDeleteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Course course = JSON.toJavaObject(JsonUtil.getParm(req), Course.class);
        try {
            DataBaseHelper.getInstance().delete("DELETE  FROM Course WHERE Cno = '" + course.getNo() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JsonUtil.response(resp, "删除成功！", "删除成功！", "删除失败！", Constant.MANAGER);





    }
}
