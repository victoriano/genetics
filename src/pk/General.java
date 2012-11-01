package pk;

import java.io.*;
import java.util.*;


public class General {

    /**
     * This method starts the execution of the program
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    
    public static void main(String... args) throws FileNotFoundException, IOException {
       
        Test();
      //randomTest();  	
      
  }
  
  
    /**
     * A method for testing the algorithm 
     * passing data sets found online
     * @param problemSize
     * @param knapsacks
     * @param maxInt
     * @param iterations
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    
    public static void Test() throws FileNotFoundException, IOException{
        
    	/* Data Source:
        *  http://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html
        */
      int evaluations = 5000;
      double mutationRate = 0.1;
      int populationSize = 10; 
      int tournamentSize = 4; 		
      
      long[] values = readFile("datasets/p1/p01_p.txt");
      long[] costs = readFile("datasets/p1/p01_w.txt");
      long[] constraints = readFile("datasets/p1/p01_c.txt");
      
      int knapsacks = constraints.length;
      int n = values.length;
      
      System.out.println( "Knapsacks: " + knapsacks+  " | Total N¼ Items: " + n +  " ------->>");
      System.out.println();
      
      /* Print values parsed */
      System.out.print("Values: ");
      for (int u=0; u < values.length; ++u){
        System.out.print(values[u] + " " );
    }
    
    /* Print costs parsed */
    System.out.print("| Costs: ");
    for (int u=0; u < costs.length; ++u){
        System.out.print(costs[u] + " " );
    }
    
    System.out.print("| Constraints: ");
    for (int u=0; u < constraints.length; ++u){
        System.out.print(constraints[u] + " " );
    }
    
    System.out.println();
    
    /* Create Genetic object*/
    Gen gen = new Gen(values, costs, constraints, knapsacks,  mutationRate, populationSize, tournamentSize, evaluations);
    
    /*Solve it */
    CandidateBits cb = gen.solve();
    
    System.out.println();
    
    /* Print Results */
    System.out.print( "Execution Time-> " + gen.executionTime() + " Fitness-> " + cb.eval() + " Sum-> " + cb.sum + " + | Evals-> " + gen.evaluations() + " ");
    System.out.println("** Solution: " + cb.toString());
    System.out.println();
    System.out.println("Latest popultion: ");
    System.out.println();
    gen.printPopulation();
    
}

    /**
	* A Test to run different 
	* batches of problems with 
	* cost and values generated with
	* random numbers 
    */
	
    
	public static void randomTest() throws FileNotFoundException, IOException{
      
		int evaluations = 50;
		double mutationRate = 0.1;
		int populationSize = 10; 
		int tournamentSize = 4; 
		
		int batches = 2;
		int n = 8;  
		int maxInt = 10;
		int knapsacks = 2;
		
      Random random = new Random();
      long[] values = new long[n];
      long[] costs = new long[n];
      long[] constraints = new long[knapsacks];

      
      for (int i=0; i < batches; ++i){
         
        /* Generate Values and Costs randomly */
        for(int j=0; j < values.length; ++j){
            values[j] = random.nextInt(maxInt);
            costs[j] = random.nextInt(maxInt);          
        }
        
        for(int j=0; j < constraints.length; ++j){
        constraints[j] = random.nextInt(maxInt);
        }
        
        System.out.println( "Knapsacks: " + knapsacks+  " | Total N¼ Items: " + n +  " ------->>");
        System.out.println();
        
        /* Print values parsed */
        System.out.print("Values: ");
        for (int u=0; u < values.length; ++u){
            System.out.print(values[u] + " " );
        }
        
        /* Print costs parsed */
        System.out.print("| Costs: ");
        for (int u=0; u < costs.length; ++u){
            System.out.print(costs[u] + " " );
        }
        
        System.out.print("| Constraints: ");
        for (int u=0; u < constraints.length; ++u){
            System.out.print(constraints[u] + " " );
        }
        
        System.out.println();
        
        /* Create Genetic object*/
        Gen gen = new Gen(values, costs, constraints, knapsacks,  mutationRate, populationSize, tournamentSize, evaluations);
        
        /*Solve it */
        CandidateBits cb = gen.solve();
        
        System.out.println();
        
        /* Print Results */
        System.out.print( "Execution Time-> " + gen.executionTime() + " Fitness-> " + cb.eval() + " Sum-> " + cb.sum + " + | Evals-> " + gen.evaluations() + " ");
        System.out.println("** Solution: " + cb.toString());
        System.out.println();
        System.out.println("Latest popultion: ");
        System.out.println();
        gen.printPopulation();
        
    }
}



    /**
     * This method returns the contents of a file in an array of longs
     * @param fileName
     * @return values
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static long[] readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
        List<Long> ret = new ArrayList<Long>();
        String s = in.readLine();
        while (s != null) {
            ret.add(Long.parseLong(s));
            s = in.readLine();
        }
        in.close();
        long[] r = new long[ret.size()];
        for (int i = 0; i < ret.size(); ++i) {
            r[i] = ret.get(i);
        }
        return r;
    }
    

    
}
