package jp.com.inotaku.domain;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jp.com.inotaku.repository.ConsumerRepository;
import jp.com.inotaku.service.ConsumerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/META-INF/spring/applicationContext.xml","file:src/main/resources/META-INF/spring/applicationContext-jpa.xml"})
public class ConsumerTest {
	
	@Autowired
	ConsumerRepository consumerRepository;
	
	@PersistenceContext
	EntityManager em;

	
	    @Test
	    @Transactional
	    public void addConsumer() {
	     Consumer c = new Consumer();
	     c.setConsumerName("test");
	     c.setPassword("abc");
	    /* c.setEmail("a@a");*/
	     em.persist(c);
	     em.flush();
	     em.clear();
	     
	     
	     System.out.println(c);
	     
	     assertNotNull(c.getId());
	    
	}
	   
}
