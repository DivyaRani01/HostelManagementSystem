//Creating a model with the name of User
package com.hostelMS.model;
//importing required packages
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;



@Entity
@Data
public class User {
	//usimg validation constraints checking the correctness of the data entered by the user.
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@NotNull
	@Size(min=3,max=15,message="Username must be more than 3 characters and upto 15characters")
	private String userName;
	@NotNull
	@Pattern(regexp="[0-9]{10}",message="Phone number must contain 10 Digits")
	private String userPhone;
	@NotNull
	@Size(min=5,max=20,message="Password should be more than 5 chars")
	@NotNull
	private String userPassword;
	@NotNull
	@Size(min=4,max=20,message="Address must be more than 2 chars and one word only")
	private String userAddress;
	private String userRole;
	private int userFee;
	//many to one mapping
	@ManyToOne
	private Room userRoom;
	

}
