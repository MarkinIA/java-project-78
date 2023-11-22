package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema {

    private boolean required;

    private int size;
    private boolean pickedSize;

    private boolean shaped;

    private Map<String, BaseSchema> stepMap = new HashMap<>();

    public MapSchema() {
        required = false;
        pickedSize = false;
        shaped = false;
    }
    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(int num) {
        this.size = num;
        this.pickedSize = true;
        return this;
    }

    public boolean isValid(Object obj) {
        return checkRequired(obj) && checkSize(obj) && checkShaped(obj);
    }

    public boolean checkRequired(Object obj) {
        return !required || (!Objects.isNull(obj) && obj instanceof Map);
    }

    public boolean checkSize(Object obj) {
        if (pickedSize && !Objects.isNull(obj) && obj instanceof Map) {
            Map<String, Object> map = new HashMap<>((Map<? extends String, ?>) obj);
            if (map.size() != size) {
                return false;
            }
        }
        return true;
    }

    public boolean checkShaped(Object obj) {
        if (shaped) {
            Map<String, Object> map = new HashMap<>((Map<? extends String, ?>) obj);
            for (Map.Entry<String, Object> val : map.entrySet()) {
                BaseSchema baseSchema  = stepMap.get(val.getKey());
                if (!baseSchema.isValid(val.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void shape(Map<String, BaseSchema> data) {
        this.shaped = true;
        this.stepMap = data;
    }
}
