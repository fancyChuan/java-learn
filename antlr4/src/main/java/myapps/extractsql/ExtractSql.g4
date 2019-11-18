
grammar ExtractSql;

assign: TARGET '=' VALUE;



TARGET : [a-zA-Z0-9_]+;
VALUE : [a-zA-Z0-9_\n\t]+;