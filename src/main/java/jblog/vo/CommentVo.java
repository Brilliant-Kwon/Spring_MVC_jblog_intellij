package jblog.vo;

import java.sql.Date;

public class CommentVo {
    private Long cmtNo; // pk
    private Long postNo;
    private String cmtContent; // not null
    private Date regDate; // not null



    public CommentVo() {
    }

    public CommentVo(Long cmtNo, Long postNo, String cmtContent, Date regDate) {
        this.cmtNo = cmtNo;
        this.postNo = postNo;
        this.cmtContent = cmtContent;
        this.regDate = regDate;
    }

    public Long getCmtNo() {
        return cmtNo;
    }

    public void setCmtNo(Long cmtNo) {
        this.cmtNo = cmtNo;
    }

    public Long getPostNo() {
        return postNo;
    }

    public void setPostNo(Long postNo) {
        this.postNo = postNo;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
