grammar ExprLib;
import CommonLexerRules; // 导入定义好的词法规则

prog : stat+ ;

stat : expr NEWLINE
     | ID '=' expr NEWLINE
     | NEWLINE
     ;

expr : expr ('*'|'/') expr
     | expr ('+'|'-') expr
     | INT
     | ID
     | '(' expr ')'
     ;