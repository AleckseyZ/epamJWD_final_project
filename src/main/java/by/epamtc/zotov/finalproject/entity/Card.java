package by.epamtc.zotov.finalproject.entity;

public class Card {
    int cardId;
    int userId;
    String holderName;
    int statusId;

    public Card() {
    }

    public Card(int cardId, int userId, String holderName, int statusId) {
        this.cardId = cardId;
        this.userId = userId;
        this.holderName = holderName;
        this.statusId = statusId;
    }

    public Card(int userId, String holderName, int statusId) {
        this.userId = userId;
        this.holderName = holderName;
        this.statusId = statusId;
    }

    public Card(int userId, String holderName) {
        this.userId = userId;
        this.holderName = holderName;
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
        if (statusId != other.statusId)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [cardId=" + cardId + ", holderName=" + holderName + ", statusId=" + statusId + ", userId=" + userId
                + "]";
    }

}