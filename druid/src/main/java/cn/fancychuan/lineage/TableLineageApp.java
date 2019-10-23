package cn.fancychuan.lineage;

import cn.fancychuan.utils.FileUtils;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLUseStatement;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class TableLineageApp {
    public static void main(String[] args) throws IOException {
        String sql = FileUtils.readFromFile("src/main/resources/mysql-sample.sql");
        Map<String, TreeSet<String>> lineages = getFromTo(sql);
        System.out.println(lineages);
    }

    public static Map<String, TreeSet<String>> getFromTo (String sql) throws ParserException {
        List<SQLStatement> stmts = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        TreeSet<String> fromSet = new TreeSet<>();
        TreeSet<String> toSet = new TreeSet<>();
        if (stmts == null) {
            return null;
        }

        String database="DEFAULT";
        for (SQLStatement stmt : stmts) {
            SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(JdbcConstants.MYSQL);
            if (stmt instanceof SQLUseStatement) {
                database = ((SQLUseStatement) stmt).getDatabase().getSimpleName().toUpperCase();
            }
            stmt.accept(statVisitor);
            Map<TableStat.Name, TableStat> tables = statVisitor.getTables();
            if (tables != null) {
                final String db = database;
                tables.forEach((tableName, stat) -> {
                    if (stat.getCreateCount() > 0 || stat.getInsertCount() > 0) {
                        String to = tableName.getName().toUpperCase();
                        if (!to.contains("."))
                            to = db + "." + to;
                        toSet.add(to);
                    } else if (stat.getSelectCount() > 0) {
                        String from = tableName.getName().toUpperCase();
                        if (!from.contains("."))
                            from = db + "." + from;
                        fromSet.add(from);
                    }
                });
            }
        }
        Map<String, TreeSet<String>> results = new HashMap<>();
        results.put("input", fromSet);
        results.put("output", toSet);
        return results;
    }
}
