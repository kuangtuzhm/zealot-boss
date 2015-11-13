package com.zealot.boss.base.dao.authorize;

import java.util.List;

import com.zealot.boss.base.entity.authorize.Department;
import com.zealot.exception.AppException;

/**
 * 部门Dao
 *
 */
public interface DepartmentDao {

	/**
	 * 保存
	 */
	public Integer saveDepartment(Department dep) throws AppException;
	
	/**
	 * 修改
	 * @param dep
	 * @throws AppException
	 */
	public void updateDepartment(Department dep) throws AppException;
	
	/**
	 * 删除
	 * @param depId
	 * @throws AppException
	 */
	public void deleteDepartment(Department depId) throws AppException;
	
	/**
	 * 查找
	 * @param depId
	 * @return
	 * @throws AppException
	 */
	public Department findDepartmentById(Integer depId) throws AppException;
	
	/**
	 * 查找部门下是否有用户
	 * @param depId
	 * @return
	 * @throws AppException
	 */
	public Long countUserByDepartment(Integer depId) throws AppException;
	
	/**
	 * 查找所有子菜單
	 * @param parentId
	 * @return
	 * @throws AppException
	 */
	public List<Department> findByParentId(Integer parentId) throws AppException;
	
	/**
	 * 根据path查询
	 * @param path
	 * @return
	 * @throws AppException
	 */
	public List<Department> findByPath(String path) throws AppException;
	
	/**
	 * 查找所有
	 * @return
	 * @throws AppException
	 */
	public List<Department> findAll() throws AppException;
}
