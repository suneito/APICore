package cat.babot.data.utilities;

import java.security.SecureRandom;

public class RandomEnumGenerator <T extends Enum<T>> {
    private static final SecureRandom PRNG = new SecureRandom();
    private final T[] values;

    public RandomEnumGenerator(Class<T> e) {
        values = e.getEnumConstants();
    }

    public T randomEnum() {
        return values[PRNG.nextInt(values.length)];
    }
}
