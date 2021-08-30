package by.epamtc.zotov.finalproject.entity;

import java.sql.Date;

public class Loan {
    private int loanId;
    private int bookId;
    private int cardId;
    private int statusId;
    private Date startDate;
    private Date endDate;

    public Loan() {
    }

    public Loan(int loanId, int bookId, int cardId, int statusId, Date startDate, Date endDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.cardId = cardId;
        this.statusId = statusId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Loan(int bookId, int cardId, int statusId, Date startDate, Date endDate) {
        this.bookId = bookId;
        this.cardId = cardId;
        this.statusId = statusId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Loan(int bookId, int cardId, int statusId, Date startDate) {
        this.bookId = bookId;
        this.cardId = cardId;
        this.statusId = statusId;
        this.startDate = startDate;
    }

    public int getLoanId() {
        return loanId;
    }
    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getCardId() {
        return cardId;
    }
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bookId;
        result = prime * result + cardId;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + loanId;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + statusId;
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
        Loan other = (Loan) obj;
        if (bookId != other.bookId)
            return false;
        if (cardId != other.cardId)
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (loanId != other.loanId)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (statusId != other.statusId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Loan [bookId=" + bookId + ", cardId=" + cardId + ", endDate=" + endDate + ", loanId=" + loanId
                + ", startDate=" + startDate + ", statusId=" + statusId + "]";
    }  
}