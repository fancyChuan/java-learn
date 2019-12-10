package cn.fancychuan.calculator;

import cn.fancychuan.calculator.g4out.exprlabel.ExprLibLabelBaseVisitor;
import cn.fancychuan.calculator.g4out.exprlabel.ExprLibLabelLexer;
import cn.fancychuan.calculator.g4out.exprlabel.ExprLibLabelParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public static void main(String[] args) throws IOException {
        CharStream input = CharStreams.fromFileName("E:\\JavaWorkshop\\java-learn\\antlr4\\src\\main\\java\\cn\\fancychuan\\calculator\\t.expr");
        ExprLibLabelLexer lexer = new ExprLibLabelLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprLibLabelParser parser = new ExprLibLabelParser(tokens);
        ParseTree tree = parser.prog();
        // 使用自定义的访问器
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}


/**
 * 自定义访问器
 */
class EvalVisitor extends ExprLibLabelBaseVisitor {
    Map<String, Integer> memory = new HashMap<>();

    // expr NEWLINE             # printExpr
    @Override
    public Object visitPrintExpr(ExprLibLabelParser.PrintExprContext ctx) {
        Object value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    // ID '=' expr NEWLINE      # assign
    @Override
    public Object visitAssign(ExprLibLabelParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = (int) visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    // | '(' expr ')'             # parens
    @Override
    public Object visitParens(ExprLibLabelParser.ParensContext ctx) {
        return visit(ctx.expr()); // 返回子表达式的值
    }

    // expr ('*'|'/') expr      # MulDiv
    @Override
    public Object visitMulDiv(ExprLibLabelParser.MulDivContext ctx) {
        int left = (int) visit(ctx.expr(0));
        int right = (int) visit(ctx.expr(1));
        if (ctx.MUL() != null) { // 含有MUL操作. todo: 书上的示例是 ctx.op.getType() == ExprLibLabelParser.MUL，为什么没有op属性，是因为antlr版本问题？
            return left * right;
        }
        return left / right;
    }

    // expr ('+'|'-') expr      # AddSub
    @Override
    public Object visitAddSub(ExprLibLabelParser.AddSubContext ctx) {
        int left = (int) visit(ctx.expr(0));
        int right = (int) visit(ctx.expr(1));
        if (ctx.ADd() != null) {
            return left + right;
        }
        return left - right;
    }

    @Override
    public Object visitId(ExprLibLabelParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) {
            return memory.get(id);
        }
        return 0;
    }

    @Override
    public Object visitInt(ExprLibLabelParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }
}
