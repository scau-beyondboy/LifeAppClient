package scau.com.lifeappclient.model;

import java.io.Serializable;

import scau.com.lifeappclient.adapter.NoticeListItemAdapter;

public class NoticeInfo implements Serializable {
    private Long noticeId;
    private int type= NoticeListItemAdapter.VIEW_TYPE_ITEM;

    private String noticeTitle;

    private String noticeDate;

    private String noticeEditor;

    private String noticeIden;

    private String noticeDesc;

    private static final long serialVersionUID = 1L;

    public NoticeInfo(int type) {
        this.type = type;
    }

    public Long getNoticeId() {
        return noticeId;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? null : noticeTitle.trim();
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeEditor() {
        return noticeEditor;
    }

    public void setNoticeEditor(String noticeEditor) {
        this.noticeEditor = noticeEditor == null ? null : noticeEditor.trim();
    }

    public String getNoticeIden() {
        return noticeIden;
    }

    public void setNoticeIden(String noticeIden) {
        this.noticeIden = noticeIden == null ? null : noticeIden.trim();
    }

    public String getNoticeDesc() {
        return noticeDesc;
    }

    public void setNoticeDesc(String noticeDesc) {
        this.noticeDesc = noticeDesc == null ? null : noticeDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", noticeId=").append(noticeId);
        sb.append(", noticeTitle=").append(noticeTitle);
        sb.append(", noticeDate=").append(noticeDate);
        sb.append(", noticeEditor=").append(noticeEditor);
        sb.append(", noticeIden=").append(noticeIden);
        sb.append(", noticeDesc=").append(noticeDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}