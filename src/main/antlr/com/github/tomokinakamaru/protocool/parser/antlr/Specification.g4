grammar Specification;

@lexer::members {
    @Override
    public void recover(RecognitionException e) {
        throw new RuntimeException(e.getMessage());
    }
}

@parser::header {
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import com.github.tomokinakamaru.protocool.analysis.data.*;
    import com.github.tomokinakamaru.protocool.automaton.Automaton;
    import com.github.tomokinakamaru.protocool.symboltable.ClazzTable;
    import com.github.tomokinakamaru.protocool.symboltable.ForeignTypeTable;
    import com.github.tomokinakamaru.protocool.symboltable.ParameterTable;
    import com.github.tomokinakamaru.protocool.entity.ForeignType;
}

@parser::members {
    @Override
    public void notifyErrorListeners(Token t, String s, RecognitionException e) {
        throw new RuntimeException(String.format("%s (L%dC%d)", s, t.getLine(), t.getCharPositionInLine()));
    }
}

specification returns [
    String packageName = "",
    Path packagePath = Paths.get(""),
    final ClazzTable clazzTable = new ClazzTable(),
    final ForeignTypeTable foreignTypeTable = new ForeignTypeTable(),
    final MethodNodeNameTable methodNodeNameTable = new MethodNodeNameTable(),
    final MethodNodeTable methodNodeTable = new MethodNodeTable(),
    final ClassNodeNameTable classNodeNameTable = new ClassNodeNameTable(),
    final ClassNodeTable classNodeTable = new ClassNodeTable(),
    final Skeletons skeletons = new Skeletons()
]: pkg? clazz+ EOF ;

pkg returns [
    SpecificationContext ownerSpecification
]: 'package' qualifiedName ';' ;

clazz returns [
    SpecificationContext ownerSpecification,
    final ParameterTable parameterTable = new ParameterTable(),
    Automaton automaton
]: head body ;

head: 'class' name ('<' parameter (',' parameter)* '>')? superClazz? interfaces? ;

superClazz: 'extends' reference ;

interfaces: 'implements' reference+ ;

body: '{' (chain | (parameter ';'))* '}' ;

parameter returns [
    ClazzContext ownerClazz
]: name ('extends' reference)? ;

chain returns [
    ClazzContext ownerClazz,
    String nodeBaseName,
    String nodeName,
    final Parameters parameters = new Parameters(),
    final MethodNodeNames methodNodeNames = new MethodNodeNames();
]: STATIC? reference expression ('by' qualifiedName)? ';' ;

expression returns [
    Automaton automaton
]: term (OR term)* ;

term: factor+ ;

factor: element (OPTIONAL | REPEAT0 | REPEAT1)? ;

element: method | '(' expression ')';

method returns [
    ClazzContext ownerClazz,
    String normalizedText,
    String signature,
    String nodeBaseName,
    String nodeName,
    final Parameters parameters = new Parameters(),
    boolean isStatic,
    String evaluator
]: name '(' (argument (',' argument)*)? ')' ;

argument returns [
    String normalizedText,
    String signature
]: reference ELLIPSIS? name ;

reference returns [
    ClazzContext ownerClazz,
    ClazzContext clazzDestination,
    ChainContext ownerChain,
    ParameterContext parameterDestination,
    ForeignType foreignTypeDestination,
    String normalizedText,
    String signature,
    final Parameters parameters = new Parameters()
]: qualifiedName ('<' (referenceOrWildcard) (',' (referenceOrWildcard))* '>')? ARRAY* ;

referenceOrWildcard: reference | wildcard ;

wildcard returns [
    String normalizedText
]: '?' (('extends' | SUPER) reference)?;

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
