package lv.iljapavlovs.cucumber.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtil {
    private static DataHolder testData = DataHolder.getInstance();

    public static String findAndReplaceContextVariables(String text) {
        if (text != null) {
            Pattern p = Pattern.compile("\\$\\{(\\S+)\\}");
            Matcher m = p.matcher(text);
            while (m.find()) {
                String value = m.group(0);
                String key = m.group(1);
                text = replaceTextVariable(text, value, key);
            }
        }
        return text;
    }

    private static String replaceTextVariable(String text, String textToReplace, String key) {
        if (testData.getTestDataMap().containsKey(key)) {
            String value = testData.getTestDataMap().get(key).get();
            log.debug("Text replace " + key + "=" + value);
            return text.replace(textToReplace, value);
        } else {
            throw new IllegalArgumentException("Unknown key - " + textToReplace);
        }
    }

}