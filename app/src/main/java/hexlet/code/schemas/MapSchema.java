package hexlet.code.schemas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ValidationInterface;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.ArrayList;

public class MapSchema extends BaseSchema {
    private List<ValidationInterface> validationRules;

    private Map<String, BaseSchema> stepMap = new HashMap<>();

    public MapSchema() {
        validationRules = new ArrayList<>();
    }
    public MapSchema required() {
        validationRules.add(m -> (!Objects.isNull(m) && m instanceof Map));
        return this;
    }

    public MapSchema sizeof(int num) {
        validationRules.add(m -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper
                    .convertValue(m, new TypeReference<>() {
                    });
            //Map<String, Object> map = new HashMap<>((Map<? extends String, ?>) m);
            return map.size() == num;
        });
        return this;
    }

    public void shape(Map<String, BaseSchema> data) {
        this.stepMap = data;
        if (!stepMap.isEmpty()) {
            validationRules.add(m -> {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper
                        .convertValue(m, new TypeReference<>() {
                        });
                for (Map.Entry<String, Object> val : map.entrySet()) {
                    BaseSchema baseSchema  = stepMap.get(val.getKey());
                    if (!baseSchema.isValid(val.getValue())) {
                        return false;
                    }
                }
                return true;
            });
        }
    }

    public boolean isValid(Object obj) throws IOException {
        for (ValidationInterface validation : validationRules) {
            if (!validation.validateData(obj)) {
                return false;
            }
        }
        return true;
    }
}
