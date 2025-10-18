package edu.charlotte.simplearithmeticparser.utils;

public final class Constants {
    private Constants() {}
    public static final String JOBNAME_SIMPLE_ARITHMETIC_AST_GENERATION = "SIMPLE_ARITHMETIC_AST_GENERATION";
    public static final String JOBNAME_SIMPLE_ARITHMETIC_PARSE_TREE_GENERATION = "SIMPLE_ARITHMETIC_PARSE_TREE_GENERATION";

    public static final String AST_GENERATION_EXTENSION = "_AST.txt";
    public static final String PARSE_TREE_GENERATION_EXTENSION = "_ParseTree.txt";

    public static final String INPUT_FILE = "input.file";
    public static final String OUTPUT_FILE = "output.file";
    public static final String JOB_NAME = "job.name";

    public static final String ERROR_MESSAGE_FOR_MISSING_JOB_PARAMETERS = "Missing required job parameters. " +
            "Use --job.name=<jobName> and --input.file=<inputFile>";

    public static final String PARSE_TREE_NODE_SIMPLE_ARITHMETIC_PROGRAM = "SimpleArithmeticProgram";
    public static final String PARSE_TREE_NODE_SIMPLE_ARITHMETIC_EXPRESSION = "expr";
    public static final String PARSE_TREE_NODE_SIMPLE_ARITHMETIC_TERM = "term";
    public static final String PARSE_TREE_NODE_SIMPLE_ARITHMETIC_FACTOR = "factor";
    public static final String PARSE_TREE_NODE_SIMPLE_ARITHMETIC_NUMBER = "number";
    public static final String PARSE_TREE_NODE_SIMPLE_ARITHMETIC_DIGIT = "digit";

    public static final String PARSE_TREE_GENERATION_PROCESS_SUFFIX = " Parse Tree Generation Process";
    public static final String SIMPLE_ARITHMETIC = "Simple Arithmetic";
}