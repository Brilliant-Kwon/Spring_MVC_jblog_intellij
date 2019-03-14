package jblog.vo;

import java.sql.Date;

public class PostVo {
    private Long postNo;//pk
    private Long cateNo;
    private String postTitle; // not null
    private String postContent;
    private Date regDate; //not null

    @Override
    public String toString() {
        return "PostVo{" +
                "postNo=" + postNo +
                ", cateNo=" + cateNo +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", regDate=" + regDate +
                '}';
    }

    public PostVo() {
    }

    public PostVo(Long postNo, Long cateNo, String postTitle, String postContent, Date regDate) {
        this.postNo = postNo;
        this.cateNo = cateNo;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.regDate = regDate;
    }

    public Long getPostNo() {
        return postNo;
    }

    public void setPostNo(Long postNo) {
        this.postNo = postNo;
    }

    public Long getCateNo() {
        return cateNo;
    }

    public void setCateNo(Long cateNo) {
        this.cateNo = cateNo;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}

