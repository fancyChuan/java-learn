## Antlr

### 学习示例
- 入门案例：数组转字符串[arrayinit](src/main/java/cn/fancychuan/arrayinit)
- 使用访问器构造一个计算器 [calculator](src/main/java/cn/fancychuan/calculator)
    - 使用了语法导入。即将大的语法拆分成逻辑单元，比如拆成词法分析器的语法(CommonLexerRules.g4)和语法分析器的语法(ExprLib.g4)
    - 使用了标签功能，即给规则打标签（ExprLibLabel.g4），一般在使用访问器模式遍历抽象语法树时会使用标签
    - 计算器实现 [Calculator.java](src/main/java/cn/fancychuan/calculator/Calculator.java)
- 使用监听器构建一个翻译程序：

### 笔记
解析器：能够分析、计算、执行语句的程序。比如计算器、读取配置文件的程序、python解释器

翻译器：将一门语言转换为另一门语言的程序。比如java到C#的转换器和普通的编译器

语法分析器parser（或者叫句法分析器syntax analyzer）：识别语言的程序
- 句法syntax：约束语言中的各个组成部分之间关系的规则
- 语法grammar：一系列规则的集合，每条规则表述出一种词汇结构
- 语法分析器的两个功能：检查语法的正确性、构造一颗抽象语法树
- 使用前向预测来进行规则的决策(前瞻分析)。具体做法是将输入的符号与每个备选分支的起始符号进行比较
- antlr的语法分析器能够自动检测语法错误，并从语法错误中恢复，不影响下一个语法规则的分析

ANTLR元语言：antlr语法本身遵循的一种专门用来描述其他语言的语法


词法分析器lexer：把输入的文本转为词法符号的程序
- 词法分析lexical analysis（或者叫词法符号化tokenizing）： 将字符聚集为单词或者符号token的过程
- 词法符号至少包含两部分信息：
    - 词法符号的类型（从而能够通过类型来识别词法结构）
    - 该词法符号对应的文本
    

递归下降的语法分析器最神奇的地方在于：通过对方法stat() assign() expr() 的调用描述出调用路线图，并将其映射到了语法分析书的节点上
```
// assign: ID '=' expr ';' // 这是一条规则，规则名字为 assign
void assign() {     // 根据assign规则生成的方法
    match(ID);      // 将当前的输入符号和ID相比较，然后将其消费掉
    match('=');
    expr();         // 通过调用方法expr() 来匹配一个表达式
    match(';');
}
```
- 调用match()对应了语法分析树的叶子节点。也就是说：在match()的实现方法中，要有“增加一个新的叶子节点”这样的操作


```
// 从当前输入位置开始，匹配多种语句
stat: assign        // 第一个备选分支 （'|'符号是备选分支的分隔符）
    | ifstat
    | whilestat
    ...
    
// 对上面这个规则的解析更像是一个switch语句：
void stat() {
    switch( << 当前输入的词法符号 >> )
        case ID     : assign(); break;
        case IF     : ifstat(); break; // IF 是if关键词的词法符号类型
        case WHILE  : whilestat(); break;
        ...
        default     : << 抛出无可选方案的异常 >>
}

```
- 因此在上面的这个规则中，stat()方法必须通过检查下一个词法符号做出语法分析决策或者预测，决定选择哪个备选分支


前瞻词法符号lookahead token： 指任何一个在被匹配和消费之前就由语法分析器嗅探出的词法符号

> 语法分析器在分析语法的时候，需要做各种各样的决策。比如上述例子中，走到stat()就需要决定接下来往哪个分支走，而ID、IF、WHILE就可以理解为前瞻词法符号，它能告诉词法分析器应该往哪里走。当然，有的时候需要更多的前瞻词法符号才能做出决策，也就是遇到歧义性语句时

歧义性语句: 指存在不止一种语义的语句。也就是说，这种语句中的单词序列能够匹配多种语法结构


TokenStream： 连接词法分析器和语法分析器的管道

antlr尽可能多的使用共享数据结构来节省内存

语法分析树的遍历：Antlr提供了两种方法
- 语法分析树监听器：
    - 监听器机制的优秀之处在于：一切都是自动进行的，我们不需要编写对语法分析树的遍历代码，也不需要让监听器显式访问子节点
- 语法分析树访问器：
    - 在命令行中输入-visitor就可以指示antlr为一个语法生成访问器接口（visitor interface）。
    - 语法中每条规则对应接口中的一个visit方法


语法分析器的规则以小写字母开头

词法分析器的规则以大写字母开头


如何使大型语法维持在可控范围之内：
- 语法导入。将常用的规则提取到一个“模块”
- 处理有错误的输入。


使用访问器
- 给备选分支加上标签。如果备选分支没有标签，ANTLR就只会为每条规则生成一个方法，而对备选分支不生成方法


定制语法分析过程
- 在语法中嵌入任意动作 [Rows.g4](https://github.com/fancychuan/java-learn/tree/master/antlr4/src/main/java/cn/fancychuan/rows)
- 使用语义判定改变语法分析过程 [Data.g4](https://github.com/fancychuan/java-learn/tree/master/antlr4/src/main/java/cn/fancychuan/data)


