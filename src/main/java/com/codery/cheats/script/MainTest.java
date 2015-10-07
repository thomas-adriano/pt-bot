package com.codery.cheats.script;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;

/**
 * Created by thomasadriano on 05/10/15.
 */
public class MainTest {

    public static void main(String[] args) throws IOException {
        CharStream stream = new ANTLRFileStream("src/test/resources/script.pb");
        PTBGrammarLexer lexer = new PTBGrammarLexer(stream);
        PTBGrammarParser parser = new PTBGrammarParser(new CommonTokenStream(lexer));

        parser.prog().enterRule(new ParseTreeListener() {
            @Override
            public void visitTerminal(TerminalNode node) {
                System.out.println("visiting terminal");
            }

            @Override
            public void visitErrorNode(ErrorNode node) {
                System.out.println("visiting error");
            }

            @Override
            public void enterEveryRule(ParserRuleContext ctx) {
                System.out.println("enter every");
            }

            @Override
            public void exitEveryRule(ParserRuleContext ctx) {
                System.out.println("exit terminal");
            }
        });
    }
}
