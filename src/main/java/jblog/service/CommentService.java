package jblog.service;

import jblog.vo.CommentVo;

import java.util.List;

public interface CommentService {
    public boolean create(CommentVo vo);

    public List<CommentVo> getComments(Long postNo);
}
