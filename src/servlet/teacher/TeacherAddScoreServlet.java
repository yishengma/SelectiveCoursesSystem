package servlet.teacher;

import Utils.JsonUtil;
import bean.PostList;
import bean.Score;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import db.DataBaseHelper;
import javafx.geometry.Pos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
{
	"type": 1,
	"list": [{
			"sno": 4650,
			"score": 99
		},
		{
			"sno": 4651,
			"score": 99

		}

	]
}
 */
//@POSt  http://localhost:8080/web/teacher/score
@WebServlet(name = "TeacherAddScoreServlet",urlPatterns = "/teacher/score")
public class TeacherAddScoreServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostList list =  JSON.toJavaObject(JsonUtil.getParm(req),PostList.class);
        for (Object aList : list.getList()) {
            Score score = JSON.parseObject(aList.toString(),Score.class);
            if (score.getScore()==null){
                continue;
            }
            try {
                DataBaseHelper.getInstance().update("UPDATE selection SET Sscore =" +
                        score.getScore() + " WHERE selection.sno = " +
                        "" + score.getSno() + " AND selection.cno = "+
                        list.getType());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JsonUtil.response(resp,"添加成功！","添加成功！","添加失败！", Constant.TEACHER);
    }
}
