package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Test {

    private int id = 346;
    private String name = "Dummy test";
    private int status_id;
    private String method_name;
    private int project_id = 4;
    private int session_id = 4;
    private long start_time;
    private long end_time;
    private String env = "Dummy env";
    private String browser = "Dummy browser";
    private int author_id = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public List<Serializable> getFields() {
        return new LinkedList<>(
                List.of(id, name, status_id, method_name,
                        project_id, session_id, start_time,
                        end_time, env, browser));
    }

    @Override
    public String toString() {
        return String.format("Test{name='%s'%n, status_id=%d%n, method_name='%s'%n, project_id=%d%n, session_id=%d%n, start_time=%d%n, end_time=%d%n, env='%s'%n, browser='%s'%n, author_id=%d}%n",
                name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id);
    }
}
