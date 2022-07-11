package utils.tables_utils;

import com.google.gson.JsonObject;
import utils.JDBConnector;
import utils.JsonReader;

import java.sql.Statement;

public abstract class TableUtil {

    protected static final JsonObject QUERIES = JsonReader.getDataFromFile("queries.json");
    protected static final Statement STATEMENT = JDBConnector.getStatement();
    protected static final JsonObject TEST_CONFIGS = JsonReader.getDataFromFile("test_configs.json");

}
