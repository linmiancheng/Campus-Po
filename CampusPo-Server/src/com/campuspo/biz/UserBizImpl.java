package com.campuspo.biz;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.campuspo.bean.TsFocus;
import com.campuspo.bean.TsFocusId;
import com.campuspo.bean.TsParticipant;
import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsSkill;
import com.campuspo.bean.TsUsers;
import com.campuspo.dao.TsFocusDAO;
import com.campuspo.dao.TsPosterDAO;
import com.campuspo.dao.TsUsersDAO;

public class UserBizImpl implements UserBiz {

	private TsUsersDAO userDao;
	private TsFocusDAO userFocus;
	private TsPosterDAO posterDao;

	public TsPosterDAO getPosterDao() {
		return posterDao;
	}

	public void setPosterDao(TsPosterDAO posterDao) {
		this.posterDao = posterDao;
	}

	public void setUserDao(TsUsersDAO userDao) {
		this.userDao = userDao;
	}

	// ��ʱ����ϵͳ����ĺ��� findbyExample()��ʵ�֣��Ժ���ʱ��һ��Ҫ���������������
	//做成一个只读事务，可以提高效率。
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public JSONObject getUser(TsUsers user) {
		
		JSONObject data = new JSONObject();
//		List list = userDao.findByExample(user);
//		Iterator iter = list.iterator();
//		if (iter.hasNext()) {
//			tempUser = (TsUsers) iter.next();
//			System.out.println(tempUser.getUserId());
//		}
			TsUsers tempUser = userDao.findById(user.getUserId());
			if(tempUser!=null){
				data.put("user_id", tempUser.getUserId());
			data.put("user_email", tempUser.getUserEmail());
			data
					.put("user_screen_name", tempUser.getUserScreenName());
			data.put("user_description",
					tempUser.getUserDescription());// ��ݿ�δ����
			data
					.put("profile_icon_url", tempUser.getProfileIconUrl());
			data.put("profile_middle_icon_url",
					tempUser.getProfileMiddleIconUrl());
			data.put("profile_big_icon_url",
					tempUser.getProfileBigIconUrl());
			data.put("profile_background_url",
					tempUser.getProfileBackgroundUrl());
			data.put("user_name", tempUser.getUserName());
			data.put("gender", tempUser.getUserGender());
			data.put("school", "SCUT");
			data.put("created_at", tempUser.getCreatedAt().toString());
			data.put("following", true);
			data.put("following_count", 12);
//			JSONArray skillArray = new JSONArray();
			// Iterator iter1=user.getTsSkills().iterator();
			// while(iter1.hasNext()) {
			// JSONObject skillObject = new JSONObject();
			// TsSkill skill = (TsSkill)iter1.next();
			// skillObject.put("skillId", skill.getSkillId());
			// skillObject.put("skillDescription",skill.getSkillDescription());
			// skillArray.add(skillObject);
			// }
			// data.put("skills", skillArray);
			return data;
		// return tempUser;
			}else{
				return null;
			}
			

	}

	//做成一个只读事务，可以提高效率。
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public JSONObject checkLogin(String email, String password) {
		// String emailFormat =
		// "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		// //��������ĸ�ʽ�����볤������ʮ�������»��ߣ���ĸ�����ֹ���
		// String passwordFromat = "\\w{6,20}";
		// if(email == null || "".equals(email)){
		// //this.addFieldError("user_email","���䲻��Ϊ��");
		// return null;
		// }else if(email.matches(emailFormat)){
		// //this.addFieldError("user_email","�����ʽ����");
		// return null;
		// }
		//
		// if(password == null || "".equals(password)){
		// //this.addFieldError("user_password","���벻��Ϊ��");
		// return null;
		// }else if(password.matches(passwordFromat)){
		// // this.addFieldError("user_password","�������»��ߡ���ĸ�����ֹ��ɣ�����6~12");
		// return null;
		// }
		//
		TsUsers tempUser = new TsUsers();
		JSONObject data = new JSONObject();
		// JSONObject json = new JSONObject();
		tempUser.setUserEmail(email);
		tempUser.setUserPassword(password);

		// ��ȡ��ѯ���
		Iterator users = userDao.findByExample(tempUser).iterator();
		
		if (users.hasNext()) {
			TsUsers tempUser1 = (TsUsers)users.next();
			data.put("user_id", tempUser1.getUserId());
			data.put("user_email", tempUser1.getUserEmail());
			data
					.put("user_screen_name", tempUser1.getUserScreenName());
			data.put("user_description",
					tempUser1.getUserDescription());// ��ݿ�δ����
			data
					.put("profile_icon_url", tempUser1.getProfileIconUrl());
			data.put("profile_middle_icon_url",
					tempUser1.getProfileMiddleIconUrl());
			data.put("profile_big_icon_url",
					tempUser1.getProfileBigIconUrl());
			data.put("profile_background_url",
					tempUser1.getProfileBackgroundUrl());
			data.put("user_name", tempUser1.getUserName());
			data.put("gender", tempUser1.getUserGender());
			data.put("school", "SCUT");
			data.put("created_at", tempUser1.getCreatedAt().toString());
			data.put("following", true);
			data.put("following_count", 12);
//			JSONArray skillArray = new JSONArray();
			// Iterator iter1=user.getTsSkills().iterator();
			// while(iter1.hasNext()) {
			// JSONObject skillObject = new JSONObject();
			// TsSkill skill = (TsSkill)iter1.next();
			// skillObject.put("skillId", skill.getSkillId());
			// skillObject.put("skillDescription",skill.getSkillDescription());
			// skillArray.add(skillObject);
			// }
			// data.put("skills", skillArray);
			return data;
		}else {
			return null;
		}

	}

	// public JSONObject getUserFocus(TsFocus focus) {
	// userFocus.findByExample(focus);
	//
	// return null;
	// }

	//����ͨ��
	@SuppressWarnings("unchecked")
	//做成一个只读事务，可以提高效率。
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public JSONObject getUserFocusTimeLine(TsFocus focus, int since_id,
			int length) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray focusArray = new JSONArray();
		//TsUsers user = new TsUsers();
		//user.getTsFocusesForUserId().add(focus.getId());
		/*
		 * focus��user�����ӳ���ϵ������ѯfocus���ʱ��ͻ��ö�Ӧ�û�����Ϣ����������
		 * ���ñ���ע�ߵ���ϢֻҪ��focus���в�ѯ�Ϳ�����
		 */
		Iterator iter = userFocus.getFocusTimeLine(focus, since_id, length)
				.iterator();
		while (iter.hasNext()) {
			JSONObject focusObject = new JSONObject();
			TsFocus tempFocus = (TsFocus) iter.next();
			TsUsers userBeFocus = tempFocus.getId().getTsUsers_1();
			//userBeFocus.setUserId(tempFocus.getId().getTsUsers_1().getUserId());
			focusObject.put("user_id", userBeFocus.getUserId());
			focusObject.put("user_email", userBeFocus.getUserEmail());
			focusObject
					.put("user_screen_name", userBeFocus.getUserScreenName());
			focusObject.put("user_description",
					userBeFocus.getUserDescription());
			focusObject
					.put("profile_icon_url", userBeFocus.getProfileIconUrl());
			focusObject.put("profile_middle_icon_url",
					userBeFocus.getProfileMiddleIconUrl());
			focusObject.put("profile_big_icon_url",
					userBeFocus.getProfileBigIconUrl());
			focusObject.put("profile_background_url",
					userBeFocus.getProfileBackgroundUrl());
			focusObject.put("user_name", userBeFocus.getUserName());
			focusObject.put("gender", userBeFocus.getUserGender());
			focusObject.put("school", "SCUT");
			focusObject.put("created_at", userBeFocus.getCreatedAt().toString());
			focusObject.put("following", true);
			focusObject.put("following_count", 12);
			focusArray.add(focusObject);
		}
		data.put("user", focusArray);
		// data.put("timeline",posterDao.getPosterList(startPosition , length));
		return data;
	}
	//做成一个只读事务，可以提高效率。
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public JSONObject getFollowing(TsUsers user, int since_id, int length) {
		// TODO Auto-generated method stub
		
		
		
		
		
		return null;
	}

	
	
	
	
	
	
	
	
	
	public TsFocusDAO getUserFocus() {
		return userFocus;
	}

	public void setUserFocus(TsFocusDAO userFocus) {
		this.userFocus = userFocus;
	}

	//做成一个只读事务，可以提高效率。
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public JSONObject getMyPosters(TsUsers user, int since_id, int length) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONArray posterArray = new JSONArray();
		TsPoster Tempposter = (new TsPoster());
		Tempposter.setTsUsers(user);
		
    	Iterator iter=userDao.findById(user.getUserId()).getTsPosters().iterator();
    			//posterDao.findByExample(Tempposter).iterator();
		while(iter.hasNext()) {
			JSONObject posterObject = new JSONObject();
			TsPoster poster = (TsPoster)iter.next();
			posterObject.put("poster_id" , poster.getPosterId());
			posterObject.put("poster_title",poster.getPosterTitle());
			posterObject.put("poster_description",poster.getPosterDescription());
			if(poster.getWanted() == (short)1){
				posterObject.put("wanted" , true);//��ݿ�δ����
			}else{
				posterObject.put("wanted" , false);
			}
			posterObject.put("wanted_num" , poster.getWantedNum());
			posterObject.put("participant_num",poster.getParticipantNum());
			posterObject.put("poster_released_time",poster.getPosterReleasedTime().toString());
			Iterator it = poster.getTsParticipants().iterator();
			boolean flag = false;
			while(it.hasNext()){
				if(user.getUserId().equals(((TsParticipant)it.next()).getId().getTsUsers().getUserId()));
					flag=true;
			}
			posterObject.put("joined" , flag);
			posterObject.put("favorited" , false);
			if(user.getUserId().equals(poster.getTsUsers().getUserId())){
				posterObject.put("is_sponsor",true);
			}else{
				posterObject.put("is_sponsor",false);
			}
			posterObject.put("user_id",poster.getTsUsers().getUserId());
			posterObject.put("user_screen_name",poster.getTsUsers().getUserScreenName());
			posterObject.put("profile_icon_url",poster.getTsUsers().getProfileIconUrl());
			posterArray.add(posterObject);
		}
		data.put("timeline", posterArray);
		//data.put("timeline",posterDao.getPosterList(startPosition , length));
		return data;
	}

}
