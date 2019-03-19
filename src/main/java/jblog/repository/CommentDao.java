package jblog.repository;

import jblog.vo.CommentVo;

import java.util.List;

public interface CommentDao {
    public int write(CommentVo vo);

    public List<CommentVo> selectList(Long postNo);
}
