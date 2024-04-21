// Generated from /home/guest/Documents/KoordLanguage/src/main/antlr4/Koord.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class KoordParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AGENT=1, MODULE=2, USING=3, DEF=4, TYPE=5, FUN=6, ADT=7, FOR=8, ACTUATORS=9, 
		SENSORS=10, ALLWRITE=11, ALLREAD=12, LOCAL=13, LIST=14, MAP=15, QUEUE=16, 
		INIT=17, INT=18, FLOAT=19, BOOL=20, STRINGTYPE=21, STREAM=22, POS=23, 
		INPUTMAP=24, IF=25, ELSE=26, ATOMIC=27, PRE=28, EFF=29, TRUE=30, FALSE=31, 
		NONE=32, PID=33, NUMAGENTS=34, STOP=35, COLON=36, COMMA=37, SEMICOLON=38, 
		LPAR=39, RPAR=40, LBRACE=41, RBRACE=42, LCURLY=43, RCURLY=44, LANGLE=45, 
		RANGLE=46, AND=47, OR=48, NOT=49, UPPER=50, VARNAME=51, INUM=52, FNUM=53, 
		PLUS=54, MINUS=55, TIMES=56, BY=57, EQ=58, GEQ=59, LEQ=60, NEQ=61, ASGN=62, 
		LSHIFT=63, STRING=64, NEWLINE=65, SKIP_=66, INDENT=67, DEDENT=68;
	public static final int
		RULE_program = 0, RULE_defs = 1, RULE_funcdef = 2, RULE_adtdef = 3, RULE_param = 4, 
		RULE_event = 5, RULE_statementblock = 6, RULE_stmt = 7, RULE_forloop = 8, 
		RULE_conditional = 9, RULE_elseblock = 10, RULE_iostream = 11, RULE_funccall = 12, 
		RULE_arglist = 13, RULE_assign = 14, RULE_expr = 15, RULE_bexpr = 16, 
		RULE_aexpr = 17, RULE_constant = 18, RULE_relop = 19, RULE_allwritevars = 20, 
		RULE_allreadvars = 21, RULE_localvars = 22, RULE_decl = 23, RULE_arraydec = 24, 
		RULE_module = 25, RULE_actuatordecls = 26, RULE_sensordecls = 27, RULE_init = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "defs", "funcdef", "adtdef", "param", "event", "statementblock", 
			"stmt", "forloop", "conditional", "elseblock", "iostream", "funccall", 
			"arglist", "assign", "expr", "bexpr", "aexpr", "constant", "relop", "allwritevars", 
			"allreadvars", "localvars", "decl", "arraydec", "module", "actuatordecls", 
			"sensordecls", "init"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@agent'", "'module'", "'using'", "'def'", "'type'", "'fun'", 
			"'adt'", "'for'", "'actuators'", "'sensors'", "'allwrite'", "'allread'", 
			"'local'", "'list'", "'map'", "'queue'", "'init'", "'int'", "'float'", 
			"'boolean'", "'string'", "'stream'", "'pos'", "'inputMap'", "'if'", "'else'", 
			"'atomic'", "'pre'", "'eff'", "'true'", "'false'", "'None'", "'pid'", 
			"'numAgents'", "'stop'", "':'", "','", "';'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'<'", "'>'", null, null, "'!'", null, null, null, null, 
			"'+'", "'-'", "'*'", "'/'", "'=='", "'>='", "'<='", "'!='", "'='", "'<<'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "AGENT", "MODULE", "USING", "DEF", "TYPE", "FUN", "ADT", "FOR", 
			"ACTUATORS", "SENSORS", "ALLWRITE", "ALLREAD", "LOCAL", "LIST", "MAP", 
			"QUEUE", "INIT", "INT", "FLOAT", "BOOL", "STRINGTYPE", "STREAM", "POS", 
			"INPUTMAP", "IF", "ELSE", "ATOMIC", "PRE", "EFF", "TRUE", "FALSE", "NONE", 
			"PID", "NUMAGENTS", "STOP", "COLON", "COMMA", "SEMICOLON", "LPAR", "RPAR", 
			"LBRACE", "RBRACE", "LCURLY", "RCURLY", "LANGLE", "RANGLE", "AND", "OR", 
			"NOT", "UPPER", "VARNAME", "INUM", "FNUM", "PLUS", "MINUS", "TIMES", 
			"BY", "EQ", "GEQ", "LEQ", "NEQ", "ASGN", "LSHIFT", "STRING", "NEWLINE", 
			"SKIP_", "INDENT", "DEDENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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

	@Override
	public String getGrammarFileName() { return "Koord.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KoordParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(KoordParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(KoordParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(KoordParser.NEWLINE, i);
		}
		public List<ModuleContext> module() {
			return getRuleContexts(ModuleContext.class);
		}
		public ModuleContext module(int i) {
			return getRuleContext(ModuleContext.class,i);
		}
		public List<AllreadvarsContext> allreadvars() {
			return getRuleContexts(AllreadvarsContext.class);
		}
		public AllreadvarsContext allreadvars(int i) {
			return getRuleContext(AllreadvarsContext.class,i);
		}
		public List<AllwritevarsContext> allwritevars() {
			return getRuleContexts(AllwritevarsContext.class);
		}
		public AllwritevarsContext allwritevars(int i) {
			return getRuleContext(AllwritevarsContext.class,i);
		}
		public List<LocalvarsContext> localvars() {
			return getRuleContexts(LocalvarsContext.class);
		}
		public LocalvarsContext localvars(int i) {
			return getRuleContext(LocalvarsContext.class,i);
		}
		public InitContext init() {
			return getRuleContext(InitContext.class,0);
		}
		public List<EventContext> event() {
			return getRuleContexts(EventContext.class);
		}
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public List<TerminalNode> AGENT() { return getTokens(KoordParser.AGENT); }
		public TerminalNode AGENT(int i) {
			return getToken(KoordParser.AGENT, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(KoordParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(KoordParser.LPAR, i);
		}
		public List<TerminalNode> UPPER() { return getTokens(KoordParser.UPPER); }
		public TerminalNode UPPER(int i) {
			return getToken(KoordParser.UPPER, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(KoordParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(KoordParser.RPAR, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(58);
					match(NEWLINE);
					}
				}

				setState(61);
				defs();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==USING) {
					{
					{
					setState(62);
					module();
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14336L) != 0)) {
					{
					setState(71);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ALLREAD:
						{
						setState(68);
						allreadvars();
						}
						break;
					case ALLWRITE:
						{
						setState(69);
						allwritevars();
						}
						break;
					case LOCAL:
						{
						setState(70);
						localvars();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INIT) {
					{
					setState(76);
					init();
					}
				}

				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(79);
					event();
					}
					}
					setState(82); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VARNAME );
				setState(84);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(86);
					match(NEWLINE);
					}
				}

				setState(89);
				defs();
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ALLWRITE || _la==ALLREAD) {
					{
					setState(92);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ALLREAD:
						{
						setState(90);
						allreadvars();
						}
						break;
					case ALLWRITE:
						{
						setState(91);
						allwritevars();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INIT) {
					{
					setState(97);
					init();
					}
				}

				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(100);
					match(AGENT);
					setState(101);
					match(LPAR);
					setState(102);
					match(UPPER);
					setState(103);
					match(RPAR);
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(104);
						match(NEWLINE);
						}
						}
						setState(109);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==USING) {
						{
						{
						setState(110);
						module();
						}
						}
						setState(115);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LOCAL) {
						{
						setState(116);
						localvars();
						}
					}

					setState(120); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(119);
						event();
						}
						}
						setState(122); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==VARNAME );
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==AGENT );
				setState(128);
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(130);
					match(NEWLINE);
					}
				}

				setState(133);
				defs();
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ALLWRITE || _la==ALLREAD) {
					{
					setState(136);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case ALLREAD:
						{
						setState(134);
						allreadvars();
						}
						break;
					case ALLWRITE:
						{
						setState(135);
						allwritevars();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(140);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==USING) {
					{
					{
					setState(141);
					module();
					}
					}
					setState(146);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LOCAL) {
					{
					setState(147);
					localvars();
					}
				}

				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INIT) {
					{
					setState(150);
					init();
					}
				}

				setState(154); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(153);
					event();
					}
					}
					setState(156); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VARNAME );
				setState(158);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefsContext extends ParserRuleContext {
		public List<FuncdefContext> funcdef() {
			return getRuleContexts(FuncdefContext.class);
		}
		public FuncdefContext funcdef(int i) {
			return getRuleContext(FuncdefContext.class,i);
		}
		public List<AdtdefContext> adtdef() {
			return getRuleContexts(AdtdefContext.class);
		}
		public AdtdefContext adtdef(int i) {
			return getRuleContext(AdtdefContext.class,i);
		}
		public DefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitDefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefsContext defs() throws RecognitionException {
		DefsContext _localctx = new DefsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_defs);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(162);
					funcdef();
					}
					} 
				}
				setState(167);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEF) {
				{
				{
				setState(168);
				adtdef();
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncdefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(KoordParser.DEF, 0); }
		public TerminalNode FUN() { return getToken(KoordParser.FUN, 0); }
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode LPAR() { return getToken(KoordParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(KoordParser.RPAR, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public FuncdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterFuncdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitFuncdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitFuncdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdefContext funcdef() throws RecognitionException {
		FuncdefContext _localctx = new FuncdefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(DEF);
			setState(175);
			match(FUN);
			setState(176);
			match(VARNAME);
			setState(177);
			match(LPAR);
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(178);
				param();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(RPAR);
			setState(185);
			match(COLON);
			setState(186);
			match(NEWLINE);
			setState(187);
			statementblock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdtdefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(KoordParser.DEF, 0); }
		public TerminalNode UPPER() { return getToken(KoordParser.UPPER, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public AdtdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_adtdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterAdtdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitAdtdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitAdtdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdtdefContext adtdef() throws RecognitionException {
		AdtdefContext _localctx = new AdtdefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_adtdef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(DEF);
			setState(190);
			match(UPPER);
			setState(191);
			match(COLON);
			setState(192);
			match(NEWLINE);
			setState(193);
			match(INDENT);
			setState(195); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(194);
				decl();
				}
				}
				setState(197); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0) );
			setState(199);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(KoordParser.TYPE, 0); }
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(TYPE);
			setState(202);
			match(VARNAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EventContext extends ParserRuleContext {
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public List<TerminalNode> COLON() { return getTokens(KoordParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(KoordParser.COLON, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(KoordParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(KoordParser.NEWLINE, i);
		}
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode PRE() { return getToken(KoordParser.PRE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EFF() { return getToken(KoordParser.EFF, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public EventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitEvent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitEvent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventContext event() throws RecognitionException {
		EventContext _localctx = new EventContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_event);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(VARNAME);
			setState(205);
			match(COLON);
			setState(206);
			match(NEWLINE);
			setState(207);
			match(INDENT);
			setState(208);
			match(PRE);
			setState(209);
			match(COLON);
			setState(210);
			expr();
			setState(211);
			match(NEWLINE);
			setState(212);
			match(EFF);
			setState(213);
			match(COLON);
			setState(214);
			match(NEWLINE);
			setState(215);
			statementblock();
			setState(216);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementblockContext extends ParserRuleContext {
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StatementblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementblock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterStatementblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitStatementblock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitStatementblock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementblockContext statementblock() throws RecognitionException {
		StatementblockContext _localctx = new StatementblockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statementblock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(INDENT);
			setState(220); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(219);
				stmt();
				}
				}
				setState(222); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3377734248038656L) != 0) );
			setState(224);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public FunccallContext funccall() {
			return getRuleContext(FunccallContext.class,0);
		}
		public IostreamContext iostream() {
			return getRuleContext(IostreamContext.class,0);
		}
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public ForloopContext forloop() {
			return getRuleContext(ForloopContext.class,0);
		}
		public TerminalNode STOP() { return getToken(KoordParser.STOP, 0); }
		public TerminalNode ATOMIC() { return getToken(KoordParser.ATOMIC, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt);
		try {
			setState(243);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				assign();
				setState(227);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				funccall();
				setState(230);
				match(NEWLINE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(232);
				iostream(0);
				setState(233);
				match(NEWLINE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(235);
				conditional();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(236);
				forloop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(237);
				match(STOP);
				setState(238);
				match(NEWLINE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(239);
				match(ATOMIC);
				setState(240);
				match(COLON);
				setState(241);
				match(NEWLINE);
				setState(242);
				statementblock();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForloopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(KoordParser.FOR, 0); }
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode ASGN() { return getToken(KoordParser.ASGN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(KoordParser.COMMA, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public ForloopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forloop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterForloop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitForloop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitForloop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForloopContext forloop() throws RecognitionException {
		ForloopContext _localctx = new ForloopContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_forloop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(FOR);
			setState(246);
			match(VARNAME);
			setState(247);
			match(ASGN);
			setState(248);
			expr();
			setState(249);
			match(COMMA);
			setState(250);
			expr();
			setState(251);
			match(COLON);
			setState(252);
			match(NEWLINE);
			setState(253);
			statementblock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(KoordParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public ElseblockContext elseblock() {
			return getRuleContext(ElseblockContext.class,0);
		}
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_conditional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(IF);
			setState(256);
			expr();
			setState(257);
			match(COLON);
			setState(258);
			match(NEWLINE);
			setState(259);
			statementblock();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(260);
				elseblock();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElseblockContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(KoordParser.ELSE, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public ElseblockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseblock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterElseblock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitElseblock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitElseblock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseblockContext elseblock() throws RecognitionException {
		ElseblockContext _localctx = new ElseblockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_elseblock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(ELSE);
			setState(264);
			match(COLON);
			setState(265);
			match(NEWLINE);
			setState(266);
			statementblock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IostreamContext extends ParserRuleContext {
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode LSHIFT() { return getToken(KoordParser.LSHIFT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public IostreamContext iostream() {
			return getRuleContext(IostreamContext.class,0);
		}
		public IostreamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_iostream; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterIostream(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitIostream(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitIostream(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IostreamContext iostream() throws RecognitionException {
		return iostream(0);
	}

	private IostreamContext iostream(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IostreamContext _localctx = new IostreamContext(_ctx, _parentState);
		IostreamContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_iostream, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(269);
			match(VARNAME);
			setState(270);
			match(LSHIFT);
			setState(271);
			expr();
			}
			_ctx.stop = _input.LT(-1);
			setState(278);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new IostreamContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_iostream);
					setState(273);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(274);
					match(LSHIFT);
					setState(275);
					expr();
					}
					} 
				}
				setState(280);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunccallContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(KoordParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(KoordParser.RPAR, 0); }
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode UPPER() { return getToken(KoordParser.UPPER, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public FunccallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funccall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterFunccall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitFunccall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitFunccall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunccallContext funccall() throws RecognitionException {
		FunccallContext _localctx = new FunccallContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funccall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			_la = _input.LA(1);
			if ( !(_la==UPPER || _la==VARNAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(282);
			match(LPAR);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 30)) & ~0x3f) == 0 && ((1L << (_la - 30)) & 17229677087L) != 0)) {
				{
				setState(283);
				arglist();
				}
			}

			setState(286);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArglistContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KoordParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KoordParser.COMMA, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterArglist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitArglist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitArglist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			expr();
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(289);
				match(COMMA);
				setState(290);
				expr();
				}
				}
				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode ASGN() { return getToken(KoordParser.ASGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(KoordParser.LBRACE, 0); }
		public AexprContext aexpr() {
			return getRuleContext(AexprContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(KoordParser.RBRACE, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(VARNAME);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(297);
				match(LBRACE);
				setState(298);
				aexpr(0);
				setState(299);
				match(RBRACE);
				}
			}

			setState(303);
			match(ASGN);
			setState(304);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public AexprContext aexpr() {
			return getRuleContext(AexprContext.class,0);
		}
		public BexprContext bexpr() {
			return getRuleContext(BexprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr);
		try {
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				aexpr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				bexpr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BexprContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(KoordParser.NOT, 0); }
		public List<BexprContext> bexpr() {
			return getRuleContexts(BexprContext.class);
		}
		public BexprContext bexpr(int i) {
			return getRuleContext(BexprContext.class,i);
		}
		public TerminalNode LPAR() { return getToken(KoordParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(KoordParser.RPAR, 0); }
		public List<AexprContext> aexpr() {
			return getRuleContexts(AexprContext.class);
		}
		public AexprContext aexpr(int i) {
			return getRuleContext(AexprContext.class,i);
		}
		public RelopContext relop() {
			return getRuleContext(RelopContext.class,0);
		}
		public TerminalNode FALSE() { return getToken(KoordParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(KoordParser.TRUE, 0); }
		public FunccallContext funccall() {
			return getRuleContext(FunccallContext.class,0);
		}
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode AND() { return getToken(KoordParser.AND, 0); }
		public TerminalNode OR() { return getToken(KoordParser.OR, 0); }
		public BexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterBexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitBexpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitBexpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BexprContext bexpr() throws RecognitionException {
		return bexpr(0);
	}

	private BexprContext bexpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BexprContext _localctx = new BexprContext(_ctx, _parentState);
		BexprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_bexpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(311);
				match(NOT);
				setState(312);
				bexpr(10);
				}
				break;
			case 2:
				{
				setState(313);
				match(LPAR);
				setState(314);
				bexpr(0);
				setState(315);
				match(RPAR);
				}
				break;
			case 3:
				{
				setState(317);
				aexpr(0);
				setState(318);
				relop();
				setState(319);
				aexpr(0);
				}
				break;
			case 4:
				{
				setState(321);
				match(FALSE);
				}
				break;
			case 5:
				{
				setState(322);
				match(TRUE);
				}
				break;
			case 6:
				{
				setState(323);
				funccall();
				}
				break;
			case 7:
				{
				setState(324);
				match(VARNAME);
				}
				break;
			case 8:
				{
				setState(325);
				aexpr(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(336);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(334);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
					case 1:
						{
						_localctx = new BexprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_bexpr);
						setState(328);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(329);
						match(AND);
						setState(330);
						bexpr(8);
						}
						break;
					case 2:
						{
						_localctx = new BexprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_bexpr);
						setState(331);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(332);
						match(OR);
						setState(333);
						bexpr(7);
						}
						break;
					}
					} 
				}
				setState(338);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AexprContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(KoordParser.LPAR, 0); }
		public List<AexprContext> aexpr() {
			return getRuleContexts(AexprContext.class);
		}
		public AexprContext aexpr(int i) {
			return getRuleContext(AexprContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(KoordParser.RPAR, 0); }
		public TerminalNode MINUS() { return getToken(KoordParser.MINUS, 0); }
		public FunccallContext funccall() {
			return getRuleContext(FunccallContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode LBRACE() { return getToken(KoordParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KoordParser.RBRACE, 0); }
		public TerminalNode STRING() { return getToken(KoordParser.STRING, 0); }
		public TerminalNode NONE() { return getToken(KoordParser.NONE, 0); }
		public TerminalNode TIMES() { return getToken(KoordParser.TIMES, 0); }
		public TerminalNode BY() { return getToken(KoordParser.BY, 0); }
		public TerminalNode PLUS() { return getToken(KoordParser.PLUS, 0); }
		public AexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aexpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterAexpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitAexpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitAexpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AexprContext aexpr() throws RecognitionException {
		return aexpr(0);
	}

	private AexprContext aexpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AexprContext _localctx = new AexprContext(_ctx, _parentState);
		AexprContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_aexpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(340);
				match(LPAR);
				setState(341);
				aexpr(0);
				setState(342);
				match(RPAR);
				}
				break;
			case 2:
				{
				setState(344);
				match(MINUS);
				setState(345);
				aexpr(9);
				}
				break;
			case 3:
				{
				setState(346);
				funccall();
				}
				break;
			case 4:
				{
				setState(347);
				constant();
				}
				break;
			case 5:
				{
				setState(348);
				match(VARNAME);
				setState(349);
				match(LBRACE);
				setState(350);
				aexpr(0);
				setState(351);
				match(RBRACE);
				}
				break;
			case 6:
				{
				setState(353);
				match(STRING);
				}
				break;
			case 7:
				{
				setState(354);
				match(NONE);
				}
				break;
			case 8:
				{
				setState(355);
				match(VARNAME);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(366);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(364);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
					case 1:
						{
						_localctx = new AexprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_aexpr);
						setState(358);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(359);
						_la = _input.LA(1);
						if ( !(_la==TIMES || _la==BY) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(360);
						aexpr(9);
						}
						break;
					case 2:
						{
						_localctx = new AexprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_aexpr);
						setState(361);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(362);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(363);
						aexpr(8);
						}
						break;
					}
					} 
				}
				setState(368);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode FNUM() { return getToken(KoordParser.FNUM, 0); }
		public TerminalNode INUM() { return getToken(KoordParser.INUM, 0); }
		public TerminalNode PID() { return getToken(KoordParser.PID, 0); }
		public TerminalNode NUMAGENTS() { return getToken(KoordParser.NUMAGENTS, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 13510824651915264L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelopContext extends ParserRuleContext {
		public TerminalNode LANGLE() { return getToken(KoordParser.LANGLE, 0); }
		public TerminalNode RANGLE() { return getToken(KoordParser.RANGLE, 0); }
		public TerminalNode GEQ() { return getToken(KoordParser.GEQ, 0); }
		public TerminalNode LEQ() { return getToken(KoordParser.LEQ, 0); }
		public TerminalNode EQ() { return getToken(KoordParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(KoordParser.NEQ, 0); }
		public RelopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterRelop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitRelop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitRelop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelopContext relop() throws RecognitionException {
		RelopContext _localctx = new RelopContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_relop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4323561195391942656L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllwritevarsContext extends ParserRuleContext {
		public TerminalNode ALLWRITE() { return getToken(KoordParser.ALLWRITE, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public AllwritevarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allwritevars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterAllwritevars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitAllwritevars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitAllwritevars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllwritevarsContext allwritevars() throws RecognitionException {
		AllwritevarsContext _localctx = new AllwritevarsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_allwritevars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(ALLWRITE);
			setState(374);
			match(COLON);
			setState(375);
			match(NEWLINE);
			setState(376);
			match(INDENT);
			setState(378); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(377);
				decl();
				}
				}
				setState(380); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0) );
			setState(382);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllreadvarsContext extends ParserRuleContext {
		public TerminalNode ALLREAD() { return getToken(KoordParser.ALLREAD, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public AllreadvarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allreadvars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterAllreadvars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitAllreadvars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitAllreadvars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllreadvarsContext allreadvars() throws RecognitionException {
		AllreadvarsContext _localctx = new AllreadvarsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_allreadvars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			match(ALLREAD);
			setState(385);
			match(COLON);
			setState(386);
			match(NEWLINE);
			setState(387);
			match(INDENT);
			setState(389); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(388);
				decl();
				}
				}
				setState(391); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0) );
			setState(393);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocalvarsContext extends ParserRuleContext {
		public TerminalNode LOCAL() { return getToken(KoordParser.LOCAL, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public LocalvarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localvars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterLocalvars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitLocalvars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitLocalvars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalvarsContext localvars() throws RecognitionException {
		LocalvarsContext _localctx = new LocalvarsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_localvars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(LOCAL);
			setState(396);
			match(COLON);
			setState(397);
			match(NEWLINE);
			setState(398);
			match(INDENT);
			setState(400); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(399);
				decl();
				}
				}
				setState(402); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0) );
			setState(404);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public TerminalNode VARNAME() { return getToken(KoordParser.VARNAME, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INT() { return getToken(KoordParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(KoordParser.BOOL, 0); }
		public TerminalNode FLOAT() { return getToken(KoordParser.FLOAT, 0); }
		public TerminalNode POS() { return getToken(KoordParser.POS, 0); }
		public TerminalNode QUEUE() { return getToken(KoordParser.QUEUE, 0); }
		public TerminalNode STRINGTYPE() { return getToken(KoordParser.STRINGTYPE, 0); }
		public TerminalNode STREAM() { return getToken(KoordParser.STREAM, 0); }
		public TerminalNode UPPER() { return getToken(KoordParser.UPPER, 0); }
		public List<ArraydecContext> arraydec() {
			return getRuleContexts(ArraydecContext.class);
		}
		public ArraydecContext arraydec(int i) {
			return getRuleContext(ArraydecContext.class,i);
		}
		public TerminalNode ASGN() { return getToken(KoordParser.ASGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACE) {
				{
				{
				setState(407);
				arraydec();
				}
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(413);
			match(VARNAME);
			setState(416);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASGN) {
				{
				setState(414);
				match(ASGN);
				setState(415);
				expr();
				}
			}

			setState(418);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArraydecContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(KoordParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(KoordParser.RBRACE, 0); }
		public ArraydecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arraydec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterArraydec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitArraydec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitArraydec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArraydecContext arraydec() throws RecognitionException {
		ArraydecContext _localctx = new ArraydecContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_arraydec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(LBRACE);
			setState(421);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleContext extends ParserRuleContext {
		public TerminalNode USING() { return getToken(KoordParser.USING, 0); }
		public TerminalNode UPPER() { return getToken(KoordParser.UPPER, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public ActuatordeclsContext actuatordecls() {
			return getRuleContext(ActuatordeclsContext.class,0);
		}
		public SensordeclsContext sensordecls() {
			return getRuleContext(SensordeclsContext.class,0);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(USING);
			setState(424);
			match(UPPER);
			setState(425);
			match(COLON);
			setState(426);
			match(NEWLINE);
			setState(427);
			match(INDENT);
			setState(440);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ACTUATORS) {
					{
					setState(428);
					actuatordecls();
					}
				}

				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SENSORS) {
					{
					setState(431);
					sensordecls();
					}
				}

				}
				break;
			case 2:
				{
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SENSORS) {
					{
					setState(434);
					sensordecls();
					}
				}

				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ACTUATORS) {
					{
					setState(437);
					actuatordecls();
					}
				}

				}
				break;
			}
			setState(442);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActuatordeclsContext extends ParserRuleContext {
		public TerminalNode ACTUATORS() { return getToken(KoordParser.ACTUATORS, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ActuatordeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actuatordecls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterActuatordecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitActuatordecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitActuatordecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActuatordeclsContext actuatordecls() throws RecognitionException {
		ActuatordeclsContext _localctx = new ActuatordeclsContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_actuatordecls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(ACTUATORS);
			setState(445);
			match(COLON);
			setState(446);
			match(NEWLINE);
			setState(447);
			match(INDENT);
			setState(449); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(448);
				decl();
				}
				}
				setState(451); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0) );
			setState(453);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SensordeclsContext extends ParserRuleContext {
		public TerminalNode SENSORS() { return getToken(KoordParser.SENSORS, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(KoordParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(KoordParser.DEDENT, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public SensordeclsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sensordecls; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterSensordecls(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitSensordecls(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitSensordecls(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SensordeclsContext sensordecls() throws RecognitionException {
		SensordeclsContext _localctx = new SensordeclsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_sensordecls);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			match(SENSORS);
			setState(456);
			match(COLON);
			setState(457);
			match(NEWLINE);
			setState(458);
			match(INDENT);
			setState(460); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(459);
				decl();
				}
				}
				setState(462); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1125899923423232L) != 0) );
			setState(464);
			match(DEDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InitContext extends ParserRuleContext {
		public TerminalNode INIT() { return getToken(KoordParser.INIT, 0); }
		public TerminalNode COLON() { return getToken(KoordParser.COLON, 0); }
		public TerminalNode NEWLINE() { return getToken(KoordParser.NEWLINE, 0); }
		public StatementblockContext statementblock() {
			return getRuleContext(StatementblockContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KoordListener ) ((KoordListener)listener).exitInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KoordVisitor ) return ((KoordVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			match(INIT);
			setState(467);
			match(COLON);
			setState(468);
			match(NEWLINE);
			setState(469);
			statementblock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return iostream_sempred((IostreamContext)_localctx, predIndex);
		case 16:
			return bexpr_sempred((BexprContext)_localctx, predIndex);
		case 17:
			return aexpr_sempred((AexprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean iostream_sempred(IostreamContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean bexpr_sempred(BexprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean aexpr_sempred(AexprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001D\u01d8\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0003\u0000<\b\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000@\b\u0000\n\u0000\f\u0000C\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0005\u0000H\b\u0000\n\u0000\f\u0000K\t\u0000"+
		"\u0001\u0000\u0003\u0000N\b\u0000\u0001\u0000\u0004\u0000Q\b\u0000\u000b"+
		"\u0000\f\u0000R\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000X\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000]\b\u0000\n\u0000\f\u0000"+
		"`\t\u0000\u0001\u0000\u0003\u0000c\b\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0005\u0000j\b\u0000\n\u0000\f\u0000m\t"+
		"\u0000\u0001\u0000\u0005\u0000p\b\u0000\n\u0000\f\u0000s\t\u0000\u0001"+
		"\u0000\u0003\u0000v\b\u0000\u0001\u0000\u0004\u0000y\b\u0000\u000b\u0000"+
		"\f\u0000z\u0004\u0000}\b\u0000\u000b\u0000\f\u0000~\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0003\u0000\u0084\b\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0005\u0000\u0089\b\u0000\n\u0000\f\u0000\u008c\t\u0000\u0001\u0000"+
		"\u0005\u0000\u008f\b\u0000\n\u0000\f\u0000\u0092\t\u0000\u0001\u0000\u0003"+
		"\u0000\u0095\b\u0000\u0001\u0000\u0003\u0000\u0098\b\u0000\u0001\u0000"+
		"\u0004\u0000\u009b\b\u0000\u000b\u0000\f\u0000\u009c\u0001\u0000\u0001"+
		"\u0000\u0003\u0000\u00a1\b\u0000\u0001\u0001\u0005\u0001\u00a4\b\u0001"+
		"\n\u0001\f\u0001\u00a7\t\u0001\u0001\u0001\u0005\u0001\u00aa\b\u0001\n"+
		"\u0001\f\u0001\u00ad\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0005\u0002\u00b4\b\u0002\n\u0002\f\u0002\u00b7\t\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003"+
		"\u00c4\b\u0003\u000b\u0003\f\u0003\u00c5\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0004\u0006\u00dd\b\u0006\u000b\u0006\f\u0006\u00de\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u00f4\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u0106\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u0115\b\u000b\n\u000b\f\u000b\u0118\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u011d\b\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u0124\b\r\n\r\f\r\u0127\t\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u012e\b\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u0135\b\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0147\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0005\u0010\u014f\b\u0010\n\u0010\f\u0010\u0152\t\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0165\b\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005"+
		"\u0011\u016d\b\u0011\n\u0011\f\u0011\u0170\t\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0004\u0014\u017b\b\u0014\u000b\u0014\f\u0014\u017c\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0004\u0015\u0186\b\u0015\u000b\u0015\f\u0015\u0187\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0004\u0016\u0191\b\u0016\u000b\u0016\f\u0016\u0192\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0005\u0017\u0199\b\u0017\n\u0017\f\u0017"+
		"\u019c\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01a1\b"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u01ae\b\u0019\u0001\u0019\u0003\u0019\u01b1\b\u0019\u0001\u0019"+
		"\u0003\u0019\u01b4\b\u0019\u0001\u0019\u0003\u0019\u01b7\b\u0019\u0003"+
		"\u0019\u01b9\b\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0004\u001a\u01c2\b\u001a\u000b\u001a\f"+
		"\u001a\u01c3\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0004\u001b\u01cd\b\u001b\u000b\u001b\f\u001b"+
		"\u01ce\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0000\u0003\u0016 \"\u001d\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&(*,.02468\u0000\u0006\u0001\u000023\u0001\u000089\u0001\u000067\u0002"+
		"\u0000!\"45\u0002\u0000-.:=\u0003\u0000\u0010\u0010\u0012\u001722\u0202"+
		"\u0000\u00a0\u0001\u0000\u0000\u0000\u0002\u00a5\u0001\u0000\u0000\u0000"+
		"\u0004\u00ae\u0001\u0000\u0000\u0000\u0006\u00bd\u0001\u0000\u0000\u0000"+
		"\b\u00c9\u0001\u0000\u0000\u0000\n\u00cc\u0001\u0000\u0000\u0000\f\u00da"+
		"\u0001\u0000\u0000\u0000\u000e\u00f3\u0001\u0000\u0000\u0000\u0010\u00f5"+
		"\u0001\u0000\u0000\u0000\u0012\u00ff\u0001\u0000\u0000\u0000\u0014\u0107"+
		"\u0001\u0000\u0000\u0000\u0016\u010c\u0001\u0000\u0000\u0000\u0018\u0119"+
		"\u0001\u0000\u0000\u0000\u001a\u0120\u0001\u0000\u0000\u0000\u001c\u0128"+
		"\u0001\u0000\u0000\u0000\u001e\u0134\u0001\u0000\u0000\u0000 \u0146\u0001"+
		"\u0000\u0000\u0000\"\u0164\u0001\u0000\u0000\u0000$\u0171\u0001\u0000"+
		"\u0000\u0000&\u0173\u0001\u0000\u0000\u0000(\u0175\u0001\u0000\u0000\u0000"+
		"*\u0180\u0001\u0000\u0000\u0000,\u018b\u0001\u0000\u0000\u0000.\u0196"+
		"\u0001\u0000\u0000\u00000\u01a4\u0001\u0000\u0000\u00002\u01a7\u0001\u0000"+
		"\u0000\u00004\u01bc\u0001\u0000\u0000\u00006\u01c7\u0001\u0000\u0000\u0000"+
		"8\u01d2\u0001\u0000\u0000\u0000:<\u0005A\u0000\u0000;:\u0001\u0000\u0000"+
		"\u0000;<\u0001\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=A\u0003\u0002"+
		"\u0001\u0000>@\u00032\u0019\u0000?>\u0001\u0000\u0000\u0000@C\u0001\u0000"+
		"\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000BI\u0001"+
		"\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000DH\u0003*\u0015\u0000EH\u0003"+
		"(\u0014\u0000FH\u0003,\u0016\u0000GD\u0001\u0000\u0000\u0000GE\u0001\u0000"+
		"\u0000\u0000GF\u0001\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001"+
		"\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000\u0000"+
		"KI\u0001\u0000\u0000\u0000LN\u00038\u001c\u0000ML\u0001\u0000\u0000\u0000"+
		"MN\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000OQ\u0003\n\u0005\u0000"+
		"PO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000"+
		"\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0005\u0000"+
		"\u0000\u0001U\u00a1\u0001\u0000\u0000\u0000VX\u0005A\u0000\u0000WV\u0001"+
		"\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000"+
		"Y^\u0003\u0002\u0001\u0000Z]\u0003*\u0015\u0000[]\u0003(\u0014\u0000\\"+
		"Z\u0001\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000]`\u0001\u0000\u0000"+
		"\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_b\u0001\u0000"+
		"\u0000\u0000`^\u0001\u0000\u0000\u0000ac\u00038\u001c\u0000ba\u0001\u0000"+
		"\u0000\u0000bc\u0001\u0000\u0000\u0000c|\u0001\u0000\u0000\u0000de\u0005"+
		"\u0001\u0000\u0000ef\u0005\'\u0000\u0000fg\u00052\u0000\u0000gk\u0005"+
		"(\u0000\u0000hj\u0005A\u0000\u0000ih\u0001\u0000\u0000\u0000jm\u0001\u0000"+
		"\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lq\u0001"+
		"\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000np\u00032\u0019\u0000on\u0001"+
		"\u0000\u0000\u0000ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000"+
		"qr\u0001\u0000\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000"+
		"\u0000tv\u0003,\u0016\u0000ut\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000"+
		"\u0000vx\u0001\u0000\u0000\u0000wy\u0003\n\u0005\u0000xw\u0001\u0000\u0000"+
		"\u0000yz\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{}\u0001\u0000\u0000\u0000|d\u0001\u0000\u0000\u0000}~\u0001"+
		"\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000"+
		"\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u0000\u0000"+
		"\u0001\u0081\u00a1\u0001\u0000\u0000\u0000\u0082\u0084\u0005A\u0000\u0000"+
		"\u0083\u0082\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000"+
		"\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u008a\u0003\u0002\u0001\u0000"+
		"\u0086\u0089\u0003*\u0015\u0000\u0087\u0089\u0003(\u0014\u0000\u0088\u0086"+
		"\u0001\u0000\u0000\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089\u008c"+
		"\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0001\u0000\u0000\u0000\u008b\u0090\u0001\u0000\u0000\u0000\u008c\u008a"+
		"\u0001\u0000\u0000\u0000\u008d\u008f\u00032\u0019\u0000\u008e\u008d\u0001"+
		"\u0000\u0000\u0000\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u008e\u0001"+
		"\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0094\u0001"+
		"\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0095\u0003"+
		",\u0016\u0000\u0094\u0093\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000"+
		"\u0000\u0000\u0095\u0097\u0001\u0000\u0000\u0000\u0096\u0098\u00038\u001c"+
		"\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000"+
		"\u0000\u0098\u009a\u0001\u0000\u0000\u0000\u0099\u009b\u0003\n\u0005\u0000"+
		"\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000"+
		"\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000"+
		"\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0000\u0000\u0001"+
		"\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0;\u0001\u0000\u0000\u0000\u00a0"+
		"W\u0001\u0000\u0000\u0000\u00a0\u0083\u0001\u0000\u0000\u0000\u00a1\u0001"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a4\u0003\u0004\u0002\u0000\u00a3\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00ab"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00aa"+
		"\u0003\u0006\u0003\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ac\u0003\u0001\u0000\u0000\u0000\u00ad\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0005\u0004\u0000\u0000\u00af\u00b0"+
		"\u0005\u0006\u0000\u0000\u00b0\u00b1\u00053\u0000\u0000\u00b1\u00b5\u0005"+
		"\'\u0000\u0000\u00b2\u00b4\u0003\b\u0004\u0000\u00b3\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005(\u0000"+
		"\u0000\u00b9\u00ba\u0005$\u0000\u0000\u00ba\u00bb\u0005A\u0000\u0000\u00bb"+
		"\u00bc\u0003\f\u0006\u0000\u00bc\u0005\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\u0005\u0004\u0000\u0000\u00be\u00bf\u00052\u0000\u0000\u00bf\u00c0\u0005"+
		"$\u0000\u0000\u00c0\u00c1\u0005A\u0000\u0000\u00c1\u00c3\u0005C\u0000"+
		"\u0000\u00c2\u00c4\u0003.\u0017\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c8\u0005D\u0000\u0000\u00c8\u0007\u0001\u0000\u0000\u0000\u00c9"+
		"\u00ca\u0005\u0005\u0000\u0000\u00ca\u00cb\u00053\u0000\u0000\u00cb\t"+
		"\u0001\u0000\u0000\u0000\u00cc\u00cd\u00053\u0000\u0000\u00cd\u00ce\u0005"+
		"$\u0000\u0000\u00ce\u00cf\u0005A\u0000\u0000\u00cf\u00d0\u0005C\u0000"+
		"\u0000\u00d0\u00d1\u0005\u001c\u0000\u0000\u00d1\u00d2\u0005$\u0000\u0000"+
		"\u00d2\u00d3\u0003\u001e\u000f\u0000\u00d3\u00d4\u0005A\u0000\u0000\u00d4"+
		"\u00d5\u0005\u001d\u0000\u0000\u00d5\u00d6\u0005$\u0000\u0000\u00d6\u00d7"+
		"\u0005A\u0000\u0000\u00d7\u00d8\u0003\f\u0006\u0000\u00d8\u00d9\u0005"+
		"D\u0000\u0000\u00d9\u000b\u0001\u0000\u0000\u0000\u00da\u00dc\u0005C\u0000"+
		"\u0000\u00db\u00dd\u0003\u000e\u0007\u0000\u00dc\u00db\u0001\u0000\u0000"+
		"\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000"+
		"\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e0\u00e1\u0005D\u0000\u0000\u00e1\r\u0001\u0000\u0000\u0000"+
		"\u00e2\u00e3\u0003\u001c\u000e\u0000\u00e3\u00e4\u0005A\u0000\u0000\u00e4"+
		"\u00f4\u0001\u0000\u0000\u0000\u00e5\u00e6\u0003\u0018\f\u0000\u00e6\u00e7"+
		"\u0005A\u0000\u0000\u00e7\u00f4\u0001\u0000\u0000\u0000\u00e8\u00e9\u0003"+
		"\u0016\u000b\u0000\u00e9\u00ea\u0005A\u0000\u0000\u00ea\u00f4\u0001\u0000"+
		"\u0000\u0000\u00eb\u00f4\u0003\u0012\t\u0000\u00ec\u00f4\u0003\u0010\b"+
		"\u0000\u00ed\u00ee\u0005#\u0000\u0000\u00ee\u00f4\u0005A\u0000\u0000\u00ef"+
		"\u00f0\u0005\u001b\u0000\u0000\u00f0\u00f1\u0005$\u0000\u0000\u00f1\u00f2"+
		"\u0005A\u0000\u0000\u00f2\u00f4\u0003\f\u0006\u0000\u00f3\u00e2\u0001"+
		"\u0000\u0000\u0000\u00f3\u00e5\u0001\u0000\u0000\u0000\u00f3\u00e8\u0001"+
		"\u0000\u0000\u0000\u00f3\u00eb\u0001\u0000\u0000\u0000\u00f3\u00ec\u0001"+
		"\u0000\u0000\u0000\u00f3\u00ed\u0001\u0000\u0000\u0000\u00f3\u00ef\u0001"+
		"\u0000\u0000\u0000\u00f4\u000f\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005"+
		"\b\u0000\u0000\u00f6\u00f7\u00053\u0000\u0000\u00f7\u00f8\u0005>\u0000"+
		"\u0000\u00f8\u00f9\u0003\u001e\u000f\u0000\u00f9\u00fa\u0005%\u0000\u0000"+
		"\u00fa\u00fb\u0003\u001e\u000f\u0000\u00fb\u00fc\u0005$\u0000\u0000\u00fc"+
		"\u00fd\u0005A\u0000\u0000\u00fd\u00fe\u0003\f\u0006\u0000\u00fe\u0011"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\u0019\u0000\u0000\u0100\u0101"+
		"\u0003\u001e\u000f\u0000\u0101\u0102\u0005$\u0000\u0000\u0102\u0103\u0005"+
		"A\u0000\u0000\u0103\u0105\u0003\f\u0006\u0000\u0104\u0106\u0003\u0014"+
		"\n\u0000\u0105\u0104\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000"+
		"\u0000\u0106\u0013\u0001\u0000\u0000\u0000\u0107\u0108\u0005\u001a\u0000"+
		"\u0000\u0108\u0109\u0005$\u0000\u0000\u0109\u010a\u0005A\u0000\u0000\u010a"+
		"\u010b\u0003\f\u0006\u0000\u010b\u0015\u0001\u0000\u0000\u0000\u010c\u010d"+
		"\u0006\u000b\uffff\uffff\u0000\u010d\u010e\u00053\u0000\u0000\u010e\u010f"+
		"\u0005?\u0000\u0000\u010f\u0110\u0003\u001e\u000f\u0000\u0110\u0116\u0001"+
		"\u0000\u0000\u0000\u0111\u0112\n\u0001\u0000\u0000\u0112\u0113\u0005?"+
		"\u0000\u0000\u0113\u0115\u0003\u001e\u000f\u0000\u0114\u0111\u0001\u0000"+
		"\u0000\u0000\u0115\u0118\u0001\u0000\u0000\u0000\u0116\u0114\u0001\u0000"+
		"\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0017\u0001\u0000"+
		"\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0119\u011a\u0007\u0000"+
		"\u0000\u0000\u011a\u011c\u0005\'\u0000\u0000\u011b\u011d\u0003\u001a\r"+
		"\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000"+
		"\u0000\u011d\u011e\u0001\u0000\u0000\u0000\u011e\u011f\u0005(\u0000\u0000"+
		"\u011f\u0019\u0001\u0000\u0000\u0000\u0120\u0125\u0003\u001e\u000f\u0000"+
		"\u0121\u0122\u0005%\u0000\u0000\u0122\u0124\u0003\u001e\u000f\u0000\u0123"+
		"\u0121\u0001\u0000\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125"+
		"\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126"+
		"\u001b\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128"+
		"\u012d\u00053\u0000\u0000\u0129\u012a\u0005)\u0000\u0000\u012a\u012b\u0003"+
		"\"\u0011\u0000\u012b\u012c\u0005*\u0000\u0000\u012c\u012e\u0001\u0000"+
		"\u0000\u0000\u012d\u0129\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000"+
		"\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0130\u0005>\u0000"+
		"\u0000\u0130\u0131\u0003\u001e\u000f\u0000\u0131\u001d\u0001\u0000\u0000"+
		"\u0000\u0132\u0135\u0003\"\u0011\u0000\u0133\u0135\u0003 \u0010\u0000"+
		"\u0134\u0132\u0001\u0000\u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000"+
		"\u0135\u001f\u0001\u0000\u0000\u0000\u0136\u0137\u0006\u0010\uffff\uffff"+
		"\u0000\u0137\u0138\u00051\u0000\u0000\u0138\u0147\u0003 \u0010\n\u0139"+
		"\u013a\u0005\'\u0000\u0000\u013a\u013b\u0003 \u0010\u0000\u013b\u013c"+
		"\u0005(\u0000\u0000\u013c\u0147\u0001\u0000\u0000\u0000\u013d\u013e\u0003"+
		"\"\u0011\u0000\u013e\u013f\u0003&\u0013\u0000\u013f\u0140\u0003\"\u0011"+
		"\u0000\u0140\u0147\u0001\u0000\u0000\u0000\u0141\u0147\u0005\u001f\u0000"+
		"\u0000\u0142\u0147\u0005\u001e\u0000\u0000\u0143\u0147\u0003\u0018\f\u0000"+
		"\u0144\u0147\u00053\u0000\u0000\u0145\u0147\u0003\"\u0011\u0000\u0146"+
		"\u0136\u0001\u0000\u0000\u0000\u0146\u0139\u0001\u0000\u0000\u0000\u0146"+
		"\u013d\u0001\u0000\u0000\u0000\u0146\u0141\u0001\u0000\u0000\u0000\u0146"+
		"\u0142\u0001\u0000\u0000\u0000\u0146\u0143\u0001\u0000\u0000\u0000\u0146"+
		"\u0144\u0001\u0000\u0000\u0000\u0146\u0145\u0001\u0000\u0000\u0000\u0147"+
		"\u0150\u0001\u0000\u0000\u0000\u0148\u0149\n\u0007\u0000\u0000\u0149\u014a"+
		"\u0005/\u0000\u0000\u014a\u014f\u0003 \u0010\b\u014b\u014c\n\u0006\u0000"+
		"\u0000\u014c\u014d\u00050\u0000\u0000\u014d\u014f\u0003 \u0010\u0007\u014e"+
		"\u0148\u0001\u0000\u0000\u0000\u014e\u014b\u0001\u0000\u0000\u0000\u014f"+
		"\u0152\u0001\u0000\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0150"+
		"\u0151\u0001\u0000\u0000\u0000\u0151!\u0001\u0000\u0000\u0000\u0152\u0150"+
		"\u0001\u0000\u0000\u0000\u0153\u0154\u0006\u0011\uffff\uffff\u0000\u0154"+
		"\u0155\u0005\'\u0000\u0000\u0155\u0156\u0003\"\u0011\u0000\u0156\u0157"+
		"\u0005(\u0000\u0000\u0157\u0165\u0001\u0000\u0000\u0000\u0158\u0159\u0005"+
		"7\u0000\u0000\u0159\u0165\u0003\"\u0011\t\u015a\u0165\u0003\u0018\f\u0000"+
		"\u015b\u0165\u0003$\u0012\u0000\u015c\u015d\u00053\u0000\u0000\u015d\u015e"+
		"\u0005)\u0000\u0000\u015e\u015f\u0003\"\u0011\u0000\u015f\u0160\u0005"+
		"*\u0000\u0000\u0160\u0165\u0001\u0000\u0000\u0000\u0161\u0165\u0005@\u0000"+
		"\u0000\u0162\u0165\u0005 \u0000\u0000\u0163\u0165\u00053\u0000\u0000\u0164"+
		"\u0153\u0001\u0000\u0000\u0000\u0164\u0158\u0001\u0000\u0000\u0000\u0164"+
		"\u015a\u0001\u0000\u0000\u0000\u0164\u015b\u0001\u0000\u0000\u0000\u0164"+
		"\u015c\u0001\u0000\u0000\u0000\u0164\u0161\u0001\u0000\u0000\u0000\u0164"+
		"\u0162\u0001\u0000\u0000\u0000\u0164\u0163\u0001\u0000\u0000\u0000\u0165"+
		"\u016e\u0001\u0000\u0000\u0000\u0166\u0167\n\b\u0000\u0000\u0167\u0168"+
		"\u0007\u0001\u0000\u0000\u0168\u016d\u0003\"\u0011\t\u0169\u016a\n\u0007"+
		"\u0000\u0000\u016a\u016b\u0007\u0002\u0000\u0000\u016b\u016d\u0003\"\u0011"+
		"\b\u016c\u0166\u0001\u0000\u0000\u0000\u016c\u0169\u0001\u0000\u0000\u0000"+
		"\u016d\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000"+
		"\u016e\u016f\u0001\u0000\u0000\u0000\u016f#\u0001\u0000\u0000\u0000\u0170"+
		"\u016e\u0001\u0000\u0000\u0000\u0171\u0172\u0007\u0003\u0000\u0000\u0172"+
		"%\u0001\u0000\u0000\u0000\u0173\u0174\u0007\u0004\u0000\u0000\u0174\'"+
		"\u0001\u0000\u0000\u0000\u0175\u0176\u0005\u000b\u0000\u0000\u0176\u0177"+
		"\u0005$\u0000\u0000\u0177\u0178\u0005A\u0000\u0000\u0178\u017a\u0005C"+
		"\u0000\u0000\u0179\u017b\u0003.\u0017\u0000\u017a\u0179\u0001\u0000\u0000"+
		"\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c\u017a\u0001\u0000\u0000"+
		"\u0000\u017c\u017d\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000"+
		"\u0000\u017e\u017f\u0005D\u0000\u0000\u017f)\u0001\u0000\u0000\u0000\u0180"+
		"\u0181\u0005\f\u0000\u0000\u0181\u0182\u0005$\u0000\u0000\u0182\u0183"+
		"\u0005A\u0000\u0000\u0183\u0185\u0005C\u0000\u0000\u0184\u0186\u0003."+
		"\u0017\u0000\u0185\u0184\u0001\u0000\u0000\u0000\u0186\u0187\u0001\u0000"+
		"\u0000\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000"+
		"\u0000\u0000\u0188\u0189\u0001\u0000\u0000\u0000\u0189\u018a\u0005D\u0000"+
		"\u0000\u018a+\u0001\u0000\u0000\u0000\u018b\u018c\u0005\r\u0000\u0000"+
		"\u018c\u018d\u0005$\u0000\u0000\u018d\u018e\u0005A\u0000\u0000\u018e\u0190"+
		"\u0005C\u0000\u0000\u018f\u0191\u0003.\u0017\u0000\u0190\u018f\u0001\u0000"+
		"\u0000\u0000\u0191\u0192\u0001\u0000\u0000\u0000\u0192\u0190\u0001\u0000"+
		"\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000"+
		"\u0000\u0000\u0194\u0195\u0005D\u0000\u0000\u0195-\u0001\u0000\u0000\u0000"+
		"\u0196\u019a\u0007\u0005\u0000\u0000\u0197\u0199\u00030\u0018\u0000\u0198"+
		"\u0197\u0001\u0000\u0000\u0000\u0199\u019c\u0001\u0000\u0000\u0000\u019a"+
		"\u0198\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b"+
		"\u019d\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000\u019d"+
		"\u01a0\u00053\u0000\u0000\u019e\u019f\u0005>\u0000\u0000\u019f\u01a1\u0003"+
		"\u001e\u000f\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2\u01a3\u0005"+
		"A\u0000\u0000\u01a3/\u0001\u0000\u0000\u0000\u01a4\u01a5\u0005)\u0000"+
		"\u0000\u01a5\u01a6\u0005*\u0000\u0000\u01a61\u0001\u0000\u0000\u0000\u01a7"+
		"\u01a8\u0005\u0003\u0000\u0000\u01a8\u01a9\u00052\u0000\u0000\u01a9\u01aa"+
		"\u0005$\u0000\u0000\u01aa\u01ab\u0005A\u0000\u0000\u01ab\u01b8\u0005C"+
		"\u0000\u0000\u01ac\u01ae\u00034\u001a\u0000\u01ad\u01ac\u0001\u0000\u0000"+
		"\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b0\u0001\u0000\u0000"+
		"\u0000\u01af\u01b1\u00036\u001b\u0000\u01b0\u01af\u0001\u0000\u0000\u0000"+
		"\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b9\u0001\u0000\u0000\u0000"+
		"\u01b2\u01b4\u00036\u001b\u0000\u01b3\u01b2\u0001\u0000\u0000\u0000\u01b3"+
		"\u01b4\u0001\u0000\u0000\u0000\u01b4\u01b6\u0001\u0000\u0000\u0000\u01b5"+
		"\u01b7\u00034\u001a\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000\u01b6\u01b7"+
		"\u0001\u0000\u0000\u0000\u01b7\u01b9\u0001\u0000\u0000\u0000\u01b8\u01ad"+
		"\u0001\u0000\u0000\u0000\u01b8\u01b3\u0001\u0000\u0000\u0000\u01b9\u01ba"+
		"\u0001\u0000\u0000\u0000\u01ba\u01bb\u0005D\u0000\u0000\u01bb3\u0001\u0000"+
		"\u0000\u0000\u01bc\u01bd\u0005\t\u0000\u0000\u01bd\u01be\u0005$\u0000"+
		"\u0000\u01be\u01bf\u0005A\u0000\u0000\u01bf\u01c1\u0005C\u0000\u0000\u01c0"+
		"\u01c2\u0003.\u0017\u0000\u01c1\u01c0\u0001\u0000\u0000\u0000\u01c2\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000\u01c3\u01c4"+
		"\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001\u0000\u0000\u0000\u01c5\u01c6"+
		"\u0005D\u0000\u0000\u01c65\u0001\u0000\u0000\u0000\u01c7\u01c8\u0005\n"+
		"\u0000\u0000\u01c8\u01c9\u0005$\u0000\u0000\u01c9\u01ca\u0005A\u0000\u0000"+
		"\u01ca\u01cc\u0005C\u0000\u0000\u01cb\u01cd\u0003.\u0017\u0000\u01cc\u01cb"+
		"\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01cc"+
		"\u0001\u0000\u0000\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000\u01cf\u01d0"+
		"\u0001\u0000\u0000\u0000\u01d0\u01d1\u0005D\u0000\u0000\u01d17\u0001\u0000"+
		"\u0000\u0000\u01d2\u01d3\u0005\u0011\u0000\u0000\u01d3\u01d4\u0005$\u0000"+
		"\u0000\u01d4\u01d5\u0005A\u0000\u0000\u01d5\u01d6\u0003\f\u0006\u0000"+
		"\u01d69\u0001\u0000\u0000\u00005;AGIMRW\\^bkquz~\u0083\u0088\u008a\u0090"+
		"\u0094\u0097\u009c\u00a0\u00a5\u00ab\u00b5\u00c5\u00de\u00f3\u0105\u0116"+
		"\u011c\u0125\u012d\u0134\u0146\u014e\u0150\u0164\u016c\u016e\u017c\u0187"+
		"\u0192\u019a\u01a0\u01ad\u01b0\u01b3\u01b6\u01b8\u01c3\u01ce";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}