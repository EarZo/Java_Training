import java.util.Scanner;

class ReverseNumber{
	
	public static int reverse(int userEntry){
		
		double reversedNumber = 0;
		int count = 0;
		
		do{
			reversedNumber = reversedNumber + (userEntry % 10 * Math.pow(10, -count));

			userEntry = userEntry / 10;

			count++;
		} while(userEntry > 0);

		return (int) (reversedNumber * Math.pow(10, (count - 1)));
	}
	
	public static void main(String[] args){

		Scanner scn = new Scanner(System.in);
		
		System.out.print("\nPlease enter a number: ");
		
		System.out.println("\nThe reversed number is: " + reverse(scn.nextInt()));
	}
}