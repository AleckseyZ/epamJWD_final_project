package by.epamtc.zotov.finalproject.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private int bookId;
    private String author;
    private String blurb;
    private String title;
    private String cover;
    private String tags;
    private int amount;
    
    public Book() {
    }

    public Book(int bookId, String author, String blurb, String title, String cover, String tags, int amount) {
        this.bookId = bookId;
        this.author = author;
        this.blurb = blurb;
        this.title = title;
        this.cover = cover;
        this.tags = tags;
        this.amount = amount;
    }

    public Book(String author, String blurb, String title, String cover, String tags, int amount) {
        this.author = author;
        this.blurb = blurb;
        this.title = title;
        this.cover = cover;
        this.tags = tags;
        this.amount = amount;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((blurb == null) ? 0 : blurb.hashCode());
        result = prime * result + bookId;
        result = prime * result + ((cover == null) ? 0 : cover.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Book other = (Book) obj;
        if (amount != other.amount)
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (blurb == null) {
            if (other.blurb != null)
                return false;
        } else if (!blurb.equals(other.blurb))
            return false;
        if (bookId != other.bookId)
            return false;
        if (cover == null) {
            if (other.cover != null)
                return false;
        } else if (!cover.equals(other.cover))
            return false;
        if (tags == null) {
            if (other.tags != null)
                return false;
        } else if (!tags.equals(other.tags))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book [amount=" + amount + ", author=" + author + ", blurb=" + blurb + ", bookId=" + bookId
                + ", coverPath=" + cover + ", tags=" + tags + ", title=" + title + "]";
    }
}