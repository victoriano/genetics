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
         
    	bestResults();
    	
    	/*
        double[] best10 = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        double[] best22 = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        double[] best50 = {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE};
        System.out.println("---------------");
        best10 = best(best10, solveClass10(data10(), 2)); //0.0
        System.out.println("10:\tfitness:\t" + best10[0] + "\tevaluations:\t" + best10[1] + "\ttime:\t" + best10[2]);
        best22 = best(best22, solveClass22(data22(), 2)); //0.0
        System.out.println("22:\tfitness:\t" + best22[0] + "\tevaluations:\t" + best22[1] + "\ttime:\t" + best22[2]);
        best50 = best(best50, solveClass50(data50(), 2)); //1036576.0
        System.out.println("50:\tfitness:\t" + best50[0] + "\tevaluations:\t" + best50[1] + "\ttime:\t" + best50[2]);
        */
    }
    
    /**
     * a method for testing the algorithm
     */
    public static void bestResults(){
        
    	int partitions = 2;
        int n = 5;
        int iterations = 100;
        int maxInt = 10;
        Random random = new Random();
        long[] values = new long[n];
        long[] costs = new long[n];
        System.out.println(partitions+"-"+maxInt+"-"+n+"----------------------------------");
        System.out.println();
        
        for (int i=0; i < iterations; ++i){
            for(int j=0; j < values.length; ++j){
                values[j] = random.nextInt(maxInt);
            	costs[j] = random.nextInt(maxInt);
            }
            //Gen gen = new Gen(values, partitions, 150 * partitions * values.length, 3, 5000 * partitions * values.length);
            //Gen(long[] values, int partitions, int populationSize, int tournamentSize, int evaluations){
            Gen gen = new Gen(values, costs, partitions, 5, 3, 5);
            CandidateBits cb = gen.solve();
           
            /* Print values */
            System.out.print("Values: ");
            for (int u=0; u < values.length; ++u){
                System.out.print(values[u] + " " );
            }
            
            System.out.println();
            
            /* Print Results */
            System.out.print( "Execution Time-> " + gen.executionTime() + " Fitness-> " + cb.eval() + " | Evals-> " + gen.evaluations() + " ");
            System.out.println("** Solution: " + cb.toString());
            System.out.println();
            //gen.printPopulation();
        }
    }
    
    public static double[] best(double[] a, double[] b){
        if (a[0] < b[0])
            return a;
        if (a[0] > b[0])
            return b;
        if (a[1] < b[1])
            return a;
        if (a[1] > b[1])
            return b;
        if (a[2] < b[2])
            return a;
        if (a[2] > b[2])
            return b;
        return a;
    }
    
    /* public static double[] solveClass10(long[] values, int partitions){
        CandidateBits solution;
        CandidateBits best = null;
        long executionTime=0;
        long evals=0;
        int tries=100;
        do{
            Gen gen = new Gen(values, partitions, 100, 3, 3000);
            solution = gen.solve();
            if (best==null || best.eval() > solution.eval())
                best = solution;
            executionTime += gen.executionTime();
            evals += (long) gen.evaluations();
            gen.printPopulation();
        }while(best.eval() > 0 && --tries > 0);
        
        return new double[] {best.eval(), evals, executionTime};
    }
    
    public static double[] solveClass22(long[] values, int partitions){
        CandidateBits solution;
        CandidateBits best = null;
        Gen gen;
        long executionTime=0;
        long evals=0;
        int tries=100;
        do{
            gen = new Gen(values, partitions, 100, 3, 5000);
            solution = gen.solve();
            if (best==null || best.eval() > solution.eval())
                best = solution;
            executionTime += gen.executionTime();
            evals += (long) gen.evaluations();
        }while(best.eval() > 0 && --tries > 0);
        return new double[] {best.eval(), evals, executionTime};
    }
    
    public static double[] solveClass50(long[] values, int partitions){
        CandidateBits solution;
        CandidateBits best = null;
        Gen gen;
        long executionTime=0;
        long evals=0;
        int tries=100;
        do{
            gen = new Gen(values, partitions, 150, 5, 100000);
            solution = gen.solve();
            if (best==null || best.eval() > solution.eval())
                best = solution;
            executionTime += gen.executionTime();
            evals += (long) gen.evaluations();
        }while(best.eval() > 0 && --tries > 0);
        return new double[] {best.eval(), evals, executionTime};
    }
     */
    
    
    /**
     * This method is used to implement testing in an easier way
     * @param problemSize
     * @param partitions
     * @param maxInt
     * @param iterations
     */
    /*
    public static void test(int problemSize, int partitions, int maxInt, int iterations) {
        System.out.println(partitions+"-"+maxInt+"-"+problemSize+"----------------------------------");
        Random random = new Random();
        for (int i = 0; i < iterations; ++i) {
            long[] values = new long[problemSize];
            for (int j = 0; j < values.length; ++j) {
                values[j] = Math.abs(random.nextInt(maxInt));
            }
            
            BruteForce bf = new BruteForce(values, partitions);
            
            Gen gen = new Gen(values, partitions, 150 * partitions * values.length, 3, 5000 * partitions * values.length);
            

            CandidateBits cbf = bf.solve();
            CandidateBits cg = gen.solve();
            
            System.out.println(
                    bf.executionTime()  + "\t"+ gen.executionTime()  +
                    "\t"+cbf.eval()  +"\t" + cg.eval() + "\t" +
                    bf.evaluations()  + "\t"+gen.evaluations());

        }
    }
		*/
    /**
     * This method returns the contents of a file in an array of integers
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
    
    public static long[] data10(){
        long[] ret = new long[10];
        int i=0;
        ret[i++] = 771;
        ret[i++] = 121;
        ret[i++] = 281;
        ret[i++] = 854;
        ret[i++] = 885;
        ret[i++] = 734;
        ret[i++] = 486;
        ret[i++] = 1003;
        ret[i++] = 83;
        ret[i++] = 62;
        return ret;
    }
    
    public static long[] data22(){
        int i=0;
        long[] ret = new long[22];
        ret[i++] = 25264128;
        ret[i++] = 3964928;
        ret[i++] = 9207808;
        ret[i++] = 27983872;
        ret[i++] = 116697770;
        ret[i++] = 233395541;
        ret[i++] = 2031616;
        ret[i++] = 75792384;
        ret[i++] = 11894784;
        ret[i++] = 27623424;
        ret[i++] = 83951616;
        ret[i++] = 86999040;
        ret[i++] = 72155136;
        ret[i++] = 47775744;
        ret[i++] = 98598912;
        ret[i++] = 8159232;
        ret[i++] = 6094847;
        ret[i++] = 28999680;
        ret[i++] = 24051712;
        ret[i++] = 15925248;
        ret[i++] = 32866304;
        ret[i++] = 2719744;
        return ret;
    }
    
    public static long[] data50(){
        int i=0;
        long[] ret = new long[50];
        ret[i++] = 907657426551623L;
        ret[i++] = 926537097695442L;
        ret[i++] = 11827571545041200L;
        ret[i++] = 160471229005013L;
        ret[i++] = 540756848073284L;
        ret[i++] = 882716220470872L;
        ret[i++] = 875859107321477L;
        ret[i++] = 655829465193191L;
        ret[i++] = 368532263742526L;
        ret[i++] = 508720816004917L;
        ret[i++] = 573699694804659L;
        ret[i++] = 1097742081928245L;
        ret[i++] = 108571471604032L;
        ret[i++] = 218209493553848L;
        ret[i++] = 503949276953292L;
        ret[i++] = 497726405190439L;
        ret[i++] = 315236008935388L;
        ret[i++] = 214044139340927L;
        ret[i++] = 443334784025869L;
        ret[i++] = 677532771156936L;
        ret[i++] = 1109700086915429L;
        ret[i++] = 1092057468715095L;
        ret[i++] = 24184253805423L;
        ret[i++] = 68871185476820L;
        ret[i++] = 75548015683424L;
        ret[i++] = 629882288922012L;
        ret[i++] = 671908248397004L;
        ret[i++] = 448808079353362L;
        ret[i++] = 98977648164301L;
        ret[i++] = 1098239218875374L;
        ret[i++] = 347842687332198L;
        ret[i++] = 157784368050269L;
        ret[i++] = 935138223287921L;
        ret[i++] = 683949757125904L;
        ret[i++] = 774639801423724L;
        ret[i++] = 337226946600281L;
        ret[i++] = 1109641498364918L;
        ret[i++] = 72064329935342L;
        ret[i++] = 1003033707296290L;
        ret[i++] = 1013104235958762L;
        ret[i++] = 258974006008027L;
        ret[i++] = 36276381621069L;
        ret[i++] = 499604752919056L;
        ret[i++] = 686902933543728L;
        ret[i++] = 465930593212991L;
        ret[i++] = 418310437121768L;
        ret[i++] = 16848771847494L;
        ret[i++] = 788146186821414L;
        ret[i++] = 448694017461307L;
        ret[i++] = 157784368050269L;
        return ret;
    }
    
}
