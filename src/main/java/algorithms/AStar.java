package algorithms;

import common.HammingComparator;
import common.ManhattanComparator;
import common.Move;
import states.Operator;
import states.State;
import lombok.Getter;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class AStar {
    private final Integer result;
    private final Integer deep = 0; //glebokosc - ile krokow zostalo wykonane
    private final Set<State> openedSet = new HashSet<>();
    private final Set<State> closedSet = new HashSet<>();
    private final List<State> openedList = new ArrayList<>();
    private int processedStates;
    private int maxDepth;
    private double duration;
    private long startTime;
    private int visitedStates;
    private String solution;

    public AStar() {
        this.result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0).hashCode();
    }


    public boolean isFinalState(State state) {
        if (state.hashCode() == result) {
            return isListTheSame(state);
        }
        return false;
    }

    public boolean isListTheSame(State state) {
        ArrayList<Integer> listToCheck = state.getFifteenPuzzle();
        for (int i = 0; i < listToCheck.size(); i++) {
            if (listToCheck.get(i) == 0) continue;
            if (listToCheck.get(i) != i + 1) return false;
        }
        long stopTime = System.nanoTime();
        duration = Precision.round(((stopTime - startTime) / 1000000.0), 3);
        processedStates = closedSet.size();
        maxDepth = state.getDeep();
        solution = getOperatorsFromParents(state);
        return true;
    }

    public List<State> getNeighbors(State state) {
        List<State> states = new ArrayList<>();
        Operator.stream().forEach(e -> Move.moveZero(state, e).ifPresent(states::add));
        return states;
    }

    public boolean compute(State state, String method) {
        startTime = System.nanoTime();

        if (!method.equals("hamm") && !method.equals("manh")) {
            System.out.println("Provided wrong method");
            return false;
        }

        if (isFinalState(state)) {
            System.out.println(state);
            return true;
        }

        state.setDeep(0);
        openedList.add(state);
        openedSet.add(state);

        visitedStates = 1;

        while (!openedList.isEmpty()) {

            if (method.equals("manh")) {
                openedList.sort(new ManhattanComparator());
            } else {
                openedList.sort(new HammingComparator());
            }
            State stateFromOpenList = openedList.get(0);

            openedList.remove(0);
            openedSet.remove(stateFromOpenList);

            closedSet.add(stateFromOpenList);

            List<State> neighbors = getNeighbors(stateFromOpenList);

            for (State neighbor : neighbors) {
                if (isFinalState(neighbor)) {
                    System.out.println(neighbor);
                    return true;
                }
                if (openedSet.contains(neighbor) || closedSet.contains(neighbor)) {
                    continue;
                }
                visitedStates++;
                openedList.add(neighbor);
                openedSet.add(neighbor);
            }
        }
        System.out.println("Failure");
        solution = "-1";
        return true;
    }

    private String getOperatorsFromParents(State state) {
        StringBuilder solution = new StringBuilder(state.getOperator().toString());
        while (state.getParent().getDeep() != 0) {
            State parent = state.getParent();
            solution.insert(0, parent.getOperator().toString());
            state = parent;
        }
        return solution.toString();
    }
}
