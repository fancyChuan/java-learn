grammar Rows;

@parser::members { // 在生成的RowsParser
    int col;
    public RowsParser(TokenStream input, int col) { // 自定义构造器
        this(input);
        this.col = col;
    }
}

file : (row NL)+ ;

row
locals [int i=0]
     : ( STUFF
         {
            $i++;
            if ($i == col) System.out.println($STUFF.text);
         }
        )+
     ;

TAB  : '\t' -> skip ; // 匹配，但不传给语法分析器
NL   : '\r'?'\n' ;  // 匹配并传给语法分析器
STUFF: ~[\t\r\n]+ ; // 匹配出\r\n之外的任何字符