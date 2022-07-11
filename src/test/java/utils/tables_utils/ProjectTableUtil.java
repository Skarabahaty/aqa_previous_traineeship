package utils.tables_utils;

import models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectTableUtil extends AbstractUtil {

    static {
        TEST_TABLE = "project";
    }
    private static final String TEST_TABLE;

    public static void add(Project project) {
        String queryDraft = QUERIES.get("insert_into_project").getAsString();
        String insertQueryDraft = constructInsertQueryDraft(project, queryDraft);
        String insertQuery = String.format(insertQueryDraft, TEST_TABLE);
        try {
            STATEMENT.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String constructInsertQueryDraft(Project project, String queryDraft) {
        return queryDraft + "('" + project.getName() + "')";
    }

    public static void checkForPresenceAndSetID(Project project) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromTestEntry(project);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(checkForPresenceQuery);
            resultSet.next();
            project.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String getCheckForPresenceQueryFromTestEntry(Project project) {
        return String.format(
                QUERIES.get("check_project_for_presence").getAsString(),
                TEST_TABLE,
                project.getName());
    }

    public static boolean isProjectPresentInDatabase(Project project) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromTestEntry(project);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(checkForPresenceQuery);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    public static Project getProjectAndAddTiInDB() {
        String projectName = TEST_CONFIGS.get("project").getAsString();
        Project project = new Project(projectName);
        if (! isProjectPresentInDatabase(project)) {
            add(project);
        }
        checkForPresenceAndSetID(project);
        return project;
    }
}
