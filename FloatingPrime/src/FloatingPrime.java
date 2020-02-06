import java.util.Scanner;

public class FloatingPrime {
	static boolean isPrime(int n) {
		// Corner cases 
        if (n <= 1) return false; 
        if (n <= 3) return true; 
        // This is checked so that we can skip 
        // middle five numbers in below loop 
        if (n % 2 == 0 || n % 3 == 0) return false; 
        for (int i = 5; i * i <= n; i = i + 6) {
        	if (n % i == 0 || n % (i + 2) == 0) return false; 
        }
        return true; 
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			String s = sc.nextLine();
			if(s.equals("0.0")) break;
			float f = Float.parseFloat(s);
			boolean checked = false;
			for(int i = 1; i <= 3; i++) {
				int num = (int) (f * Math.pow(10, i));
				if(isPrime(num)) {
					checked = true;
					break;
				}
				else checked = false;
			}
			if(f < 1.0 || f > 10.0) {
				System.out.println("INPUT BETWEEN 1.0 and 10.0 ONLY!");
			}
			else {
				if(checked) System.out.println("TRUE");
				else System.out.println("FALSE");
			}
		}
	}
}
