package projekt.QueryBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateBuilder extends AbstractSql{
    private String table;
    private List<String> columns = new ArrayList();
    private List<String> columnParams = new ArrayList();


    public CreateBuilder(String table) {
        this.table = table;
    }

    public CreateBuilder newColumn(String columnName){
        this.columnParams.add(columnName);
        return this;
    }
    public CreateBuilder endColumn(){
        this.columns.add(String.join(" ", this.columnParams));
        this.columnParams.clear();
        return this;
    }
    public CreateBuilder withDataType(String dataType){
        String type;
        switch (dataType.toLowerCase(Locale.ROOT)){
            case "int":type = "INTEGER";break;

            case "float":
            case "double":
            case "real": type = "REAL";break;

            case "date":
            case "string":
            default:type = "TEXT";break;
        }
        this.columnParams.add(type);
        return this;
    }
    public CreateBuilder withDataSize(int dataSize){
        this.columnParams.add("("+dataSize+")");
        return this;
    }
    public CreateBuilder primaryKey(){
        this.columnParams.add("PRIMARY KEY");
        return this;
    }
    public CreateBuilder notNull(){
        this.columnParams.add("NOT NULL");
        return this;
    }
    public CreateBuilder autoIncrement(){
        this.columnParams.add("AUTOINCREMENT");
        return this;
    }
    public CreateBuilder withForeignKey(String columnName, String tableName){
        this.columnParams.add("FOREIGN KEY("+columnName+"Id) REFERENCES "+tableName+"(id)");
        return this;
    }


    public String build() {
        StringBuilder sql = (new StringBuilder("CREATE TABLE IF NOT EXISTS ")).append(this.table+" ");
        this.appendList(sql, this.columns, "( ", ", ");
        sql.append(")");
        return sql.toString();
    }
}
