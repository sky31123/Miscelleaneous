import java.util.Stack;
//Not ideal solution
//O( n^2 )
//Only good for learning recursion
//Space complexity is O( 1 ) but using Functional Stack so can't say

//For better solution use Mathematical Approach O( n )
/*
 * 1. Find Max. Any number > max can be used for max
 * 2. Find A and A1
 * 3. Find B and B1
 * 4. Fill the Stack using B and B'
 * O( 4n ) = O( n )
 */
class Stack_Reversal_Using_Recursion { 

    static Stack<Integer> stack = new Stack<>(); 
      
    static void insert_at_bottom(int x) 
    { 
  
        if(stack.isEmpty()) 
            stack.push(x); 
  
        else
        { 
            int a = stack.peek(); 
            stack.pop(); 
            insert_at_bottom(x); 
            stack.push(a); 
        } 
    } 
    static void reverse() 
    { 
        if(stack.size() > 0) 
        { 
            int x = stack.peek(); 
            stack.pop(); 
            reverse(); 
            insert_at_bottom(x); 
        } 
    } 
	public static void main(String[] args) {
		
		stack.push(-9);
		stack.push(-8);
		stack.push(-3);
		stack.push(4);
		//stack = 1,2,3,4
		System.out.println(stack); 

		reverse(); 
          
        System.out.println("Reversed Stack"); 
          
        System.out.println(stack); 	
        
        better_Solution();
        
        System.out.println("Using mathematical approch");
        System.out.println(stack);

}
	private static void better_Solution() {
		
		int size = stack.size();
		
		//Finding Max
		int max = 0;
		for(int item : stack){
			if( max < Math.abs(item) )
				max = Math.abs(item);
		}
		max = max + 1;
		
		//Finding A and A1
		int A = 0;
		int A1 = 0;
		
		while( stack.size() > 0 )
		{
			int x = stack.pop();
			A += Math.abs(x);
			A *= max;
			
			if( x < 0 )
				A1 += (max+1);
			else
				A1 += max;
			A1 *= max;
		}
		//A and A' hold single sum total value from which using max we can find out entire stack but in same order so we find out B and B1
		
		int B = 0;
		int B1 = 0;
		
		for( int i = 0; i < size; i++)
		{
			A = A / max;
			A1 = A1/ max;
			
			int A_mdl = A % max;
			A -= A_mdl;
			
			int A1_mdl = A1 % max;
			if( A1_mdl == 0 )
				A1_mdl = max;
			else
				A1_mdl = max + 1;
			A1 -= A1_mdl;
			
			B += A_mdl;
			B *= max;
			
			B1 += A1_mdl;
			B1 *= max;
			
		}
		
		//Now that we have B and B1 in desired order let's re-populate the Stack
		for( int i = 0; i < size; i++)
		{
			B = B / max;
			B1 = B1/ max;
			
			int B_mdl = B % max;
			B -= B_mdl;
			
			int B1_mdl = B1 % max;
			if( B1_mdl != 0 )
				B_mdl = -B_mdl;
			if( B1_mdl ==0 )
				B1 -= max;
			else
				B1 -= (max+1);
			
			stack.push( B_mdl );
			
		}
		
	}

}
