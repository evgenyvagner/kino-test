package lv.iljapavlovs.cucumber.util;

import cucumber.runtime.java.guice.ScenarioScoped;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Getter
@Setter
public class DataHolder {

    private static final DataHolder INSTANCE = new DataHolder();
    private Map<String, Supplier<String>> testDataMap = new HashMap<>();
    private String[] seatArr;

    public static DataHolder getInstance() {
        return INSTANCE;
    }
}