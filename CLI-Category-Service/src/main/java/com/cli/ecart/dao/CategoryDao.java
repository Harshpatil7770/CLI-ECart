package com.cli.ecart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cli.ecart.entity.Category;

/*
 * @Repository means class directly communicate with database.
 * JpaRepository provide by default method such as create,read ,update,delete
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

	@Query(value = "select * from category c where c.category_id=?", nativeQuery = true)
	Category findByCategoryId(long categoryId);

//	@Query(value = "select * from category c where c.categoryId=?", nativeQuery = true)
//	boolean findByCategoryId(long categoryId);

}
