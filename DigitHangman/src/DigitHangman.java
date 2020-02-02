import java.util.Scanner;

public class DigitHangman {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] number = new String[12];
		Boolean[] numberFound = new Boolean[12];
		StringBuilder wrongGuess = new StringBuilder();
		
		String s = sc.nextLine();
		number = s.split(" ");
		for(int i = 0; i < numberFound.length; i++) numberFound[i] = false;
		for(int i = 0; i < 5; i++) {
			String guess = sc.nextLine();
			boolean found = false;
			for(int j = 0; j < number.length; j++) {
				if(number[j].equals(guess)) {
					numberFound[j] = true;
					found = true;
				}
			}
			if(!found) wrongGuess.append(guess+" ");
			for(int j = 0; j < numberFound.length; j++) {
				if(numberFound[j]) System.out.print(number[j]+" ");
				else System.out.print("_ ");
			}
			System.out.println(wrongGuess);
		}
		int score = 0;
		for(int i = 0; i < numberFound.length; i++) {
			if(numberFound[i]) score++;
		}
		System.out.println(score);
	}
}
