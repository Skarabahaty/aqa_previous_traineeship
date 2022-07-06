package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class TestEntry {

    private String name = "test";
    private int statusId;
    private String methodName;
    private int projectId = 4;
    private int sessionId = 4;
    private Timestamp startTime;
    private Timestamp endTime;
    private String env = "env";
    private String browser = "browser";
    private int authorId = 0;

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = new Timestamp(startTime);
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = new Timestamp(endTime);
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
        return String.format("TestEntry{name='%s' , status_id=%d , method_name='%s' , project_id=%d , session_id=%d , start_time=%s , end_time=%s , env='%s' , browser='%s' , author_id=%d} ",
                name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
    }
}
