package com.zealot.boss.base.service.authorize;

import java.util.List;

import com.zealot.boss.base.entity.authorize.Department;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;


public interface DepartmentService {
	
	public Department findById(Integer id) throws AppException;
	
	/**
	 * 查找所有子菜單
	 */
	public List <Department> getChildrenDepartmentList(Integer parentId) throws AppException;
	
	/**
	 * 根据路径查询路径菜單
	 */
	public List <Department> getDepartmentListByPath(String path) throws AppException;

	/**
	 * 查找所有根部门
	 */
	public List<Department> findRootDepartmentList() throws AppException;
	
	/**
	 * 查找所有部门
	 */
	public List<Department> findAll() throws AppException;
	
	
	/**
	 *從所有菜單中找子菜單
	 */
	public List<Department> findChildList(Department department, List<Department> allDepartmentList);
	
	/**
	 * 添加department
	 * @param department
	 * @throws ServiceException
	 */
	public void add(Department department) throws AppException;
	
	/**
	 * 更新department
	 * @param department
	 * @throws ServiceException
	 */
	public void update(Department department) throws AppException;
	
	/**
	 * 删除department
	 * @param department
	 * @throws ServiceException
	 */
	public void delete(Department department) throws AppException,ResultException;

}
