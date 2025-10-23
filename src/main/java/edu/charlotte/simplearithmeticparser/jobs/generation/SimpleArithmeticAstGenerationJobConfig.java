package edu.charlotte.simplearithmeticparser.jobs.generation;

import edu.charlotte.simplearithmeticparser.grammars.GenerateAstForSimpleArithmetic;
import edu.charlotte.simplearithmeticparser.listeners.common.JobLoggingListener;
import edu.charlotte.simplearithmeticparser.tree.generation.SimpleArithmeticAstGenerationProcess;
import edu.charlotte.simplearithmeticparser.utils.Constants;
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
public class SimpleArithmeticAstGenerationJobConfig {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;
    private final int chunkSize;

    public SimpleArithmeticAstGenerationJobConfig(
            PlatformTransactionManager transactionManager,
            JobRepository jobRepository,
            @Value("${chunk-size:10}") int chunkSize) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
        this.chunkSize = chunkSize;
        log.info("Initialized SimpleArithmeticAstGenerationJobConfig with chunk size: {}", this.chunkSize);
    }

    @Bean
    @StepScope
    public GenerateAstForSimpleArithmetic generateAstForSimpleArithmetic() {
        log.debug("Creating step-scoped GenerateAstForSimpleArithmetic bean.");
        return new GenerateAstForSimpleArithmetic(Constants.ABSTRACT_SYNTAX_TREE);
    }

    @Bean
    @StepScope
    public SimpleArithmeticAstGenerationProcess simpleArithmeticAstGenerationProcess(GenerateAstForSimpleArithmetic generateAstForSimpleArithmetic) {
        log.debug("Creating step-scoped SimpleArithmeticAstGenerationProcess bean.");
        return new SimpleArithmeticAstGenerationProcess(generateAstForSimpleArithmetic);
    }

    @Bean
    public Step simpleArithmeticAstGenerationStep(ItemReader<String> inputFileReader,
                                                  SimpleArithmeticAstGenerationProcess simpleArithmeticAstGenerationProcess,
                                                  FlatFileItemWriter<String> outputFileWriter) {
        log.info("Configuring simpleArithmeticAstGenerationStep with chunk size: {}", this.chunkSize);
        return new StepBuilder("simpleArithmeticAstGenerationStep", jobRepository)
                .<String, String>chunk(chunkSize, transactionManager)
                .reader(inputFileReader)
                .processor(simpleArithmeticAstGenerationProcess)
                .writer(outputFileWriter)
                .build();
    }

    @Bean
    public Job loadSimpleArithmeticAstGenerationJob(JobRepository jobRepository,
                                                    JobLoggingListener jobLoggingListener,
                                                    Step simpleArithmeticAstGenerationStep) {
        log.debug("Configuring loadSimpleArithmeticAstGenerationJob.");
        return new JobBuilder("loadSimpleArithmeticAstGenerationJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobLoggingListener)
                .start(simpleArithmeticAstGenerationStep)
                .build();
    }
}