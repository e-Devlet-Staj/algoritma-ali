import java.util.Scanner;

public class IndexedCapitalization {
	public static String indexedCapitalization(String string, int[] indexes) {
		char [] charArray = string.toCharArray();
		String result = "";
		for(int i =0;i<indexes.length;i++) {
		try {
				charArray[indexes[i]-1] = Character.toUpperCase(charArray[indexes[i]-1]);
		}catch(Exception e) {} 
		}
		return String.valueOf(charArray);
	}
	public static void main ( String args[]) {
		System.out.println("Enter the string:");
		Scanner input = new Scanner(System.in);
		String inputString = input.nextLine();
		System.out.println("Which indexes do you want to change? (Enter indexes at same line)");
		String[] indexesString =input.nextLine().split(" ");
		int[] indexes = new int[indexesString.length];
		int i=0;
		for(String s : indexesString) {
			indexes[i]=Integer.parseInt(s);
			i++;
		}
		System.out.println(indexedCapitalization(inputString,indexes));
	}
}
