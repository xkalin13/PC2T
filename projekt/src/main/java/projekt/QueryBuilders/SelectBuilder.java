package projekt.QueryBuilders;

import java.util.ArrayList;
import java.util.List;

public class SelectBuilder extends AbstractSql {

    private boolean distinct;
    private boolean updates;
    private boolean existCheck;

    private List<Object> columns = new ArrayList();
    private List<String> table = new ArrayList();
    private List<String> join = new ArrayList();
    private List<String> leftJoin = new ArrayList();
    private List<String> where = new ArrayList();
    private List<String> groupBy = new ArrayList();
    private List<String> orderBy = new ArrayList();
    private List<String> having = new ArrayList();


    public SelectBuilder() { }

    public SelectBuilder(String table) {
        this.table.add(table);
    }

    protected SelectBuilder(SelectBuilder anotherQuery) {
        this.distinct = anotherQuery.distinct;
        this.updates = anotherQuery.updates;
        for (Object column:anotherQuery.columns) {
            if (column instanceof SelectBuilder.SubQuery) {
                this.columns.add( ((SelectBuilder.SubQuery)column).clone());
            } else {
                this.columns.add(column);
            }
        }

        this.table.addAll(anotherQuery.table);
        this.join.addAll(anotherQuery.join);
        this.leftJoin.addAll(anotherQuery.leftJoin);
        this.where.addAll(anotherQuery.where);
        this.groupBy.addAll(anotherQuery.groupBy);
        this.having.addAll(anotherQuery.having);
        this.orderBy.addAll(anotherQuery.orderBy);
    }
    public SelectBuilder column(String column) {
        this.columns.add(column);
        return this;
    }
    public SelectBuilder from(String table) {
        this.table.add(table);
        return this;
    }

    public SelectBuilder whereEquals(String column, Object value) {
        where(column + " = '" + value + "'");
        return this;
    }

    public SelectBuilder where(String expr) {
        this.where.add(expr);
        return this;
    }

    public SelectBuilder orderBy(String name) {
        orderBy(name,false);
        return this;
    }
    public SelectBuilder orderBy(String name, boolean order) {
        if (order) {
            this.orderBy.add(name + " ASC");
        } else {
            this.orderBy.add(name + " DESC");
        }

        return this;
    }
    public SelectBuilder leftJoin(String join) {
        this.leftJoin.add(join);
        return this;
    }

    public SelectBuilder join(String join) {
        this.join.add(join);
        return this;
    }
    public SelectBuilder having(String expr) {
        this.having.add(expr);
        return this;
    }
    public SelectBuilder groupBy(String expr) {
        this.groupBy.add(expr);
        return this;
    }
    public SelectBuilder updates() {
        this.updates = true;
        return this;
    }
    public SelectBuilder distinct() {
        this.distinct = true;
        return this;
    }
    public SelectBuilder ifExists() {
        this.existCheck = true;
        return this;
    }

    public String build() {
        StringBuilder sql = new StringBuilder();
        if (existCheck){
            sql.append("IF EXISTS(");
        }
        sql.append("SELECT ");
        if (this.distinct) {
            sql.append("DISTINCT ");
        }
        if (this.columns.size() == 0) {
            sql.append("*");
        } else {
            this.appendList(sql, this.columns, "", ", ");
        }

        this.appendList(sql, this.table, " FROM ", ", ");
        this.appendList(sql, this.join, " JOIN ", " JOIN ");
        this.appendList(sql, this.leftJoin, " LEFT JOIN ", " LEFT JOIN ");
        this.appendList(sql, this.where, " WHERE ", " AND ");
        this.appendList(sql, this.groupBy, " GROUP BY ", ", ");
        this.appendList(sql, this.having, " HAVING ", " AND ");
        this.appendList(sql, this.orderBy, " ORDER BY ", ", ");
        if (this.updates) {
            sql.append(" FOR UPDATE");
        }
        if (existCheck){
            sql.append(") BEGIN SELECT 0 END");
        }
        return sql.toString();
    }



    public static class SubQuery extends SelectBuilder {
        private String alias;

        public SubQuery(String alias) {
            this.alias = alias;
        }

        protected SubQuery(SubQuery anotherQuery) {
            super(anotherQuery);
            this.alias = anotherQuery.alias;
        }

        public SubQuery clone() {
            return new SubQuery(this);
        }

        public String toString() {
            return "(" + super.toString() + ") AS " + this.alias;
        }
    }

}
