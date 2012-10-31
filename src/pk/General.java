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
         
    	randomTest();
    	//Test();
    	
    }
    
    
     /**
     * A method for testing the algorithm 
     * passing random coefficients an costs
     * @param problemSize
     * @param partitions
     * @param maxInt
     * @param iterations
     */
    public static void randomTest(){
        
    	int partitions = 1;
        int n = 8;
        int batches = 1;
        int maxInt = 10;
        
        Random random = new Random();
        long[] values = new long[n];
        long[] costs = new long[n];
        
        System.out.println( "Partitions: " + partitions+  " | Total N¼ Items: " + n + " | MaxInt: " + maxInt +  " ------->>");
        
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
            Gen gen = new Gen(values, costs, partitions, 8, 3, 200 );
            
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
     * A method for testing the algorithm 
     * passing random coefficients an costs
     * @param problemSize
     * @param partitions
     * @param maxInt
     * @param iterations
     */
    public static void Test(){
        
    	int partitions = 1;
        int n = 10;
        int maxInt = 10;
        
        long[] values = new long[n];
        long[] costs = new long[n];
        
        System.out.println( "Partitions: " + partitions+  " | Total N¼ Items: " + n + " | MaxInt: " + maxInt +  " ------->>");
                    
            values[0] = 71;
            values[1] = 21;
            values[2] = 51;
            values[3] = 54;
            values[4] = 15;
            values[5] = 34;
            values[6] = 86;
            values[7] = 103;
            values[8] = 3;
            values[9] = 2;
            
            costs[0] = 25;
            costs[1] = 76;
            costs[2] = 987;
            costs[3] = 4;
            costs[4] = 55;
            costs[5] = 453;
            costs[6] = 563;
            costs[7] = 19;
            costs[8] = 1678;
            costs[9] = 34;
 
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
            Gen gen = new Gen(values, costs, partitions, 8, 3, 5);
            
            
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
