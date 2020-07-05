import java.util.Scanner;



public class MasterMind {
	public static int numberOf(String str,char c) {
	    int res=0;
	    if(str==null)
	        return res;
	    for(int i=0;i<str.length();i++)
	        if(c==str.charAt(i))
	            res++;
	    return res;
	}
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the secret code:");
		String secretCode=input.nextLine();
		String attempt ="";
		int positions =0;
		int letters =0;
		int[] countSecretCode = new int[6];
		int[] countAttempt = new int[6];
		char temp =' ';
		char[] attemptArray = new char[4];
		System.out.printf("Enter your guess: (Code length is %d) "
				+ "---Press 'x' to give up---\n" ,secretCode.length());
		while(true) {
			positions =0;
			letters =0;
			
			attempt=input.nextLine();
			if(attempt.equals("x")) {
				System.out.println("Game is closed");
				return;
			}
			for(int i=0;i<secretCode.length();i++) {
				if(secretCode.charAt(i)==attempt.charAt(i)) {
					positions++;	
				}
			}
			attemptArray=attempt.toCharArray();
			for(int i=0;i<secretCode.length();i++) {
				temp=secretCode.charAt(i);
				for(int j=0;j<attempt.length();j++) {
					if(attemptArray[j]==temp) {
						attemptArray[j]='0';
						break;
					}
				}
			}
			for(int i=0;i<attemptArray.length;i++) {
				if(attemptArray[i]=='0') {
					letters++;
				}
			}
			
			System.out.printf("Positions: %d\n",positions);
			System.out.printf("Letters: %d\n",letters-positions);
			System.out.println("_________________________________________________");
			if(positions == 4) {
				System.out.println("Congrats you guessed the secret code correctly :)");
				return;
			}
		}
		
	}
}
