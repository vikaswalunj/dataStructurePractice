package ds.ArrayString;

public class LeetcodeArray {
    /* buy and sell stocks as many times as you want - hard
       https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     */

    public int buyAndSellStock(int[] price) {

        if (price == null || price.length < 2)
            return 0;

        //intitilaize profit and local min
        int profit = 0, j = 0;

        for(int i = 1; i < price.length; i++){
            //update local minimum if decreasing sequence is found
            if (price[i-1] > price[i])
                j = i;

            //sell shares if current element is peak
            if (price[i] >= price[i-1] && (price[i] > price[i+1] || i+1 == price.length)) {
                profit += (price[i] - price[j]);
            }
        }
        return profit;
    }
}
