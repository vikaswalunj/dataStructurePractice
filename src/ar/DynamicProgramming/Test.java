package ar.DynamicProgramming;

public class Test {
	
	public static void main(String[] args){
		
		FunctionsDynamicProgram dp = new FunctionsDynamicProgram();
		int [] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		int res = dp.lis(arr, 9);
		System.out.println(res);
	}

}
