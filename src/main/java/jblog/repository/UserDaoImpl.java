package jblog.repository;

import jblog.exception.UserDaoException;
import jblog.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int insert(UserVo vo) {
        int insertedCount = 0;
        try {
            insertedCount = sqlSession.insert("users.insert", vo);
        } catch (Exception e) {
            System.err.println("DAO:Error : " + e.getMessage());
            UserDaoException ex = new UserDaoException("회원 가입 중 오류");
            ex.setVo(vo);
            throw ex;
        }
        return insertedCount;
    }

    @Override
    public UserVo selectUser(String id) {
        System.out.println("다오 아이디 체크");
        System.out.println(id);
        return sqlSession.selectOne("users.check", id);
    }

    @Override
    public UserVo selectUser(String id, String password) {
        System.out.println("다오");
        Map map = new HashMap();
        map.put("id", id);
        map.put("password", password);
        System.out.println("맵 : "+ map.toString());

        UserVo vo = sqlSession.selectOne("users.selectUser", map);
        System.out.println(vo.toString());

        return vo;
    }
}
