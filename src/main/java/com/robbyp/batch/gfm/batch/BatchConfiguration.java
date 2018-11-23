package com.robbyp.batch.gfm.batch;

import com.robbyp.batch.gfm.model.EnrichedFail;
import com.robbyp.batch.gfm.model.Fail;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public EntityManagerFactory entityManagerFactory;

    @Bean
    public JpaPagingItemReader<Fail> reader() {
        return new JpaPagingItemReaderBuilder<Fail>()
                .name("failItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select f from Fail f")
                .pageSize(200)
                .build();
    }

    @Bean
    public FailProcessor processor() {
        return new FailProcessor();
    }

    @Bean
    public JpaItemWriter<EnrichedFail> writer() {
        return new JpaItemWriterBuilder<EnrichedFail>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Job processFailJob(Step enrichStep) {
        return jobBuilderFactory.get("processFailJob")
                .incrementer(new RunIdIncrementer())
                .flow(enrichStep)
                .end()
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Step enrichStep(JpaItemWriter<EnrichedFail> writer, TaskExecutor taskExecutor) {
        return stepBuilderFactory.get("enrichStep")
                .<Fail, EnrichedFail>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .taskExecutor(taskExecutor)
                .build();
    }
}