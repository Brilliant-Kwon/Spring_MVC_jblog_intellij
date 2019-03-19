package jblog.service;

import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

import java.util.List;

public interface PostService {
    public List<PostVo> getList(CategoryVo vo);

    public boolean write(PostVo vo);

    public PostVo getPost(Long postNo);
}
