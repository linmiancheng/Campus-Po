package com.campuspo.action;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.campuspo.bean.TsFavour;
import com.campuspo.bean.TsFavourId;
import com.campuspo.bean.TsFocus;
import com.campuspo.bean.TsFocusId;
import com.campuspo.bean.TsPoster;
import com.campuspo.bean.TsUsers;
import com.campuspo.biz.UserBiz;
import com.opensymphony.xwork2.ActionSupport;

public class userAction extends ActionSupport {
	private UserBiz userBiz;
	private JSONObject data;
	// private String data;
	private int code;

	// ����restful����
	private String email;
	private String password;

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

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public String MUserLogin() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// �����ʽ��������ʽ
		// String emailFormat =
		// "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		// //��������ĸ�ʽ�����볤������ʮ�������»��ߣ���ĸ�����ֹ���
		// String passwordFromat = "\\w{6,20}";
		HttpSession session = request.getSession(false);
		System.out.print("sessioni" + session);
		if (session != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			if (userId != null) {
				TsUsers tempUser = new TsUsers();
				tempUser.setUserId(userId);
				data = userBiz.getUser(tempUser);
				if (data != null) {
					code = 1;
					return SUCCESS;
				}
			}
		}
		// �û�δ��¼
		code = 0;
		return NONE;
	}

	public String userLogin() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// �����ʽ��������ʽ
		// String emailFormat =
		// "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		// //��������ĸ�ʽ�����볤������ʮ�������»��ߣ���ĸ�����ֹ���
		// String passwordFromat = "\\w{6,20}";
		String user_email = request.getParameter("email");
		String user_password = request.getParameter("password");
		if (user_email.equals("") || user_password.equals("")) {
			code = 0;
			return NONE;
		}
		Integer userId = null;
		data = userBiz.checkLogin(user_email, user_password);
		if (data != null) {
			/*
			 * �û���½��ʱ�򴴽�session�����浽cookie�С�
			 */
			userId = (Integer) data.get("user_id");
			HttpSession session1 = request.getSession();
			System.out.println("userId" + userId);
			session1.setAttribute("userId", userId);
			session1.setMaxInactiveInterval(24 * 60 * 60);
			Cookie cookie = new Cookie("sessionId", session1.getId());
			System.out.println(session1.getAttribute("userId"));
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
			code = 1;
			return SUCCESS;
		} else {
			code = 0;
			return NONE;
		}

		// String user_email = request.getParameter("email");
		// String user_password = request.getParameter("password");
		// System.out.println("email"+user_email+"password"+user_password+"**************************");
		// if (user_email.equals("")||user_password.equals("")) {
		// code = 0;
		// return NONE;
		// }
		// Integer userId = null;
		// data = userBiz.checkLogin(user_email,user_password);
		// if(data != null){
		// /*
		// * �û���½��ʱ�򴴽�session�����浽cookie�С�
		// */
		// userId=(Integer) data.get("user_id");
		// HttpSession session = request.getSession();
		// System.out.println("userId" + userId);
		// session.setAttribute("userId", userId);
		// session.setMaxInactiveInterval(60 * 60);
		// Cookie cookie = new Cookie("sessionId", session.getId());
		// System.out.println(session.getAttribute("userId"));
		// cookie.setMaxAge(60 * 60);
		// response.addCookie(cookie);
		// code=1;
		// return SUCCESS;
		// }else{
		// code=0;
		// return NONE;
		// }

		// �����û���֤����ʱ����
		// if(user_email == null || "".equals(user_email)){
		// //this.addFieldError("user_email","���䲻��Ϊ��");
		// code = 1;
		// return ERROR;
		// }else if(user_email.matches(emailFormat)){
		// //this.addFieldError("user_email","�����ʽ����");
		// code=1;
		// return ERROR;
		// }
		//
		// if(user_password == null || "".equals(user_password)){
		// //this.addFieldError("user_password","���벻��Ϊ��");
		// code=1;
		// return ERROR;
		// }else if(user_password.matches(passwordFromat)){
		// // this.addFieldError("user_password","�������»��ߡ���ĸ�����ֹ��ɣ�����6~12");
		// code=1;
		// return ERROR;
		// }

		// TsUsers tempUser = new TsUsers();
		// //JSONObject json = new JSONObject();
		// tempUser.setUsersEmail(user_email);
		// tempUser.setUsersPassword(user_password);
		//
		// ��ȡ��ѯ���
		// TsUsers user = userBiz.getUsers(tempUser);
		// if(user!=null){
		// data.put("user_id",user.getUsersId());
		// data.put("user_email",user.getUsersEmail());
		// data.put("user_screen_name",user.getUsersScreenName());
		// data.put("user_profile_icon_url",user.getUsersProfile());
		// data.put("user_signature",user.getUsersSignature());
		// data.put("user_gender",user.getUsersGender());
		// data.put("isFoucs",false);
		// JSONArray skillArray = new JSONArray();
		// Iterator iter=user.getSkills().iterator();
		// while(iter.hasNext()) {
		// JSONObject skillObject = new JSONObject();
		// TsSkill skill = (TsSkill)iter.next();
		// skillObject.put("skillId", skill.getSkillId());
		// skillObject.put("skillDescription",skill.getSkillDescription());
		// skillArray.add(skillObject);
		// }
		// data.put("skills", skillArray);
		// //data = json.toString();
		// //data.put("skills",user.getSkills().toString());
		// code = 1;
		// //response.getWriter().print(taskObject.toString());
		// return SUCCESS;
		// }else{
		// data = null;
		// code = 0;
		// return NONE;
		// }

	}

	public String getUserInfo() throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession session = request.getSession(false);
		System.out.print("session" + session);
		if (session != null) {
			Integer userId = null;
			if((request.getParameter("user_id"))==null){
				userId = (Integer) session.getAttribute("userId");
				System.out.println("Sdsd");
			}else{
				userId = Integer.valueOf(request.getParameter("user_id"));
				System.out.println("WWWWWWW");
			}
			if (userId != null) {
				TsUsers tempUser = new TsUsers();
				tempUser.setUserId(userId);
				data = userBiz.getUser(tempUser);
				if (data != null) {
					code = 1;
					return SUCCESS;
				}
			}
		}
		// �û�δ��¼
		code = 0;
		return NONE;

	}

	public String addFocus() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();

		// ��ȡ��ע��Ϣ
		HttpSession session = request.getSession(false);
		if (session != null) {
			// �û��Ѵ��ڵ�½״̬
			Integer userId = (Integer) session.getAttribute("userId");
			if (userId != null) {

				/*
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 
		    	 * 	
		    	 */

				// ��ȡ�ղ���Ϣ
				code = 1;
				return SUCCESS;
			}
		}
		// �û�δ��¼
		code = 0;
		return NONE;

	}

	public String getFocusTimeLine() throws IOException {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		if (session != null) {
			// �û��Ѵ��ڵ�½״̬
			Integer userId = (Integer) session.getAttribute("userId");
			if (userId != null) {
				int sinceId = 0;
				int length = 20;
				TsUsers tempUser = new TsUsers();
				tempUser.setUserId(userId);
				TsFocusId focusId = new TsFocusId();
				focusId.setTsUsers(tempUser);
				TsFocus focus = new TsFocus();
				focus.setId(focusId);
				data = userBiz.getUserFocusTimeLine(focus, sinceId, length);
				if (data != null) {
					code = 1;
					return SUCCESS;
				}
			}
		}
		// �û�δ��¼
		code = 0;
		return NONE;
	}

	// ����ͨ��
	public String getMySponsorPosters() throws IOException {
		// ��ȡ�û���Ϣ
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset= utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		if (session != null) {
			Integer userId = (Integer) session.getAttribute("userId");
			if (userId != null) {
				int sinceId = 0;
				int length = 20;
				TsUsers user = new TsUsers();
				user.setUserId(userId);
				data = userBiz.getMyPosters(user, sinceId, length);
				if (data != null) {
					code = 1;
					return SUCCESS;
				}
			}
		}
		// �û�δ��¼
		code = 0;
		return NONE;
	}
	// Cookie[] cookies = request.getCookies();
	// if ( cookies!= null) {
	// for (Cookie cookie : cookies) {
	// String value = cookie.getValue();
	// }
	// } else {
	//
	// }
	// return SUCCESS;

}
// public String execute() throws Exception{
// //TsUsers user = new TsUsers();
// //user.setUsersId(1);
// //user.setUsersPassword("1234");
// return SUCCESS;
// }
