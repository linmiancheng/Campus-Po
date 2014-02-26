package com.campuspo.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsUsers;
import com.campuspo.biz.PosterBiz;
import com.campuspo.biz.UserBiz;
import com.campuspo.dao.TsPosterDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml"})
//@Transactional
public class testLogin {
	
	@Resource
	private UserBiz userBiz;
	@Resource
	private TsPosterDAO posterDAO;
	@Resource
	private PosterBiz posterBiz;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() {
		String a = userBiz.checkLogin("1938304514@qq.com", "1234").toString();
		System.out.print(a);
		//assertEquals("dd",a);
	}
	
	/*这里会抛出延时加载异常，因为延时加载处理是用web过滤器，但是此时用
	 * 测试类测试的时候是不会用到过滤器的
	 */
	@Test
	public void test() {
		posterBiz.getPosterList(new Integer(13), 0, 2);
	}
	@Test
	//@Rollback
	@Transactional
	public void test2() {
		TsPoster pos = new TsPoster();
		TsUsers user = new TsUsers();
		//测试用*************
		user.setUserId(new Integer(13));
		pos.setTsUsers(user);
		pos.setParticipantNum(3);
		pos.setPosterDescription("very funny");
		pos.setPosterReleasedTime((new Timestamp(System.currentTimeMillis())));
		pos.setPosterTitle("china");
		pos.setWanted((short)1);
		pos.setWantedNum(6);
		pos.setIsdelete(false);
		posterDAO.save(pos);
		//assertEquals(pos.getPosterTitle(),posterDAO.findById(new Integer(71)).getPosterTitle());
	}

}
