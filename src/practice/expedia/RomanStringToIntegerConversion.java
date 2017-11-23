package practice.expedia;

import java.util.Set;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RomanStringToIntegerConversion {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] romanString = br.readLine().split("");

        HashMap<String, Integer> romanToIntegerMap = new HashMap<String, Integer>();
        romanToIntegerMap.put("I", 1);
        romanToIntegerMap.put("V", 5);
        romanToIntegerMap.put("X", 10);
        romanToIntegerMap.put("L", 50);
        romanToIntegerMap.put("C", 100);
        romanToIntegerMap.put("D", 500);
        romanToIntegerMap.put("M", 1000);

        int numLength = romanString.length;
        Set<Integer> lessIndices = new HashSet<Integer>();

        for(int i = 0; i < numLength; ++i){
            if(i+1 < numLength){
                if(romanToIntegerMap.get(romanString[i]) < romanToIntegerMap.get(romanString[i+1]))
                    lessIndices.add(i);
            }
        }

        int num = 0;
        for(int i = 0; i < numLength;){
            if(!lessIndices.contains(i)){
                num = num + romanToIntegerMap.get(romanString[i]);
                ++i;
            }
            else{
                num = num + romanToIntegerMap.get(romanString[i+1]) - romanToIntegerMap.get(romanString[i]);
                i+=2;
            }
        }
        System.out.println("The integer representation of the roman numeral is : " + num);
    }
}
