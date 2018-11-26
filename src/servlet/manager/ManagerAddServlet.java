package servlet.manager;


import Utils.JsonUtil;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ManagerAddServlet", urlPatterns = "/manager/add")
public class ManagerAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Student[] list = JSON.toJavaObject(JsonUtil.getParmArray(req),Student[].class);
        System.out.println(req.getParameter("type"));

//        switch (type) {
////            case Constant.STUDENT:
////                for (Object o : list) {
////                    Student student = (Student) o;
////                    try {
////                        DataBaseHelper.getInstance().insert("INSERT INTO student " +
////                                "(Sno,Sname, Ssex, Sage, Sschool)" + " value （" +
////                                student.getNo() + "," +
////                                student.getName() + "," +
////                                student.getSex() + "," +
////                                student.getAge() + "," +
////                                student.getSchool() + "," +
////                                ")");
////                    } catch (SQLException e) {
////                        e.printStackTrace();
////                    }
////                }
////                break;
////            case Constant.TEACHER:
////                for (Object o : list) {
////                    Teacher teacher = (Teacher) o;
////                    try {
////                        DataBaseHelper.getInstance().insert("INSERT INTO teacher " +
////                                "(Tno,Tname, Tsex, Ttel, Tschool)" + " value （" +
////                                teacher.getNo() + "," +
////                                teacher.getName() + "," +
////                                teacher.getSex() + "," +
////                                teacher.getTel() + "," +
////                                teacher.getSchool() + "," +
////                                ")");
////                    } catch (SQLException e) {
////                        e.printStackTrace();
////                    }
////                }
////                break;
////            default:
////                break;
////        }
////
////        JsonUtil.response(resp,"添加成功！","添加成功！","添加失败！");
//        System.out.println(type+ Arrays.toString(list));
    }

}
