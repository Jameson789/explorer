import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    //Starting point tests
    @Test
    public void testStartingPointCenterIsh() {
        int[][] island = {
            {3,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,0,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] expected = {2,2};
        assertArrayEquals(expected, ExplorerSearch.startLocation(island));
    }

    @Test
    public void testStartingPointTopLeftCorner() {
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] expected = {0,0};
        assertArrayEquals(expected, ExplorerSearch.startLocation(island));
    }

    @Test
    public void testStartingPoint2_4() {
        int[][] island = {
            {2,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,0,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] expected = {2,4};
        assertArrayEquals(expected, ExplorerSearch.startLocation(island));
    }

    @Test
    public void testStartingPointNoLocation() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.startLocation(island);
        });
        assertEquals("No starting point", exception.getMessage());
    }

    @Test
    public void testPossibleMovesCenter() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,0,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] location = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("2,3")); // left
        assertTrue(moveSet.contains("2,1")); // right
    }

    @Test
    public void testPossibleMovesAllPossible() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,1,1,3,1},
            {1,1,0,1,3,3},
            {3,1,1,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] location = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("2,3")); // left
        assertTrue(moveSet.contains("2,1")); // right
        assertTrue(moveSet.contains("1,2")); // up
        assertTrue(moveSet.contains("3,2")); // down
    }

    @Test
    public void testPossibleMovesNone() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,2,1,3,1},
            {1,2,0,2,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] location = {2, 2};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);

        assertTrue(moves.isEmpty()); 
        
    } 

    @Test
    public void testPossibleMovesRightBoundary() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,1,1,3,1},
            {1,1,1,1,3,0},
            {3,1,1,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] location = {2, 5};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("1,5")); // up
        assertTrue(moveSet.contains("3,5")); // down
    }

    @Test
    public void testPossibleMovesCorner() {
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,1,1,3,1},
            {1,1,1,1,3,1},
            {3,1,1,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] location = {0, 0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1")); 
    }


    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
