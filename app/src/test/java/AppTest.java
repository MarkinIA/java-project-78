import static org.assertj.core.api.Assertions.assertThat;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class AppTest {
    @Test
    void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(5)).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();

        assertThat(schema.minLength(1).isValid("hexlet")).isTrue();
        assertThat(schema.minLength(10).isValid("hexlet")).isFalse();


        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();

        assertThat(schema.isValid("what does the fox say")).isFalse();

    }

    @Test
    void testNumberSchema() {
        Validator n = new Validator();
        NumberSchema schemaNum = n.number();
        assertThat(schemaNum.isValid(null)).isTrue();
        assertThat(schemaNum.positive().isValid(null)).isTrue();

        schemaNum.required();

        assertThat(schemaNum.isValid(null)).isFalse();
        assertThat(schemaNum.isValid("5")).isFalse();
        assertThat(schemaNum.isValid(10)).isTrue();

        assertThat(schemaNum.isValid(-10)).isFalse();
        assertThat(schemaNum.isValid(0)).isFalse();

        schemaNum.range(5, 10);

        assertThat(schemaNum.isValid(5)).isTrue();
        assertThat(schemaNum.isValid(10)).isTrue();
        assertThat(schemaNum.isValid(4)).isFalse();
        assertThat(schemaNum.isValid(11)).isFalse();
    }

    @Test
    void testMapSchema() throws Exception {
        Validator v = new Validator();
        MapSchema schema = v.map();
        assertThat(schema.isValid(null)).isTrue(); // true

        schema.required();

        assertThat(schema.isValid(null)).isFalse(); // false
        assertThat(schema.isValid(new HashMap())).isTrue(); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isTrue(); // true

        schema.sizeof(2);

        assertThat(schema.isValid(data)).isFalse();  // false
        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue(); // true
    }

    @Test
    void testShapeValidation() throws Exception {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "");
        assertThat(schema.isValid(human1)).isFalse(); // true
    }
}
