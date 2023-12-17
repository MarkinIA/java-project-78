package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> stepMap = new HashMap<>();

    public MapSchema() {
    }
    public MapSchema required() {
        validationRules.add(m -> (!Objects.isNull(m) && m instanceof Map));
        isRequired = true;
        return this;
    }

    public MapSchema sizeof(int num) {
        validationRules.add(m -> {
            Map<String, Object> map = (Map) m;
            return map.size() == num;
        });
        return this;
    }

    public void shape(Map<String, BaseSchema> data) {
        this.stepMap = data;
        if (!stepMap.isEmpty()) {
            validationRules.add(m -> {
                Map<String, Object> map = (Map) m;
                for (Map.Entry<String, Object> val : map.entrySet()) {
                    BaseSchema baseSchema  = stepMap.get(val.getKey());
                    if (stepMap.containsKey(val.getKey())) {
                        if (!baseSchema.isValid(val.getValue())) {
                            return false;
                        }
                    }
                }
                return true;
            });
        }
    }
}
