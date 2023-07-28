package Model;

import java.sql.Date;

public class User {
    private int userId;
    private String userName;
    private String passWord;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;

    public User(int userId, String userName, String passWord, Date createDate, String createdBy, Date lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getUserId() {
        return userId;
    }
}
