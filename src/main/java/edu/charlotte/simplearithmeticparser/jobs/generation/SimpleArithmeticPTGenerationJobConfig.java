package edu.charlotte.simplearithmeticparser.jobs.generation;

import edu.charlotte.simplearithmeticparser.tree.generation.SimpleArithmeticPTGenerationProcess;
import edu.charlotte.simplearithmeticparser.grammars.GeneratePTForSimpleArithmetic;
import edu.charlotte.simplearithmeticparser.listeners.common.JobLoggingListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class SimpleArithmeticPTGenerationJobConfig {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    private final int chunkSize;

    public SimpleArithmeticPTGenerationJobConfig(
            PlatformTransactionManager transactionManager,
            JobRepository jobRepository,
            @Value("${chunk-size:10}") int chunkSize) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
        this.chunkSize = chunkSize;
        log.info("Initialized SimpleArithmeticPTGenerationJobConfig with chunk size: {}", this.chunkSize);
    }

    @Bean
    @StepScope
    public GeneratePTForSimpleArithmetic generatePTForSimpleArithmetic() {
        log.debug("Creating step-scoped GeneratePTForSimpleArithmetic bean.");
        return new GeneratePTForSimpleArithmetic();
    }

    @Bean
    @StepScope
    public SimpleArithmeticPTGenerationProcess simpleArithmeticPTGenerationProcess(GeneratePTForSimpleArithmetic generatePTForSimpleArithmetic) {
        log.debug("Creating step-scoped SimpleArithmeticPTGenerationProcess bean.");
        return new SimpleArithmeticPTGenerationProcess(generatePTForSimpleArithmetic);
    }

    @Bean
    public Step simpleArithmeticPTGenerationStep(ItemReader<String> inputFileReader,
                                                 SimpleArithmeticPTGenerationProcess simpleArithmeticPTGenerationProcess,
                                                 FlatFileItemWriter<String> outputFileWriter) {
        log.info("Configuring simpleArithmeticPTGenerationStep with chunk size: {}", this.chunkSize);
        return new StepBuilder("simpleArithmeticPTGenerationStep", jobRepository)
                .<String, String>chunk(chunkSize, transactionManager)
                .reader(inputFileReader)
                .processor(simpleArithmeticPTGenerationProcess)
                .writer(outputFileWriter)
                .build();
    }

    @Bean
    public JobLoggingListener jobLoggingListener() {
        return new JobLoggingListener();
    }

    @Bean
    public Job loadSimpleArithmeticPTGenerationJob(JobRepository jobRepository,
                                                   JobLoggingListener jobLoggingListener,
                                                   Step simpleArithmeticPTGenerationStep) {
        log.debug("Configuring loadSimpleArithmeticPTGenerationJob.");
        return new JobBuilder("loadSimpleArithmeticPTGenerationJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobLoggingListener)
                .start(simpleArithmeticPTGenerationStep)
                .build();
    }
}