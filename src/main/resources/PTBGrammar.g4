grammar PTBGrammar;

NEWLINE : [\r\n]+ | [\r]+ ;
INT     : [0-9]+ ;
POTION  : [1-3];
SPELL   : [4-9] | [0] | 'F'[0-9] | 'F'[10-12] ;
STRING  : [a-zA-Z]+;

WS : [ \t\r\n\u000C]+ -> skip;

prog : REPEAT '(' instructions ')';
instructions : (action)+;
action : (USE_POTION | CAST_SPELL) constraints?;
REPEAT : 'repeat' REPEATER;
REPEATER : 'once' | 'times('INT')' | 'forever';
USE_POTION : 'use potion' POTION;
CAST_SPELL : 'cast spell' SPELL;

constraints: (INTERVAL | AFTER | SCHEDULE)+;

INTERVAL : 'interval' INT;

AFTER : (CAST_SPELL | USE_POTION) 'used' INT 'times';

SCHEDULE : 'schedule' INT;