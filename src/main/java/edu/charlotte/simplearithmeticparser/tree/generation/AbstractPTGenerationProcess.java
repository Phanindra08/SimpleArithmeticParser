package edu.charlotte.simplearithmeticparser.tree.generation;

import edu.charlotte.simplearithmeticparser.tree.nodes.AstNode;
import edu.charlotte.simplearithmeticparser.grammars.AbstractPTGenerator;
import edu.charlotte.simplearithmeticparser.utils.Constants;
import edu.charlotte.simplearithmeticparser.utils.ParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

@StepScope
@Slf4j
public abstract class AbstractPTGenerationProcess<TGenerator extends AbstractPTGenerator<?, ?, TListener>,
        TListener extends ParseTreeListener> implements ItemProcessor<String, String>, StepExecutionListener {

    private final TGenerator parseTreeGenerator;
    private final String processorName;
    public AbstractPTGenerationProcess(TGenerator parseTreeGenerator) {
        this.parseTreeGenerator = parseTreeGenerator;
        this.processorName = this.parseTreeGenerator.getTypeName();
        log.info("'{}' is initialized.", getDisplayName());
    }

    // Abstract methods to be implemented by subclasses
    protected abstract AstNode getPTRootFromListener(TListener listener);

    private String getDisplayName() {
        return this.processorName + Constants.PARSE_TREE_GENERATION_PROCESS_SUFFIX;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Before step for the '{}'. Step Name is '{}'.", getDisplayName(), stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("After step for the '{}'. Step Name is '{}', Status is '{}'.",
                getDisplayName(), stepExecution.getStepName(), stepExecution.getExitStatus().getExitCode());
        return stepExecution.getExitStatus();
    }

    @Override
    public String process(@NonNull String item) {
        log.debug("Processing the input item: {}.", ParserUtils.formatInputForLogging(item));
        StringBuilder outputTree = new StringBuilder("Generated Parse Tree is:").append("\n");
        String errorMessage = this.parseTreeGenerator.generateParseTreeFromInput(item);
        try {
            if(errorMessage == null) {
                TListener listener = this.parseTreeGenerator.getListener();
                AstNode parseTreeRoot = getPTRootFromListener(listener);
                if(parseTreeRoot != null) {
                    parseTreeRoot.generateTree("", true, outputTree);
                    log.debug("Parse Tree is generated successfully for the {}.", this.processorName);
                } else {
                    String nullPTError = "Parse Tree generation completed without any explicit errors, but returned a null Parse Tree root.";
                    log.error("{}", nullPTError);
                    return nullPTError;
                }
            } else
                return errorMessage;
        } catch (Exception e) {
            log.error("Error during Parse Tree generation for the item: {}. The Error is: {}",
                    ParserUtils.formatInputForLogging(item), e.getMessage(), e);
            throw new RuntimeException("Error during Parse Tree generation due to internal error.", e);
        }
        return outputTree.toString();
    }
}