package com.qqms.daoImpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qqms.dao.PublicDao;
import com.qqms.util.LogUtil;
import com.qqms.util.PageUtil;

/**
 * 
 * 持久层共同DaoImpl实现类
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@SuppressWarnings("unchecked")
@Repository("publicDao")
public class PublicDaoImpl<T> implements PublicDao<T> {

	/** 注入Hibernate会话工厂 */
	@Autowired
	private SessionFactory sessionFactory;

	// 注册监听器
	/*
	 * @PostConstruct public void registerListeners() { EventListenerRegistry
	 * registry = ((SessionFactoryImpl)
	 * sessionFactory).getServiceRegistry().getService
	 * (EventListenerRegistry.class);
	 * registry.getEventListenerGroup(EventType.POST_COMMIT_INSERT
	 * ).appendListener(new PostInsert());
	 * registry.getEventListenerGroup(EventType
	 * .POST_COMMIT_UPDATE).appendListener(new PostUpdate()); }
	 */

	/**
	 * 获取当前的session会话
	 * 
	 * @return 当前的session
	 */
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存
	 */
	public Serializable save(T o) {
		Serializable serializable = this.getCurrentSession().save(o);
		LogUtil.getLogs(this.getCurrentSession(), o, LogUtil.LOGS_INSERT,
				LogUtil.LOGS_INSERT_TEXT, LogUtil.LOGS_INSERT_NAME);
		return serializable;
	}

	/**
	 * 删除
	 */
	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	/**
	 * 更新
	 */
	public void update(T o) {
		this.getCurrentSession().update(o);
		LogUtil.getLogs(this.getCurrentSession(), o, LogUtil.LOGS_UPDATE,
				LogUtil.LOGS_UPDATE_TEXT, LogUtil.LOGS_UPDATE_NAME);
	}

	/**
	 * 删除或更新
	 */
	public void deleteToUpdate(T o) {
		this.getCurrentSession().update(o);
		LogUtil.getLogs(this.getCurrentSession(), o, LogUtil.LOGS_DELETE,
				LogUtil.LOGS_DELETE_TEXT, LogUtil.LOGS_DELETE_NAME);
	}

	/**
	 * 保存或更新
	 */
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	/**
	 * 根据指定的hql查询
	 */
	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	/**
	 * 根据指定的sql查询
	 */
	@SuppressWarnings("rawtypes")
	public List findBySQL(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).list();
	}

	/**
	 * 根据指定的hql,进行条件查询
	 */
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}		
		return q.list();
	}

	/**
	 * 根据指定的hql,进行分页条件查询
	 */
	public List<T> find(String hql, Map<String, Object> params, Integer page,
			Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/**
	 * 根据id获取指定的对象
	 */
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	/**
	 * 根据hql,进行条件查询
	 */
	public T get(String hql, Map<String, Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月29日 下午1:55:40
	 * @Title:get
	 * @Description:TODO 根据sql得到一条数据（这里描述这个方法的作用）
	 * @param sql
	 * @return
	 * @throws:
	 */
	public T uniqueResult(String sql){
		return (T)this.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	/**
	 * 获取对象的个数
	 */
	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	/**
	 * 根据条件获取对象的个数
	 */
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 下午7:29:51
	 * @Title:findTotalCount
	 * @Description:TODO 根据sql查询总数（这里描述这个方法的作用）
	 * @param sql
	 * @return
	 * @throws:
	 */
	public Long findTotalCount(String sql) {
		sql = "select count(*) from (" + sql + ") newTableName";
		return ((BigInteger) this.getCurrentSession().createSQLQuery(sql).uniqueResult())
				.longValue();
	}

	/**
	 * 执行hql,返回影响的行数
	 */
	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 执行带参数的hql,返回影响的行数
	 */
	public Integer executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}
	/**
	 * 执行hql,并分页,返回对象List集合
	 * PCH add
	 */
	public List<T> find(String hql,PageUtil pageUtil){
		int page = pageUtil.getPage();
		int rows = pageUtil.getRows();
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	/**
	 * (非 Javadoc) 
	 * <p>Title: findBySql</p> 
	 * <p>Description: </p> 
	 * @param sql
	 * @param pageUtil
	 * @return 
	 * @see com.qqms.dao.PublicDao#findBySql(java.lang.String, com.qqms.util.PageUtil)
	 */
	public List<Object> findBySql(String sql,PageUtil pageUtil){
		int page = pageUtil.getPage();
		int rows = pageUtil.getRows();
		Query q = this.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
}
