package jblog.vo;

public class BlogVo {
    private Long userNo;//pk
    private String blogTitle;//not null
    private String logoFile;

    @Override
    public String toString() {
        return "BlogVo{" +
                "userNo=" + userNo +
                ", blogTitle='" + blogTitle + '\'' +
                ", logoFile='" + logoFile + '\'' +
                '}';
    }

    public BlogVo() {
    }

    public BlogVo(Long userNo, String blogTitle, String logoFile) {
        this.userNo = userNo;
        this.blogTitle = blogTitle;
        this.logoFile = logoFile;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }
}
