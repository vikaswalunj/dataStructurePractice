package ds.CodingCompetition;

public class SellingFruits {
	
	private static int mD;
	
	public int maxDays(int x, int f, int d, int p) {
		
		if (d == 0)
			return mD;
		
		if (f > 0 && d > 0){
			mD = mD + f;
			d = d - (f*x);
			
			maxDays(x , f, d, p);
		}
		
		if (f == 0 ) {
			int rD = d - (d/x);
			f = 
			d = d - (f*p);
			mD = mD++;
			
		}	
		
		return mD;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
