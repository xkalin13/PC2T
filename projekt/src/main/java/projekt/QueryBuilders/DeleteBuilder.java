package projekt.QueryBuilders;

import java.util.ArrayList;
import java.util.List;

public class DeleteBuilder extends AbstractSql {
    private String table;
    private List<String> where = new ArrayList();

    public DeleteBuilder(String table) {
        this.table = table;
    }

    public String build() {
        StringBuilder sql = (new StringBuilder("DELETE FROM ")).append(this.table);
        this.appendList(sql, this.where, " WHERE ", " AND ");
        return sql.toString();
    }
    public DeleteBuilder whereEquals(String column, Object value) {
        where(column + " = " + value);
        return this;
    }

    public DeleteBuilder where(String expr) {
        this.where.add(expr);
        return this;
    }

}
