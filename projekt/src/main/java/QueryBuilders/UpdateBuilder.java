package QueryBuilders;

import java.util.ArrayList;
import java.util.List;

public class UpdateBuilder extends AbstractSql {
    private String table;
    private List<String> sets = new ArrayList();
    private List<String> where = new ArrayList();

    public UpdateBuilder(String table) {
        this.table = table;
    }
    public UpdateBuilder set(String column, String value){
        return set(column +" = "+ value);
    }
    public UpdateBuilder set(String expr) {
        this.sets.add(expr);
        return this;
    }
    public UpdateBuilder whereEquals(String column, String value) {
        where(column + " = " + value);
        return this;
    }
    public UpdateBuilder where(String expr) {
        this.where.add(expr);
        return this;
    }

    public String build() {
        StringBuilder sql = (new StringBuilder("UPDATE ")).append(this.table);
        this.appendList(sql, this.sets, " SET ", ", ");
        this.appendList(sql, this.where, " WHERE ", " AND ");
        return sql.toString();
    }


}
