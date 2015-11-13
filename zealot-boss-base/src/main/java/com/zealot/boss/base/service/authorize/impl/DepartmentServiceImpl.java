package com.zealot.boss.base.service.authorize.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zealot.boss.base.dao.authorize.DepartmentDao;
import com.zealot.boss.base.entity.authorize.Department;
import com.zealot.boss.base.service.authorize.DepartmentService;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.exception.message.MessageCode;


@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentDao departmentDAO;
		
	/**
	 * 查找单个部门信息
	 */
	public Department findById(Integer id) throws AppException {
		return departmentDAO.findDepartmentById(id);
	}
	
	/**
	 * 查找所有子菜單
	 */
	public List <Department> getChildrenDepartmentList(Integer parentId) throws AppException
	{
		List <Department> departmentList = departmentDAO.findByParentId( parentId );
		return departmentList;
	}
	
	/**
	 * 根据路径查询路径菜單
	 */
	public List <Department> getDepartmentListByPath(String path) throws AppException
	{
		Department dep = new Department();
		dep.setPath(path);
		List <Department> departmentList = departmentDAO.findByPath(path);
		return departmentList;
	}

	/**
	 * 查找所有根部门
	 */
	public List<Department> findRootDepartmentList() throws AppException {
		List<Department> departmentList = this.getChildrenDepartmentList(0);
		return departmentList;
	}
	
	/**
	 * 查找所有部门
	 */
	public List<Department> findAll() throws AppException {
		return departmentDAO.findAll();
	}
	
	
	/**
	 *從所有菜單中找子菜單
	 */
	public List<Department> findChildList(Department department, List<Department> allDepartmentList){
		List<Department> tmpList = new LinkedList<Department>();
		for( Department  childDepartment : allDepartmentList){
			if( childDepartment.getParentId().intValue() == department.getId().intValue() ){
				tmpList.add( childDepartment );
			}
		}
		return tmpList;
	}
	
	/**
	 * 添加department
	 * @param department
	 * @throws AppException
	 */
	public void add(Department department) throws AppException {
		Date date = new Date();
		department.setCreateTime(date);
		department.setUpdateTime(date);
		List<Department> existDepartment = this.getChildrenDepartmentList(department.getParentId());
		department.setIorder(existDepartment.size() + 1);
		departmentDAO.saveDepartment(department);
	}
	
	/**
	 * 更新department
	 * @param department
	 * @throws AppException
	 */
	public void update(Department department) throws AppException {
		//String[] ignoreArray = { "id", "createTime" };
		Department persistent = findById(department.getId());
		//BeanUtils.copyProperties(department, persistent, ignoreArray);
		persistent.setName(department.getName());
		persistent.setIorder(department.getIorder());
		persistent.setUpdateTime(new Date());
		persistent.setUpdatedBy(department.getUpdatedBy());
		departmentDAO.updateDepartment(persistent);
	}
	
	/**
	 * 删除department
	 * @param department
	 * @throws AppException
	 */
	public void delete(Department department) throws AppException,ResultException {
		List<Department> childList = this.getChildrenDepartmentList(department.getId());
		if (childList.size() > 0) {
			throw new ResultException(MessageCode.DEP_CHILD_EXITS);
		}
		Long count = departmentDAO.countUserByDepartment(department.getId());
		if (count > 0) {
			throw new ResultException(MessageCode.DEP_USER_EXITS);
		}
		departmentDAO.deleteDepartment(department);
	}

}
