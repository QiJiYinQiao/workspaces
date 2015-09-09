package com.qqms.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.qqms.util.PageUtil;

/**
 * 
 * 持久层共同Dao接口
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface PublicDao<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            待保存的对象
	 * @return 保存成功返回实现序列化接口的实体
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            待删除的对象
	 */
	public void delete(T o);

	/**
	 * 修改一个对象
	 * 
	 * @param o
	 *            待修改的对象
	 */
	public void update(T o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 *            待保存或更新的对象
	 */
	public void saveOrUpdate(T o);

	/**
	 * 根据指定的HQL查询
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 根据指定的hql查询出来的对象列表
	 */
	public List<T> find(String hql);

	/**
	 * 根据指定的ID获取对象
	 * 
	 * @param c
	 *            对象的类类型
	 * @param id
	 *            序列化的ID
	 * @return 根据ID获取的对象类型
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 通过指定的hql查询数据对象的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long count(String hql);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 下午7:31:54
	 * @Title:findTotalCount
	 * @Description:TODO查询sql总数（这里描述这个方法的作用）
	 * @param sql
	 * @return
	 * @throws:
	 */
	public Long findTotalCount(String sql);
	/**
	 * 带事务的hql执行
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 数据库影响的行数
	 */
	public Integer executeHql(String hql);

	/**
	 * 根据指定的hql,进行条件查询
	 * 
	 * @param hql
	 *            指定的hql
	 * @param params
	 *            条件
	 * @return 查询出来的对象列表
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 根据指定的hql,进行分页条件查询
	 * 
	 * @param hql
	 *            指定的hql
	 * @param params
	 *            条件
	 * @param page
	 *            当前页
	 * @param rows
	 *            大小
	 * @return 根据条件查询当前页的对象列表
	 */
	public List<T> find(String hql, Map<String, Object> params, Integer page,
			Integer rows);

	/**
	 * 根据指定的hql,进行条件查询
	 * 
	 * @param hql
	 *            指定的hql
	 * @param param
	 *            条件查询hql
	 * @return 条件查询到得对象
	 */
	public T get(String hql, Map<String, Object> param);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月29日 下午1:56:53
	 * @Title:uniqueResult
	 * @Description:TODO 根据sql返回一条唯一的数据（这里描述这个方法的作用）
	 * @param sql
	 * @return
	 * @throws:
	 */
	public T uniqueResult(String sql);

	/**
	 * 根据指定hql,获取条件查询的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @param params
	 *            条件参数
	 * @return 查询到得条件的个数
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 根据指定hql,相应的参数批量执行
	 * 
	 * @param hql
	 *            指定的hql
	 * @param params
	 *            相应的参数
	 * @return 数据库影响的行数
	 */
	public Integer executeHql(String hql, Map<String, Object> params);

	/**
	 * 根据指定的sql语句,进行查询
	 * 
	 * @param sql
	 *            指定的sql
	 * @return 查询出来的对象集合
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQL(String sql);

	/**
	 * 删除或更新
	 * 
	 * @param o
	 *            删除或更新的对象
	 */
	public void deleteToUpdate(T o);
	/**
	 * hql查询，加分页
	 * PCH add
	 */
	public List<T> find(String hql,PageUtil pageUtil);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月3日 下午1:48:45
	 * @Title:findBySql
	 * @Description:TODO 根据sql做分页查询（这里描述这个方法的作用）
	 * @param sql
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	public List<Object> findBySql(String sql,PageUtil pageUtil);
}
