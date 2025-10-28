package edu.charlotte.simplearithmeticparser.grammars;

import edu.charlotte.simplearithmeticparser.SimpleArithmeticLexer;
import edu.charlotte.simplearithmeticparser.SimpleArithmeticParser;
import edu.charlotte.simplearithmeticparser.listeners.parsetree.SimpleArithmeticAstListener;
import edu.charlotte.simplearithmeticparser.utils.Constants;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class GenerateAstForSimpleArithmetic extends AbstractTreeGenerator<SimpleArithmeticLexer,
        SimpleArithmeticParser, SimpleArithmeticAstListener> {

    public GenerateAstForSimpleArithmetic(String treeType) {
        super(treeType);
    }

    @Override
    protected SimpleArithmeticLexer createLexerInstance(CharStream input) {
        return new SimpleArithmeticLexer(input);
    }

    @Override
    protected SimpleArithmeticParser createParserInstance(CommonTokenStream tokens) {
        return new SimpleArithmeticParser(tokens);
    }

    @Override
    protected ParseTree invokeTopLevelParseRule(SimpleArithmeticParser parser) {
        return parser.simpleArithmeticProgram();
    }

    @Override
    protected SimpleArithmeticAstListener createTreeListenerInstance() {
        return new SimpleArithmeticAstListener();
    }

    @Override
    public String getTypeName() {
        return Constants.SIMPLE_ARITHMETIC;
    }
}