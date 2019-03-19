package jblog.repository;

import jblog.vo.CategoryVo;
import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CateDaoImpl implements CateDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int insert(CategoryVo vo) {
        return sqlSession.insert("cate.insert",vo);
    }

    @Override
    public List<CategoryVo> selectAll(UserVo vo) {
        return sqlSession.selectList("cate.selectAll", vo);
    }

    @Override
    public CategoryVo selectbyNo(Long CateNo) {
        return sqlSession.selectOne("cate.selectbyNo",CateNo);
    }
}
