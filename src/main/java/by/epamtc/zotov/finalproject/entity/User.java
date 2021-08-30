package by.epamtc.zotov.finalproject.entity;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {
    private int userId;
    private int userTypeId;
    private String username;
    private byte[] password;
    private byte[] salt;
    private String email;

    public User() {
    }

    public User(int userId, int userTypeId, String username, byte[] password, byte[] salt, String email) {
        this.userId = userId;
        this.userTypeId = userTypeId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
    }

    public User(String username, byte[] password, byte[] salt, String email) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + Arrays.hashCode(password);
        result = prime * result + Arrays.hashCode(salt);
        result = prime * result + userId;
        result = prime * result + userTypeId;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (!Arrays.equals(password, other.password))
            return false;
        if (!Arrays.equals(salt, other.salt))
            return false;
        if (userId != other.userId)
            return false;
        if (userTypeId != other.userTypeId)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", password=" + Arrays.toString(password) + ", salt=" + Arrays.toString(salt)
                + ", userId=" + userId + ", userTypeId=" + userTypeId + ", username=" + username + "]";
    }
}