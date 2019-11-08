## Druid抽象语法树和SQL解析

### AST节点类型
主要有三种抽象类型：
- SQLObject
- SQLExpr
- SQLStatement


另外有各种数据库的类型分别继承SQLObject：
- MySqlObject
- DB2Object
- OracleSQLObject
- PGSQLObject
- 