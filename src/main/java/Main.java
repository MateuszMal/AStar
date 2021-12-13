import algorithms.AStar;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        switch (args[0]){
//            case "bfs" :
//                break;
//            case "dsf":
//                break;
//            case "astr":
//                break;
//            default:
//                throw new IllegalArgumentException("Provided wrong argument");
//        }

//        Bfs bfs = new Bfs();
//        List<Integer> entryPoint = new ArrayList<>(List.of(
//                1, 15, 2, 0,
//                3, 5, 7, 6,
//                8, 10, 9, 4,
//                11, 14, 12, 13));
//        List<Integer> algorithm = bfs.moveZero(entryPoint, Operator.LEFT);
//        System.out.println(algorithm);
//        List<Integer> algorithm1 = bfs.moveZero(entryPoint, Operator.RIGHT);
//        System.out.println(algorithm1);
//        List<Integer> algorithm2 = bfs.moveZero(entryPoint, Operator.UP);
//        System.out.println(algorithm2);
//        List<Integer> algorithm3 = bfs.moveZero(entryPoint, Operator.DOWN);
//        System.out.println(algorithm3);

        AStar astar = new AStar();
        ArrayList<Integer> endStatus = new ArrayList<>(List.of(1, 2, 0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

        System.out.println(astar.checkValuesAreOnWrongPosition(endStatus));

    }
}
