package jblog.service;

import jblog.repository.CommentDao;
import jblog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDaoImpl;

    @Override
    public boolean create(CommentVo vo) {
        return commentDaoImpl.write(vo) == 1;
    }

    @Override
    public List<CommentVo> getComments(Long postNo) {
        System.out.println("서비스 댓글 : " + commentDaoImpl.selectList(postNo));
        return commentDaoImpl.selectList(postNo);
    }
}
