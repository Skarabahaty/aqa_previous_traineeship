package utils.tables_utils;

import com.google.gson.JsonObject;
import models.Author;
import models.Project;
import models.Session;
import utils.JDBConnector;
import utils.JsonReader;

import java.sql.Statement;
import java.util.Map;

public class CommonUtil {

    protected CommonUtil() {
    }

    protected static final JsonObject QUERIES = JsonReader.getDataFromFile("queries.json");
    protected static final Statement STATEMENT = JDBConnector.getStatement();
    protected static final JsonObject TEST_CONFIGS = JsonReader.getDataFromFile("test_configs.json");

    public static Map<String, Integer> initializeDatabaseAndReturnNeededIDs() {
        Session session = SessionTableUtil.getSessionAndAddItInDB();
        Project project = ProjectTableUtil.getProjectAndAddItInDB();
        Author author = AuthorTableUtil.getAuthorAndAddItInDB();

        return Map.of("session", session.getId(),
                        "project", project.getId(),
                        "author", author.getId());
    }
}
