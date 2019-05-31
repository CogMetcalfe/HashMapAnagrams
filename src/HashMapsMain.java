import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapsMain {

	public static void main(String[] args) {
		//System.out.println(sortStr("7894565123/*-+1234567890-=]#[';/.piutwqasdfghjklzxcvbnm"));
		readAndAnagToHashMap("words.txt");
	}
	
	
	public static HashMap<String, Integer> readAndAnagToHashMap(String filename) {
		HashMap<String, Integer> wordAnags = new HashMap<String, Integer>();
		
		String[] strs = read(filename);
		String[] anags = new String[strs.length];
		for(int i=0;i<strs.length;i++) {
			anags[i] = sortStr(strs[i]);
			anags[i] = anags[i].replaceAll(" ", "");
			//System.out.println(anags[i]);
		}
		int bestAnagCount=0;
		ArrayList<String> bestStrings = new ArrayList<String>();
		for(int i=0;i<anags.length;i++) {
			int anagCount=-1;
			for(String str:anags) {
				//System.out.println(anags[i] + " ? " + str);
				if(anags[i].equals(str)) {
					anagCount++;
					//System.out.println(anags[i] + " = " + str);
				}
			}
			wordAnags.put(strs[i], anagCount);
			if(anagCount>bestAnagCount) {
				bestStrings.clear();
				bestStrings.add(strs[i]);
				bestAnagCount=anagCount;
			}else if(anagCount==bestAnagCount) {
				if(bestStrings.size()>0) {
					if(strs[i].length()>bestStrings.get(0).length()) {
						bestStrings.clear();
						bestStrings.add(strs[i]);
					}else if(strs[i].length()==bestStrings.get(0).length()) {
						bestStrings.add(strs[i]);
					}
				}else {
					bestStrings.add(strs[i]);
				}
			}
			System.out.println(strs[i] + " : " + anagCount);
		}
		System.out.println();
		System.out.println("The following words have the most anagrams (" + bestAnagCount + "): ");
		for(String s:bestStrings) {
			System.out.println(s);
		}
		return wordAnags;
	}
	
	public static String[] read(String filename) {
		File f = new File(filename);
		ArrayList<String> lines = new ArrayList<String>();
		Scanner s;
		try {
			s = new Scanner(f);
			int c=0;
			while(s.hasNextLine()) {
				c++;
				lines.add(s.nextLine());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new String[0];
		}
		
		ArrayList<String> lineWords = new ArrayList<String>();
		
		for(int i=0;i<lines.size();i++) {
			String line = lines.get(i);
			if(line.length()!=0) {
				//lineWords.add(line.split(" ")[0]);
				//lineWords.add(line.replaceAll(" ",""));
				lineWords.add(line);
			}
		}
		
		String[] out = lineWords.toArray(new String[lineWords.size()]);
		return out;
	}
	
	public static String sortStr(String in) {
		if(in==""||in==null) {
			return in;
		}
		String out="";
		ArrayList<Character> charArr = new ArrayList<Character>();
		for(int i=0;i<in.length();i++) {
			charArr.add(in.charAt(i));
		}
		charArr = quickSort(charArr);
		for(int i=0;i<charArr.size();i++) {
			out=out+charArr.get(i);
		}
		return out;
	}
	
	public static <T extends Comparable<T>> ArrayList<T> quickSort(ArrayList<T> arr, int leftBound, int rightBound) {
		//System.out.println(leftBound + " - " +  rightBound);
		boolean left=true;
		//ArrayList<int[]> indicesLeft= new ArrayList<int[]>();
		int leftIndex, rightIndex;
		leftIndex = leftBound;
		rightIndex = rightBound;
		if(leftBound==rightBound) {
			return arr;
		}
		while(leftIndex!=rightIndex) {
			//System.out.println(leftIndex + ", " + rightIndex);
			if(arr.get(leftIndex).compareTo(arr.get(rightIndex))>0) {
				//swap
				T temp=arr.get(leftIndex);
				arr.set(leftIndex, arr.get(rightIndex));
				arr.set(rightIndex, temp);
				left=!left;
			}
				
			if(left) {
				leftIndex++;
			}else {
				rightIndex--;
			}
			
		}
		if(rightIndex==rightBound) {
			quickSort(arr, leftBound, rightBound-1);
		}else {
			//System.out.println("NEXT: " + leftBound + " " +  (leftIndex));
			//System.out.println("NEXT: " + (1+rightIndex) + " " +  rightBound);
			quickSort(arr,leftBound, leftIndex);
			quickSort(arr, rightIndex+1, rightBound);
		}
		
		return arr;
	}
	
	public static <T extends Comparable<T>> ArrayList<T> quickSort(ArrayList<T> arr) {
		return quickSort(arr, 0, arr.size()-1);
	}

}






