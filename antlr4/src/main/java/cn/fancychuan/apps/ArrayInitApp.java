package cn.fancychuan.apps;

import cn.fancychuan.g4out.ArrayInitLexer;
import cn.fancychuan.g4out.ArrayInitParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class ArrayInitApp {

    public static void main(String[] args) throws IOException {
        // ANTLRInputStream input = new ANTLRInputStream(System.in); // 从标准输入新建一个CharStream
        String inputText = "{1,5,7,9}";
        // 从字符串新建一个CharStream
        CharStream input = CharStreams.fromString(inputText);
        // 新建一个词法分析器，处理输入的CharStream
        ArrayInitLexer lexer = new ArrayInitLexer(input);
        // 新建一个词法符号的缓冲区，用于存储词法分析器将生成的词法符号
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        // 新建一个语法分析器，处理词法符号缓冲区中的内容
        ArrayInitParser parser = new ArrayInitParser(tokenStream);
        // 针对我们定义的init规则开始语法分析
        ParseTree tree = parser.init();
        System.out.println(tree.toStringTree(parser)); // 打印LISP风格的语法树
    }
}
