package edu.charlotte.simplearithmeticparser.tree.generation;

import edu.charlotte.simplearithmeticparser.tree.nodes.AstNode;
import edu.charlotte.simplearithmeticparser.grammars.GeneratePTForSimpleArithmetic;
import edu.charlotte.simplearithmeticparser.listeners.parsetree.SimpleArithmeticParseTreeListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleArithmeticPTGenerationProcess extends AbstractPTGenerationProcess<GeneratePTForSimpleArithmetic, SimpleArithmeticParseTreeListener> {

    public SimpleArithmeticPTGenerationProcess(GeneratePTForSimpleArithmetic generatePTForSimpleArithmetic) {
        super(generatePTForSimpleArithmetic);
        log.debug("SimpleArithmeticPTGenerationProcess is initialized.");
    }

    @Override
    protected AstNode getPTRootFromListener(SimpleArithmeticParseTreeListener listener) {
        return listener.getParseTree();
    }
}