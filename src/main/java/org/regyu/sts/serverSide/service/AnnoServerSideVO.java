package org.regyu.sts.serverSide.service;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.regyu.sts.cmmn.validator.Phone;

//Annotaion을 이용한 vaild check ex) @Phone
public class AnnoServerSideVO {

	@NotEmpty(message = "필수 입력 값입니다.")
	@Email
	private String mberId;

	@NotEmpty
	@Size(min = 8, max = 20, message = "8자 이상 20자 이하로 입력해주세요.")
	private String password;

	@NotEmpty
	@Size(min = 8, max = 20)
	private String passwordConfirm;

	@NotEmpty
	private String mberNm;

	@NotEmpty
	@Phone
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
		return "AnnoServerSideVO [mberId=" + mberId + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", mberNm=" + mberNm + ", phoneNumber=" + phoneNumber + "]";
	}

}
