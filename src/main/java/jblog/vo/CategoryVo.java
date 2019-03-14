package jblog.vo;

import java.sql.Date;

public class CategoryVo {
    private Long cateNo;//pk
    private Long userNo;
    private String cateName;//not null
    private String description;
    private Date regDate; //not null

    @Override
    public String toString() {
        return "CategoryVo{" +
                "cateNo=" + cateNo +
                ", userNo=" + userNo +
                ", cateName='" + cateName + '\'' +
                ", description='" + description + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public CategoryVo() {
    }

    public CategoryVo(Long cateNo, Long userNo, String cateName, String description, Date regDate) {
        this.cateNo = cateNo;
        this.userNo = userNo;
        this.cateName = cateName;
        this.description = description;
        this.regDate = regDate;
    }

    public Long getCateNo() {
        return cateNo;
    }

    public void setCateNo(Long cateNo) {
        this.cateNo = cateNo;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
