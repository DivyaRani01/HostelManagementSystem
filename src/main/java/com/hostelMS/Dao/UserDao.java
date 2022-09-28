package com.hostelMS.Dao;

import com.hostelMS.Exception.GlobalException;
import com.hostelMS.model.User;

public interface UserDao {
	
	public User viewRoom(int uId);
	public int viewDueAmount(int uId);
	public User viewProfile(int uId);
	public int changePhone(int uId,String phone);
	public int changePassword(int uId,String oldPwd,String newPwd) throws GlobalException;

}
