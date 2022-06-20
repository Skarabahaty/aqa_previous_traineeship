package data;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractData {

    private final Map<String, Object> data;

    public AbstractData(HashMap<String, Object> map) {
        this.data = map;
    }

    public int getInt(String key) {
        return (int) (long) data.get(key);
    }

    public String getString(String key) {
        return (String) data.get(key);
    }

    public Object getObject(String key) {
        return data.get(key);
    }
}
