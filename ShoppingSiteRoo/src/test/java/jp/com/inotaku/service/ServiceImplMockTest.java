package jp.com.inotaku.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import jp.com.inotaku.domain.Consumer;
import jp.com.inotaku.repository.ConsumerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImplMockTest {

	@Mock
	private ConsumerRepository consumerRepository;
	
	@InjectMocks
	private ConsumerService ConsumerService = new ConsumerServiceImpl(); 
	
	
	private Consumer consumer1;
	private Consumer consumer2;
	private List<Consumer> consumerList = new ArrayList<Consumer>();
	
	@Before
	public void init(){
		consumer1 = new Consumer("abc", "aaa", "a@a", 100);
		consumer2 = new Consumer("xyz", "bbb", "b@b", 500);
		consumerList.add(consumer1);
		consumerList.add(consumer2);
	}
	
	@Test
	public void findAllTest() {
		doReturn(consumerList).when(consumerRepository).findAll();
		List<Consumer> newConsumerList = ConsumerService.findAllConsumers();
		assertThat("a@a", is(newConsumerList.get(0).getEmail()));
		verify(consumerRepository).findAll();
	}

	@Test
	public void findOneTest(){
		when(consumerRepository.findOne(anyLong())).thenReturn(null);
		doReturn(consumerList.get(0)).when(consumerRepository).findOne(1L);
		Consumer newConsumer1 = ConsumerService.findConsumer(1L);
		Consumer newConsumer2 = ConsumerService.findConsumer(2L);
		assertThat("abc", is(newConsumer1.getConsumerName()));
		assertThat(newConsumer2, nullValue());
		verify(consumerRepository).findOne(1L);
	}
	
	@Test
	public void saveTest(){
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocationOnMock){
				Consumer consumer = (Consumer)invocationOnMock.getArguments()[0];
				consumerList.add(consumer);
				return null;
			}
		})
		.when(consumerRepository).save((Consumer)anyObject());
		Consumer newConsumer = new Consumer("new", "new", "new@new", 300);
		ConsumerService.saveConsumer(newConsumer);
		assertThat("new", is(consumerList.get(2).getConsumerName()));
		verify(consumerRepository).save(newConsumer);
	}
}
