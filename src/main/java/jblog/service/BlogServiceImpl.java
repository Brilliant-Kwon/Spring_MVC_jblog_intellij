package jblog.service;

import jblog.repository.BlogDao;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogDao blogDaoImpl;

    @Override
    public List<BlogVo> getBlogList() {
        return null;
    }

    @Override
    public BlogVo getBlog(UserVo vo) {
        System.out.println("서비스 getBlog");
        return blogDaoImpl.select(vo);
    }

    @Override
    public boolean create(BlogVo vo) {
        return blogDaoImpl.insert(vo)==1;
    }

    @Override
    public boolean modify(BlogVo vo) {
        return blogDaoImpl.update(vo)==1;
    }
}
