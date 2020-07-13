import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ProductPartitions {
	public static void main(String args[]) {
		/*you can try any number with
		* prod_int_part(x) 				*/ 
	}
	static void prod_int_part(int number) { 
        System.out.print("[" + (productCombinationNum(number)) + ", ["); 
        int temp = number;
        while (temp % 2 == 0) { 
        	temp /= 2; 
        	if(temp==1) {
        		System.out.println("2]]");
        		return;
        	}
            System.out.print(2 + ", ");   
        } 
        // n must be odd at this point.  So we can 
        // increase i by 2 at every loop 
        for (int i = 3; i <= Math.sqrt(number)+1; i += 2) { 
            // While i divides n, print i and divide n 
            while (temp % i == 0) { 
                System.out.print(i); 
                temp /= i;
                if(temp!=1) {
                	System.out.print(", ");
                }
                else {
                	System.out.print("");
                }
            } 
        }
        System.out.print("]]");
    } 
	static int productCombinationNum(int number) {
		int[] allDivisors=divisors(number);
		int size = primeDivisorNum(number);
		int maxSize =(int) Math.pow(allDivisors.length, size);
		if(size>allDivisors.length || size <=1) {
			return 0;
		}
		HashSet<String> unique = new HashSet<String>();
		int[] indexQueue = new int[size];
		int temp= 0;
		int product = 1;
		String tempString = "";
		for(int i =0;i<maxSize ;i++) {
			temp = i;
			product=1;
			for(int j = 0;j<size;j++) {
				indexQueue[j]=temp%allDivisors.length;
				product*=allDivisors[indexQueue[j]];
				if(product>number) {break;}
				temp/=allDivisors.length;
			}
			if(product == number) { 
				tempString= "";
				Arrays.sort(indexQueue);
				for(int j = 0;j<size;j++) {
					tempString=tempString.concat(Integer.toString(allDivisors[indexQueue[j]]));
					tempString=tempString.concat(",");
				}
				unique.add(tempString);
			}
		}
		return unique.size();
	}
	//converts int ArrayList to int[]
	public static int[] convertIntegers(ArrayList<Integer> integers){
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++){
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
	}
	//finds all divisors of given number and puts them into an int array
	public static int[] divisors(int num) {
		ArrayList<Integer> allDivisors = new ArrayList<Integer>();
		allDivisors.add(1);
		int temp=num;
		for(int i =2; i<Math.pow(num, 0.5)+1;i++) {
			if(num%i==0) {
				temp=num;
				temp/=i;
				if(i>=Math.pow(num, 0.5)) {
					allDivisors.add(i);
					break;
				}
				allDivisors.add(i);
				allDivisors.add(temp);
			}
		}
		return convertIntegers(allDivisors);
	}
	/*for a given number n 
	  we can represent it like n = 2^A x 3^B x ....
	  this function returns A+B+...   				*/
	static int primeDivisorNum(int x) {
		int count = 0;
		for(int i =2;x!=1;i++) {
			if(x%i==0) {
				count++;
				x/=i;
				i--;
			}
		}
		return count;
	}
}