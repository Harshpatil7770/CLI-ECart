package com.cli.ecart.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cli.ecart.dao.CategoryDao;
import com.cli.ecart.dto.CategoryDTO;
import com.cli.ecart.entity.Category;
import com.cli.ecart.entity.STATUS;
import com.cli.ecart.messagebroker.CategoryProducerAndConsumerDetails;

/*
 * @Service is business annotations, we keep all business login in this class.
 * A class implement interface ,so we need to implement all interface methods.
 */
@Component
@Service
public class CategoryServiceImpl implements CategoryService {

	Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryDao categoryDao;

	@Autowired(required = true)
	Category category;

	@Autowired
	private CategoryProducerAndConsumerDetails categoryProducerAndConsumerDetails;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insert(CategoryDTO categoryDTO) {

		if (categoryDTO.getCategoryName() == null || categoryDTO.getCategoryName().isBlank()) {
			logger.info("Category Name is null. No Need to proceed further.");
			return;
		}
		category.setCategoryName(categoryDTO.getCategoryName());
		if (categoryDTO.getCategoryStatus() == null) {
			logger.info("Category status is null. No Need to proceed further.");
			return;
		}
		if (categoryDTO.getCategoryStatus() != null) {
			if (categoryDTO.getCategoryStatus().equals(STATUS.ACTIVE)) {
				category.setCategoryStatus(STATUS.ACTIVE);
				category.setActiveDate(new Date());
			} else {
				category.setCategoryStatus(STATUS.INACTIVE);
				category.setInActiveDate(new Date());
			}
		}
		category.setDescription(categoryDTO.getDescription());
		logger.debug(category.toString());
		categoryProducerAndConsumerDetails.sendMessage(category.toString());
		logger.info("Category Details :: {}", category.toString());
		categoryDao.save(category);
	}

	@Override
	public void update(CategoryDTO categoryDTO) {

		Category existingCategory = categoryDao.findByCategoryId(categoryDTO.getCategoryId());
		if (existingCategory != null) {
			category.setCategoryId(categoryDTO.getCategoryId());
			if (categoryDTO.getCategoryName() == null) {
				category.setCategoryName(existingCategory.getCategoryName());
			}
			if (categoryDTO.getCategoryName() != null) {
				category.setCategoryName(categoryDTO.getCategoryName());
			}
			if (categoryDTO.getDescription() == null) {
				category.setDescription(existingCategory.getDescription());
			}
			if (categoryDTO.getDescription() != null) {
				category.setDescription(categoryDTO.getDescription());
			}
			if (categoryDTO.getCategoryStatus() != null && categoryDTO.getCategoryStatus().equals(STATUS.ACTIVE)) {
				category.setCategoryStatus(categoryDTO.getCategoryStatus());
				category.setActiveDate(new Date());
			}
			if (categoryDTO.getCategoryStatus() != null && categoryDTO.getCategoryStatus().equals(STATUS.INACTIVE)) {
				category.setCategoryStatus(categoryDTO.getCategoryStatus());
				category.setInActiveDate(new Date());
			}
			if (categoryDTO.getCategoryStatus() == null && existingCategory.getCategoryStatus().equals(STATUS.ACTIVE)) {
				category.setCategoryStatus(existingCategory.getCategoryStatus());
				category.setActiveDate(existingCategory.getActiveDate());
			}
			if (categoryDTO.getCategoryStatus() == null && categoryDTO.getCategoryStatus().equals(STATUS.INACTIVE)) {
				category.setCategoryStatus(existingCategory.getCategoryStatus());
				category.setInActiveDate(existingCategory.getInActiveDate());
			}

			logger.debug("Updated Category details {} ", category);
			logger.info("Updated Category details :: {} ", category.toString());
			categoryProducerAndConsumerDetails.sendMessage(category.toString());
			categoryDao.save(category);

		} else {
			logger.info("Entered category details not found in system . No need to proceed further.");
			return;
		}
	}

	@Override
	public void delete(long categoryId) {

		Category existingCategory = categoryDao.findByCategoryId(categoryId);
		if (existingCategory != null) {
			categoryDao.deleteById(categoryId);
			logger.info("Deleted Category Id {} succesfully", categoryId);
		} else {
			logger.info("Entered category Id not present in database. No need to proceed further.");
			return;
		}

	}

	@Override
	public Category findCategoryById(long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCategoryLists(List<CategoryDTO> catLists) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Category> findAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findByStatus(int categoryStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertListsOfCategories(List<CategoryDTO> categoryDTOs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateListsOfCategories(List<CategoryDTO> categoryDTOs) {
		// TODO Auto-generated method stub

	}

}
