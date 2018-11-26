package bean;

public class Student {
    private int mNo;
    private String mName;
    private String mSex;
    private int mAge;
    private String mSchool;
    private Integer mScore;

    public Integer getScore() {
        return mScore;
    }

    public void setScore(Integer score) {
        mScore = score;
    }

    public int getNo() {
        return mNo;
    }

    public void setNo(int no) {
        mNo = no;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getSchool() {
        return mSchool;
    }

    public void setSchool(String school) {
        mSchool = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mNo=" + mNo +
                ", mName='" + mName + '\'' +
                ", mSex='" + mSex + '\'' +
                ", mAge=" + mAge +
                ", mSchool='" + mSchool + '\'' +
                '}';
    }
}
