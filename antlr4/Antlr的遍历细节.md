## Antlr的遍历细节

```java
// org.antlr.v4.runtime.tree.ParseTreeWalker
package org.antlr.v4.runtime.tree;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

public class ParseTreeWalker {
    public static final ParseTreeWalker DEFAULT = new ParseTreeWalker();

    // walk方法是深度优先遍历树的入口，可以指定监听器
    public void walk(ParseTreeListener listener, ParseTree t) {
		if ( t instanceof ErrorNode) { //判断是否为异常节点
			listener.visitErrorNode((ErrorNode)t); // 访问异常节点
			return;
		}
		else if ( t instanceof TerminalNode) { // 判断是否是没有子节点的结点
			listener.visitTerminal((TerminalNode)t);
			return;
		}
		RuleNode r = (RuleNode)t;
        enterRule(listener, r); //TODO：这里应该是监听器模式遍历树的入口
        // 从这一行开始使用访问器模式深度优先遍历树
        int n = r.getChildCount(); // 先拿到下一级的子节点数
        for (int i = 0; i<n; i++) {
            walk(listener, r.getChild(i)); // 遍历下级每一个节点，这里是递归用法，对每一次遍历都需要重复同样的过程
        }
        
		exitRule(listener, r); // 退出监听器模式
    }

    protected void enterRule(ParseTreeListener listener, RuleNode r) {
		ParserRuleContext ctx = (ParserRuleContext)r.getRuleContext();
		listener.enterEveryRule(ctx);
		ctx.enterRule(listener);
    }

    protected void exitRule(ParseTreeListener listener, RuleNode r) {
		ParserRuleContext ctx = (ParserRuleContext)r.getRuleContext();
		ctx.exitRule(listener);
		listener.exitEveryRule(ctx);
    }
}


```
其中类ParseTreeListener是所有监听器的父类