package lv.iljapavlovs.cucumber.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Getter
@Setter
public class TestDataContext {

    private static final TestDataContext INSTANCE = new TestDataContext();
    private Map<String, Supplier<String>> testDataMap = new HashMap<>();
    private String[] seatArr;

    public static TestDataContext getInstance() {
        return INSTANCE;
    }
}