package states;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class State implements Comparable<State> {
    private ArrayList<Integer> fifteenPuzzle;
    private final int rows;
    private final int columns;
    private State parent;
    private Operator operator;
    private int wrongPositions;
    private int deep;

    public State(ArrayList<Integer> fifteenPuzzle, int rows, int columns) {
        validation(fifteenPuzzle, rows, columns);
        this.fifteenPuzzle = fifteenPuzzle;
        this.rows = rows;
        this.columns = columns;
        this.wrongPositions = checkValuesAreOnWrongPosition();
    }

    private void validation(ArrayList<Integer> fifteenPuzzle, int rows, int columns) {
        int correctListSize = rows * columns;
        if(fifteenPuzzle.size() != correctListSize){
            throw new RuntimeException("The length of the list is incorrect");
        }
    }

    public int manhattan() {
        return checkValuesDistance() + wrongPositions + deep;
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

    private int checkValuesDistance() {
        int distance = 0;
        for (int i = 0; i < fifteenPuzzle.size(); i++) {
            if (fifteenPuzzle.get(i) == 0) continue;
            if (fifteenPuzzle.get(i) != i + 1) {
                int value = fifteenPuzzle.get(i) - 1;
                int currentColumn = i % columns;
                int currentRow = i / rows;
                int correctColumnPos = value % columns;
                int correctRowPos = value / rows;
                distance += (Math.abs(currentColumn - correctColumnPos))
                        + (Math.abs(currentRow - correctRowPos));
            }
        }
        return distance;
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
}
