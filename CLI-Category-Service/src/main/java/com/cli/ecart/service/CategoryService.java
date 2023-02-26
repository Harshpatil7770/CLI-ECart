package com.cli.ecart.service;

import java.util.List;

import com.cli.ecart.dto.CategoryDTO;
import com.cli.ecart.entity.Category;

public interface CategoryService {

	public void insert(CategoryDTO categoryDTO);

	public void update(CategoryDTO categoryDTO);

	public void delete(long categoryId);

	public Category findCategoryById(long categoryId);

	public void addCategoryLists(List<CategoryDTO> catLists);

	public List<Category> findAllCategories();

	public List<Category> findByStatus(int categoryStatus);

	public void insertListsOfCategories(List<CategoryDTO> categoryDTOs);

	public void updateListsOfCategories(List<CategoryDTO> categoryDTOs);

}
