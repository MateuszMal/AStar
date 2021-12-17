package states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
        this.wrongPositions = checkValuesAreOnWrongPosition(fifteenPuzzle);
    }

    private int checkValuesAreOnWrongPosition(List<Integer> list) {
        int wrongPositionValues = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) continue; // jesli 0 to nie sprawdzaj
            if (list.get(i) != i + 1) {  // jesli nie na swoim miejscu
                wrongPositionValues++;
            }
        }
        return wrongPositionValues;
    }

    @Override
    public String toString() {
        return "State{" +
                "fifteenPuzzle=" + fifteenPuzzle +
                ", parent=" + parent +
                ", operator=" + operator +
                '}';
    }

    @Override
    public int hashCode() {
        return fifteenPuzzle.hashCode();
    }

    @Override
    public int compareTo(State other) {
        if ((this.wrongPositions + this.deep) > (other.wrongPositions + other.deep)) {
            return 1;
        } else if ((this.wrongPositions + this.deep) < (other.wrongPositions + other.deep)) {
            return -1;
        } else {
            return 0;
        }
    }
}
