package jblog.service;

import jblog.repository.PostDao;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDaoImpl;

    @Override
    public List<PostVo> getList(CategoryVo vo) {
        return postDaoImpl.selectAll(vo);
    }

    @Override
    public boolean write(PostVo vo) {
        return postDaoImpl.insert(vo)==1;
    }
}
