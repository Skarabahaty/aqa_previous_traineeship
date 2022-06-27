package data;

import com.google.gson.JsonObject;

public abstract class AbstractData {

    private final JsonObject data;

    public AbstractData(JsonObject json) {
        this.data = json;
    }

    public int getInt(String key) {
        return data.get(key).getAsInt();
    }

    public String getString(String key) {
        return data.get(key).getAsString();
    }

    public JsonObject getJsonObject(String key) {
        return data.get(key).getAsJsonObject();
    }

}
