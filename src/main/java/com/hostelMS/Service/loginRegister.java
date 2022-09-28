package com.hostelMS.Service;

import com.hostelMS.Exception.GlobalException;

public interface loginRegister {
	
	public void register()throws GlobalException;
	public void login() throws GlobalException;

}
