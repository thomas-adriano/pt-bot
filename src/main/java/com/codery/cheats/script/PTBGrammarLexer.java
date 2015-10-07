// Generated from /Users/thomasadriano/Git/priston/src/main/resources/PTBGrammar.g4 by ANTLR 4.5.1
package com.codery.cheats.script;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PTBGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, NEWLINE=3, INT=4, POTION=5, SPELL=6, STRING=7, WS=8, REPEAT=9, 
		REPEATER=10, USE_POTION=11, CAST_SPELL=12, INTERVAL=13, AFTER=14, SCHEDULE=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "NEWLINE", "INT", "POTION", "SPELL", "STRING", "WS", "REPEAT", 
		"REPEATER", "USE_POTION", "CAST_SPELL", "INTERVAL", "AFTER", "SCHEDULE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "NEWLINE", "INT", "POTION", "SPELL", "STRING", "WS", 
		"REPEAT", "REPEATER", "USE_POTION", "CAST_SPELL", "INTERVAL", "AFTER", 
		"SCHEDULE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PTBGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PTBGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21\u00ab\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3"+
		"\3\4\6\4\'\n\4\r\4\16\4(\3\4\6\4,\n\4\r\4\16\4-\5\4\60\n\4\3\5\6\5\63"+
		"\n\5\r\5\16\5\64\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7>\n\7\3\b\6\bA\n\b\r\b"+
		"\16\bB\3\t\6\tF\n\t\r\t\16\tG\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13j\n\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\5\17\u0093\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21\3\2\n\4\2\f\f\17\17\3\2\17\17\3\2\62;\3\2\63\65\4\2\62\62"+
		"\66;\3\2\62\64\4\2C\\c|\5\2\13\f\16\17\"\"\u00b5\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7/\3\2\2\2\t\62\3"+
		"\2\2\2\13\66\3\2\2\2\r=\3\2\2\2\17@\3\2\2\2\21E\3\2\2\2\23K\3\2\2\2\25"+
		"i\3\2\2\2\27k\3\2\2\2\31x\3\2\2\2\33\u0085\3\2\2\2\35\u0092\3\2\2\2\37"+
		"\u00a0\3\2\2\2!\"\7*\2\2\"\4\3\2\2\2#$\7+\2\2$\6\3\2\2\2%\'\t\2\2\2&%"+
		"\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)\60\3\2\2\2*,\t\3\2\2+*\3\2\2"+
		"\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/&\3\2\2\2/+\3\2\2\2\60\b"+
		"\3\2\2\2\61\63\t\4\2\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65"+
		"\3\2\2\2\65\n\3\2\2\2\66\67\t\5\2\2\67\f\3\2\2\28>\t\6\2\29:\7H\2\2:>"+
		"\t\4\2\2;<\7H\2\2<>\t\7\2\2=8\3\2\2\2=9\3\2\2\2=;\3\2\2\2>\16\3\2\2\2"+
		"?A\t\b\2\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\20\3\2\2\2DF\t\t\2"+
		"\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\b\t\2\2J\22\3\2"+
		"\2\2KL\7t\2\2LM\7g\2\2MN\7r\2\2NO\7g\2\2OP\7c\2\2PQ\7v\2\2QR\3\2\2\2R"+
		"S\5\25\13\2S\24\3\2\2\2TU\7q\2\2UV\7p\2\2VW\7e\2\2Wj\7g\2\2XY\7v\2\2Y"+
		"Z\7k\2\2Z[\7o\2\2[\\\7g\2\2\\]\7u\2\2]^\7*\2\2^_\3\2\2\2_`\5\t\5\2`a\7"+
		"+\2\2aj\3\2\2\2bc\7h\2\2cd\7q\2\2de\7t\2\2ef\7g\2\2fg\7x\2\2gh\7g\2\2"+
		"hj\7t\2\2iT\3\2\2\2iX\3\2\2\2ib\3\2\2\2j\26\3\2\2\2kl\7w\2\2lm\7u\2\2"+
		"mn\7g\2\2no\7\"\2\2op\7r\2\2pq\7q\2\2qr\7v\2\2rs\7k\2\2st\7q\2\2tu\7p"+
		"\2\2uv\3\2\2\2vw\5\13\6\2w\30\3\2\2\2xy\7e\2\2yz\7c\2\2z{\7u\2\2{|\7v"+
		"\2\2|}\7\"\2\2}~\7u\2\2~\177\7r\2\2\177\u0080\7g\2\2\u0080\u0081\7n\2"+
		"\2\u0081\u0082\7n\2\2\u0082\u0083\3\2\2\2\u0083\u0084\5\r\7\2\u0084\32"+
		"\3\2\2\2\u0085\u0086\7k\2\2\u0086\u0087\7p\2\2\u0087\u0088\7v\2\2\u0088"+
		"\u0089\7g\2\2\u0089\u008a\7t\2\2\u008a\u008b\7x\2\2\u008b\u008c\7c\2\2"+
		"\u008c\u008d\7n\2\2\u008d\u008e\3\2\2\2\u008e\u008f\5\t\5\2\u008f\34\3"+
		"\2\2\2\u0090\u0093\5\31\r\2\u0091\u0093\5\27\f\2\u0092\u0090\3\2\2\2\u0092"+
		"\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7w\2\2\u0095\u0096\7u\2"+
		"\2\u0096\u0097\7g\2\2\u0097\u0098\7f\2\2\u0098\u0099\3\2\2\2\u0099\u009a"+
		"\5\t\5\2\u009a\u009b\7v\2\2\u009b\u009c\7k\2\2\u009c\u009d\7o\2\2\u009d"+
		"\u009e\7g\2\2\u009e\u009f\7u\2\2\u009f\36\3\2\2\2\u00a0\u00a1\7u\2\2\u00a1"+
		"\u00a2\7e\2\2\u00a2\u00a3\7j\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7f\2\2"+
		"\u00a5\u00a6\7w\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00aa\5\t\5\2\u00aa \3\2\2\2\f\2(-/\64=BGi\u0092\3\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}