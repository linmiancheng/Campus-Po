package com.campuspo.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsParticipant;
import com.campuspo.bean.TsPoster;

/**
 * A data access object (DAO) providing persistence and search support for
 * TsPoster entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.campuspo.bean.TsPoster
 * @author MyEclipse Persistence Tools
 */

public class TsPosterDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TsPosterDAO.class);
	// property constants
	public static final String POSTER_TITLE = "posterTitle";
	public static final String POSTER_DESCRIPTION = "posterDescription";
	public static final String WANTED = "wanted";
	public static final String WANTED_NUM = "wantedNum";
	public static final String PARTICIPANT_NUM = "participantNum";
	public static final String ISDELETE = "isdelete";

	protected void initDao() {
		// do nothing
	}

	//************获取活动时间轴***************
	public List getPosterList(final int startPosition , final int length) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Criteria c = session.createCriteria(TsPoster.class);
				c.addOrder(Order.desc("posterId"));
				c.setFirstResult(startPosition);
				c.setMaxResults(length);
				return c.list();
			}
		});
	}
	
//	//*************获取活动详情***********
//	public JSONObject getPoster(TsPoster poster) {
//		// TODO Auto-generated method stub
//		JSONObject data = new JSONObject();
//		JSONObject posterObject = new JSONObject();
//		TsPoster posterTemp = null ;//获取查询结果
//		posterObject.put("poster_id" , poster.getPosterId());
//		posterObject.put("poster_title",poster.getPosterTitle());
//		posterObject.put("poster_description",poster.getPosterDescription());
//		posterObject.put("wanted" , 1);//数据库未更新
//		posterObject.put("wanted_num" , poster.getWantedNum());
//		posterObject.put("participant_num",poster.getParticipantNum());
//		posterObject.put("released_time",poster.getPosterReleasedTime());
//		posterObject.put("joined" , "");
//		posterObject.put("favorited" , "");
//		posterObject.put("isSponser","");
//		posterObject.put("user_id",poster.getTsUsers().getUserId());
//		posterObject.put("user_screename",poster.getTsUsers().getUserId());
//		posterObject.put("profile_icon_url",poster.getTsUsers().getUserId());
//		data.put("poster", posterObject);
//		return null;
//	}
//	
	//***********获取参与者列表**************
//	public JSONObject getParticipants(TsPoster poster) {
//		// TODO Auto-generated method stub
//
//		JSONObject data = new JSONObject();
//		JSONArray posterArray = new JSONArray();
//    	Iterator iter=null;//查询结果
//		while(iter.hasNext()) {
//			JSONObject participantObject = new JSONObject();
//			TsParticipant participant = (TsParticipant)iter.next();
//			participantObject.put("user_id","");
//			participantObject.put("user_email","");
//			participantObject.put("user_screen_name","");
//			participantObject.put("user_description","");
//			participantObject.put("profile_icon_url","");
//			participantObject.put("profile_middle_icon_url","");
//			participantObject.put("profile_big_icon_url","");
//			participantObject.put("profile_background_url","");
//			participantObject.put("user_name","");
//			participantObject.put("gender","");
//			participantObject.put("school","");
//			participantObject.put("created_at","");
//			participantObject.put("following","");
//			participantObject.put("following_count","");
//			//将查询结果转化为json
//			posterArray.add(participantObject);
//		}
//		data.put("participants", posterArray);
//		data.put("joinedAt","");
//        data.put("posterId", "");
//		//data.put("timeline",posterDao.getPosterList(startPosition , length));
//		return data;
//	}
//	
	
	public void save(TsPoster transientInstance) {
		log.debug("saving TsPoster instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TsPoster persistentInstance) {
		log.debug("deleting TsPoster instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TsPoster findById(java.lang.Integer id) {
		log.debug("getting TsPoster instance with id: " + id);
		try {
			TsPoster instance = (TsPoster) getHibernateTemplate().get(
					"com.campuspo.bean.TsPoster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TsPoster instance) {
		log.debug("finding TsPoster instance by example");
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
		log.debug("finding TsPoster instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TsPoster as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPosterTitle(Object posterTitle) {
		return findByProperty(POSTER_TITLE, posterTitle);
	}

	public List findByPosterDescription(Object posterDescription) {
		return findByProperty(POSTER_DESCRIPTION, posterDescription);
	}

	public List findByWanted(Object wanted) {
		return findByProperty(WANTED, wanted);
	}

	public List findByWantedNum(Object wantedNum) {
		return findByProperty(WANTED_NUM, wantedNum);
	}

	public List findByParticipantNum(Object participantNum) {
		return findByProperty(PARTICIPANT_NUM, participantNum);
	}

	public List findByIsdelete(Object isdelete) {
		return findByProperty(ISDELETE, isdelete);
	}

	public List findAll() {
		log.debug("finding all TsPoster instances");
		try {
			String queryString = "from TsPoster";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TsPoster merge(TsPoster detachedInstance) {
		log.debug("merging TsPoster instance");
		try {
			TsPoster result = (TsPoster) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TsPoster instance) {
		log.debug("attaching dirty TsPoster instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TsPoster instance) {
		log.debug("attaching clean TsPoster instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TsPosterDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TsPosterDAO) ctx.getBean("TsPosterDAO");
	}
}