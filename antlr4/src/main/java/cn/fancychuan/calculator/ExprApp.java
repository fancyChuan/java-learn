package cn.fancychuan.calculator;

import cn.fancychuan.calculator.g4out.expr.ExprLexer;
import cn.fancychuan.calculator.g4out.expr.ExprParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class ExprApp {
    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("E:\\JavaWorkshop\\java-learn\\antlr4\\src\\main\\resources\\t.expr");
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));
    }
}
