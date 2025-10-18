package edu.charlotte.simplearithmeticparser.listeners.ast;

import edu.charlotte.simplearithmeticparser.SimpleArithmeticBaseListener;
import edu.charlotte.simplearithmeticparser.SimpleArithmeticParser;
import edu.charlotte.simplearithmeticparser.ast.nodes.AstNode;
import edu.charlotte.simplearithmeticparser.utils.AstListenerUtils;
import edu.charlotte.simplearithmeticparser.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

@Slf4j
public class SimpleArithmeticAstListener extends SimpleArithmeticBaseListener {
    private final Stack<AstNode> stack;
    private final Set<String> identifiersSet;

    public SimpleArithmeticAstListener() {
        this.stack = new Stack<>();
        this.identifiersSet = new HashSet<>();
        log.debug("SimpleArithmeticAstListener initialized.");
    }

    // DL Program (root of the file)
    @Override
    public void enterSimpleArithmeticProgram(SimpleArithmeticParser.SimpleArithmeticProgramContext ctx) {
        log.debug("Entering SimpleArithmetic-Program rule: {}.", ctx.getText());
        stack.push(new AstNode(Constants.AST_NODE_SIMPLE_ARITHMETIC_PROGRAM));
    }

    @Override
    public void exitSimpleArithmeticProgram(SimpleArithmeticParser.SimpleArithmeticProgramContext ctx) {
        log.debug("Exiting SimpleArithmetic-Program rule: {}.", ctx.getText());
        List<AstNode> childNodes = AstListenerUtils.exitGrammarRule(ctx, stack);
        AstListenerUtils.addChildrenToLastNodeInStack(childNodes, Constants.AST_NODE_SIMPLE_ARITHMETIC_PROGRAM, ctx.getText(), stack);
    }

    // Formula Handling
    @Override
    public void enterExpr(SimpleArithmeticParser.ExprContext ctx) {
        log.debug("Entering Expression rule: {}.", ctx.getText());
        stack.push(new AstNode(Constants.AST_NODE_SIMPLE_ARITHMETIC_EXPRESSION));
    }

    @Override
    public void exitExpr(SimpleArithmeticParser.ExprContext ctx) {
        log.debug("Exiting Expression rule: {}.", ctx.getText());
        List<AstNode> childNodes = AstListenerUtils.exitGrammarRule(ctx, stack);
        AstListenerUtils.addChildrenToLastNodeInStack(childNodes, Constants.AST_NODE_SIMPLE_ARITHMETIC_EXPRESSION, ctx.getText(), stack);
    }

    // Program Handling
    @Override
    public void enterTerm(SimpleArithmeticParser.TermContext ctx) {
        log.debug("Entering term rule: {}.", ctx.getText());
        stack.push(new AstNode(Constants.AST_NODE_SIMPLE_ARITHMETIC_TERM));
    }

    @Override
    public void exitTerm(SimpleArithmeticParser.TermContext ctx) {
        log.debug("Exiting term rule: {}", ctx.getText());
        List<AstNode> childNodes = AstListenerUtils.exitGrammarRule(ctx, stack);
        AstListenerUtils.addChildrenToLastNodeInStack(childNodes, Constants.AST_NODE_SIMPLE_ARITHMETIC_TERM, ctx.getText(), stack);
    }

    @Override
    public void enterFactor(SimpleArithmeticParser.FactorContext ctx) {
        log.debug("Entering Factor rule: {}", ctx.getText());
        stack.push(new AstNode(Constants.AST_NODE_SIMPLE_ARITHMETIC_FACTOR));
    }

    @Override
    public void exitFactor(SimpleArithmeticParser.FactorContext ctx) {
        log.debug("Exiting Factor rule: {}", ctx.getText());
        List<AstNode> childNodes = AstListenerUtils.exitGrammarRule(ctx, stack);
        AstListenerUtils.addChildrenToLastNodeInStack(childNodes, Constants.AST_NODE_SIMPLE_ARITHMETIC_FACTOR, ctx.getText(), stack);
    }

    @Override
    public void enterNumber(SimpleArithmeticParser.NumberContext ctx) {
        log.debug("Entering Number rule: {}", ctx.getText());
        stack.push(new AstNode(Constants.AST_NODE_SIMPLE_ARITHMETIC_NUMBER));
    }

    @Override
    public void exitNumber(SimpleArithmeticParser.NumberContext ctx) {
        log.debug("Exiting Number rule: {}", ctx.getText());
        List<AstNode> childNodes = AstListenerUtils.exitGrammarRule(ctx, stack);
        AstListenerUtils.addChildrenToLastNodeInStack(childNodes, Constants.AST_NODE_SIMPLE_ARITHMETIC_NUMBER, ctx.getText(), stack);
    }

    @Override
    public void enterDigit(SimpleArithmeticParser.DigitContext ctx) {
        log.debug("Entering Digit rule: {}", ctx.getText());
        stack.push(new AstNode(Constants.AST_NODE_SIMPLE_ARITHMETIC_DIGIT));
    }

    @Override
    public void exitDigit(SimpleArithmeticParser.DigitContext ctx) {
        log.debug("Exiting Digit rule: {}", ctx.getText());
        List<AstNode> childNodes = AstListenerUtils.exitGrammarRule(ctx, stack);
        AstListenerUtils.addChildrenToLastNodeInStack(childNodes, Constants.AST_NODE_SIMPLE_ARITHMETIC_DIGIT, ctx.getText(), stack);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        log.debug("Visiting terminal: '{}'", node.getText());
        stack.push(new AstNode(node.getText()));
    }

    // Return the final Ast root node
    public AstNode getAst() {
        if(stack.size() > 1)
            log.warn("Stack contains more than one element after AST generation. There might be a possible issue in listener logic. " +
                    "Stack size is: {} and the contents are: {}", stack.size(), stack);
        return stack.isEmpty() ? null : stack.pop();
    }

    // Return the final Set of Identifiers
    public Set<String> getIdentifiers() {
        return Collections.unmodifiableSet(this.identifiersSet);
    }
}