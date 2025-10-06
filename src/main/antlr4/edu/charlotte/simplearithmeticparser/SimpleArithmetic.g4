grammar SimpleArithmetic;

simpleArithmeticProgram
    : expr EOF
    ;

expr
    : expr ADDITION term
    | term
    ;

term
    : term MULTIPLICATION factor
    | factor
    ;

factor
    : OPEN_BRACKETS expr CLOSE_BRACKETS
    | number
    ;

number
    : number DIGIT
    | DIGIT
    ;

DIGIT           : [0-9];
OPEN_BRACKETS   : '(';
CLOSE_BRACKETS  : ')';
ADDITION        : '+';
MULTIPLICATION  : '*';
WS              : [ \t\r\n]+ -> skip;
LINE_COMMENT    : '//' ~[\r\n]* -> skip;