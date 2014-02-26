package com.campuspo.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsUsers;

/**
 * A data access object (DAO) providing persistence and search support for
 * TsUsers entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.campuspo.bean.TsUsers
 * @author MyEclipse Persistence Tools
 */

public class TsUsersDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TsUsersDAO.class);
	// property constants
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_PASSWORD = "userPassword";
	public static final String USER_DESCRIPTION = "userDescription";
	public static final String USER_SCREEN_NAME = "userScreenName";
	public static final String USER_NAME = "userName";
	public static final String PROFILE_ICON_URL = "profileIconUrl";
	public static final String PROFILE_MIDDLE_ICON_URL = "profileMiddleIconUrl";
	public static final String PROFILE_BIG_ICON_URL = "profileBigIconUrl";
	public static final String PROFILE_BACKGROUND_URL = "profileBackgroundUrl";
	public static final String ISDELETE = "isdelete";
	public static final String USER_GENDER = "userGender";
	public static final String USER_CONTACT = "userContact";

	protected void initDao() {
		// do nothing
	}

	public void save(TsUsers transientInstance) {
		log.debug("saving TsUsers instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TsUsers persistentInstance) {
		log.debug("deleting TsUsers instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TsUsers findById(java.lang.Integer id) {
		log.debug("getting TsUsers instance with id: " + id);
		try {
			TsUsers instance = (TsUsers) getHibernateTemplate().get(
					"com.campuspo.bean.TsUsers", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsUsers instance) {
		log.debug("finding TsUsers instance by example");
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
		log.debug("finding TsUsers instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsUsers as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findByUserDescription(Object userDescription) {
		return findByProperty(USER_DESCRIPTION, userDescription);
	}

	public List findByUserScreenName(Object userScreenName) {
		return findByProperty(USER_SCREEN_NAME, userScreenName);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByProfileIconUrl(Object profileIconUrl) {
		return findByProperty(PROFILE_ICON_URL, profileIconUrl);
	}

	public List findByProfileMiddleIconUrl(Object profileMiddleIconUrl) {
		return findByProperty(PROFILE_MIDDLE_ICON_URL, profileMiddleIconUrl);
	}

	public List findByProfileBigIconUrl(Object profileBigIconUrl) {
		return findByProperty(PROFILE_BIG_ICON_URL, profileBigIconUrl);
	}

	public List findByProfileBackgroundUrl(Object profileBackgroundUrl) {
		return findByProperty(PROFILE_BACKGROUND_URL, profileBackgroundUrl);
	}

	public List findByIsdelete(Object isdelete) {
		return findByProperty(ISDELETE, isdelete);
	}

	public List findByUserGender(Object userGender) {
		return findByProperty(USER_GENDER, userGender);
	}

	public List findByUserContact(Object userContact) {
		return findByProperty(USER_CONTACT, userContact);
	}

	public List findAll() {
		log.debug("finding all TsUsers instances");
		try {
			String queryString = "from TsUsers";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsUsers merge(TsUsers detachedInstance) {
		log.debug("merging TsUsers instance");
		try {
			TsUsers result = (TsUsers) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsUsers instance) {
		log.debug("attaching dirty TsUsers instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsUsers instance) {
		log.debug("attaching clean TsUsers instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TsUsersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TsUsersDAO) ctx.getBean("TsUsersDAO");
	}
}