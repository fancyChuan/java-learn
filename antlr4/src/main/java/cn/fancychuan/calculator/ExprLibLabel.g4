// 带标签的四则运算语法
// 我们需要给每个备选分支加上标签
grammar ExprLibLabel;
import CommonLexerRules; // 导入定义好的词法规则

prog : stat+ ;

stat : expr NEWLINE             # printExpr
     | ID '=' expr NEWLINE      # assign
     | NEWLINE                  # blank
     | 'clear'                  # clear
     ;

expr : expr ('*'|'/') expr      # MulDiv
     | expr ('+'|'-') expr      # AddSub
     | INT                      # int
     | ID                       # id
     | '(' expr ')'             # parens
     ;

// 为运算符这样的词法符号定义名字，以便在访问器中把这些名字当成java常量来引用
MUL  : '*' ;
DIV  : '/' ;
ADd  : '+' ;
SUB  : '-' ;