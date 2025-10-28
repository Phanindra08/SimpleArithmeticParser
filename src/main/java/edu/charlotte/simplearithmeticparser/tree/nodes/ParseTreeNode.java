package edu.charlotte.simplearithmeticparser.tree.nodes;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Define the ParseTreeNode class
@Getter
@Slf4j
public class ParseTreeNode {
    @Setter
    private String value;
    private final List<ParseTreeNode> children;

    public ParseTreeNode() {
        this.value = null;
        this.children = new ArrayList<>();
    }

    public ParseTreeNode(String value) {
        this.value = Objects.requireNonNull(value, "ParseTreeNode value cannot be null upon construction.");
        this.children = new ArrayList<>();
    }

    public ParseTreeNode(String value, List<ParseTreeNode> children) {
        this.value = Objects.requireNonNull(value, "ParseTreeNode value cannot be null upon construction.");
        this.children = Objects.requireNonNull(children, "ParseTreeNode children cannot be null upon construction.");
    }

    public void addChildren(List<ParseTreeNode> childrenNodes) {
        Objects.requireNonNull(childrenNodes, "List of children nodes to be added cannot be null.");
        this.children.addAll(childrenNodes);
        log.debug("Added '{}' children to the node '{}'", childrenNodes.size(), this.value);
    }

    // Generating the ParseTree in a tree structure
    public void generateTree(String indent, boolean isLast, StringBuilder outputTree) {
        String connector = isLast ? "└── " : "├── "; // Decide whether to use └── (last child) or ├── (middle child) for the current node.
        outputTree.append(indent).append(connector).append(value).append("\n");
        log.debug("Processed Parse Tree node: '{}'", value);
        String childIndent = indent + (isLast ? "    " : "│   "); // Determine new indentation for child nodes
        for (int i = 0; i < children.size(); i++)
            children.get(i).generateTree(childIndent, i == children.size() - 1, outputTree);
    }

    @Override
    public String toString() {
        return "ParseTreeNode(value='" + value + "', childrenCount=" + children.size() + ")";
    }
}