package com.cli.ecart.dto;



import java.util.Date;

import com.cli.ecart.entity.STATUS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO {

	private long categoryId;

	private String categoryName;

	private String description;

	private STATUS categoryStatus;

	private Date activeDate;

	private Date inActiveDate;
}
