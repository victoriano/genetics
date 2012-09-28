/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk;

import java.util.Arrays;


public class BruteForceThread extends Thread{

    int[] best;
    double bestDif;
    CandidateBits solution;
    int partitions;
    int[] genes;
    long[] values;
    int start;
    
    /**
     * Creates a brute force solver with the given values and number of partitions
     * @param values
     * @param partitions 
     */
    BruteForceThread(long[] values, int[] genes, int partitions, int n){
        this.genes = Arrays.copyOf(genes, genes.length);
        this.values = values;
        this.partitions = partitions;
        this.start = n;
        bestDif = Double.MAX_VALUE;
    }
    
    public void run(){
        bruteForce(genes, start);
        CandidateBits ret = new CandidateBits(values, 0, (int)this.partitions, new EvalCounter());
        ret.setGenes(best);
        solution = ret;
    }
    
    /**
     * This method is called in a recursive way to solve the problem by brute force
     * @param genes
     * @param n 
     */
    private void bruteForce(int[] genes, int n){
        if (n==genes.length){
            eval(genes);
            return;
        }
        for (int i=0; i < partitions; ++i){
            genes[n] = i;
            bruteForce(genes, n+1);
        }
    }
    
    /**
     * This method is used to evaluate a given array of genes.
     * @param genes 
     */
    private void eval(int[] genes){
        double[] dif = new double[(int)partitions];
        dif[0] = values[0];
        for (int i=0; i < genes.length; ++i){
            dif[genes[i]] += values[i+1];
        }
        
        double min = Double.MAX_VALUE;
        for (int j=0; j < dif.length; ++j)
            if (dif[j] < min)
                min = dif[j];
        
        for (int j=0; j < dif.length; ++j)
            dif[j] -= min;
        
        double eval = 0;
        for (int j=0; j < dif.length; ++j)
            eval += dif[j];
        
        eval = Math.abs(eval);
        if (eval < bestDif){
            best = Arrays.copyOf(genes, genes.length);
            bestDif = eval;
            System.out.println(bestDif);
        }
    }
    


}
