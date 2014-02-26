package com.campuspo.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsFocus;
import com.campuspo.bean.TsFocusId;
import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsUsers;

/**
 * A data access object (DAO) providing persistence and search support for
 * TsFocus entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.campuspo.bean.TsFocus
 * @author MyEclipse Persistence Tools
 */

public class TsFocusDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TsFocusDAO.class);
	// property constants
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public void save(TsFocus transientInstance) {
		log.debug("saving TsFocus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TsFocus persistentInstance) {
		log.debug("deleting TsFocus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List getFocusTimeLine(final TsFocus focus, final int since_id, final int length){
		// TODO Auto-generated method stub
				return super.getHibernateTemplate().executeFind(new HibernateCallback(){

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						// TODO Auto-generated method stub
						Criteria c = session.createCriteria(TsFocus.class);
						c.add(Example.create(focus));
						c.addOrder(Order.desc("time"));
						c.setFirstResult(since_id);
						c.setMaxResults(length);
						return c.list();
					}
				});
			}

	public TsFocus findById(com.campuspo.bean.TsFocusId id) {
		log.debug("getting TsFocus instance with id: " + id);
		try {
			TsFocus instance = (TsFocus) getHibernateTemplate().get(
					"com.campuspo.bean.TsFocus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsFocus instance) {
		log.debug("finding TsFocus instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TsFocus instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsFocus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIsdelete(Object isdelete) {
		return findByProperty(ISDELETE, isdelete);
	}

	public List findAll() {
		log.debug("finding all TsFocus instances");
		try {
			String queryString = "from TsFocus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsFocus merge(TsFocus detachedInstance) {
		log.debug("merging TsFocus instance");
		try {
			TsFocus result = (TsFocus) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsFocus instance) {
		log.debug("attaching dirty TsFocus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsFocus instance) {
		log.debug("attaching clean TsFocus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TsFocusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TsFocusDAO) ctx.getBean("TsFocusDAO");
	}
}