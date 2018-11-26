package bean;

public class Result<T> {

    private boolean mResult;
    private String mMsg;
    private T mT;

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

    public T getT() {
        return mT;
    }

    public void setT(T t) {
        mT = t;
    }
}
