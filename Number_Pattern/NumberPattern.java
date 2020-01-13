public class NumberPattern{
	
	public static void main(String[] args){
		
		for(int i = 1; i <= 9; i++){
			
			System.out.print(i + " |");
			
			for(int j = 1; j <=9; j++){
				
				if((j * i) < 10){
					System.out.print(' ');
				}
				
				System.out.print(j * i + " ");
			}
			
			System.out.println();
		}
	}
}