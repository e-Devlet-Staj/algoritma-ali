import java.util.Scanner;



public class MasterMind {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the secret code:");
		String secretCode=input.nextLine();
		String attempt ="";
		int positions =0;
		int letters =0;
		char temp =' ';
		char[] attemptArray = new char[secretCode.length()];
		while(true) {
			System.out.printf("(Code length is %d)"
					+ "\nEnter your guess:          ---Press 'x' to give up---\n" ,secretCode.length());
			positions =0;
			letters =0;	
			attempt=input.nextLine();
			if(attempt.equals("x")) {
				System.out.println("Game is closed");
				return;
			}
			if(attempt.length()!=secretCode.length()) {
				System.out.println("The given code's length is different than secret code.");
				continue;
			}
			for(int i=0;i<secretCode.length();i++) {
				if(secretCode.charAt(i)==attempt.charAt(i)) {
					positions++;	
				}
			}
			attemptArray=attempt.toCharArray();
			//attemptimizde secretCode daki harflerden herhangi birini görünce(index önemsenmeksizin)
			//attempin o charını 0 a ceviriyoruz ki daha sonraki aramalarda tekrardan saymayalım
			for(int i=0;i<secretCode.length();i++) {
				temp=secretCode.charAt(i);
				for(int j=0;j<attempt.length();j++) {
					if(attemptArray[j]==temp) {
						attemptArray[j]='0';
						letters++;
						break;
					}
				}
			}
			//yukardaki letters sayma metodu aynı indexte olanları da dahil ettiği için
			//positions ı cıkarmamız lazım
			letters-=positions;
			System.out.printf("Positions: %d\n",positions);
			System.out.printf("Letters: %d\n",letters);
			System.out.println("|||||||||||||||||||||||||||||||||||||");
			if(positions == secretCode.length()) {
				System.out.println("Congrats you guessed the secret code correctly :)");
				return;
			}
		}
		
	}
}
