package Utils;

import bean.Result;
import bean.SelectCourse;
import bean.Student;
import bean.Teacher;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class JsonUtil {

    public static JSONObject getParm(HttpServletRequest request) throws UnsupportedEncodingException {
        int len = request.getContentLength();
        byte[] callbackBody = new byte[len];
        try {
            ServletInputStream sis = request.getInputStream();
            sis.read(callbackBody, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //接收json数据
        String s = new String(callbackBody, "UTF-8");
        return JSONObject.parseObject(s.substring(s.indexOf("{"), s.lastIndexOf("}") + 1));
    }
    public static JSONArray getParmArray(HttpServletRequest request) throws UnsupportedEncodingException {
        int len = request.getContentLength();
        byte[] callbackBody = new byte[len];
        try {
            ServletInputStream sis = request.getInputStream();
            sis.read(callbackBody, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //接收json数据
        String s = new String(callbackBody, "UTF-8");
        return JSONObject.parseArray(s.substring(s.indexOf("["), s.lastIndexOf("]") + 1));
    }
    private static <T> Result createResult(T t, String success, String fail,String type) {
        Result result = new Result();
        if (t != null) {
            result.setMsg(success);
            result.setResult(true);
            if (t instanceof Student){
                result.setStudent((Student) t);
            }
            if (t instanceof Teacher ){
                result.setTeacher((Teacher) t);
            }
            if (t instanceof List){
                result.setList((List) t);
            }
            if (t instanceof String){
                result.setMsg((String) t);
            }
            result.setType(type);
        } else {
            result.setMsg(fail);
            result.setResult(false);
            result.setType(type);
        }
        return result;
    }

    public static <T> void response(HttpServletResponse response,T t, String success, String fail,String type) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String userJson = JSON.toJSONString(createResult(t,success,fail,type));
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }

    public static <T> void response(HttpServletResponse response,T t) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String userJson = JSON.toJSONString(t);
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }

}
