package edu.charlotte.simplearithmeticparser.listeners.parsetree;

import edu.charlotte.simplearithmeticparser.SimpleArithmeticBaseListener;
import edu.charlotte.simplearithmeticparser.SimpleArithmeticParser;
import edu.charlotte.simplearithmeticparser.tree.nodes.ParseTreeNode;
import edu.charlotte.simplearithmeticparser.utils.AstListenerUtils;
import edu.charlotte.simplearithmeticparser.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

@Slf4j
public class SimpleArithmeticAstListener extends SimpleArithmeticBaseListener {
    private final Stack<ParseTreeNode> stack;
    private static final List<String> EXCLUDED_LIST_OF_AST_NODES = new ArrayList<>();

    static {
        EXCLUDED_LIST_OF_AST_NODES.add(Constants.OPEN_BRACKETS);
        EXCLUDED_LIST_OF_AST_NODES.add(Constants.CLOSE_BRACKETS);
        EXCLUDED_LIST_OF_AST_NODES.add(Constants.END_OF_FILE);
        log.info("SimpleArithmeticAstListener static list is initialized with {} entries.", EXCLUDED_LIST_OF_AST_NODES.size());
    }

    public SimpleArithmeticAstListener() {
        this.stack = new Stack<>();
        log.debug("SimpleArithmeticAstListener initialized.");
    }

    // Expression Handling
    @Override
    public void exitExpr(SimpleArithmeticParser.ExprContext ctx) {
        log.debug("Exiting Expression rule: {}.", ctx.getText());
        if(ctx.ADDITION() != null) {
            ParseTreeNode node = AstListenerUtils.exitGrammarRule(ctx, stack);
            stack.push(node);
        }
    }

    // Term Handling
    @Override
    public void exitTerm(SimpleArithmeticParser.TermContext ctx) {
        log.debug("Exiting term rule: {}", ctx.getText());
        if(ctx.MULTIPLICATION() != null) {
            ParseTreeNode node = AstListenerUtils.exitGrammarRule(ctx, stack);
            stack.push(node);
        }
    }

    @Override
    public void exitNumber(SimpleArithmeticParser.NumberContext ctx) {
        int length = ctx.getText().length();
        log.debug("Exiting Number rule: {}", ctx.getText());
        if(length > 1) {
            stack.pop();
            stack.pop();
            stack.push(new ParseTreeNode(ctx.getText()));
        }
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        log.debug("Visiting terminal: '{}'", node.getText());
        if(!EXCLUDED_LIST_OF_AST_NODES.contains(node.getText()))
            stack.push(new ParseTreeNode(node.getText()));
    }

    // Return the final Parse Tree root node
    public ParseTreeNode getParseTree() {
        if(stack.size() > 1)
            log.warn("Stack contains more than one element after Parse Tree generation. There might be a possible issue in listener logic. " +
                    "Stack size is: {} and the contents are: {}", stack.size(), stack);
        return stack.isEmpty() ? null : stack.pop();
    }
}