package lv.iljapavlovs.cucumber.util;

import cucumber.runtime.java.guice.ScenarioScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.Setter;

@ScenarioScoped
@Getter
@Setter
public class DataHolderDI {
    private String sharedVariable;
}