package algorithms;

import Common.Move;
import lombok.Getter;
import states.Operator;
import states.State;
import states.StateMap;

import java.util.*;

@Getter
public class AStar {
    private final Integer result;
    private final Integer deep = 0; //glebokosc - ile krokow zostalo wykonane
    private final StateMap stateMap = new StateMap();
//    private final Map<Integer, List<Integer>> openedMap = new HashMap<>();
    private final HashSet<Integer> openedSet = new HashSet<>();
    private final HashSet<Integer> closedSet = new HashSet<>();
    private final List<State> openedList = new ArrayList<>();
//    private final SortedSet<State> openedTree = new TreeSet<>();
    private final List<State> closedList = new ArrayList<>();
    private int counter;

    public AStar() {
        this.result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0).hashCode();
    }

    public Integer estimate(Integer deep, Integer heuristic) {
        return deep + heuristic;
    }

    public boolean isEnd(Map<Integer, List<Integer>> mapToCheck) {
        for (Map.Entry<Integer, List<Integer>> entry : mapToCheck.entrySet()) {
            if (entry.getKey() != result) return false;
            return isListTheSame(entry.getValue());
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

    public boolean isFinalState(State state){
        if(state.hashCode() == result){
            return isListTheSame(state.getFifteenPuzzle());
        }
        return false;
    }

    // funkcja heurystyczna
    public int checkValuesAreOnWrongPosition(List<Integer> list) {
        int wrongPositionValues = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) continue; // jesli 0 to nie sprawdzaj
            if (list.get(i) != i + 1) {  // jesli nie na swoim miejscu
                wrongPositionValues++;
            }
        }
        return wrongPositionValues;
    }

    public List<State> getNeighbors(State state) {
        List<State> states = new ArrayList<>();
        Operator.stream().forEach(e -> {
            Move.moveZero(state, e).ifPresent(states::add);
        });
        return states;
    }


    public boolean compute(State state) {
        if (isFinalState(state)) {
            System.out.println(state);
            return true;
        }

        state.setDeep(0);
        openedList.add(state);
        openedSet.add(state.hashCode());

        while (!openedList.isEmpty()) {

            Collections.sort(openedList);
            State stateFromOpenList = openedList.get(0);


            openedList.remove(0);
            openedSet.remove(stateFromOpenList.hashCode());

            closedList.add(stateFromOpenList);
            closedSet.add(stateFromOpenList.hashCode());

            List<State> neighbors = getNeighbors(stateFromOpenList);

            for (State neighbor : neighbors) {
                if (isFinalState(neighbor)) {
                    System.out.println(neighbor);
                    return true;
                }
                if (openedSet.contains(neighbor.hashCode()) || closedSet.contains(neighbor.hashCode())) {
                    continue;
                }
                openedList.add(neighbor);
                openedSet.add(neighbor.hashCode());
            }
        }
        System.out.println("Failure");
        return true;
    }
}
