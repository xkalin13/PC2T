package QueryBuilders;

import java.util.ArrayList;
import java.util.List;

public class InsertBuilder extends AbstractSql {

    private String table;
    private List<String> columns = new ArrayList();
    private List<Object> values = new ArrayList();

    public InsertBuilder(String table) {
        this.table = "`" + table + "`";
    }
    public InsertBuilder set(String columnName, Object value) {
        this.columns.add("`" + columnName + "`");
        this.values.add("'" + value + "'");
        return this;
    }

    public String build() {
        StringBuilder sql = (new StringBuilder("INSERT INTO ")).append(this.table).append(" (");
        this.appendList(sql, this.columns, "", ", ");
        sql.append(") VALUES (");
        this.appendList(sql, this.values, "", ", ");
        sql.append(")");
        return sql.toString();
    }
}