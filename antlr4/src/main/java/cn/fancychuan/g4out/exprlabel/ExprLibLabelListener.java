// Generated from E:/JavaWorkshop/java-learn/antlr4/src/main/g4files\ExprLibLabel.g4 by ANTLR 4.7.2
package cn.fancychuan.g4out.exprlabel;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprLibLabelParser}.
 */
public interface ExprLibLabelListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprLibLabelParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ExprLibLabelParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprLibLabelParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ExprLibLabelParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link ExprLibLabelParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(ExprLibLabelParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link ExprLibLabelParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(ExprLibLabelParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ExprLibLabelParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(ExprLibLabelParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link ExprLibLabelParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(ExprLibLabelParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link ExprLibLabelParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlank(ExprLibLabelParser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link ExprLibLabelParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlank(ExprLibLabelParser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(ExprLibLabelParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(ExprLibLabelParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(ExprLibLabelParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(ExprLibLabelParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(ExprLibLabelParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(ExprLibLabelParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(ExprLibLabelParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(ExprLibLabelParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(ExprLibLabelParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link ExprLibLabelParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(ExprLibLabelParser.IntContext ctx);
}