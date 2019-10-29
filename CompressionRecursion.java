
public class CompressionRecursion {

	public static void main(String[] args) {
		System.out.println(decompress("qwwwwwwwwweeeeerrtyyyyyqqqqwEErTTT"));
		System.out.println(decompress("    whello there?!?!##@@@!"));

	}
	
	public static String decompress(String compressedText) {
		if(compressedText.length()==1) {
			return compressedText;
		}
		
		if(Character.isDigit(compressedText.charAt(0))) {
			if(compressedText.length()==2)
				return compressedText;
			if(compressedText.charAt(1)==compressedText.charAt(2)) {
				char c = compressedText.charAt(0);
				c++;
				return decompress(c +compressedText.substring(2,compressedText.length()));
			}
			else {
				return compressedText.charAt(0)+decompress(compressedText.substring(1,compressedText.length()));
			}
		}
		
		if(compressedText.charAt(0)==compressedText.charAt(1)) {
			return decompress('2'+compressedText.substring(1, compressedText.length()));
		}
		else {
			return compressedText.charAt(0)+decompress(compressedText.substring(1,compressedText.length()));
		}
	}

}
