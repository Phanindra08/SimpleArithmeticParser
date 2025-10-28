package edu.charlotte.simplearithmeticparser.tree.generation;

import edu.charlotte.simplearithmeticparser.grammars.GenerateAstForSimpleArithmetic;
import edu.charlotte.simplearithmeticparser.listeners.parsetree.SimpleArithmeticAstListener;
import edu.charlotte.simplearithmeticparser.tree.nodes.ParseTreeNode;
import edu.charlotte.simplearithmeticparser.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleArithmeticAstGenerationProcess extends AbstractTreeGenerationProcess<GenerateAstForSimpleArithmetic, SimpleArithmeticAstListener> {

    public SimpleArithmeticAstGenerationProcess(GenerateAstForSimpleArithmetic generatePTForSimpleArithmetic) {
        super(generatePTForSimpleArithmetic, Constants.AST_GENERATION_PROCESS_SUFFIX);
        log.debug("SimpleArithmeticAstGenerationProcess is initialized.");
    }

    @Override
    protected ParseTreeNode getTreeRootFromListener(SimpleArithmeticAstListener listener) {
        return listener.getParseTree();
    }
}