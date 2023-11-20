package hexlet.code.schemas;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapSchema {

    private boolean required = false;

    private int size;
    private boolean pickedSize = false;

    private Map<String, StringSchema> stepMap = new HashMap<>();

    public MapSchema() {

    }
    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeOf(int size) {
        this.size = size;
        this.pickedSize = true;
        return this;
    }

    public boolean isValid(Object obj) throws Exception {
        if (required && (Objects.isNull(obj) || !(obj instanceof Map))) {
            return false;
        } else if (pickedSize && !Objects.isNull(obj) && obj instanceof Map) {
            Map<String, Object> map = new HashMap<>((Map<? extends String, ?>) obj);
            if (map.size() != size) {
                return false;
            }
        } else {
            Map<String, Object> map = new HashMap<>((Map<? extends String, ?>) obj);
            for (Map.Entry<String, Object> val : map.entrySet()) {
                StringSchema stringSchema  = stepMap.get(val.getKey());
                if (!stringSchema.isValid(val.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void shape(Map<String, StringSchema> data) {
        this.stepMap = data;
    }
}
