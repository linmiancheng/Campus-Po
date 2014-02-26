package com.campuspo.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.campuspo.bean.TsFavour;
import com.campuspo.bean.TsFavourId;
import com.campuspo.bean.TsParticipant;
import com.campuspo.bean.TsParticipantId;
import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsUsers;
import com.campuspo.biz.PosterBiz;
import com.campuspo.dao.PosterDao;
import com.opensymphony.xwork2.ActionSupport;

public class GetPostersAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PosterBiz posterBiz;
	private JSONObject data = new JSONObject();
	private int code;
	
	
	public String getPosterList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		int startPosition= 0;
		int length = 20;
		 HttpSession session = request.getSession(false);
		    if (session!=null){
		    	  //用户已处于登陆状态
		    	Integer userId = (Integer) session.getAttribute("userId");
		    	if (userId!=null){
		    		data = posterBiz.getPosterList(userId,startPosition , length);
		    		if(data.get("timeline")!=null){
		    			code = 1;
		    			return SUCCESS; 
		    		}
		    	}
		    }
		    //用户未登录
			code=0;
			return NONE;
		
//		int startPosition= 0;
//		int length = 3;
//		data = posterBiz.getPosterList(startPosition , length);
//		if(data.get("timeline")!=null){
//			code = 1;
//			return SUCCESS; 
//		}else{
//			code = 0;
//			return ERROR;
//		}
	}
	
	
	public String getParticipantList() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		int startPosition= 0;
		int length = 20;
		 HttpSession session = request.getSession(false);
		    if (session!=null){
		    	  //用户已处于登陆状态
		    	String posterId = request.getParameter("poster_id");
		    	System.out.print("poster_id"+posterId);
		    	//posterId = "1";
		    	if (posterId!=null){
		    		TsPoster pos = new TsPoster();
		    		pos.setPosterId(Integer.valueOf(posterId));
		    		data = posterBiz.getParticipants(pos);
		    		if(data.get("participants")!=null){
		    			code = 1;
		    			return SUCCESS; 
		    		}
		    	}
		    }
		    //用户未登录
			code=0;
			return NONE;
		
	}
	
	public String getPoster() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		//HttpServletRequest request = ServletActionContext.getRequest();
		String posterId = request.getParameter("poster_id");
		
		HttpSession session = request.getSession(false);
	    if (session!=null){
	    	  //用户已处于登陆状态
	    	Integer userId = (Integer) session.getAttribute("userId");
	    	if (userId!=null){
	    		data = posterBiz.getPoster(posterId,userId);
	    		if(data.get("poster")!=null){
	    			code = 1;
	    			return SUCCESS; 
	    		}
	    	}
	    }
	    //用户未登录
		code=0;
		return NONE;
//		//测试用
//		posterId = "1";
//		//***
//		data = posterBiz.getPoster(posterId);
//		if(data.get("poster")!=null){
//			code = 1;
//			return SUCCESS; 
//		}else{
//			code = 0;
//			return ERROR;
//		}
	}
	
	//用户发布活动  测试通过
	public String releasePoster() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		//获取用户发布活动的信息
		//
		HttpSession session = request.getSession(false);
	    if (session!=null){
	    	  //用户已处于登陆状态
	    	Integer userId = (Integer) session.getAttribute("userId");
	    	if (userId!=null){
	    		TsPoster poster = new TsPoster();
	    		TsUsers user = new TsUsers();
	    		//测试用*************
	    		user.setUserId(userId);
	    		poster.setParticipantNum(Integer.valueOf(0));
	    		poster.setPosterTitle(request.getParameter("poster_title"));
	    		if(request.getParameter("wanted")=="true"){
	    			poster.setWanted((short) 1);
	    		}else{
	    			poster.setWanted((short)0);
	    		}	    		
	    		poster.setIsdelete(false);
	    		poster.setPosterDescription(request.getParameter("poster_description"));
	    		System.out.println(request.getParameter("poster_description"));
	    		poster.setPosterReleasedTime(new Timestamp(System.currentTimeMillis()));
	    		poster.setWantedNum(Integer.valueOf(request.getParameter("wanted_num")));
	    		poster.setTsUsers(user);
	    		data=posterBiz.addPoster(poster,user);
	    		if(data.get("poster")!=null){
	    			code = 1;
	    			return SUCCESS; 
	    		}
	    	}
	    }
//		TsPoster poster = new TsPoster();
//		TsUsers user = new TsUsers();
//		//测试用*************
//		user.setUserId(14);
//		poster.setParticipantNum(4);
//		poster.setPosterTitle("爬山");
//		poster.setWanted((short)1);
//		poster.setIsdelete(false);
//		poster.setPosterDescription("funy");
//		poster.setPosterReleasedTime(new Timestamp(System.currentTimeMillis()));
//		poster.setWantedNum(12);
//		poster.setTsUsers(user);
//		
//		
//		//********************
//		 //用户未登录
		code=0;
		return NONE;
	}
	
	
	//参加活动
		public String participant() throws IOException {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html; charset= utf-8");
			//获取发起活动关注信息
			
			HttpSession session = request.getSession(false);
		    if (session!=null){
		    	  //用户已处于登陆状态
		    	Integer userId  = (Integer) session.getAttribute("userId");
		    	String posterId = request.getParameter("poster_id");
		    	if (userId!=null){
		    		TsUsers user = new TsUsers();
		    		TsPoster poster = new TsPoster();
		    		user.setUserId(Integer.valueOf(userId));
		    		poster.setPosterId(Integer.valueOf(posterId));
		    		TsParticipantId id = new TsParticipantId();
		    		id.setTsPoster(poster);
		    		id.setTsUsers(user);
		    		TsParticipant participant = new TsParticipant();
		    		participant.setId(id);
		    		if(posterBiz.addParticipant(participant)){
			    		code = 1;
			    		return SUCCESS;
		    		}
		    	}
		    }
			code=0;
			return NONE;
		}
		
	public String quitJoin() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		//获取发起活动关注信息
		
		HttpSession session = request.getSession(false);
	    if (session!=null){
	    	  //用户已处于登陆状态
	    	Integer userId  = (Integer) session.getAttribute("userId");
	    	String posterId = request.getParameter("poster_id");
	    	if (userId!=null){

	    		TsUsers user = new TsUsers();
	    		TsPoster pos = new TsPoster();
	    		user.setUserId(userId);
	    		pos.setPosterId(Integer.valueOf(posterId));
	    		TsParticipant par = new TsParticipant();
	    		TsParticipantId id = new TsParticipantId();
	    		id.setTsPoster(pos);
	    		id.setTsUsers(user);
	    		par.setId(id);
	    		if(posterBiz.removeParticipant(par));
	    		code = 1;
	    		return SUCCESS;
	    	}
	    }
		code=0;
		return NONE;
	}
	
	//用户收藏活动  测试通过
	public String addFavour() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		//获取发起活动关注信息
		
		HttpSession session = request.getSession(false);
	    if (session!=null){
	    	  //用户已处于登陆状态
	    	Integer userId = (Integer) session.getAttribute("userId");
	    	if (userId!=null){
	    		TsUsers user = new TsUsers();
	    		TsPoster poster = new TsPoster();
	    		//测试用*************
	    		user.setUserId(14);
	    		poster.setPosterId(1);
	    		TsFavour favour = new TsFavour(new TsFavourId(poster,user));
	    		posterBiz.addFavour(favour);
	    		
	    		//获取收藏信息
	    		code = 1;
	    		return SUCCESS;
	    	}
	    }
	    //用户未登录
		code=0;
		return NONE;
		
//		TsUsers user = new TsUsers();
//		TsPoster poster = new TsPoster();
//		//测试用*************
//		user.setUserId(14);
//		poster.setPosterId(1);
//		TsFavour favour = new TsFavour(new TsFavourId(poster,user));
//		//获取收藏信息
//		code = 1;
//		return SUCCESS;
	}
	
	
	
	
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public PosterBiz getPosterBiz() {
		return posterBiz;
	}
	public void setPosterBiz(PosterBiz posterBiz) {
		this.posterBiz = posterBiz;
	}
	
	
}
