package balancedBrackets;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class Balancers {
	
	//Create hash map for mapping characters
    @SuppressWarnings("serial")
	public static HashMap<Character, Character> mapper = new HashMap<Character, Character>() {{
    	put('(', ')');
        put('{', '}');
        put('[', ']');
    }};
    
    //Get keys and values for checking if items exists
    public static Set<Character> mapperKeys = mapper.keySet();
    public static Collection<Character> mapperValues = mapper.values();
	
    
    
    
    /* Check for balanced parentheses using simple if-else statements
	 * This has minimal abstractions, but is faster because at fewer key-value pairs,
	 * hash maps can be slower to access
	 */
	static String isBalanced(String s) 
    {
        //Check if string is null or empty
        if (s == null || s.isEmpty())
            return "false";
        
        //Add items to stack for LIFO structure balancing
        Stack<Character> balancer = new Stack<Character>();
        
        //Iterate over characters in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            //If char is (, [, or {, add to stack
            if (c == '(' || c == '[' || c == '{') {
                balancer.add(c);
            }
            
            //If char is ), ], or }, check what prior item is on stack to verify balanced brackets
            if (c == ')' || c == ']' || c == '}') {
                if (balancer.size() == 0)
                    return "false";
                    
                char mostRecentOpen = balancer.pop();
                
                //Check for closed parentheses
                if (c == ')' && !(mostRecentOpen == '(')) {
                    return "false";
                }
                
                //Check for closed bracket
                if (c == ']' && !(mostRecentOpen == '[')) {
                    return "false";
                }
                
                //Check for closed squiggly bracket
                if (c == '}' && !(mostRecentOpen == '{')) {
                    return "false";
                }
            }
        }
        
        if (balancer.size() != 0)
            return "false";
            
        return "true";
    }
    
    
    
	/* Check for balanced parentheses using a hashmap to map characters. 
	 * This provides some abstraction so we don't have to create an if statement for each type of bracket
	 */
    static String isBalancedHashMap(String s) 
    {
        //Check if string is null or empty
        if (s == null || s.isEmpty())
            return "false";
        
        //Add items to stack for LIFO structure balancing
        Stack<Character> balancer = new Stack<Character>();
        
        
        //Iterate over characters in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            //If char is (, [, or {, add to stack
            if (mapperValues.contains(c)) {
                balancer.add(c);
            }
            
            //If char is ), ], or }, check what prior item is on stack to verify balanced brackets
            if (mapperKeys.contains(c)) {
                if (balancer.size() == 0)
                    return "false";
                   
                //Check if the most recent item on the stack matches what should be in the mapper
                char mostRecentOpen = balancer.pop();              
                if (mostRecentOpen != mapper.get(c))
                    return "false";
            }
        }
        
        if (balancer.size() != 0)
            return "false";
            
        return "true";
    }
    
    
    
    /* Check for balanced parentheses using a hashmap to map characters. 
	 * Minimizes number of branches to a single if/else if
	 * Also reduces number of "contains" checks to just 1 instead of 2
	 */
    static String isBalancedFasterHashMap(String s) {
    	//Check if string is null or empty
        if (s == null || s.isEmpty())
            return "false";
        
        //Add items to stack for LIFO structure balancing
        Stack<Character> balancer = new Stack<Character>();
        
        
        //Iterate over characters in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            //If char is (, [, or {, add to stack
            if (mapperKeys.contains(c))
            	balancer.push(mapper.get(c));
            
            else if (balancer.isEmpty() || balancer.pop() != c)
            	return "false";
            
        }
            
        return "true";
    }
    
    
    /* This method is the least readable, and is the hardest to expand on for other cases
     * However, it is the fastest here. Instead of using the stack, we use arrays with
     * an integer pointing to the last used index in that array.
	 */
    static String isBalancedNoStack(String s) {
    	//Check if string is null or empty
        if (s == null || s.isEmpty())
            return "false";
        
        //Add items to stack for LIFO structure balancing
        char[] balancer = new char[s.length()];
        int currentEndIdx = 0;
        
        //Iterate over characters in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            //If char is (, [, or {, add to stack
            if (c == '(' || c == '[' || c == '{')
            	balancer[currentEndIdx++] = c;
            
            //If pointer to end of used items in array is 0, same as if stack was empty
            else if (currentEndIdx == 0)
            	return "false";
            
            //If char is ), ], or }, check what prior item is on stack to verify balanced brackets
            else if (c == ')' || c == ']' || c == '}') {
                    
                char mostRecentOpen = balancer[currentEndIdx - 1];
                
                //Check for closed parentheses
                if (c == ')' && !(mostRecentOpen == '('))
                    return "false";
                
                //Check for closed bracket
                if (c == ']' && !(mostRecentOpen == '['))
                    return "false";
                
                //Check for closed squiggly bracket
                if (c == '}' && !(mostRecentOpen == '{'))
                    return "false";
                
                //Decrement pointer to end of used array
                currentEndIdx -= 1;
            }
            
        }
            
        return "true";
    }
}
