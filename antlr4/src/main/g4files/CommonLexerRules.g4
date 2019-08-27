// 通用的词法规则，注意关键词以 lexer开头
lexer grammar CommonLexerRules;

ID   : [a-zA-Z]+ ;      // 匹配标识符
INT  : [0-9]+ ;         // 匹配整数
NEWLINE : '\r'?'\n' ;   // 告诉语法分析器一个新行的开始（即语句终止标志）
WS   : [\t' ']+ -> skip;   // 丢弃空白字符： 制表符和空格