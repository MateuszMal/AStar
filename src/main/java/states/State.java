package states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class State implements Comparable<State> {
    private ArrayList<Integer> fifteenPuzzle;
    private State parent;
    private Operator operator;
    private int wrongPositions;
    private int deep;

    public State(ArrayList<Integer> fifteenPuzzle) {
        this.fifteenPuzzle = fifteenPuzzle;
        this.wrongPositions = checkValuesAreOnWrongPosition();
    }

    private int checkValuesAreOnWrongPosition() {
        int wrongPositionValues = 0;
        for (int i = 0; i < fifteenPuzzle.size(); i++) {
            if (fifteenPuzzle.get(i) == 0) continue; // jesli 0 to nie sprawdzaj
            if (fifteenPuzzle.get(i) != i + 1) {  // jesli nie na swoim miejscu
                wrongPositionValues++;
            }
        }
        return wrongPositionValues;
    }

    public int manhattan() {
       return checkValuesDistance() + wrongPositions + deep;
    }

    private int checkValuesDistance() {
        int distance = 0;
        for (int i = 0; i < fifteenPuzzle.size(); i++) {
            if (fifteenPuzzle.get(i) == 0) continue;
            if (fifteenPuzzle.get(i) != i + 1) {
                int value = fifteenPuzzle.get(i) - 1;
                int currentColumn = i % 4;
                int currentRow = i / 4;
                int correctColumnPos = value % 4;
                int correctRowPos = value / 4;
                distance += (Math.abs(currentColumn - correctColumnPos))
                        + (Math.abs(currentRow - correctRowPos));
            }
        }
        return distance;
    }

    @Override
    public String toString() {
        return "State{" +
                "fifteenPuzzle=" + fifteenPuzzle +
                ", parent=" + parent +
                ", operator=" + operator +
                ", deep=" + deep +
                '}';
    }

    @Override
    public int hashCode() {
        return fifteenPuzzle.hashCode();
    }

    @Override
    public int compareTo(State other) {
        return Integer.compare(this.wrongPositions + this.deep, other.wrongPositions + other.deep);
    }
}
