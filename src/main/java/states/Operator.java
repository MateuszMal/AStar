package states;

import java.util.stream.Stream;

public enum Operator {
    L, R, U, D;

    public static Stream<Operator> stream() {
        return Stream.of(Operator.values());
    }
}
