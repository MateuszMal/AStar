package states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class StateMap {
    private List<Integer> statesMap = new ArrayList<>();
    private List<Integer> closed = new ArrayList<>();
    private Map<Integer, List<Integer>> opened = new HashMap<>();
}
