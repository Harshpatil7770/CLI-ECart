package com.cli.ecart.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cli.ecart.dto.CategoryDTO;
import com.cli.ecart.service.CategoryService;

/*
 * @RestCOntroller is combination of @Controller and @ResponseBody so we don't need to right separate @ResponseBody annotations.
 * @RequestMapping is used to map the web requests
 * @CrossOrigin with the help of this annotation we connect our application to UI
 * 
 */
@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoryResource {

	/*
	 * Logger used from log4j, We can use info,debug,error logs. Logger info
	 * basically gives us info upto this point our code get executed properly.
	 * logger.error gives us info that is something is happens wrong or some error
	 * gets occurred. debug logs give us input related info.
	 */
	private static final Logger logger = LogManager.getLogger(CategoryResource.class);

	/*
	 * @Autowired annotations is used to create the instance of bean, It inject bean
	 * automatically
	 */

	@Autowired
	private CategoryService categoryService;

	/*
	 * @PostMapping used to create an resource.
	 * 
	 * @RequestBody is used to send the data in the form of Json.
	 * 
	 * Rule for rest api- A) use noun B) follow camelcase C) don't use special
	 * character expect hyphen and forward slash. d) separate out words with the
	 * help for hyphen not a underscore (it makes more clear what we are doing here)
	 * e) avoid file extension.
	 */

	@PostMapping("/save")
	public void insert(@RequestBody CategoryDTO categoryDTO) {
		long startTime = System.currentTimeMillis();
		logger.info("started category insert process....");
		categoryService.insert(categoryDTO);
		logger.info("Total time required to insert category process is : {} s",
				((System.currentTimeMillis() - startTime) / 1000) % 60);
	}

	/*
	 * @PutMapping is used to update the resource. or to update complete data huge
	 * filed we go for Putmapping.
	 * 
	 * @RequestBody is used to send the data in the form of json
	 */

	@PutMapping("/update")
	public void update(@RequestBody CategoryDTO categoryDTO) {
		long startTime = System.currentTimeMillis();
		categoryService.update(categoryDTO);
		logger.info("Total time required to perform category update operation is : {} s",
				((System.currentTimeMillis() - startTime) / 1000) % 60);

	}

	@DeleteMapping("/delete/{categoryId}")
	public void delete(@PathVariable long categoryId) {
		long startTime = System.currentTimeMillis();
		categoryService.delete(categoryId);
		logger.info("Total time required to perform category delete operation is : {} s",
				((System.currentTimeMillis() - startTime) / 1000) % 60);
	}
}
