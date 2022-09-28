
//room model to assign one room to multiple students
package com.hostelMS.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//using getter and setter annotation from lombok
@Entity
@Getter
@Setter
@ToString
public class Room  {
	
	@Id
	private int roomId;
	private String roomName;
	private String roomType;
	
}
