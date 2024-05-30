package com.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.springbatch.entity.CustomerDetails;

import com.springbatch.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class CustomerBatchConfig {
	
//	  @Autowired
//	 JobBuilderFactory jobBuilderFactory;
//	
//	 @Autowired
//	 StepBuilderFactory stepBuilderFactory;
//	
//	 
//	 @Autowired
//	 CustomerRepository customerRepository;
	
	
	private JobBuilderFactory builderFactory;
	
	

	private StepBuilderFactory stepBuilderFactory;
	
	private CustomerRepository customerRepository;
	
	
	public CustomerBatchConfig(JobBuilderFactory builderFactory, StepBuilderFactory stepBuilderFactory,
			CustomerRepository customerRepository) {
		super();
		this.builderFactory = builderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.customerRepository = customerRepository;
	}
	 
   @Bean
	public FlatFileItemReader	<CustomerDetails > reader(){
		
	FlatFileItemReader<CustomerDetails > itemReader = new FlatFileItemReader<>();
	
	itemReader.setResource(new FileSystemResource("src/main/resources/customer.csv"));
	itemReader.setName("csv-reader");
	itemReader.setLinesToSkip(1);
	itemReader.setLineMapper(linemapper());
	return itemReader;
	
	}
	
   
	private LineMapper<CustomerDetails> linemapper(){
		DefaultLineMapper<CustomerDetails>mapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("id","customerId","firstName","lastName","company","city","country","phone1","phone2","subscriptionDetails");
		
		BeanWrapperFieldSetMapper<CustomerDetails> beanWrapperFieldSetMapper =new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(CustomerDetails.class);
		
		mapper.setLineTokenizer(delimitedLineTokenizer);
		mapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return mapper;
	}
	
	
	@Bean
	public CustomerProcessor  procecssor() {
		
		return new CustomerProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<CustomerDetails> writer(){
		RepositoryItemWriter<CustomerDetails> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}
	

	@Bean
	public Step stepOne() {
		
		return stepBuilderFactory.get("csv-step").<CustomerDetails,CustomerDetails>chunk(10)
		  .reader(reader())
		  .processor(procecssor())
		  .writer(writer())
		  .taskExecutor(taskExecutor())
		  .build();
	}
	
	
	
     @SuppressWarnings("deprecation")
	@Bean
	public Job runjob(){
		return builderFactory.get("CustomerDetails")
				.flow(stepOne())
				.end()
				.build();
     }
	
	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
		
	}
	
	

}
