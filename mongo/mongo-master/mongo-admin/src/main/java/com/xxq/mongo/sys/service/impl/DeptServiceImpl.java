package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.core.page.MybatisPageHelper;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Dept;
import com.xxq.mongo.sys.mapper.DeptMapper;
import com.xxq.mongo.sys.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Autowired
    private DeptMapper sysDeptMapper;


   /* public int save(Dept record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysDeptMapper.insertSelective(record);
        }
        return sysDeptMapper.updateByPrimaryKeySelective(record);
    }
*/
   // @Override
    public int delete(Dept record) {
        return sysDeptMapper.deleteByPrimaryKey(record.getId());
    }

    //@Override
    public int delete(List<Dept> records) {
        for(Dept record:records) {
            delete(record);
        }
        return 1;
    }

    //@Override
    public Dept findById(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
    }

    @Override
    public List<Dept> findTree() {
        List<Dept> sysDepts = new ArrayList<>();
        List<Dept> depts = sysDeptMapper.findAll();
        for (Dept dept : depts) {
            if (dept.getParentId() == null || dept.getParentId() == 0) {
                dept.setLevel(0);
                sysDepts.add(dept);
            }
        }
        findChildren(sysDepts, depts);
        return sysDepts;
    }

    private void findChildren(List<Dept> sysDepts, List<Dept> depts) {
        for (Dept sysDept : sysDepts) {
            List<Dept> children = new ArrayList<>();
            for (Dept dept : depts) {
                if (sysDept.getId() != null && sysDept.getId().equals(dept.getParentId())) {
                    dept.setParentName(sysDept.getName());
                    dept.setLevel(sysDept.getLevel() + 1);
                    children.add(dept);
                }
            }
            sysDept.setChildren(children);
            findChildren(children, depts);
        }
    }
}
