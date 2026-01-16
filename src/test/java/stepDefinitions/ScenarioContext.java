package stepDefinitions;
import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static ThreadLocal<Map<String, Object>> data =
            ThreadLocal.withInitial(HashMap::new);

    public static void set(String key, Object value) {
        data.get().put(key, value);
    }

    public static Object get(String key) {
        return data.get().get(key);
    }

    public static void clear() {
        data.remove();
    }
}
