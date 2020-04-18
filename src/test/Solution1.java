// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.ArrayList;
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution1
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumHours(int rows, int columns, List<List<Integer> > grid)
    {
        if (rows < 0 || columns < 0 || grid == null) {
            return -1;
        }

        boolean hostedByAll = false;
        int hour = 0;
        while (!hostedByAll) {
            distributeFiletoadjcentCells(rows, columns, grid);
            hostedByAll = translateAndCheckForEmptyServer(rows, columns, grid);
            hour++;
        }

        return hour;
    }

    public void distributeFiletoadjcentCells(int rows, int columns, List<List<Integer> > grid) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.get(i).get(j) == 0) {
                    if (isValidToChange(rows, columns, i, j, grid)) {
                        grid.get(i).set(j, -1);
                    }
                }
            }
        }
    }

    public boolean isValidToChange(int rows, int columns, int i, int j, List<List<Integer>> grid) {
        if (i > 0 && i < rows-1 && j > 0 && j < columns-1) {
            if ((grid.get(i).get(j+1) == 1) ||
                    (grid.get(i).get(j-1) == 1) ||
                    (grid.get(i-1).get(j) == 1) ||
                    (grid.get(i+1).get(j) == 1)) {
                return true;
            }
        } else if (i == 0 && j > 0 && j < columns-1) {
            if ((grid.get(i).get(j+1) == 1) ||
                    (grid.get(i).get(j-1) == 1) ||
                    (grid.get(i+1).get(j) == 1)) {
                return true;
            }
        } else if ( i == rows-1 && j > 0 && j < columns-1) {
            if ((grid.get(i).get(j+1) == 1) ||
                    (grid.get(i).get(j-1) == 1) ||
                    (grid.get(i-1).get(j) == 1)
                    ) {
                return true;
            }
        } else if (j == 0 && i > 0 && i < rows-1) {
            if ((grid.get(i).get(j+1) == 1) ||
                    (grid.get(i-1).get(j) == 1) ||
                    (grid.get(i+1).get(j) == 1)) {
                return true;
            }
        } else if (j == columns-1 && i > 0 && i < rows-1) {
            if ((grid.get(i).get(j-1) == 1) ||
                    (grid.get(i-1).get(j) == 1) ||
                    (grid.get(i+1).get(j) == 1)) {
                return true;
            }
        } else if (i == 0 && j == 0) {
            if ((grid.get(i).get(j+1) == 1) ||
                    (grid.get(i+1).get(j) == 1)) {
                return true;
            }
        } else if (i == rows-1 && j == columns -1) {
            if ((grid.get(i).get(j-1) == 1) ||
                    (grid.get(i-1).get(j) == 1)) {
                return true;
            }
        } else if (i == 0 && j == columns-1){
            if ((grid.get(i).get(j-1) == 1) ||
                    (grid.get(i+1).get(j) == 1)) {
                return true;
            }
        } else if (i == rows-1 && j == 0) {
            if ((grid.get(i).get(j+1) == 1) ||
                    (grid.get(i-1).get(j) == 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean translateAndCheckForEmptyServer(int rows, int columns, List<List<Integer>> grid) {
        boolean zeroPresent = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid.get(i).get(j) == -1) {
                    grid.get(i).set(j, 1);
                }
                if (grid.get(i).get(j) == 0) {
                    zeroPresent = true;
                }
            }
        }
        return zeroPresent;
    }
    // METHOD SIGNATURE ENDS

    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> row0 = new ArrayList<>();
        row0.set(0, 1);
        row0.set(1, 0);
        row0.set(2, 0);
        row0.set(3, 0);
        row0.set(4, 0);
        List<Integer> row1 = new ArrayList<>();
        row1.set(0, 0);
        row1.set(1, 1);
        row1.set(2, 0);
        row1.set(3, 0);
        row1.set(4, 0);
        List<Integer> row2 = new ArrayList<>();
        row2.set(0, 0);
        row2.set(1, 0);
        row2.set(2, 1);
        row2.set(3, 0);
        row2.set(4, 0);
        List<Integer> row3 = new ArrayList<>();
        row3.set(0, 0);
        row3.set(1, 0);
        row3.set(2, 0);
        row3.set(3, 1);
        row3.set(4, 0);
        List<Integer> row4 = new ArrayList<>();
        row4.set(0, 0);
        row4.set(1, 0);
        row4.set(2, 0);
        row4.set(3, 0);
        row4.set(4, 1);
        grid.set(0, row0);
        grid.set(1, row1);
        grid.set(2, row2);
        grid.set(3, row3);
        grid.set(4, row4);
        Solution1 sol = new Solution1();
        System.out.print(sol.minimumHours(5, 5, grid));
    }
}