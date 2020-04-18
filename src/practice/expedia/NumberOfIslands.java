/*
package practice.expedia;

*/
/**
 * Question : Given an nxm grid (2-d character array) initially containing only '1' and '0', find the number of islands.
 * An island is represented as a set of any '1' connected to any other '1' horizontally or vertically, but
 * not diagonally.
 * Ex.
 * 1 1 1 0 0
 * 1 1 0 0 0
 * 0 0 0 1 0
 * Should return two islands
 * Ex.
 * 1 1 0 0
 * 1 0 1 0
 * 1 0 0 1
 * Should return three islands
 *//*

public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = new char[][] { { '1', '1', '0', '0' }, { '1', '1', '0', '0' }, { '0', '0', '1', '1' }, { '0', '0', '1', '1' } };
        System.out.println("Number of islands: " + numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        */
/**
         * Implementation here
         *//*

         
         if (grid==null || grid.length==0)
             return 0;
         int counter = 0;
         
         boolean visited[][] = new boolean[grid.length][grid[0].length];
         for (int i=0; i< grid.length; i++) {
             for (int j=0; j<grid[0].length; j++) {
             
                 if (grid[i][j] == '1' && !visited[i][j]) {
                     counter++;
                     visited[i][j] = true;
                     mark(grid, i, j); 
             
             }
         
         }    
            
        return counter;
    }
    
    public void mark(char[][] grid, int i, int j) {
    
     //  if (grid[i][j] != '1')
     //      return;
           
     //  grid[i][j] = 'V';
     //  visited[i][j] = true;
       LinkedList<Node> que = new LinkedList<Node>();
       Node node = new Node (i, j);
       que.offer(node); 
       while (!que.isEmpty()) {
           Node node2 = que.poll();
           int row = node2.row;
           int col = node2.col;
           
           if (grid[row-1][col] == '1') {
               que.offer(new Node(row-1, col));
               grid[row-1][col] = 'v';
           
           
           }
           
           if (grid[row+1][col] == '1') {
               que.offer(new Node(row+1, col));
               grid[row+1][col] = 'v';
           
           
           }
           
           if (grid[row][col-1] == '1') {
               que.offer(new Node(row, col-1));
               grid[row][col-1] = 'v';
           
           
           }
           
           if (grid[row][col+1] == '1') {
               que.offer(new Node(row, col+1));
               grid[row][col+1] = 'v';
           
           
           }
       
       
       }
       
       mark(grid, i-1, j);
       mark(grid, i+1, j);
       mark(grid, i, j-1);
       mark(grid, i, j+1);     
    
    }

}

class Node{*/
/**//*

    int row;
    int col;

}*/
