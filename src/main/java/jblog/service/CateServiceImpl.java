package jblog.service;

import jblog.repository.CateDao;
import jblog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    CateDao cateDaoImpl;

    @Override
    public boolean createDefault(CategoryVo vo) {
        return cateDaoImpl.insert(vo) == 1;
    }
}
