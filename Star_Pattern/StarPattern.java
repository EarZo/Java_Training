public class StarPattern{
	
	public static void main(String[] args){
		
		int count = 0;
		
		for(int i = 1; i <= 19; i++){
			
			if(i <= 10){
				
				for(int j = i; j < 10; j++){
					System.out.print(' ');
				}
			
				for(int k = 1; k <= ((2 * i) - 1); k++){
					System.out.print('*');
				}
			}else{
				count++;
			
				for(int k = 10; k < i; k++){
					System.out.print(' ');
				}
				
				for(int j = 1; j <= ((2 * (i - (2 * count))) - 1); j++){
					System.out.print('*');
				}
			}
			
			System.out.println();
		}
	}
}