package com.hostelMS.Dao;
import com.hostelMS.Exception.GlobalException;
import com.hostelMS.model.User;


public interface HostelMSDao {

	
	
		public int registration(User u1) throws GlobalException;
		public User login(String username,String password) throws GlobalException;
		

	}

