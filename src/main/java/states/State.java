package states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class State {
    private ArrayList<Integer> fifteenPuzzle;
    private State parent;
    private Operator operator;

    public State(ArrayList<Integer> fifteenPuzzle) {
        this.fifteenPuzzle = fifteenPuzzle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(fifteenPuzzle, state.fifteenPuzzle) && Objects.equals(parent, state.parent) && operator == state.operator;
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
}
