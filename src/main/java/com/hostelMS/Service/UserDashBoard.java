package com.hostelMS.Service;

import com.hostelMS.Exception.GlobalException;

public interface UserDashBoard {
	
	public void dashboard(int uId) throws GlobalException;
	public void viewRoom();
	public void viewDueAmount();
	public void viewProfile();
	public void changePhonenumber();
	public void changePassword() throws GlobalException;

}
