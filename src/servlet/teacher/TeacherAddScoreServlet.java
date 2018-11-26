package servlet.teacher;

import Utils.JsonUtil;
import bean.Score;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import db.DataBaseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "TeacherAddScoreServlet",urlPatterns = "/teacher/score")
public class TeacherAddScoreServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cno = req.getParameter("cno");
        ArrayList list =  JSON.toJavaObject(JsonUtil.getParmArray(req),ArrayList.class);
        // Student student = JSON.toJavaObject(JsonUtil.getParm(req), Student.class);
        for (Object aList : list) {
            Score score = (Score) aList;
            try {
                DataBaseHelper.getInstance().update("UPDATE selection SET score " +
                        score.getScore() + " WHERE selection.sno = " +
                        "'" + score.getSno() + "' AND selection.cno = "+
                        "'"+cno+"'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JsonUtil.response(resp,"添加成功！","添加成功！","添加失败！");
    }
}
