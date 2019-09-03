package cn.fancychuan.rows;

import cn.fancychuan.rows.g4out.RowsLexer;
import cn.fancychuan.rows.g4out.RowsParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class RowsApp {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("E:\\JavaWorkshop\\java-learn\\antlr4\\src\\main\\java\\cn\\fancychuan\\rows\\rows.txt");
        RowsLexer lexer = new RowsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        int col = 2; // 取第2列的数据
        RowsParser parser = new RowsParser(tokens, col); // 传递列号作为参数
        parser.setBuildParseTree(false); // 不需要建立语法分析树
        parser.file(); // 开始语法分析
    }
}
