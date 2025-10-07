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

    public static final String AST_NODE_DL_PROGRAM = "DLProgram";
    public static final String AST_NODE_DL_FORMULA = "Formula";
    public static final String AST_NODE_DL_PROGRAM_CONTEXT = "Program";
    public static final String AST_NODE_DL_BINARY_EXPRESSION = "BinaryExpression";
    public static final String AST_NODE_DL_TERM_WITH_PARENTHESES = "TermWithParentheses";

    public static final String AST_NODE_REL_DL_PROGRAM = "RelationalDLProgram";
    public static final String AST_NODE_REL_DL_FORMULA = "Relational Formula";
    public static final String AST_NODE_REL_DL_PROGRAM_CONTEXT = "Relational Program";
    public static final String AST_NODE_REL_DL_TERM = "Relational Term";

    public static final char PROGRAM_CONSIDERED_L = 'L';
    public static final char PROGRAM_CONSIDERED_R = 'R';
    public static final char PROGRAM_CONSIDERED_G = 'G';
    public static final String LEFT_PROGRAM = "@L";
    public static final String RIGHT_PROGRAM = "@R";

    public static final String REL_DL_OPEN_BRACKETS = "(#";
    public static final String REL_DL_CLOSE_BRACKETS = ")#";
    public static final String REL_DL_COMMA = ",#";
    public static final String REL_DL_ASSIGNMENT_OPERATOR = ":=#";

    public static final String DL_ASSIGNMENT_OPERATOR = ":=";
    public static final String DL_SEMI_COLON = ";";

    public static final String AST_GENERATION_PROCESS_SUFFIX = " Ast Generation Process";
    public static final String KEYMAERAX_OUTPUT_CONVERSION_SUFFIX = " to KeYmaeraX Output Conversion Process";

    public static final String DIFFERENTIAL_DYNAMIC_LOGIC = "Differential Dynamic Logic";
}