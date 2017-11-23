package practice.expedia;

public class AddNumInString {

    public int calculateSum(String a){
    	int sum = 0;

        String num = "";
    	for(int i = 0; i < a.length(); i++) {
	        if(Character.isDigit(a.charAt(i))) {
	            num = num + a.charAt(i);
	        } else {
	            if(!num.equals("")) {
	                sum = sum + Integer.parseInt(num);
	                num = "";
	            }
	        }
	    }
    	if (num != "") 
    	sum = sum + Integer.parseInt(num);
	
	    return sum;
    }
    
    public static void main(String[] args){
    	AddNumInString t = new AddNumInString();
    	String a="jklmn489pjro635ops";
    	int result = t.calculateSum(a);
    	System.out.println(+result);
    }

}
