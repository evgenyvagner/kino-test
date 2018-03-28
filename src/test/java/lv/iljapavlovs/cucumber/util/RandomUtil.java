package lv.iljapavlovs.cucumber.util;


import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static int getRandomNumberInRange(int upperLimit) {
        return ThreadLocalRandom.current().nextInt(0, upperLimit);
    }
}
