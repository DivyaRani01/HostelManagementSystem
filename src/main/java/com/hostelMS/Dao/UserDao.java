/*
 *Creating a Dao Interface to create abstract method with the name of uerdao.
 */
package com.hostelMS.Dao;
//importing required pacakages
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.model.User;
//interface named Userdao
public interface UserDao {
	
	public User viewRoom(int uId);
	public int viewDueAmount(int uId);
	public User viewProfile(int uId);
	public int changePhone(int uId,String phone);
	public int changePassword(int uId,String oldPwd,String newPwd) throws GlobalException;

}
