package jblog.repository;

import jblog.vo.CategoryVo;
import jblog.vo.PostVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImpl implements PostDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public List<PostVo> selectAll(CategoryVo vo) {
        return sqlSession.selectList("post.selectAll", vo);
    }

    @Override
    public int insert(PostVo vo) {
        return sqlSession.insert("post.insert", vo);
    }
}
