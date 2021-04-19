package QueryBuilders;

import java.util.List;

public abstract class AbstractSql {

    public AbstractSql(){}

    protected void appendList(StringBuilder sql, List<?> queryList, String initialValue, String separator) {
        boolean first = true;
        for (Object query:queryList) {
            if (first) {
                sql.append(initialValue);
                first = false;
            } else {
                sql.append(separator);
            }
            sql.append(query);
        }

    }

}
