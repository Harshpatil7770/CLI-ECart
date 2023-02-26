package com.cli.ecart.messagebroker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.cli.ecart.util.TOPIC;

/*
 * @Component is a class level annotations. It is used to mark a class is a java bean.
 */
@Component
public class CategoryProducerAndConsumerDetails {

	/*
	 * static final means constant
	 */
	private static final Logger logger = LogManager.getLogger(CategoryProducerAndConsumerDetails.class);

	/*
	 * @Autowired annotations is used to create instance of bean. It inject bean
	 * automatically.
	 * 
	 */

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(final String msg) {
		long sendingTime = System.currentTimeMillis();
		logger.info("sending messsage to queue.....");
		kafkaTemplate.send(TOPIC.CATEGORY_TOPIC, msg);
		logger.info("message sent succesfully..!!!\n Time required to put message over the queue is : {} s",
				((System.currentTimeMillis() - sendingTime) / 1000) % 60);
	}
}
