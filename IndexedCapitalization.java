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
		int[] c = {1,2,4};
		System.out.println(indexedCapitalization("qwertyu",c));

	}
}
