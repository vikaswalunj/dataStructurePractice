import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = new int[]{1,2,3,7,5,6,4,8};
        sol.findSubArray(arr);
    }

   public void findSubArray(int[] arr) {

       if (arr == null || arr.length < 1)
           System.out.print("wrong");

       int leftIndex = -1, rightIndex = -1;
       int maxSoFar = Integer.MIN_VALUE;
       for (int i = 0; i < arr.length; i++ ) {
           if (arr[i] > maxSoFar) {
               maxSoFar = arr[i];
           }

           if (arr[i] < maxSoFar) {
               rightIndex = i;
           }
       }

       int minSoFar = Integer.MAX_VALUE;
       for (int j = arr.length - 1; j >= 0; j--) {
           if (arr[j] < minSoFar) {
               minSoFar = arr[j];
           }

           if (arr[j] > minSoFar) {
               leftIndex = j;
           }
       }

       System.out.println(+leftIndex +" " +rightIndex);

   }
}
