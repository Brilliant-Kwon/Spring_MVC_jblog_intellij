package jblog.service;

import jblog.vo.UserVo;

public interface UserService {

    public boolean join(UserVo vo);//회원 가입

    public UserVo getUser(String id);//중복 검사

    public UserVo getUser(String id, String password);//로그인
}
