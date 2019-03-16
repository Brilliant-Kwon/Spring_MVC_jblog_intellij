package jblog.repository;

import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogDaoImpl implements BlogDao {
    @Autowired
    SqlSession sqlSession;


    @Override
    public List<BlogVo> selectAll() {
        return sqlSession.selectList("blog.selectAll");
    }

    @Override
    public BlogVo select(UserVo vo) {
        System.out.println("다오 select");
        return sqlSession.selectOne("blog.select",vo);
    }

    @Override
    public int insert(BlogVo vo) {
        return sqlSession.insert("blog.insert", vo);
    }

    @Override
    public int update(BlogVo vo) {
        return sqlSession.update("blog.update",vo);
    }
}
