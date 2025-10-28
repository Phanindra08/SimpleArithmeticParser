package edu.charlotte.simplearithmeticparser.tree.generation;

import edu.charlotte.simplearithmeticparser.tree.nodes.ParseTreeNode;
import edu.charlotte.simplearithmeticparser.grammars.AbstractTreeGenerator;
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
public abstract class AbstractTreeGenerationProcess<TGenerator extends AbstractTreeGenerator<?, ?, TListener>,
        TListener extends ParseTreeListener> implements ItemProcessor<String, String>, StepExecutionListener {

    private final TGenerator treeGenerator;
    private final String processorName;
    private final String treeGenerationSuffix;
    public AbstractTreeGenerationProcess(TGenerator treeGenerator, String treeGenerationSuffix) {
        this.treeGenerator = treeGenerator;
        this.processorName = this.treeGenerator.getTypeName();
        this.treeGenerationSuffix = treeGenerationSuffix;
        log.info("'{}' is initialized.", getDisplayName());
    }

    // Abstract methods to be implemented by subclasses
    protected abstract ParseTreeNode getTreeRootFromListener(TListener listener);

    private String getDisplayName() {
        return this.processorName + this.treeGenerationSuffix;
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
        StringBuilder outputTree = new StringBuilder("Generated ").append(this.treeGenerator.getTreeType()).append(" is:").append("\n");
        String errorMessage = this.treeGenerator.generateTreeFromInput(item);
        try {
            if(errorMessage == null) {
                TListener listener = this.treeGenerator.getListener();
                ParseTreeNode treeRoot = getTreeRootFromListener(listener);
                if(treeRoot != null) {
                    treeRoot.generateTree("", true, outputTree);
                    log.debug("{} is generated successfully for the {}.", this.treeGenerator.getTreeType(), this.processorName);
                } else {
                    String nullPTError = this.treeGenerator.getTreeType() +
                            " generation completed without any explicit errors, but returned a null " +
                            this.treeGenerator.getTreeType() + " root.";
                    log.error("{}", nullPTError);
                    return nullPTError;
                }
            } else
                return errorMessage;
        } catch (Exception e) {
            log.error("Error during {} generation for the item: {}. The Error is: {}",
                    this.treeGenerator.getTreeType(), ParserUtils.formatInputForLogging(item), e.getMessage(), e);
            throw new RuntimeException("Error during " + this.treeGenerator.getTreeType() + " generation due to internal error.", e);
        }
        return outputTree.toString();
    }
}