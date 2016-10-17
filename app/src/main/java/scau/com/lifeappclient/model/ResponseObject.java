package scau.com.lifeappclient.model;

/**
 * Created by beyondboy on 2016/10/17.
 */
public class ResponseObject<T> {
    private boolean status;
    private String msg;
    private int code;
    private T data;

    public ResponseObject() {
    }

    public ResponseObject(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseObject(boolean status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseObject(boolean status, String msg, int code) {
        this.status = status;
        this.msg = msg;
        this.code = code;
    }

    public ResponseObject(boolean status, String msg, int code, T data) {
        this.status = status;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
