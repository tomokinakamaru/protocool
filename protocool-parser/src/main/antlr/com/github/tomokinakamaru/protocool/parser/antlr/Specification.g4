grammar Specification;

specification: pkg? clazz+ EOF ;

pkg: 'package' qualifiedName ';' ;

clazz: head body ;

head: 'class' name ('<' parameter (',' parameter)* '>')? superClazz? interfaces? ;

superClazz: 'extends' reference ;

interfaces: 'implements' reference+ ;

body: '{' (chain | (parameter ';'))* '}' ;

parameter: name ('extends' reference)? ;

chain: STATIC? reference expression ('by' qualifiedName)? ';' ;

expression: term (OR term)* ;

term: factor+ ;

factor: element (OPTIONAL | REPEAT0 | REPEAT1)? ;

element: method | '(' expression ')';

method: name '(' (argument (',' argument)*)? ')';

argument: reference ELLIPSIS? name ;

referenceOrWildcard: reference | wildcard ;

reference: qualifiedName ('<' (referenceOrWildcard) (',' (referenceOrWildcard))* '>')? ARRAY* ;

wildcard: '?' (('extends' | SUPER) reference)?;

qualifiedName: (NAME '.')* NAME ;

name: NAME ;

STATIC: 'static' ;

SUPER: 'super' ;

OR: '|' ;

OPTIONAL: '?' ;

REPEAT0: '*' ;

REPEAT1: '+' ;

ELLIPSIS: '...' ;

ARRAY: '[' ']' ;

NAME: [a-zA-Z_][a-zA-Z0-9_]* ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;
