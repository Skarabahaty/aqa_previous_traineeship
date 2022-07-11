package utils.tables_utils;

import com.google.gson.JsonObject;
import utils.JDBConnector;
import utils.JsonReader;

import java.sql.Statement;

public abstract class TableUtil {

    protected static final JsonObject QUERIES = JsonReader.getDataFromFile("queries.json");
    protected static String TEST_TABLE;
    protected static final Statement STATEMENT = JDBConnector.getStatement();

}
