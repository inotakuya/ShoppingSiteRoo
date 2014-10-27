package jp.com.inotaku.domain;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jp.com.inotaku.service.ConsumerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/batch-csv-context.xml")
public class BatchCsvTest {

	
	  @Autowired private JobLauncher jobLauncher;
	  
	  @Autowired private Job job1;
	  
	  @Autowired ConsumerService consumerService;
	 
	@Test
	public void test() throws InterruptedException {
		
		/*GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/batch-context.xml");
		ctx.refresh();
			
		JobLauncher jobLauncher = ctx.getBean("jobLauncher", JobLauncher.class);

		Job job1 = ctx.getBean("job1", Job.class);*/
		

		Map<String, JobParameter> jobParmMap = new HashMap<String, JobParameter>();
		jobParmMap.put("inputFile", new JobParameter("consumer.csv"));

		try {
			jobLauncher.run(job1, new JobParameters(jobParmMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*for(Consumer consumer:consumerService.findAllConsumers()){
			System.out.println(consumer);
		}*/
		
			System.out.println("終了");
	}
}
