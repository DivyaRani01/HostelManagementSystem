//A interface having various abstract methods that has to be performed by the Admin 
package com.hostelMS.Service;

//creating a interface with the name of Admindashboard
public interface AdminDashBoard {
	
	public void dashboard();
	public void fetchAllRooms();
	public void fetchAllUsers();
	public void createRoom();
	public void allotRoom();
	public void deleteUser();
	public void userInARoom();
	public void addDueAmount();
	public void depositDueAmount();
	public void viewUserProfile();

	

}
