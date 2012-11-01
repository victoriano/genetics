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
            //Gen(long[] values, int partitions, int populationSize, int tournamentSize, int evaluations){
            Gen gen = new Gen(values, costs, constraints, knapsacks, 10, 4, 5000);
                      
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
    
   public static void randomTest(){
       
   	   int knapsacks = 1;
       int n = 8;
       int batches = 1;
       int maxInt = 10;
       
       Random random = new Random();
       long[] values = new long[n];
       long[] costs = new long[n];
       long[] constraints = new long[knapsacks];
       
       System.out.println( "Partitions: " + knapsacks+  " | Total N¼ Items: " + n + " | MaxInt: " + maxInt +  " ------->>");
       
       /* Generate Values and Costs randomly */
       for (int i=0; i < batches; ++i){
           for(int j=0; j < values.length; ++j){
               values[j] = random.nextInt(maxInt);
           	   costs[j] = random.nextInt(maxInt);
           	   constraints[j] = random.nextInt(maxInt);
           }

           /* Print values */
           System.out.print("Values: ");
           for (int u=0; u < values.length; ++u){
               System.out.print(values[u] + " " );
           }
           
           /* Print costs */
           System.out.print("| Costs: ");
           for (int u=0; u < costs.length; ++u){
               System.out.print(costs[u] + " " );
           }
           System.out.println();
           System.out.println();
           
           /* Create Genetic object*/
           //Gen(long[] values, int partitions, int populationSize, int tournamentSize, int evaluations){
           Gen gen = new Gen(values, costs, constraints, knapsacks, 2, 3, 60 );
           
           /*Solve it */
           CandidateBits cb = gen.solve();
          

           System.out.println();
           
           /* Print Results */
           System.out.print( "Execution Time-> " + gen.executionTime() + " Fitness-> " + cb.eval() + " Sum-> " + cb.sum + " + | Evals-> " + gen.evaluations() + " ");
           System.out.println("** Solution: " + cb.toString());
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
