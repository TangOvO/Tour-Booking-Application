package AiLvYou.util;

public class RequestResult {
    //错误代码。100为正确
    private int errorCode;
    //返回结果
    private Object result;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
