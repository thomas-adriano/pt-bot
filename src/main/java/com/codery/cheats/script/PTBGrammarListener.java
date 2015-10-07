// Generated from /Users/thomasadriano/Git/priston/src/main/resources/PTBGrammar.g4 by ANTLR 4.5.1
package com.codery.cheats.script;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PTBGrammarParser}.
 */
public interface PTBGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PTBGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(PTBGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link PTBGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(PTBGrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link PTBGrammarParser#instructions}.
	 * @param ctx the parse tree
	 */
	void enterInstructions(PTBGrammarParser.InstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PTBGrammarParser#instructions}.
	 * @param ctx the parse tree
	 */
	void exitInstructions(PTBGrammarParser.InstructionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PTBGrammarParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(PTBGrammarParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PTBGrammarParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(PTBGrammarParser.ActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PTBGrammarParser#constraints}.
	 * @param ctx the parse tree
	 */
	void enterConstraints(PTBGrammarParser.ConstraintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PTBGrammarParser#constraints}.
	 * @param ctx the parse tree
	 */
	void exitConstraints(PTBGrammarParser.ConstraintsContext ctx);
}