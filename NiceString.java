import java.util.Scanner;

public class NiceString {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the string:");
		String inputString = input.nextLine();
		if( (doubleLetter(inputString)+doesntContainBubabe(inputString)+ min3Vowel(inputString)) < 2  ) {
			System.out.println("Not a nice string");
		}
		else {
			System.out.println("Is nice string");
		}
	}
	public static int doesntContainBubabe (String string) {
		for(int i=0;i<string.length();i++) {
			try {
				if(string.charAt(i)=='b' && (string.charAt(i+1)=='u' || string.charAt(i+1)=='a'
						|| string.charAt(i+1)=='e')){
					return 0;
				}
			} catch (StringIndexOutOfBoundsException e) {}
		}
		return 1;
	}
	public static int min3Vowel(String string) {
		int count =0;
		String vowels = "aeiou";
		for(int i = 0;i<string.length();i++) {
			if(string.charAt(i)== 'a' || string.charAt(i)== 'e' || string.charAt(i)== 'i' ||
					string.charAt(i)== 'o' || string.charAt(i)== 'u') {
				count++;
			}
			if(count ==3) {
				return 1;
			}
		}
		return 0;
	}	
	public static int doubleLetter(String string) {
		for(int i=0;i<string.length();i++) {
			try {
				if(string.charAt(i)== string.charAt(i+1)){
					return 1;
				}
			} catch (StringIndexOutOfBoundsException e) {}
		}
		return 0;
	}
}
