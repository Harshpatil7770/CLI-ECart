package com.cli.ecart.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Entity - annotation is used to mark a class is java bean
 * @Id - Is used to set primary key.
 * @Entity and @Id annotations is compulsory in Jpa.
 * @Data,@@AllArgsConstructor,@NoArgsConstructor is used to reduce the boiler plate so we 
 *        don't need to write manually setter-getter,constructor and no-argument constructor.
 * @Table annotations is used to set name of table in db ,otherwise it will take by default class
 *        name as db name 
 */
@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	/*
	 * @Id - is used to set primary key in table.
	 * 
	 * @GeneratedValue is used to set primary key statergy.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;

	private String categoryName;

	private String description;

	private STATUS categoryStatus;

	/*
	 * @Column - annotations is used set column name in table.
	 */

	@Column(name = "ACTIVE_DATE")
	private Date activeDate;

	@Column(name = "INACTIVE_DATE")
	private Date inActiveDate;

}
