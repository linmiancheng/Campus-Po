package com.campuspo.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsFavour;
import com.campuspo.bean.TsFavourId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TsFavour entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.campuspo.bean.TsFavour
 * @author MyEclipse Persistence Tools
 */

public class TsFavourDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TsFavourDAO.class);
	// property constants
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public void save(TsFavour transientInstance) {
		log.debug("saving TsFavour instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TsFavour persistentInstance) {
		log.debug("deleting TsFavour instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TsFavour findById(com.campuspo.bean.TsFavourId id) {
		log.debug("getting TsFavour instance with id: " + id);
		try {
			TsFavour instance = (TsFavour) getHibernateTemplate().get(
					"com.campuspo.bean.TsFavour", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsFavour instance) {
		log.debug("finding TsFavour instance by example");
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
		log.debug("finding TsFavour instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsFavour as model where model."
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
		log.debug("finding all TsFavour instances");
		try {
			String queryString = "from TsFavour";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsFavour merge(TsFavour detachedInstance) {
		log.debug("merging TsFavour instance");
		try {
			TsFavour result = (TsFavour) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsFavour instance) {
		log.debug("attaching dirty TsFavour instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsFavour instance) {
		log.debug("attaching clean TsFavour instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TsFavourDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TsFavourDAO) ctx.getBean("TsFavourDAO");
	}
}