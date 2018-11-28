package servlet.manager;


import Utils.JsonUtil;
import bean.PostList;
import bean.Student;
import bean.Teacher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constant.Constant;
import db.DataBaseHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
{

    "type": "student",
    "list": [
        {
            "age": 22,
            "name": "马生",
            "no": 4650,
            "school": "广东工业大学",
            "sex": "男"
        },
{
            "age": 22,
            "name": "马生",
            "no": 4651,
            "school": "广东工业大学",
            "sex": "男"
        }
    ]
}
 */
// Post//http://localhost:8080/web/manager/add
@WebServlet(name = "ManagerAddServlet", urlPatterns = "/manager/add")
public class ManagerAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PostList list = JSON.toJavaObject(JsonUtil.getParm(req), PostList.class);
        switch (list.getType()) {
            case Constant.STUDENT:
                for (Object o : list.getList()) {
                    String s = o.toString();
                    Student student = JSON.parseObject(s, Student.class);
                    try {

                        DataBaseHelper.getInstance().insert("INSERT INTO student " +
                                "(Sno, Sname, Ssex, Sage, Sschool)" + " value (" +
                                student.getNo() + "," +
                                "'" + student.getName() + "'" + "," +
                                "'" + student.getSex() + "'" + "," +
                                student.getAge() + "," +
                                "'" + student.getSchool() + "'" +
                                ")");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case Constant.TEACHER:
                for (Object o : list.getList()) {
                    String s = o.toString();
                    Teacher teacher = JSON.parseObject(s, Teacher.class);
                    try {
                        DataBaseHelper.getInstance().insert("INSERT INTO teacher " +
                                "(Tno,Tname, Tsex, Ttel, Tschool)" + " value (" +
                                teacher.getNo() + "," +
                                "'" + teacher.getName() + "'" + "," +
                                "'" + teacher.getSex() + "'" + "," +
                                "'" + teacher.getTel() + "'" + "," +
                                "'" + teacher.getSchool() + "'" +
                                ")");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }

        JsonUtil.response(resp, "添加成功！", "添加成功！", "添加失败！", Constant.MANAGER);
    }

}
