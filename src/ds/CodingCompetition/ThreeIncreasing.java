package ds.CodingCompetition;

public class ThreeIncreasing {

	private static int ec = 0; 
    public int minEaten(int a, int b, int c){
        if (a == 0 || b == 0 || c == 0)
            return -1;
        
        if (b > a && c > b && c < 3001)
            return ec;
        
        if (b <= a && a < 3001) {
            ec = ec + (a - b + 1);
            a = b - 1;
            minEaten(a, b, c);
        }
        
        if (c <= b && b < 3001) {
            ec = ec + (b - c + 1);
            b = c - 1;
            minEaten(a, b, c);
        }
        
        return ec;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreeIncreasing te = new ThreeIncreasing();
		int result1 = te.minEaten(15, 40, 22);
		System.out.println("result1 is ="+result1);
		int result2 = te.minEaten(5, 6, 6);
		System.out.println("result2 is =" +result2);
		
	}

}
