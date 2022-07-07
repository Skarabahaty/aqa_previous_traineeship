package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TestEntry {

    public TestEntry() {
    }

    public TestEntry(int id, String name, int statusId, String methodName, int projectId, int sessionId,
                     String startTime, String endTime, String env, String browser) {
        this.id = id;
        this.name = name;
        this.statusId = statusId;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
    }

    private int id;
    private String name = "test";
    private int statusId;
    private String methodName;
    private int projectId = 7;
    private int sessionId = 21;
    private String startTime;
    private String endTime;
    private String env = "env";
    private String browser = "browser";
    private int authorId = 1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = new Timestamp(startTime).toString().replaceAll("\\.[0-9]+", "");
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = new Timestamp(endTime).toString().replaceAll("\\.[0-9]+", "");
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public List<Serializable> getFields() {
        return new LinkedList<>(
                List.of(name, statusId, methodName,
                        projectId, sessionId, startTime,
                        endTime, env, browser));
    }

    @Override
    public String toString() {
        return String.format("TestEntry{id=%d, name='%s' , status_id=%d , method_name='%s' , project_id=%d , session_id=%d , start_time=%s , end_time=%s , env='%s' , browser='%s'} ",
                id, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntry testEntry = (TestEntry) o;
        return statusId == testEntry.statusId &&
                projectId == testEntry.projectId &&
                sessionId == testEntry.sessionId &&
                name.equals(testEntry.name) &&
                methodName.equals(testEntry.methodName) &&
                startTime.equals(testEntry.startTime) &&
                endTime.equals(testEntry.endTime) &&
                env.equals(testEntry.env) &&
                browser.equals(testEntry.browser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser);
    }
}
