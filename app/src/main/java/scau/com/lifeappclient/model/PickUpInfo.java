package scau.com.lifeappclient.model;

import java.io.Serializable;

public class PickUpInfo implements Serializable {
    private Long pickupId;

    private String pickupPhone;

    private Long pickupCardNum;

    private String pickupDate;

    private static final long serialVersionUID = 1L;

    public Long getPickupId() {
        return pickupId;
    }

    public void setPickupId(Long pickupId) {
        this.pickupId = pickupId;
    }

    public String getPickupPhone() {
        return pickupPhone;
    }

    public void setPickupPhone(String pickupPhone) {
        this.pickupPhone = pickupPhone == null ? null : pickupPhone.trim();
    }

    public Long getPickupCardNum() {
        return pickupCardNum;
    }

    public void setPickupCardNum(Long pickupCardNum) {
        this.pickupCardNum = pickupCardNum;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pickupId=").append(pickupId);
        sb.append(", pickupPhone=").append(pickupPhone);
        sb.append(", pickupCardNum=").append(pickupCardNum);
        sb.append(", pickupDate=").append(pickupDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}