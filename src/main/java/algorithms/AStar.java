package algorithms;

import common.ManhattanComparator;
import common.Move;
import common.HammingComparator;
import lombok.Getter;
import states.Operator;
import states.State;

import java.util.*;

@Getter
public class AStar {
    private final Integer result;
    private final Integer deep = 0; //glebokosc - ile krokow zostalo wykonane
    private final Set<State> openedSet = new HashSet<>();
    private final Set<State> closedSet = new HashSet<>();
    private final List<State> openedList = new ArrayList<>();

    public AStar() {
        this.result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0).hashCode();
    }


    public boolean isFinalState(State state) {
        if (state.hashCode() == result) {
            return isListTheSame(state.getFifteenPuzzle());
        }
        return false;
    }

    public boolean isListTheSame(List<Integer> listToCheck) {
        for (int i = 0; i < listToCheck.size(); i++) {
            if (listToCheck.get(i) == 0) continue;
            if (listToCheck.get(i) != i + 1) return false;
        }
        return true;
    }

    public List<State> getNeighbors(State state) {
        List<State> states = new ArrayList<>();
        Operator.stream().forEach(e -> {
            Move.moveZero(state, e).ifPresent(states::add);
        });
        return states;
    }

    public boolean compute(State state, String method) {
        if(!method.equals("hamm") && !method.equals("manh")){
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

        while (!openedList.isEmpty()) {

            if(method.equals("manh")){
                openedList.sort(new ManhattanComparator());
            }
            if(method.equals("hamm"))  {
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
                openedList.add(neighbor);
                openedSet.add(neighbor);
            }
        }
        System.out.println("Failure");
        return true;
    }
}
