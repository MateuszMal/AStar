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

    public AStar() {
//        ArrayList<Integer> endStatus = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
        this.result = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0).hashCode();
    }

    public Integer estimate(Integer deep, Integer heuristic) {
        return deep + heuristic;
    }

    public boolean isEnd(ArrayList<Integer> listToCheck) {
        // zmienic na porownanie zawartosci listy
        return listToCheck.hashCode() == result;
    }

    // funkcja heurystyczna
    public int checkValuesAreOnWrongPosition(ArrayList<Integer> list) {
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


    public boolean compute(State state) {  //
        int countIteration = 0;

        // TODO dodac do explored list
        // TODO zmienic liste rodzicow, zeby mniej miejsca zabieralo

        // jesli stan jest docelowy zakoncz
        // warunkiem wejscia w liste jest
        while (!isEnd(state.getFifteenPuzzle())) {
            countIteration++;
            // oblicznanie warosci stanu
//            int wrongPositions = checkValuesAreOnWrongPosition(state.getFifteenPuzzle());
            stateMap.getOpened().add(state.hashCode()); // stanow otwartych

            //odpytywanie o sasiadow
            List<State> neighbors = getNeighbors(state);

            // wyliczanie warotsci sasiadow
            Map<State, Integer> stateWithValues = new HashMap<>();
            neighbors.forEach(neighbor -> {
                Integer value = checkValuesAreOnWrongPosition(neighbor.getFifteenPuzzle());
                stateWithValues.put(neighbor, value);
            });

            // znalezienie stanu z nanjmniejsza wartoscia
            int min = stateWithValues.values().stream()
                    .mapToInt(v -> v)
                    .min()
                    .orElseThrow(NoSuchElementException::new);
            State stateWithMinValue = new State();
            stateWithValues.forEach((key, value) -> {
                if (value == min)
                    stateWithMinValue.setFifteenPuzzle(key.getFifteenPuzzle());
                stateWithMinValue.setOperator(key.getOperator());
                stateWithMinValue.setParent(key.getParent());
            });

            state = stateWithMinValue;
        }
        //System.out.println(state.toString());
        System.out.println(countIteration);
        return true;
    }
}
