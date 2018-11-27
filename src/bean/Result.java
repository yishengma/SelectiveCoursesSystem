package bean;

import java.util.List;

public class Result {

    private boolean mResult;
    private String mMsg;
    private Student mStudent;
    private Teacher mTeacher;
    private Manager mManager;
    private List mList;
    private String mType;


    public List getList() {
        return mList;
    }

    public void setList(List list) {
        mList = list;
    }

    public Manager getManager() {
        return mManager;
    }

    public void setManager(Manager manager) {
        mManager = manager;
    }

    public Student getStudent() {
        return mStudent;
    }

    public void setStudent(Student student) {
        mStudent = student;
    }

    public Teacher getTeacher() {
        return mTeacher;
    }

    public void setTeacher(Teacher teacher) {
        mTeacher = teacher;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public boolean isResult() {
        return mResult;
    }

    public void setResult(boolean result) {
        mResult = result;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }


}
