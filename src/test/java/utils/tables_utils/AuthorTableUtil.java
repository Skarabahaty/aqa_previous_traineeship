package utils.tables_utils;

import models.Author;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorTableUtil extends TableUtil {

    static {
        TEST_TABLE = "author";
    }

    public static void add(Author author) {
        String queryDraft = QUERIES.get("insert_into_author").getAsString();
        String insertQueryDraft = constructInsertQueryDraft(author, queryDraft);
        String insertQuery = String.format(insertQueryDraft, TEST_TABLE);
        try {
            STATEMENT.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String constructInsertQueryDraft(Author author, String queryDraft) {
        StringBuilder insertQueryDraft = new StringBuilder(queryDraft);
        insertQueryDraft.append("(");
        List<Serializable> fields = author.getFields();
        int size = fields.size();
        for (int i = 0; i < size; i++) {
            Serializable value = fields.get(i);
            switch (value.getClass().getSimpleName()) {
                case "String":
                    insertQueryDraft.append("'").append(value).append("'");
                    break;
                case "Integer":
                    insertQueryDraft.append(value);
                    break;
            }

            if (size - i > 1) {
                insertQueryDraft.append(", ");
            }
        }
        insertQueryDraft.append(")");
        return insertQueryDraft.toString();
    }

    public static void checkForPresenceAndSetID(Author author) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromTestEntry(author);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(checkForPresenceQuery);
            resultSet.next();
            author.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }

    private static String getCheckForPresenceQueryFromTestEntry(Author author) {
        return String.format(
                QUERIES.get("check_author_for_presence").getAsString(),
                TEST_TABLE,
                author.getName(),
                author.getLogin(),
                author.getEmail());
    }

    public static boolean isAuthorPresentInDatabase(Author author) {
        String checkForPresenceQuery = getCheckForPresenceQueryFromTestEntry(author);
        try {
            ResultSet resultSet = STATEMENT.executeQuery(checkForPresenceQuery);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problem wit query");
        }
    }
}
