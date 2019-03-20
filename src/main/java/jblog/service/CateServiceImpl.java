package jblog.service;

import jblog.repository.CateDao;
import jblog.vo.CategoryVo;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    CateDao cateDaoImpl;

    @Override
    public boolean createDefault(CategoryVo vo) {
        return cateDaoImpl.insert(vo) == 1;
    }

    @Override
    public List<CategoryVo> getList(UserVo vo) {
        return cateDaoImpl.selectAll(vo);
    }

    @Override
    public CategoryVo getbyNo(Long cateNo) {
        return cateDaoImpl.selectbyNo(cateNo);
    }

    @Override
    public boolean deleteCate(Long cateNo) {
        return cateDaoImpl.delete(cateNo) == 1;
    }
}
