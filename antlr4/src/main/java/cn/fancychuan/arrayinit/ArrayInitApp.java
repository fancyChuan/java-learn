package cn.fancychuan.arrayinit;

import cn.fancychuan.arrayinit.g4out.ArrayInitLexer;
import cn.fancychuan.arrayinit.g4out.ArrayInitParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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

        /**
         * 下面开始遍历语法树，并执行翻译的功能
         */
        ParseTreeWalker walker = new ParseTreeWalker(); // 通用的、能够触发回调函数的语法分析树遍历器
        walker.walk(new ShortToUnicodeString(), tree);  // 遍历语法树，触发回调
        System.out.println("\n========= done! =========");
    }
}
