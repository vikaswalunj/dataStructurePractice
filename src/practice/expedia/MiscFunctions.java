package practice.expedia;

public class MiscFunctions {
	/*Segregate 0s on Left Side & 1s on right side of the Array
	 */
	/*Function to segregate all 0s on left and all 1s on right*/
	public void segregate0and1(int array[], int size)
	{
	    int left = 0, right = size-1;
	 
	    while (left < right)
	    {
	        /* Increment left index while we see 0 at left */
	        while (array[left] == 0 && left < right)
	            left++;
	        /* Decrement right index while we see 1 at right */
	        while (array[right] == 1 && left < right)
	            right--;
	        /* If left is smaller than right then there is a 1 at left and a 0 at right.  Exchange it */
	        if (left < right)
	        {
	            array[left] = 0;
	            array[right] = 1;
	            left++;
	            right--;
	        }
	    }
	}
	
	/* write a function to return/generate factorials of number*/
	
	public int fact(int n)
    {
        int result;

       if(n==1)
         return 1;

       result = fact(n-1) * n;
       return result;
    }
	

}
