package jblog.repository;

import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

import java.util.List;

public interface CateDao {
    public int insert(CategoryVo vo);// 카테고리 추가

    public List<CategoryVo> selectAll(UserVo vo); // 네비게이션출력용 전체 카테고리 가져오기

    public CategoryVo selectbyNo(Long CateNo);
}

