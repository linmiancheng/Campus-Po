package com.campuspo.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.campuspo.bean.TsPoster;

public class PosterDaoImpl  extends HibernateDaoSupport implements PosterDao{

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

	

}
