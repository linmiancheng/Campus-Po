package com.campuspo.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsParticipant;
import com.campuspo.bean.TsParticipantId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TsParticipant entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.campuspo.bean.TsParticipant
 * @author MyEclipse Persistence Tools
 */

public class TsParticipantDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TsParticipantDAO.class);
	// property constants
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	public void save(TsParticipant transientInstance) {
		log.debug("saving TsParticipant instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TsParticipant persistentInstance) {
		log.debug("deleting TsParticipant instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TsParticipant findById(com.campuspo.bean.TsParticipantId id) {
		log.debug("getting TsParticipant instance with id: " + id);
		try {
			TsParticipant instance = (TsParticipant) getHibernateTemplate()
					.get("com.campuspo.bean.TsParticipant", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsParticipant instance) {
		log.debug("finding TsParticipant instance by example");
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
		log.debug("finding TsParticipant instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TsParticipant as model where model."
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
		log.debug("finding all TsParticipant instances");
		try {
			String queryString = "from TsParticipant";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsParticipant merge(TsParticipant detachedInstance) {
		log.debug("merging TsParticipant instance");
		try {
			TsParticipant result = (TsParticipant) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsParticipant instance) {
		log.debug("attaching dirty TsParticipant instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsParticipant instance) {
		log.debug("attaching clean TsParticipant instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TsParticipantDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TsParticipantDAO) ctx.getBean("TsParticipantDAO");
	}
}