package setup;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ConfigReader {
    private final Map<String, Object> config;

    public ConfigReader(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Configuration file not found: " + fileName);
            }
            Yaml yaml = new Yaml();
            config = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public String get(String key) {
        String[] keys = key.split("\\.");
        Object value = config;
        for (String k : keys) {
            value = ((Map<String, Object>) value).get(k);
            if (value == null) break;
        }
        return value != null ? value.toString() : null;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getList(String key) {
        Object value = config.get(key);
        if (value instanceof List) {
            return (List<Map<String, Object>>) value;
        }
        throw new IllegalArgumentException("Key does not point to a list: " + key);
    }
}
