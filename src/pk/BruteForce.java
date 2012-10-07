package pk;


import java.util.*;


public class BruteForce extends ProblemSolver{

    
    int[] best;
    double bestDif;
    
    /**
     * Creates a brute force solver with the given values and number of partitions
     * @param values
     * @param partitions 
     */
    BruteForce(long[] values, int partitions){
        super(values, values, partitions);
    }
    
    /**
     * This method is called in a recursive way to solve the problem by brute force
     * @param genes
     * @param n 
     */
    private void bruteForce(int[] genes, int n){
        if (bestDif <= optimum){
            return;
        }
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
            //System.out.println(bestDif);
        }

        ++this.evalCounter.evals;
    }
    
    /**
     * This method implements the abstract method solve. In order to do so it uses brute force.
     * @return solution
     */

    public CandidateBits solve() {
        this.start = System.currentTimeMillis();
        bestDif = Double.MAX_VALUE;
        bruteForce(new int[values.length-1], 0);
        CandidateBits ret = new CandidateBits(values, costs, 0, (int)this.partitions, this.evalCounter);
        ret.setGenes(best);
        this.end = System.currentTimeMillis();
        return ret;
    }

}
