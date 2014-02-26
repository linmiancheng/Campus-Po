package com.campuspo.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsLike;
import com.campuspo.bean.TsLikeId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TsLike entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.campuspo.bean.TsLike
 * @author MyEclipse Persistence Tools
 */

public class TsLikeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TsLikeDAO.class);
	// property constants
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public void save(TsLike transientInstance) {
		log.debug("saving TsLike instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TsLike persistentInstance) {
		log.debug("deleting TsLike instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TsLike findById(com.campuspo.bean.TsLikeId id) {
		log.debug("getting TsLike instance with id: " + id);
		try {
			TsLike instance = (TsLike) getHibernateTemplate().get(
					"com.campuspo.bean.TsLike", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsLike instance) {
		log.debug("finding TsLike instance by example");
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
		log.debug("finding TsLike instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsLike as model where model."
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
		log.debug("finding all TsLike instances");
		try {
			String queryString = "from TsLike";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsLike merge(TsLike detachedInstance) {
		log.debug("merging TsLike instance");
		try {
			TsLike result = (TsLike) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsLike instance) {
		log.debug("attaching dirty TsLike instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsLike instance) {
		log.debug("attaching clean TsLike instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TsLikeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TsLikeDAO) ctx.getBean("TsLikeDAO");
	}
}