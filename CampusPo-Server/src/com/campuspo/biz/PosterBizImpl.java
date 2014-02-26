package com.campuspo.biz;

import java.sql.SQLClientInfoException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.campuspo.bean.TsFavour;
import com.campuspo.bean.TsParticipant;
import com.campuspo.bean.TsParticipantId;
import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsSkill;
import com.campuspo.bean.TsUsers;
import com.campuspo.dao.PosterDao;
import com.campuspo.dao.TsFavourDAO;
import com.campuspo.dao.TsFocusDAO;
import com.campuspo.dao.TsParticipantDAO;
import com.campuspo.dao.TsPosterDAO;


public class PosterBizImpl implements PosterBiz{
	private TsPosterDAO posterDao;
	private TsFavourDAO favourDao;
	private TsParticipantDAO participantDao;
	public TsParticipantDAO getParticipantDao() {
		return participantDao;
	}
	public void setParticipantDao(TsParticipantDAO participantDao) {
		this.participantDao = participantDao;
	}
	public TsFavourDAO getFavourDao() {
		return favourDao;
	}
	public void setFavourDao(TsFavourDAO favourDao) {
		this.favourDao = favourDao;
	}
	public void setPosterDao(TsPosterDAO posterDao) {
		this.posterDao = posterDao;
	}
	public JSONObject getPosterList(Integer userId , int startPosition,int length){
		JSONObject data = new JSONObject();
		JSONArray posterArray = new JSONArray();
    	Iterator iter=posterDao.getPosterList(startPosition , length).iterator();
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
				if(userId.equals(((TsParticipant)it.next()).getId().getTsUsers().getUserId()));
					flag=true;
			}
			posterObject.put("joined" , flag);
			posterObject.put("favorited" , false);
			if(userId.equals(poster.getTsUsers().getUserId())){
				System.out.println(userId+" ad  "+poster.getTsUsers().getUserId());
				posterObject.put("is_sponsor",true);
			}else{
				posterObject.put("is_sponsor",false);
				System.out.println(userId+"  da  "+poster.getTsUsers().getUserId());
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
	
	public JSONObject getPoster(String posterId,Integer userId) {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		JSONObject posterObject = new JSONObject();
		TsPoster posterTemp = posterDao.findById(new Integer(posterId));//��ȡ��ѯ���
		System.out.print("ddd"+posterTemp.getPosterId());
		posterObject.put("poster_id" , posterTemp.getPosterId());
		posterObject.put("poster_title",posterTemp.getPosterTitle());
		posterObject.put("poster_description",posterTemp.getPosterDescription());
		if(posterTemp.getWanted() == (short)1){
			posterObject.put("wanted" , true);//��ݿ�δ����
		}else{
			posterObject.put("wanted" , false);
		}
		posterObject.put("wanted_num" , posterTemp.getWantedNum());
		posterObject.put("particiepant_num",posterTemp.getParticipantNum());
		posterObject.put("poster_released_time",posterTemp.getPosterReleasedTime().toString());
		Iterator it = posterTemp.getTsParticipants().iterator();
		boolean flag = false;
		while(it.hasNext()){
			if(userId.equals(((TsParticipant)it.next()).getId().getTsUsers().getUserId()));
				flag=true;
		}
		posterObject.put("joined" , flag);
		posterObject.put("favorited" , false);
		if(userId.equals(posterTemp.getTsUsers().getUserId())){
			posterObject.put("is_sponsor",true);
		}else{
			posterObject.put("is_sponsor",false);
		}
		posterObject.put("user_id",posterTemp.getTsUsers().getUserId());
		posterObject.put("user_screen_name",posterTemp.getTsUsers().getUserScreenName());
		posterObject.put("profile_icon_url",posterTemp.getTsUsers().getProfileIconUrl());
		data.put("poster", posterObject);
		return data;
	}
	public JSONObject getParticipants(TsPoster poster) {
		// TODO Auto-generated method stub

		JSONObject data = new JSONObject();
		JSONArray posterArray = new JSONArray();
		TsPoster pos=posterDao.findById(poster.getPosterId());
		/*
		 * 这里遇到一个问题，为什么用findbyexample会返回所有值
		 * 考完试一定要解决
		 * 
		 */
    	Iterator iter= pos.getTsParticipants().iterator();
    	while(iter.hasNext()) {
			JSONObject participantObject = new JSONObject();
			TsParticipant participant = (TsParticipant)iter.next();
			TsUsers user = participant.getId().getTsUsers();
			participantObject.put("user_id",user.getUserId());
			participantObject.put("user_email",user.getUserEmail());
			participantObject.put("user_screen_name",user.getUserScreenName());
			participantObject.put("user_description",user.getUserDescription());
			participantObject.put("profile_icon_url",user.getProfileIconUrl());
			participantObject.put("profile_middle_icon_url",user.getProfileMiddleIconUrl());
			participantObject.put("profile_big_icon_url",user.getProfileBigIconUrl());
			participantObject.put("profile_background_url",user.getProfileBackgroundUrl());
			participantObject.put("user_name",user.getUserName());
			participantObject.put("gender",user.getUserGender());
			participantObject.put("school","scut");
			participantObject.put("created_at",user.getCreatedAt().toString());
			participantObject.put("following",true);
			participantObject.put("following_count",12);
			participantObject.put("join_at",participant.getJoinTime().toString());
			participantObject.put("poster_id", poster.getPosterId());
			//����ѯ���ת��Ϊjson
			posterArray.add(participantObject);
		}
		data.put("participants", posterArray);
		//data.put("timeline",posterDao.getPosterList(startPosition , length));
		return data;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean addParticipant(TsParticipant participant){
		// TODO Auto-generated method stub
		participant.setJoinTime((new Timestamp(System.currentTimeMillis())));
		participant.setIsdelete(false);
		participantDao.save(participant);
		System.out.println(participantDao.findById(participant.getId()).toString());
		TsPoster pos = posterDao.findById(participant.getId().getTsPoster().getPosterId());
		//if(pos.getWanted()!=0||(pos.getWantedNum()==-1)
		//		||(pos.getWantedNum() > pos.getParticipantNum())){
			pos.setParticipantNum(Integer.valueOf(pos.getParticipantNum() + 1));
			posterDao.attachDirty(pos);
			new SQLClientInfoException();
			return true;
		//}
		//return false;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public JSONObject addPoster(TsPoster poster,TsUsers user) {
		// TODO Auto-generated method stub
	/*
//	 * 如果两个发布的活动内容是一模一样的话返回第一个发布的活动的id
 * 默认肯定可以成功发布	
	 */
		JSONObject data = new JSONObject();
		poster.setPosterReleasedTime((new Timestamp(System.currentTimeMillis())));
		poster.setIsdelete(false);
		posterDao.save(poster);
		Iterator iter = posterDao.findByExample(poster).iterator();
		if(iter.hasNext()) {
			JSONObject posterObject = new JSONObject();
			poster = (TsPoster)iter.next();
			posterObject.put("poster_id" , poster.getPosterId());
			posterObject.put("poster_title",poster.getPosterTitle());
			posterObject.put("poster_description",poster.getPosterDescription());
			if(poster.getWanted() == (short)1){
				posterObject.put("wanted" , true);//��ݿ�δ����
			}else{
				posterObject.put("wanted" , false);
			}
			posterObject.put("wanted_num" , poster.getWantedNum());
			posterObject.put("particiepant_num",poster.getParticipantNum());
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
			
			data.put("poster", posterObject);
			return data;
		}else{
			return null;
		}
	}
	public void addFavour(TsFavour favour) {
		// TODO Auto-generated method stub
		favour.setTime((new Timestamp(System.currentTimeMillis())));
		favour.setIsdelete(false);
		favourDao.save(favour);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean removeParticipant(TsParticipant participant){
		//暂时是将数据删除,默认删除肯定成功
		Iterator iter = participantDao.findByExample(participant).iterator();
		if(iter.hasNext())
		participant = (TsParticipant)iter.next();
		participantDao.delete(participant);
		TsPoster pos = posterDao.findById(participant.getId().getTsPoster().getPosterId());
		pos.setParticipantNum(Integer.valueOf(pos.getParticipantNum() - 1));
		posterDao.attachDirty(pos);	
		return true;
	}
	
}
