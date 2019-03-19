package jblog.repository;

import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

import java.util.List;

public interface PostDao {
    public List<PostVo> selectAll(CategoryVo vo);

    public int insert(PostVo vo);

    public PostVo selectbyNo(Long postNo);
}
