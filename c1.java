import java.util.*;
//Strings and Arrays
//Chapter 1 questions from Cracking the Code
public class c1 {
	//Question 1: Write an algorithm to check if a string contains unique characters
	
	//With Data Structure
	public static boolean isUniqueDS(String string) {
		if (string.length() > 128) return false;
		boolean[] char_set= new boolean[128];
		for (int i= 0; i < string.length(); i++) {
			int val= string.charAt(i);
			if (char_set[val]) {//Already found this char in string
				return false;
			}
			char_set[val]= true;
		}
		return true;
	}
	//Without Data Structure
	public static boolean isUnique(String string) {
		String acc = "";
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			String s = Character.toString(c);
			if (acc.contains(s)) {
				return false;
			}
			acc += s;
		}
		//System.out.println(acc);
		return true;
	}
	
	//Question 2: Write a method to check if two strings are permutations of each other
	
	//With Data Structure
	
	//Without Data Structure
	public static boolean isPerm(String s1, String s2 ) {
		//quick check
		if (s1.length() != s2.length()) return false;
		//w1 and w2 are the string weights (the sum of the ascii total) of s1 and s2
		int w1 = 0 , w2 = 0;
		for (int i = 0; i < s1.length(); i++) {
			w1 += (int) s1.charAt(i); //converts to ascii
		}
		for (int i = 0; i < s2.length(); i++) {
			w2 += (int) s2.charAt(i); //converts to ascii
		}
		return (w1 == w2);
	}
	
	//Question 3: Write a method to replace all spaces in a string with '%20  
	//NOTE: If in java, use charArray
	
	//With Data Structure
	/**
	public static String URLifyDS(String sentence) {
		char[] cArr = sentence.toCharArray();
		//our output
		String acc = "";
		//checks if a '%20' is needed
		boolean isSpace = false;
		//how many '%20' we need
		int size = sentence.split(" ",0).length - 1;
		for (char c : cArr) {
			if (c != ' ') {
				isSpace = false;
				acc += c;
			}
			else {
				if (!isSpace && size != 0) {
				acc += "%20";
				size--;
				isSpace = true;
				}
			}
		}
		System.out.println(size);
		return acc;
	}
	*/
	
	public static String URLifyDS(String sentence, int trueLength) {
		char[] cArr = sentence.substring(0, trueLength).toCharArray();
		//our output
		String acc = "";
		//checks if a '%20' is needed
		boolean isSpace = false;
		for (char c : cArr) {
			if (c != ' ') {
				isSpace = false;
				acc += c;
			}
			else {
				if (!isSpace) {
				acc += "%20";
				isSpace = true;
				}
			}
		}
		return acc;
	}
	
	//Without Data Structure
	public static String URLify(String sentence, int trueLength) {
		//our output
		String acc = "";
		//checks if a '%20' is needed
		boolean isSpace = false;
		for (int i = 0; i < trueLength; i++) {
			if (sentence.charAt(i) != ' ') { // '' is used for char "" is used for string
				isSpace = false;
				acc += sentence.charAt(i);
			}
			else {
				if (!isSpace) {
				acc += "%20";
				isSpace = true;
				}
			}
		}
		return acc;
	}
	
	//Question 4: Check if a string is a permutation of a palindrome
	
	//With Data Structure
	public static boolean isPalindromePermutation (String string) {
		string = string.replace(" ", "");
		Map<String, Integer> map = new HashMap <String, Integer>();
		for (int i = 0; i < string.length(); i++) { //fills up map with weight of each letter
			String s = Character.toString(string.charAt(i));
			if (map.get(s) == null) {
				map.put(s,1);
			}
			else {
				map.put(s, map.get(s)+1);
			}
		}
		int count = 0;
		for (String key: map.keySet()) {
			if (map.get(key) % 2 != 0) {
				count++;
			}
		}
		return count <= 1;
	}
	
	//Without Data Structure
	
	
	
	//Question 5: Check if a string is 1 or 0 edits away
	//NOTE: Edit means remove, replace or insert a character
	
	//With Data Structure
	
	//Without Data Structure
	public static boolean oneEditAway(String str1, String str2) {
		//Makes sure length is at most one apart
		if (Math.abs(str1.length() - str2.length()) > 1) return false;
		/*
		 * someval = (min >= 2) ? 2 : 1;
		 * This is called ternary operator, which can be used as if-else. 
		 * this is equivalent to
		 * if((min >= 2) {
		 * someval =2;
		 * } else {
		 * someval =1
		 */
		String shorter = str1.length() < str2.length() ? str1 : str2; 
		//has the shorter of str1 or str2 chosen
		String longer = str1.length() < str2.length() ? str2 : str1;
		//has the longer of str1 or str2 chosen
		int index1 = 0, index2 = 0;
		boolean foundDifference = false;
		while (index1 < shorter.length() && index2 < longer.length()) {
			if (shorter.charAt(index1) != longer.charAt(index2)) { //there's a difference
				if (foundDifference) return false; //one difference too many
				foundDifference = true;
				
				if (shorter.length() == longer.length()) { //replacement check
					index1++;
				}
			}
			else {
				index1++;
			}
			index2++;
		}
		return true;
	}
	
	//Question 6: Compress a string. If it cannot be compressed, return the string
	//NOTE: Compression EX: aabcccccaaa would become a2blc5a3
	//NOTE: only upper case and lower case letters
	
	String compress(String str) {
		StringBuilder compressed = new StringBuilder();
		int countConsecutive = 0;
		for (int i = 0; i < str.length(); i++) {
			countConsecutive++;
		
		/* If next char is diff than current, append this char to result */
			if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i)) {
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
			}
		}
		return compressed.length() < str.length() ? compressed.toString() : str;
	}
	
	//Question 7 : Given an image represented by an NxN matrix, 
	//			   where each pixel in the image is 4 bytes, 
	//			   write a method to rotate the image by 90 degrees. 
	//			   Can you do this in place?
	
	boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
		int n = matrix.length;
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for (int i = first; i<last; i++) {
				int offset = i - first;
				int top = matrix[first][i];//save top
				//left->top
				matrix[first][i] = matrix[last-offset][first];
				//bottom->left
				matrix[last-offset][first] = matrix[last][last-offset];
				//right->bottom
				matrix[last][last-offset]=matrix[i][last];
				//top->right
				matrix[i][last] = top;//right <-saved top
			}
		}
		return true;
	}
	
	//Question 8: Write an algorithm such that if an element in an MxN matrix is 0, 
	//			  its entire row and column are set to 0.
	
	//Question 9: Assume you have a method isSubstringwhich checks if one word is a 
	//			  substring of another. Given two strings, s1 and s2, 
	//			  write code to check if s2 is a rotation of s1 using only one call to 
	//			  isSubstring (e.g.,"waterbottle" is a rotation of "erbottlewat").
	
	boolean isRotation(String s1, String s2) {
		int len = s1.length();
		//Check that s1 and s2 are equal length and not empty
		if (len == s2.length() && len > 0) {
			// Concatenate s1 and s1 within new buffer
			String s1s1 = s1+s1;
			return (s1s1.contains(s2));
		}
		return false;
	}
	
	public static int[] arrayReverse(int[] arr) {
		int[] newArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++ ) {
			newArr[i] = arr[arr.length-i-1];
		}
		return newArr;
	}
	
	public static void main(String [] args) {
		//String s1 = String.format("Is the string '%s' unique?\n"+isUniqueDS("bob"), "bob");
		//System.out.println(s1);
		//System.out.println(isPerm("dog","god "));
		//System.out.println(URLify("Mr John Smith     ",13));
		//int[] arr = new int[] {1,2,3,4,5};
		//System.out.println(Arrays.toString(arrayReverse(arr)));
		//System.out.println(isPalindromePermutation("taco cta     bb"));
		//System.out.println(oneEditAway("the","the  "));
	}
}
