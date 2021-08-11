package by.epamtc.zotov.finalproject.entity;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable {
    private int noticeId;
    private String titleText;
    private String bodyText;
    private Date postDate;

    public Notice() {
    }

    public Notice(int noticeId, String titleText, String bodyText, Date postDate) {
        this.noticeId = noticeId;
        this.titleText = titleText;
        this.bodyText = bodyText;
        this.postDate = postDate;
    }

    public Notice(String titleText, String bodyText, Date postDate) {
        this.titleText = titleText;
        this.bodyText = bodyText;
        this.postDate = postDate;
    }

    public int getnoticeId() {
        return noticeId;
    }

    public void setnoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + noticeId;
        result = prime * result + ((bodyText == null) ? 0 : bodyText.hashCode());
        result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
        result = prime * result + ((titleText == null) ? 0 : titleText.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Notice other = (Notice) obj;
        if (noticeId != other.noticeId)
            return false;
        if (bodyText == null) {
            if (other.bodyText != null)
                return false;
        } else if (!bodyText.equals(other.bodyText))
            return false;
        if (postDate == null) {
            if (other.postDate != null)
                return false;
        } else if (!postDate.equals(other.postDate))
            return false;
        if (titleText == null) {
            if (other.titleText != null)
                return false;
        } else if (!titleText.equals(other.titleText))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Notice [noticeId=" + noticeId
 + ", bodyText=" + bodyText + ", postDate=" + postDate
                + ", titleText=" + titleText + "]";
    }
}