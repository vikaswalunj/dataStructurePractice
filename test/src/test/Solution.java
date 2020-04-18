package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<List<Integer>> ClosestXdestinations(int numDestinations, 
                                             List<List<Integer>> allLocations,
                                             int numDeliveries)
	{
        List<List<Integer>> deliveryLocations = new ArrayList<>();
        List<Integer> coordinates = new ArrayList<>();
        
        if (numDeliveries > numDeliveries) {
            coordinates.add(0);
            coordinates.add(0);
            deliveryLocations.add(coordinates);
            return deliveryLocations;
        }
        
        double[] distanceArray = new double[numDestinations];
      
        Map<Double, List<Integer>> map = new HashMap<>();
        for (int i=0; i< numDestinations; i++) {
            List<Integer> currentLocation = allLocations.get(i);
            int x = currentLocation.get(0);
            int y = currentLocation.get(1);
            double dist = Math.sqrt((x*x)+(y*y));
            distanceArray[i] = dist;
         
            if (map.containsKey(dist)) {
                map.get(dist).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);	 
                map.put(dist, list);
            }
        }
        Arrays.sort(distanceArray);
        
        for (int j =0; j <numDeliveries;) {
            double currDistance = distanceArray[j];
            int size = map.get(currDistance).size();
            if (size > 1) {
                while (j<numDeliveries && size >0) {
                    int resultIndex = map.get(currDistance).get(size-1);
                    deliveryLocations.add(allLocations.get(resultIndex));
                    j++;
                    size--;
                }
            } else {
                deliveryLocations.add(allLocations.get(map.get(currDistance).get(0)));
                j++;
            }
        }
        
        return deliveryLocations;
    }
    // METHOD SIGNATURE ENDS
}