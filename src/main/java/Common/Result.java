package Common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@NoArgsConstructor
public class Result {
    private final int[] result = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

    public int getResultHash() {
        return this.hashCode();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(result);
    }
}
