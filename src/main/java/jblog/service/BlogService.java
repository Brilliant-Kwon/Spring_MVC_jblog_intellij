package jblog.service;

import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogService {

    public List<BlogVo> getBlogList();

    public BlogVo getBlog(UserVo vo);

    public boolean create(BlogVo vo);

    public boolean modify(BlogVo vo);


}
