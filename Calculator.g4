grammar Calculator;

expression: multiplyingExpression ((PLUS | MINUS) multiplyingExpression)*;
multiplyingExpression: atom ((TIMES | DIV) atom)*;
atom: INTEGER;

INTEGER: [0-9]+;
PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
WS: [ \t\r\n]+ -> skip;
