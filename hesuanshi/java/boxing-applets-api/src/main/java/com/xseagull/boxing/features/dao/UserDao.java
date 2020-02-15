package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Promoter;
import com.xseagull.boxing.features.entity.User;

/**
 * 用户管理
 */
public interface UserDao {
	/**
	 * 添加用户信息
	 */
	public int insert(User user);

	/**
	 * 更新用户信息
	 */
	public int update(User user);

	/**
	 * 分页查询用户信息
	 * 
	 * @param nickName
	 * @return
	 */
	public List<User> queryUserByPage(@Param("lists") String[] lists);

	/**
	 * 根据用户id查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public User findUserById(@Param("userId") String userId);

	/**
	 * 更换用户手机号
	 * 
	 * @param userId
	 * @param phone
	 * @return
	 */
	public int updatePhone(@Param("userId") String userId, @Param("phone") String phone);

	/**
	 * 根据unionid查看用户信息
	 */
	public User getUserByUnionid(String unionid);

	/**
	 * 根据openid查询用户信息
	 * 
	 * @param openid
	 * @return
	 */
	public User getUserByOpenid(String openid);

	/**
	 * 查询通知人员的服务号openid集合
	 * 
	 * @return
	 */
	public List<String> findServiceNumber();

	/**
	 * 根据用户id查询他的推荐人的信息
	 * 
	 * @param userId
	 * @return
	 */
	public Promoter findRecommenderById(@Param("userId") long userId);

	/**
	 * 我邀请的用户列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<User> findMyUser(@Param("userId") long userId);

}
