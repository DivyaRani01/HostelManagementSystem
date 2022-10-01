/*
 * Creating a Data Access Object interface  with the name of Admindao
 */

package com.hostelMS.Dao;
//importing required packages
import java.util.List;

import com.hostelMS.Exception.GlobalException;
import com.hostelMS.model.Room;
import com.hostelMS.model.User;

//creating a interface with the name of Admindao
public interface AdminDao {
	
	public List<Room> AllRooms();
	public List<User> AllUsers();
	public int createRoom(Room r) throws GlobalException;
	public int allotRoom(int uId,int rId) throws GlobalException;
	public int deleteUser(int uId) throws GlobalException;
	public List<User> userInARoom(int rId);
	public int addDueAmount(int uId,int amount) throws GlobalException;
	public int paidFeeAmount(int uId,int amount) throws GlobalException;
	public User viewUserProfile(int uId)throws GlobalException;

}
