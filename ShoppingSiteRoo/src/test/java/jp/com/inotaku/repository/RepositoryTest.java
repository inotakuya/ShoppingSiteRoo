package jp.com.inotaku.repository;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.repository.ConsumerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:META-INF/spring/applicationContext*.xml")
public class RepositoryTest {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	private Consumer consumer1;
	private Consumer consumer2;
	
	@Before
	public void init(){
		consumer1 = new Consumer("abc", "aaa", "a@a", 100);
		consumer2 = new Consumer("xyz", "bbb", "b@b", 500);
		consumerRepository.save(consumer1);
		consumerRepository.save(consumer2);
	}
	
	@After
	public void After(){
		consumerRepository.deleteAll();
	}
	
	@Test
	public void findAllTest() {
		
		List<Consumer> consumerList = consumerRepository.findAll();
		System.out.println(consumerList);
		assertThat("abc",is(consumerList.get(0).getConsumerName()));
		assertThat("aaa", is(consumerList.get(0).getPassword()));
		
	}
	
	@Test
	public void findOneTest(){
		Consumer newConsumer = consumerRepository.findOne(2L);
		assertThat("xyz", is(newConsumer.getConsumerName()));
	}
	
	@Test
	public void updateTest(){
		Consumer consumer3 = consumerRepository.findAll().get(0);
		consumer3.setConsumerName("cde");
		consumerRepository.save(consumer3);
		Consumer newCnsuConsumer = consumerRepository.findOne(consumer3.getId());
		assertThat("cde", is(newCnsuConsumer.getConsumerName()));
	}

}
