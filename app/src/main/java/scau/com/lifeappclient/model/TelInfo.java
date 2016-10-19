package scau.com.lifeappclient.model;

import java.io.Serializable;

/**
 * Created by beyondboy on 2016/10/19.
 */
public class TelInfo implements Serializable {
    private int mTelLogo;
    private long mTelNum;
    private String mTelDesc;
    private static final long serialVersionUID = 1L;

    public int getmTelLogo() {
        return mTelLogo;
    }

    public TelInfo(int mTelLogo,String mTelDesc,long mTelNum) {
        this.mTelLogo = mTelLogo;
        this.mTelNum = mTelNum;
        this.mTelDesc = mTelDesc;
    }

    public void setmTelLogo(int mTelLogo) {
        this.mTelLogo = mTelLogo;
    }

    @Override
    public String toString() {
        return "TelInfo{" +
                "mTelLogo=" + mTelLogo +
                ", mTelNum=" + mTelNum +
                ", mTelDesc='" + mTelDesc + '\'' +
                '}';
    }

    public long getmTelNum() {
        return mTelNum;
    }

    public void setmTelNum(long mTelNum) {
        this.mTelNum = mTelNum;
    }

    public String getmTelDesc() {
        return mTelDesc;
    }

    public void setmTelDesc(String mTelDesc) {
        this.mTelDesc = mTelDesc;
    }
}
