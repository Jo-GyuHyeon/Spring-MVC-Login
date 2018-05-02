package org.regyu.sts.serverSide.service;

public class ServerSideVO {
	
	private String mberId;
	private String password;
	private String passwordConfirm;
	private String mberNm;
	private String phoneNumber;
	
	public String getMberId() {
		return mberId;
	}
	
	public void setMberId(String mberId) {
		this.mberId = mberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getMberNm() {
		return mberNm;
	}

	public void setMberNm(String mberNm) {
		this.mberNm = mberNm;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "ServerSideVO [mberId=" + mberId + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", mberNm=" + mberNm + ", phoneNumber=" + phoneNumber + "]";
	}

}
