/*=================================================================================

Name:          Brandon Mota

=================================================================================*/

import java.util.*;

public class Tester
{
	public static Stack<Integer> mainstack, printstack;
	public static String in, check, oper, token ;
	public static int negate, switch1, switch2, switch3, op1, op2, op3 ;

	public static void main(String[] args)
	{
		mainstack = new Stack<Integer>(); //main stack is created
		printstack = new Stack<Integer>();
		char enter = 0 ;
	
		System.out.println("Please enter in valid data in the correct postfix notation.") ; //first details are given here
		System.out.print("If you need help, enter 'h' or '?' for help.\nThe program will continue unless you enter 'q' to exit. ---------> ") ;		
    	input() ; //calls method that asks for input.
    	
    	while (in.equalsIgnoreCase("h") || in.equalsIgnoreCase("?")) //whenever the input is "h" or "?," the program will continue to ask for data
		{
			help() ; //the method holding all the help info is called		
			input() ; //input is asked again
		}
    			    
    
    	while (!in.equalsIgnoreCase("q")) //program will continue to run until the user inputs q    
    	{   
    		controller() ; //the whole controller method is called
    	}
    	System.out.println("\nProgram Terminated.") ; //final message of the program
    }    
        
	
	
	public static void input() //used only for inputs
	{
		Scanner ask = new Scanner(System.in) ;		
		in = ask.nextLine() ; //user input must be given
	}
	   
    
    
    private static boolean readin(String check) //this will check for any math operators
    {      
            if (check.contains("+") || check.contains("-") || check.contains("*") || check.contains("/") || check.contains("%"))
            {            
            	return true ; // if any math operators are used, the method will return true            
            }            
            else
            {            	
            	return false ;
            }
        
    }
    
    private static boolean operator(String inside) //checks the operator being used
	{
    	return (inside.equals("+") || inside.equals("-") || inside.equals("*") || inside.equals("/") || inside.equals("%") );    	
	}
    
    public static boolean hasdigit(String digit) // checks if a number is involved
    {
    	 

    		if (digit.matches(".*\\d.*"))
    		{
    			return true; // if a number is involved, the method returns true
    		}
    		else
    		{
    			return false;
    		}
    } 
    
    public static void controller() //the program will be controlled by this method
    {
    	String expr = in;
		Scanner parser = new Scanner(expr);
		
		        
        while (parser.hasNext()) //if expr has any char left, then this will continue
        {
            String token = parser.next();	           
	
            if (readin(token)) //call the readin method    	
            { 
            	if(mainstack.isEmpty())
            	{            		
            		empty() ;
            	}
            	else
            	{
            	evaluator(token) ; //calls the math evaluator method
            	}
            }
	    	
            else if (token.equalsIgnoreCase("m"))    	
            {   
            	if(mainstack.isEmpty())
            	{
            		empty() ;
            	}
            	else
            	{
            	negate = mainstack.pop() ;    		
            	mainstack.push(negate * -1) ; // top of stack will be removed, edited, and pushed back, if user inputs "m" 
            	}
	        }    	
	
            else if(token.equalsIgnoreCase("r"))    	
            {  
            	if(mainstack.isEmpty())
            	{
            		empty() ;
            	}
            	else
            	{
            	exchange() ;  //calls exchange method is user inputs "r"	
            	}
            }
	
            else if(token.equalsIgnoreCase("d"))    	
            {  
            	if(mainstack.isEmpty())
            	{
            		empty() ;
            	}
            	else
            	{
            	duplicate() ; //calls duplicate method if user inputs "d"
            	}
            }
	
            else if(token.equalsIgnoreCase("p"))    	
            {  
            	if(mainstack.isEmpty())
            	{
            		empty() ;
            	}
            	else
            	{
            	System.out.println(mainstack.peek()) ; // top of stack will be printed if "p" is entered 
            	}
            }
	
            else if(token.equalsIgnoreCase("n"))    	
            {
            	if(mainstack.isEmpty())
            	{
            		empty() ;
            	}
            	else
            	{
            	System.out.println(mainstack.peek()) ;
            	mainstack.pop() ; //top of stack will be printed and removed if "n" is entered
            	}
            }
	
            else if(token.equalsIgnoreCase("f"))    	
            {            	
            	printcon() ; //method printcon will be called if "f" is entered            	
            }	            
	
            else if(token.equalsIgnoreCase("c"))    	
            {
            	if(mainstack.isEmpty())
            	{
            		empty() ;
            	}
            	else
            	{
            	clear() ; //method clear will be called if "c" is entered
            	}
            }
	
            else if(hasdigit(token))    	
            { 
            	mainstack.push(Integer.parseInt(token)); //hasdigit will be checked and if it is true, a number will be pushed into stack
            }
            
            else if(token.equalsIgnoreCase("h") || token.equalsIgnoreCase("?"))    	
            {
            	help() ; //method clear will be called if "c" is entered
            }
            
            else
            {
            	System.out.println("\nYou have entered invalid data.") ;
            	System.out.println("Make sure your input has either a number or valid input.") ;
            	System.out.println("You can still continue.") ;
    	    	System.out.print("Enter 'h' or '?' for help, or enter 'q' to exit. ---------> ") ; // message will be give if any of the above are not satisfied
            }
        }
        input() ; //user input will be asked again
    }
    
	private static void help() //all valid commands will be displayed here and only if the user inputs "h" or "?"
	{
		System.out.println("\nThese are all the valid commands.\n");
		System.out.println("p: print top");
		System.out.println("n: print top and remove");
		System.out.println("d: duplicate top");
		System.out.println("r: exchange top two items");
		System.out.println("f: print contents of stack");
		System.out.println("c: clear stack");
		System.out.println("+: add");
		System.out.println("-: subtract");
		System.out.println("*: multiply");
		System.out.println("/: integer divide");
		System.out.println("%: integer remainder");
		System.out.println("m: unary minus");
		System.out.println("q: quit");
		System.out.println("h or ?: help\n");
		System.out.println("An example of correct postfix data is: 5 6 + 1 -");
		System.out.print("Remember: The program will continue unless you enter 'q' to exit. ---------> ") ;
	}
	
	public static void exchange() //used only to exchange the top two numbers of the stack
	{
		switch1 = mainstack.pop() ;    		
    	switch2 = mainstack.pop() ;	            	
    	mainstack.push(switch1) ;    		
    	mainstack.push(switch2) ;		
	}
	
	public static void duplicate() //the tope number of the stack is duplicated
	{
		switch1 = mainstack.pop();
    	switch2 = switch1 ;   		
    	mainstack.push(switch1) ;    		
    	mainstack.push(switch2) ; 
	}
	
	public static void printcon() //the contents of the stack are printed
	{
		if(mainstack.isEmpty())
    	{
			System.out.println("\nThe stack is empty.") ;
			System.out.print("Enter in data, or enter 'q' to exit. ---------> ") ;
    	}
    	else
    	{
		while(!mainstack.isEmpty())
    	{
    		switch1 = mainstack.pop() ;    		
    		printstack.push(switch1) ; //when mainstack is not empty, swtich1 will equal the top, and then pushed to printstack
    	}
    	
    	while(!printstack.isEmpty())
    	{
    		switch2 = printstack.pop();
    		System.out.print(switch2 + " ") ;
    		mainstack.push(switch2); //swtich 2 will equal the top of printstack and is displayed, then pushed into mainstack
    	}
    	System.out.println();
    	}
	}
	
	public static void clear() //used to clear stack
	{
		while(!mainstack.isEmpty())
    	{
    		switch1 = mainstack.pop() ;    		
    		printstack.push(switch1) ; //when mainstack is not empty, swtich1 will equal the top, and then pushed to printstack
    	}
    	
    	while(!printstack.isEmpty())
    	{
    		switch2 = printstack.pop();
    		System.out.print(switch2 + " ") ; //swtich 2 will equal the top of printstack and is displayed, then pushed into mainstack
    	}
    	System.out.println();
		System.out.println() ;
    	System.out.println("The stack is now empty.") ;
    	System.out.print("Enter new data, or enter 'q' to quit. ---------> ") ; //message will be give after stack is cleared
	}
	
	public static int evaluator(String expr) //used to prepare for math operations
    {
	    int result = 0, data;
        Scanner parser = new Scanner(expr);
        
        while (parser.hasNext())
        {
            token = parser.next();        	            


            if (operator(token))
            {
                op2 = (mainstack.pop());
                op1 = (mainstack.pop());
                result = math(token.charAt(0), op1, op2); //math method is called
                mainstack.push(new Integer(result));
            }
            else
            {
            	return result;
            }           
            
        }
        
        return result;
    }	
	
	private static int math(char symbol, int numberone, int numbertwo) //the math is done here
	{
		int answer = 0 ;
		
	if (token.equals("+"))
	{
        answer = op1 + op2;
	}
	else if (token.equals("-"))
	{   
        answer = op1 - op2;
	}
	else if (token.equals("*"))
	{
        answer = op1 * op2;
	}
	else if (token.equals("/"))
	{
        answer = op1 / op2;
	}
	
	else if (token.equals("%"))
	{
		answer = op1 % op2 ;
	}
				
		return answer;	
	}
	
	private static void empty()
	{
		System.out.println("\nThis function cannot be performed.") ;
		System.out.println("The stack is empty.") ;
		System.out.print("Enter in data, then run the function. ---------> ") ;
		
	}

}
