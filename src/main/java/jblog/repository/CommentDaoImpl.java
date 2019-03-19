package jblog.repository;

import jblog.vo.CommentVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int write(CommentVo vo) {
        return sqlSession.insert("comment.insert", vo);
    }

    @Override
    public List<CommentVo> selectList(Long postNo) {
        System.out.println("다오 댓글: "+sqlSession.selectList("comment.selectList", postNo));
        return sqlSession.selectList("comment.selectList", postNo);
    }
}
