package algorithms;

import states.Operator;

import java.util.ArrayList;
import java.util.List;

public class Bfs {
    private final List<Integer> endStatus = new ArrayList<>();
    private int endHash;

    public void init() {
        endStatus.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
        this.endHash = endStatus.hashCode();
    }

    public Integer findZero(List<Integer> entryPoint) {
        for (int i = 0; i < entryPoint.size(); i++) {
            if (entryPoint.get(i) == 0) return i;
        }
        return null;
    }

    public List<Integer> moveZero(List<Integer> entryPoint, Operator operator) {
        Integer step;
        Integer zeroPos = findZero(entryPoint);
        switch (operator) {
            case L -> {
                step = 1;
                if (zeroPos % 4 != 0) {
                    Integer temp = entryPoint.get(zeroPos - step);
                    entryPoint.set(zeroPos - step, 0);
                    entryPoint.set(zeroPos, temp);
                }
            }
            case R -> {
                step = 1;
                if (zeroPos % 4 != 3) {
                    Integer temp = entryPoint.get(zeroPos + step);
                    entryPoint.set(zeroPos + step, 0);
                    entryPoint.set(zeroPos, temp);
                }
            }
            case U -> {
                step = 4;
                if (zeroPos > 3) {
                    Integer temp = entryPoint.get(zeroPos - step);
                    entryPoint.set(zeroPos - step, 0);
                    entryPoint.set(zeroPos, temp);
                }
            }
            case D -> {
                step = 4;
                if (zeroPos < 12) {
                    Integer temp = entryPoint.get(zeroPos + step);
                    entryPoint.set(zeroPos + step, 0);
                    entryPoint.set(zeroPos, temp);
                }
            }
        }
        return entryPoint;
    }

    public void compute(List<Integer> entryPoint) {
        int currentHash = 0;
        while(currentHash != endHash){
            //TODO: zrobic zeby operator chodzil w kolko
            List<Integer> integers = new ArrayList<>();
            integers = moveZero(entryPoint, Operator.L);
            currentHash = integers.hashCode();
            integers = moveZero(entryPoint, Operator.R);
            currentHash = integers.hashCode();
            integers = moveZero(entryPoint, Operator.U);
            currentHash = integers.hashCode();
            integers = moveZero(entryPoint, Operator.D);
            currentHash = integers.hashCode();
        }


    }
}
