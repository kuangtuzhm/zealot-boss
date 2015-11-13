package com.zealot.boss.base.dao.authorize.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.DepartmentDao;
import com.zealot.boss.base.entity.authorize.Department;
import com.zealot.exception.AppException;
import com.zealot.orm.dao.BaseDAO;

@Service("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

	@Resource(name="baseDAO")
    private BaseDAO<Department> baseDAO;
	
	@Override
	public Integer saveDepartment(Department dep) throws AppException {
		return (Integer)baseDAO.save(dep);
	}

	@Override
	public void updateDepartment(Department dep) throws AppException {
		baseDAO.update(dep);
	}

	@Override
	public void deleteDepartment(Department depId) throws AppException {
		baseDAO.delete(depId);
	}

	@Override
	public Department findDepartmentById(Integer depId) throws AppException {
		return baseDAO.get(Department.class, depId);
	}

	@Override
	public Long countUserByDepartment(Integer depId) throws AppException {
		String hql = "select count(1) from User u where u.depId = ?";
		List<Object> param = new ArrayList<Object>();
    	param.add(depId);
		return baseDAO.count(hql, param);
	}

	@Override
	public List<Department> findByParentId(Integer parentId)
			throws AppException {
		Department dep = new Department();
		dep.setParentId(parentId);
		return baseDAO.queryByExample(dep);
	}

	@Override
	public List<Department> findByPath(String path) throws AppException {
		Department dep = new Department();
		dep.setPath(path);
		return baseDAO.queryByExample(dep);
	}

	@Override
	public List<Department> findAll() throws AppException {
		String hql = " from Department";
		return baseDAO.find(hql);
	}
}
