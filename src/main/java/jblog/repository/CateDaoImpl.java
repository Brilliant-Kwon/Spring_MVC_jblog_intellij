package jblog.repository;

import jblog.vo.CategoryVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CateDaoImpl implements CateDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int insert(CategoryVo vo) {
        return sqlSession.insert("cate.insert",vo);
    }
}
