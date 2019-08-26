package cn.fancychuan.apps;

import cn.fancychuan.g4out.ArrayInitBaseListener;
import cn.fancychuan.g4out.ArrayInitParser;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * 将类似{1,2,3}翻译为"\u0001\u0002\u0003"
 *
 */
public class ShortToUnicodeString extends ArrayInitBaseListener {
    // 将 { 翻译为 "
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    // 将 } 翻译为 "
    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    // 将每个整数转为16进制形式，然后加前缀
    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        // 先假设不存在嵌套结构
        int value = Integer.valueOf(ctx.INT().getText());
        System.out.printf("\\u%04x", value);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }
}
