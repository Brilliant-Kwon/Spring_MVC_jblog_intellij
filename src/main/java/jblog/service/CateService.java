package jblog.service;

import jblog.vo.CategoryVo;
import jblog.vo.UserVo;

import java.util.List;

public interface CateService {
    public boolean createDefault(CategoryVo vo);

    public List<CategoryVo> getList(UserVo vo);
}
