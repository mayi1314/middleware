// Generated from /home/guest/Documents/KoordLanguage/src/main/antlr4/Koord.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KoordParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KoordVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KoordParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(KoordParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#defs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefs(KoordParser.DefsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#funcdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdef(KoordParser.FuncdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#adtdef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdtdef(KoordParser.AdtdefContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(KoordParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#event}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvent(KoordParser.EventContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#statementblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementblock(KoordParser.StatementblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(KoordParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#forloop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForloop(KoordParser.ForloopContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(KoordParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#elseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseblock(KoordParser.ElseblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#iostream}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIostream(KoordParser.IostreamContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#funccall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunccall(KoordParser.FunccallContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(KoordParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(KoordParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(KoordParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#bexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBexpr(KoordParser.BexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#aexpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAexpr(KoordParser.AexprContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(KoordParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelop(KoordParser.RelopContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#allwritevars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllwritevars(KoordParser.AllwritevarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#allreadvars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllreadvars(KoordParser.AllreadvarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#localvars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalvars(KoordParser.LocalvarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(KoordParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#arraydec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraydec(KoordParser.ArraydecContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(KoordParser.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#actuatordecls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActuatordecls(KoordParser.ActuatordeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#sensordecls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSensordecls(KoordParser.SensordeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KoordParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(KoordParser.InitContext ctx);
}