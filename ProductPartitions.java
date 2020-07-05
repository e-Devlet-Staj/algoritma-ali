import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ProductPartitions {
	/*static int productCombinationNum(int num) {
		int cnt = 0; 
        for (int i = 1; i <= Math.sqrt(num); i++) { // checking prime divisors till numbers square root is enough
            if (num % i == 0) { 
                // If divisors are equal, 
                // count only one 
                if (num / i == i) {
                    cnt++; }
  
                else {
                    cnt = cnt + 2; }
                
            } 
        } 
        return cnt;
	}*/
	
	static int productCombinationNum(int num) {
		int cnt = -1; 
		int temp =1;
		HashMap<Integer,Integer> divisorInfo = new  HashMap();
        if(num%2!=0) {
        	if(num==1) {
        		return 1;
        	}
        	else {
        		for(int i=3; num>1;i+=2) {
        			if(num%i==0) {
            			if(!divisorInfo.containsKey(i)) {
            				divisorInfo.put(i, 1);
            			}
            			else {
            				divisorInfo.put(i, divisorInfo.get(i)+1);
            			}
            			num/=i;
            			i-=2;
            		}
        		}
        	}
        }
        else {
		for(int i=2;num>1;i++) {
        		if(num%i==0) {
        			if(!divisorInfo.containsKey(i)) {
        				divisorInfo.put(i, 1);
        			}
        			else {
        				divisorInfo.put(i, divisorInfo.get(i)+1);
        			}
        			num/=i;
        			i--;
        		}
        	}    
        }
        
        //Given any number can shown as product of primes, 2^A x 3^B x ...
        //the result should a function like this -->f(A,B,...)
        //
        //
        
        System.out.println(divisorInfo.values().toArray()[0]);
        
        return 0;
	}
	
	static void prod_int_part(int n) { 
        System.out.print("[" + (productCombinationNum(n)-2) + ", [");  //we substract 2 because we are not getting 1 and the number itself 
        while (n % 2 == 0) { 
            System.out.print(2 + ", "); 
            n /= 2; 
        } 
        // n must be odd at this point.  So we can 
        // skip one element (Note i = i +2) 
        for (int i = 3; i <= Math.sqrt(n); i += 2) { 
            // While i divides n, print i and divide n 
            while (n % i == 0) { 
                System.out.print(i); 
                n /= i;
                if(n!=1) {
                	System.out.print(", ");
                }
                else {
                	System.out.print("");
                }
            } 
        }
        // This condition is to handle the case whien 
        // n is a prime number greater than 2 
        
        System.out.print("]]");
    } 
	public static void main(String args[]) {
		//prod_int_part(18);
		//HashSet<String[]> xd = new HashSet<String[]>();
		productCombinationNum(81);
		
		
	}
}
