package com.zealot.boss.base.service.authorize;

import com.zealot.boss.base.entity.authorize.User;
import com.zealot.exception.AppException;
import com.zealot.exception.ResultException;
import com.zealot.orm.model.Pagination;


public interface UserService
{
    /**
     * 用户登陆
     * @param user
     * @return
     * @throws AppException
     * @throws ResultException
     * @作者 赵海明
     * @创建时间 2015-2-28 下午3:55:33
     */
    public User login(User user) throws AppException,ResultException;
    
    /**
     * 判断用户是否存在
     * @param user
     * @return
     * @throws AppException
     * @作者 赵海明
     * @创建时间 2015-3-9 下午5:57:23
     */
    public boolean userExits(User user) throws AppException;
    
    /**
     * 判断用户是否可以新增
     * @param user
     * @return
     * @throws AppException
     * @throws ResultException
     * @作者 Administrator
     * @创建时间 2015-3-9 下午6:06:07
     */
    public boolean validUserAdd(User user) throws AppException,ResultException;

    /**
     * 新增用户
     * @param user
     * @return
     * @throws AppException
     * @作者 赵海明
     * @创建时间 2015-3-6 下午4:40:36
     */
    public Integer addUser(User user) throws AppException;
    
    /**
     * 修改用户
     * @param user
     * @throws AppException
     * @作者 赵海明
     * @创建时间 2015-3-6 下午4:42:06
     */
    public void updateUser(User user) throws AppException;
    
    /**
     * 修改用户
     * @param user
     * @throws AppException
     * @作者 赵海明
     * @创建时间 2015-7-2 下午4:42:06
     */
    public void updateUserState(User user) throws AppException;
    
    /**
     * 通过用户id查找用户
     * @param uid
     * @return
     * @作者 赵海明
     * @创建时间 2015-3-2 下午4:09:41
     */
    public User findUserByUid(int uid) throws AppException;
    
    /**
     * 修改用户密码
     * @param user
     * @return
     * @throws AppException
     * @throws ResultException
     * @作者  赵海明
     * @创建时间 2015-3-2 下午4:17:06
     */
    public void modifyUserPassWord(User user) throws AppException,ResultException;
    
    /**
     * 查询用户
     * @param user
     * @param pageNo
     * @param pangeSize
     * @return
     * @throws AppException
     * @作者 赵海明
     * @创建时间 2015-3-4 上午11:37:07
     */
    public Pagination<User> queryUserList(User user,int pageNo, int pageSize) throws AppException;
    
    public Pagination<User> queryRoleUsers(Integer roleId,int pageNo, int pageSize) throws AppException;
}
