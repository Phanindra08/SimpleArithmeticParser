package edu.charlotte.simplearithmeticparser.ast.generation;

import edu.charlotte.simplearithmeticparser.ast.nodes.AstNode;
import edu.charlotte.simplearithmeticparser.grammars.GenerateAstForSimpleArithmetic;
import edu.charlotte.simplearithmeticparser.listeners.ast.SimpleArithmeticAstListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleArithmeticAstGenerationProcess extends AbstractAstGenerationProcess<GenerateAstForSimpleArithmetic, SimpleArithmeticAstListener> {

    public SimpleArithmeticAstGenerationProcess(GenerateAstForSimpleArithmetic generateAstForSimpleArithmetic) {
        super(generateAstForSimpleArithmetic);
        log.debug("SimpleArithmeticAstGenerationProcess is initialized.");
    }

    @Override
    protected AstNode getAstRootFromListener(SimpleArithmeticAstListener listener) {
        return listener.getAst();
    }
}