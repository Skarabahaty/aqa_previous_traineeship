package data;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractData {

    public AbstractData(HashMap<String, Object> map) {
        this.data = map;
    }

    private final Map<String, Object> data;

    public int getInt(String key) {
        return (int) data.get(key);
    }

    public String getString(String key) {
        return (String) data.get(key);
    }
}
