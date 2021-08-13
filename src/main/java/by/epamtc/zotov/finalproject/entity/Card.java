package by.epamtc.zotov.finalproject.entity;

import java.sql.Date;

public class Card {
    int cardId;
    int userId;
    String holderName;
    String holderPhone;
    Date issueDate;
    int statusId;

    public Card() {
    }

    public Card(int cardId, int userId, String holderName, String holderPhone, Date issueDate, int statusId) {
        this.cardId = cardId;
        this.userId = userId;
        this.holderName = holderName;
        this.holderPhone = holderPhone;
        this.issueDate = issueDate;
        this.statusId = statusId;
    }

    public Card(int userId, String holderName, String holderPhone, Date issueDate) {
        this.userId = userId;
        this.holderName = holderName;
        this.holderPhone = holderPhone;
        this.issueDate = issueDate; 
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderPhone() {
        return holderPhone;
    }

    public void setHolderPhone(String holderPhone) {
        this.holderPhone = holderPhone;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cardId;
        result = prime * result + ((holderName == null) ? 0 : holderName.hashCode());
        result = prime * result + ((holderPhone == null) ? 0 : holderPhone.hashCode());
        result = prime * result + ((issueDate == null) ? 0 : issueDate.hashCode());
        result = prime * result + statusId;
        result = prime * result + userId;
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
        Card other = (Card) obj;
        if (cardId != other.cardId)
            return false;
        if (holderName == null) {
            if (other.holderName != null)
                return false;
        } else if (!holderName.equals(other.holderName))
            return false;
        if (holderPhone == null) {
            if (other.holderPhone != null)
                return false;
        } else if (!holderPhone.equals(other.holderPhone))
            return false;
        if (issueDate == null) {
            if (other.issueDate != null)
                return false;
        } else if (!issueDate.equals(other.issueDate))
            return false;
        if (statusId != other.statusId)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [cardId=" + cardId + ", holderName=" + holderName + ", holderPhone=" + holderPhone + ", issueDate="
                + issueDate + ", statusId=" + statusId + ", userId=" + userId + "]";
    }
}