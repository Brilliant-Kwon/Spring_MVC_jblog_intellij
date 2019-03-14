package jblog.repository;

import jblog.vo.UserVo;

public interface UserDao {

    public int insert(UserVo vo);//회원가입

    public UserVo selectUser(String id); //아이디 중복검사

    public UserVo selectUser(String id, String password); //로그인
}
