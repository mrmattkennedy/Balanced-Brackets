package balancedBrackets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.LongStream;

public class Driver {
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		//Keep track of times for each method
		long[] times = new long[10];
		for (int i = 0; i < 10; i++) {
			//Open a buffered reader to the file
			try (BufferedReader br = new BufferedReader(new FileReader("brackets2.txt"))) {
				
				//Get a line variable and start the timer
			    String line;
			    long startTime = System.nanoTime();
			    int count = 0;
			    
			    //Go through each line until the line is null
			    while ((line = br.readLine()) != null) {
			    	String result = Balancers.isBalancedNoStack(line);
			    	if (result.equals("false"))
			    		count += 1;
			    }
			    
			    //Print out the time in milliseconds
			    //System.out.println(count);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				System.out.println(duration / 1000000);
				times[i] = duration / 1000000;
			}
		}
		
		System.out.println("\n" + LongStream.of(times).sum() / times.length);
	}
}
