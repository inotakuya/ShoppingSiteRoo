package jp.com.inotaku;

import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.service.ConsumerService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component(value="consumerWriter")
/*@Transactional(propagation=Propagation.REQUIRED)*/
public class ConsumerWriter {

	@Autowired
	private ConsumerService consumerService;
	
	private static final Log log = LogFactory.getLog(ConsumerWriter.class);
	
	public void write(Consumer consumer){
		consumerService.saveConsumer(consumer);
	}
}
