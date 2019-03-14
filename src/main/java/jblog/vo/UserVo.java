package jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Date;

public class UserVo {
    private Long userNo; //pk
    @NotEmpty
    private String id; //unique
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    private Date joinDate;

    @Override
    public String toString() {
        return "UserVo{" +
                "userNo=" + userNo +
                ", id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }

    public UserVo() {
    }

    public UserVo(Long userNo, String id, String userName, String password, Date joinDate) {
        this.userNo = userNo;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.joinDate = joinDate;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
