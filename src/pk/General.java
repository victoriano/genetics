package pk;

import java.io.*;
import java.util.*;


public class General {

    /**
     * This method starts the execution of the software
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
     */
    public static void Test(){
        
    	int knapsacks = 1;
        int n = 10;
       
        long[] values = new long[n];
        long[] costs = new long[n];
        
        System.out.println( "Knapsacks: " + knapsacks+  " | Total N¼ Items: " + n +  " ------->>");
                    
            values[0] = 92;
            values[1] = 57;
            values[2] = 49;
            values[3] = 68;
            values[4] = 60;
            values[5] = 43;
            values[6] = 67;
            values[7] = 84;
            values[8] = 87;
            values[9] = 72;
            
            
            costs[0] = 23;
            costs[1] = 31;
            costs[2] = 29;
            costs[3] = 44;
            costs[4] = 53;
            costs[5] = 38;
            costs[6] = 63;
            costs[7] = 85;
            costs[8] = 89;
            costs[9] = 82;
    
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
            Gen gen = new Gen(values, costs, knapsacks, 20, 5, 234);
           
            // Optimal Solution 111010000 of p01_s.txt
            //http://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html
            
            /*Solve it */
            CandidateBits cb = gen.solve();
           

            System.out.println();
            
            /* Print Results */
            System.out.print( "Execution Time-> " + gen.executionTime() + " Fitness-> " + cb.eval() + " Sum-> " + cb.sum + " + | Evals-> " + gen.evaluations() + " ");
            System.out.println("** Solution: " + cb.toString());
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
       
       System.out.println( "Partitions: " + knapsacks+  " | Total N¼ Items: " + n + " | MaxInt: " + maxInt +  " ------->>");
       
       /* Generate Values and Costs randomly */
       for (int i=0; i < batches; ++i){
           for(int j=0; j < values.length; ++j){
               values[j] = random.nextInt(maxInt);
           	costs[j] = random.nextInt(maxInt);
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
           Gen gen = new Gen(values, costs, knapsacks, 8, 3, 200 );
           
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
