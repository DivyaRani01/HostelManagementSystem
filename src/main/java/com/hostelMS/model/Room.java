
//room model to assign one room to multiple students
package com.hostelMS.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//using data annotation from lombok
@Entity
@Data
public class Room  {
	
	@Id
	private int roomId;
	private String roomName;
	private String roomType;
	
	}
