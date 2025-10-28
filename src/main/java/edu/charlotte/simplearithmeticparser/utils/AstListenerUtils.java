package edu.charlotte.simplearithmeticparser.utils;

import edu.charlotte.simplearithmeticparser.tree.nodes.ParseTreeNode;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.*;

@Slf4j
public class AstListenerUtils {
    private AstListenerUtils() {}
    public static ParseTreeNode exitGrammarRule(ParserRuleContext ctx, Stack<ParseTreeNode> stack) {
        int expectedChildrenCount = ctx.getChildCount();
        if (stack.size() < expectedChildrenCount) {
            log.error("Stack underflow: Expected {} children for the rule '{}', but the stack has only {} elements.",
                    expectedChildrenCount, ctx.getText(), stack.size());
            throw new IllegalStateException("Critical Parse Tree construction error for the rule: " + ctx.getText());
        }

        ParseTreeNode rightChild = stack.pop();
        ParseTreeNode parentNode = stack.pop();
        ParseTreeNode leftChild = stack.pop();

        parentNode.addChildren(new ArrayList<>(Arrays.asList(leftChild, rightChild)));

        log.debug("Popped all the {} children from stack for rule '{}'.", expectedChildrenCount, ctx.getText());
        return parentNode;
    }

    public static void addChildrenToLastNodeInStack(List<ParseTreeNode> childNodes, String grammarNodeName, String contextText, Stack<ParseTreeNode> stack) {
        if (childNodes == null) {
            log.warn("Attempted to add a null list as children to the node on top of the stack for the rule '{}' (context: '{}')",
                    grammarNodeName, contextText);
            return;
        }

        if (!stack.isEmpty()) {
            stack.peek().addChildren(childNodes);
            log.debug("Added {} children to the node on top of the stack for the rule '{}' (context: '{}').",
                    childNodes.size(), grammarNodeName, contextText);
        } else {
            log.error("Stack is unexpectedly empty when exiting the rule {} for the context: {}, indicating a critical logic error.", grammarNodeName, contextText);
            throw new IllegalStateException("Cannot add children as the Stack is empty for the rule: " + grammarNodeName);
        }
    }
}