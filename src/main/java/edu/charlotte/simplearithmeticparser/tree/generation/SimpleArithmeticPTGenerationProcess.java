package edu.charlotte.simplearithmeticparser.tree.generation;

import edu.charlotte.simplearithmeticparser.tree.nodes.ParseTreeNode;
import edu.charlotte.simplearithmeticparser.grammars.GeneratePTForSimpleArithmetic;
import edu.charlotte.simplearithmeticparser.listeners.parsetree.SimpleArithmeticParseTreeListener;
import edu.charlotte.simplearithmeticparser.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleArithmeticPTGenerationProcess extends AbstractTreeGenerationProcess<GeneratePTForSimpleArithmetic, SimpleArithmeticParseTreeListener> {

    public SimpleArithmeticPTGenerationProcess(GeneratePTForSimpleArithmetic generatePTForSimpleArithmetic) {
        super(generatePTForSimpleArithmetic, Constants.PARSE_TREE_GENERATION_PROCESS_SUFFIX);
        log.debug("SimpleArithmeticPTGenerationProcess is initialized.");
    }

    @Override
    protected ParseTreeNode getTreeRootFromListener(SimpleArithmeticParseTreeListener listener) {
        return listener.getParseTree();
    }
}