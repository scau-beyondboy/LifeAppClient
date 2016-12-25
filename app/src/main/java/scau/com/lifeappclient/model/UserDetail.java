package scau.com.lifeappclient.model;

import java.io.Serializable;

public class UserDetail implements Serializable {
    private Long userId;

    private Long userNum;

    private Integer userIden;

    private String userMajor;

    private String userGradle;

    private String userClass;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Integer getUserIden() {
        return userIden;
    }

    public void setUserIden(Integer userIden) {
        this.userIden = userIden;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor == null ? null : userMajor.trim();
    }

    public String getUserGradle() {
        return userGradle;
    }

    public void setUserGradle(String userGradle) {
        this.userGradle = userGradle == null ? null : userGradle.trim();
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass == null ? null : userClass.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userNum=").append(userNum);
        sb.append(", userIden=").append(userIden);
        sb.append(", userMajor=").append(userMajor);
        sb.append(", userGradle=").append(userGradle);
        sb.append(", userClass=").append(userClass);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}