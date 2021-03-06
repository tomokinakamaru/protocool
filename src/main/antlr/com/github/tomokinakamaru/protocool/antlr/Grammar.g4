grammar Grammar;

file: package_? import_* class_* EOF ;

package_: 'package' qualifiedName ';' ;

import_: 'import' qualifiedName ';' ;

class_: head body ;

head: 'class' name ('<' parameter (',' parameter)* '>')? superClass? interfaces? ;

body: '{' ((chain | parameter) ';')* '}' ;

superClass: 'extends' reference ;

interfaces: 'implements' reference+ ;

parameter: name ('extends' reference (',' reference)*)? ;

chain: STATIC? reference expression ('return' qualifiedName)? ;

expression: term ('|' term)* ;

term: factor+ ;

factor: element (OPTIONAL | REPEAT0 | REPEAT1)? ;

element: method | '(' expression ')';

method: name '(' (argument (',' argument)*)? ')' ('{' qualifiedName '}')? ;

argument: reference ELLIPSIS? name ;

reference: qualifiedName ('<' (reference | wildcard) (',' (reference | wildcard))* '>')? ARRAY* ;

wildcard: '?' (('extends' | SUPER) reference)?;

qualifiedName: (NAME '.')* NAME ;

name: NAME ;

STATIC: 'static' ;

SUPER: 'super' ;

OPTIONAL: '?' ;

REPEAT0: '*' ;

REPEAT1: '+' ;

ELLIPSIS: '...' ;

ARRAY: '[' ']' ;

NAME: [a-zA-Z_][a-zA-Z0-9_]* ;

WS: [ \t\r\n\u000C]+ -> channel(HIDDEN) ;
