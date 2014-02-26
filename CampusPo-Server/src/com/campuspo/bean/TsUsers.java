package com.campuspo.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TsUsers entity. @author MyEclipse Persistence Tools
 */

public class TsUsers implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userEmail;
	private String userPassword;
	private String userDescription;
	private String userScreenName;
	private String userName;
	private String profileIconUrl;
	private String profileMiddleIconUrl;
	private String profileBigIconUrl;
	private String profileBackgroundUrl;
	private Boolean isdelete;
	private String userGender;
	private String userContact;
	private Timestamp createdAt;
	private Set tsSkills = new HashSet(0);
	private Set tsParticipants = new HashSet(0);
	private Set tsFocusesForFcsUserId = new HashSet(0);
	private Set tsLikes = new HashSet(0);
	private Set tsFocusesForUserId = new HashSet(0);
	private Set tsPosters = new HashSet(0);
	private Set tsFavours = new HashSet(0);

	// Constructors

	/** default constructor */
	public TsUsers() {
	}

	/** minimal constructor */
	public TsUsers(String userEmail, String userPassword, String userScreenName) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userScreenName = userScreenName;
	}

	/** full constructor */
	public TsUsers(String userEmail, String userPassword,
			String userDescription, String userScreenName, String userName,
			String profileIconUrl, String profileMiddleIconUrl,
			String profileBigIconUrl, String profileBackgroundUrl,
			Boolean isdelete, String userGender, String userContact,
			Timestamp createdAt, Set tsSkills, Set tsParticipants,
			Set tsFocusesForFcsUserId, Set tsLikes, Set tsFocusesForUserId,
			Set tsPosters, Set tsFavours) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userDescription = userDescription;
		this.userScreenName = userScreenName;
		this.userName = userName;
		this.profileIconUrl = profileIconUrl;
		this.profileMiddleIconUrl = profileMiddleIconUrl;
		this.profileBigIconUrl = profileBigIconUrl;
		this.profileBackgroundUrl = profileBackgroundUrl;
		this.isdelete = isdelete;
		this.userGender = userGender;
		this.userContact = userContact;
		this.createdAt = createdAt;
		this.tsSkills = tsSkills;
		this.tsParticipants = tsParticipants;
		this.tsFocusesForFcsUserId = tsFocusesForFcsUserId;
		this.tsLikes = tsLikes;
		this.tsFocusesForUserId = tsFocusesForUserId;
		this.tsPosters = tsPosters;
		this.tsFavours = tsFavours;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDescription() {
		return this.userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getUserScreenName() {
		return this.userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfileIconUrl() {
		return this.profileIconUrl;
	}

	public void setProfileIconUrl(String profileIconUrl) {
		this.profileIconUrl = profileIconUrl;
	}

	public String getProfileMiddleIconUrl() {
		return this.profileMiddleIconUrl;
	}

	public void setProfileMiddleIconUrl(String profileMiddleIconUrl) {
		this.profileMiddleIconUrl = profileMiddleIconUrl;
	}

	public String getProfileBigIconUrl() {
		return this.profileBigIconUrl;
	}

	public void setProfileBigIconUrl(String profileBigIconUrl) {
		this.profileBigIconUrl = profileBigIconUrl;
	}

	public String getProfileBackgroundUrl() {
		return this.profileBackgroundUrl;
	}

	public void setProfileBackgroundUrl(String profileBackgroundUrl) {
		this.profileBackgroundUrl = profileBackgroundUrl;
	}

	public Boolean getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserContact() {
		return this.userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Set getTsSkills() {
		return this.tsSkills;
	}

	public void setTsSkills(Set tsSkills) {
		this.tsSkills = tsSkills;
	}

	public Set getTsParticipants() {
		return this.tsParticipants;
	}

	public void setTsParticipants(Set tsParticipants) {
		this.tsParticipants = tsParticipants;
	}

	public Set getTsFocusesForFcsUserId() {
		return this.tsFocusesForFcsUserId;
	}

	public void setTsFocusesForFcsUserId(Set tsFocusesForFcsUserId) {
		this.tsFocusesForFcsUserId = tsFocusesForFcsUserId;
	}

	public Set getTsLikes() {
		return this.tsLikes;
	}

	public void setTsLikes(Set tsLikes) {
		this.tsLikes = tsLikes;
	}

	public Set getTsFocusesForUserId() {
		return this.tsFocusesForUserId;
	}

	public void setTsFocusesForUserId(Set tsFocusesForUserId) {
		this.tsFocusesForUserId = tsFocusesForUserId;
	}

	public Set getTsPosters() {
		return this.tsPosters;
	}

	public void setTsPosters(Set tsPosters) {
		this.tsPosters = tsPosters;
	}

	public Set getTsFavours() {
		return this.tsFavours;
	}

	public void setTsFavours(Set tsFavours) {
		this.tsFavours = tsFavours;
	}

}