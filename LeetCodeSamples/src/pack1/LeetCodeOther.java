package pack1;

public class LeetCodeOther {

	public static int reverse(int x) 
	{
		//Durum : Yapildi.
		//Tarih : 06.04.2026 
		
		//String result="";
		/*int y = Math.abs(x);
		
		while( y>0 ) 
		{
			result = result + y%10 ;
			y=y/10;
		}*/
		
		//String y = x+"";
		String result = new StringBuilder((x+"").replace("-", "")).reverse().toString();
				
		
		if(x<0) 
			result = "-" + result;
		
		String maxInt = Integer.MAX_VALUE+"";
		String minInt = Integer.MIN_VALUE+"";		
		
		//out of range control
		boolean isOutOfBound = false;
		
		if(x>0 && result.length()<maxInt.length()) 
		{
			//nothing to do
			System.out.println("");
		}
		else if(x<0 && result.length()<minInt.length()) 
		{
			//nothing to do
			System.out.println("");
		}
		else if(x>0) 
		{
			for(int i=0; i<maxInt.length(); i++) 
			{
				if(result.charAt(i)>maxInt.charAt(i)) 
				{ 
					isOutOfBound=true;
					break;
				}
				else if(result.charAt(i)<maxInt.charAt(i))
				{
					break;
				}
			}
		}		
		else if(x<0)
		{
			for(int i=0; i<minInt.length(); i++) 
			{
				if(result.charAt(i)>minInt.charAt(i)) 
				{ 
					isOutOfBound=true;
					break;
				}
				else if(result.charAt(i)<minInt.charAt(i))
				{
					break;
				}
				//other case is values equals each other
			}
		}	
		
		System.out.println("reversed:"+result);
		
		return (isOutOfBound) ? 0 : Integer.parseInt(result); 
		
	}
	
    public int countDigits(int num) 
    {
        int count = 0;
                       
        for(char ch:(num+"").toCharArray()) 
        {
        	int div = ch-'0';
        	if(num%div==0) count++;
        }
        
        return count;
    }
	
    public static void testCases() 
    {
    	/*
    	int [] arr = { 123, -123,  120, 1534236469, 1563847412, -2147483412, -2147483648, 2147483647, 1563847412, -1563847412} ;
		int index =5; 
		int result = reverse(arr[index]);
		System.out.println("Girdi :   " + arr[index] + "\nCikti :  " + result );
    	*/
    }
    
	public static void main(String[] args) {
		
		//Tarih :
		//Durum : 
		
		
		
		int [] arr = { 123, -123,  120, 1534236469, 1563847412, -2147483412, -2147483648, 2147483647, 1563847412, -1563847412} ;
		int index =5; 
		int result = reverse(arr[index]);
		System.out.println("Girdi :   " + arr[index] + "\nCikti :  " + result );

	}

}
