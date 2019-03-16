package jblog.repository;

import jblog.vo.BlogVo;
import jblog.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BlogDao {

    public List<BlogVo> selectAll();

    public BlogVo select(UserVo vo);

    public int insert(BlogVo vo);//블로그 생성

    public int update(BlogVo vo);//블로그 타이틀 변경

    // TODO: 2019-03-15 : 블로그 로고 변경
}
