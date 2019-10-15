grammar Grammar;

file: package_? import_* class_* ;

package_: 'package' qualifiedName ';' ;

import_: 'import' qualifiedName ';' ;

class_: 'class' NAME ('<' parameter (',' parameter)* '>')? superClass? interfaces? '{' ((chain | parameter) ';')* '}' ;

superClass: 'extends' reference ;

interfaces: 'implements' reference+ ;

parameter: NAME ('extends' reference)? ;

chain: STATIC? reference expression ('return' qualifiedName)? ;

expression: term ('|' term)* ;

term: factor+ ;

factor: element (OPTIONAL | REPEAT0 | REPEAT1)? ;

element: method | '(' expression ')';

method: NAME '(' (argument (',' argument)*)? ')' ;

argument: reference ELLIPSIS? NAME ;

reference: qualifiedName ('<' (reference | wildcard) (',' (reference | wildcard))* '>')? ARRAY* ;

wildcard: '?' (('extends' | SUPER) reference)?;

qualifiedName: (NAME '.')* NAME ;

STATIC: 'static' ;

SUPER: 'super' ;

OPTIONAL: '?' ;

REPEAT0: '*' ;

REPEAT1: '+' ;

ELLIPSIS: '...' ;

ARRAY: '[' ']' ;

NAME: [a-zA-Z_][a-zA-Z0-9_]* ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;
