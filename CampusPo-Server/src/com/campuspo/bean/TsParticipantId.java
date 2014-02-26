package com.campuspo.bean;

/**
 * TsParticipantId entity. @author MyEclipse Persistence Tools
 */

public class TsParticipantId implements java.io.Serializable {

	// Fields

	private TsUsers tsUsers;
	private TsPoster tsPoster;

	// Constructors

	/** default constructor */
	public TsParticipantId() {
	}

	/** full constructor */
	public TsParticipantId(TsUsers tsUsers, TsPoster tsPoster) {
		this.tsUsers = tsUsers;
		this.tsPoster = tsPoster;
	}

	// Property accessors

	public TsUsers getTsUsers() {
		return this.tsUsers;
	}

	public void setTsUsers(TsUsers tsUsers) {
		this.tsUsers = tsUsers;
	}

	public TsPoster getTsPoster() {
		return this.tsPoster;
	}

	public void setTsPoster(TsPoster tsPoster) {
		this.tsPoster = tsPoster;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TsParticipantId))
			return false;
		TsParticipantId castOther = (TsParticipantId) other;

		return ((this.getTsUsers() == castOther.getTsUsers()) || (this
				.getTsUsers() != null && castOther.getTsUsers() != null && this
				.getTsUsers().equals(castOther.getTsUsers())))
				&& ((this.getTsPoster() == castOther.getTsPoster()) || (this
						.getTsPoster() != null
						&& castOther.getTsPoster() != null && this
						.getTsPoster().equals(castOther.getTsPoster())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTsUsers() == null ? 0 : this.getTsUsers().hashCode());
		result = 37 * result
				+ (getTsPoster() == null ? 0 : this.getTsPoster().hashCode());
		return result;
	}

}