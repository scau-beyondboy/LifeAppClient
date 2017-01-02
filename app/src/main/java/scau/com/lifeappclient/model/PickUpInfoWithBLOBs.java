package scau.com.lifeappclient.model;

import java.io.Serializable;

public class PickUpInfoWithBLOBs extends PickUpInfo implements Serializable {
    private String pickupImage;

    private String pickupDesc;

    private static final long serialVersionUID = 1L;

    public String getPickupImage() {
        return pickupImage;
    }

    public void setPickupImage(String pickupImage) {
        this.pickupImage = pickupImage == null ? null : pickupImage.trim();
    }

    public String getPickupDesc() {
        return pickupDesc;
    }

    public void setPickupDesc(String pickupDesc) {
        this.pickupDesc = pickupDesc == null ? null : pickupDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pickupImage=").append(pickupImage);
        sb.append(", pickupDesc=").append(pickupDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}