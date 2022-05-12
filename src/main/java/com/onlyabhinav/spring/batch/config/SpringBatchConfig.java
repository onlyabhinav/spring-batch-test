package com.onlyabhinav.spring.batch.config;

import com.onlyabhinav.spring.batch.entity.Estimate;
import com.onlyabhinav.spring.batch.repository.EstimateRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private EstimateRepository estimateRepository;


    @Bean
    public FlatFileItemReader<Estimate> reader() {
        FlatFileItemReader<Estimate> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/estimate.csv"));
        itemReader.setName("csv_reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Estimate> lineMapper() {
        DefaultLineMapper<Estimate> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);

        lineTokenizer.setNames("fsymId", "feItem", "feFpEndDate", "consStartDate", "consEndDate", "currency",
                "fePerRel", "adjDate", "feMean", "feMedian", "feNumEst", "feLow", "feHigh", "feStdDev", "feUp",
                "feDown");

        EstimateFieldSetMapper fieldSetMapper = new EstimateFieldSetMapper();

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public EstimateProcessor processor() {
        return new EstimateProcessor();
    }

    @Bean
    public RepositoryItemWriter<Estimate> writer() {
        RepositoryItemWriter<Estimate> writer = new RepositoryItemWriter<>();
        writer.setRepository(estimateRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<Estimate, Estimate>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("load_estimate")
                .flow(step1()).end().build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(4);
        return asyncTaskExecutor;
    }

}
