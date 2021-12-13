package states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StateMap {
    private List<Integer> statesMap = new ArrayList<>();
    private List<Integer> closed = new ArrayList<>();
    private List<Integer> opened = new ArrayList<>();
}
