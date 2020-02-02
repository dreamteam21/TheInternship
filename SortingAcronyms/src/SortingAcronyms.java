import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SortingAcronyms {
	public static void main(String[] args) {
		ArrayList<String> namelist = new ArrayList<String>();
		ArrayList<String> acrolist = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.nextLine(); //skip empty line
		for(int i = 0; i < num; i++) {
			String temp = sc.nextLine();
			namelist.add(temp);
		}
		for(int i = 0; i < num; i++) {
			String[] temp = namelist.get(i).split(" ");
			StringBuilder acro = new StringBuilder();
			for(String a : temp) {
				if(a.charAt(0) >= 65 && a.charAt(0) <= 90) {
					acro.append(a.charAt(0));
				}
			}
			if(acro.length() > 0) acrolist.add(acro.toString());
		}
		Collections.sort(acrolist, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if(s1.length() != s2.length()) return s2.length() - s1.length();
				else {
					if(s1.compareTo(s2)<0) return -1; //s1 alphabetically comes first
					else return 1; //otherwise
				}
			}
		});
		for(String s : acrolist) {
			System.out.println(s);
		}
	}
}
