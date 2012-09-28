package pk;

import java.util.Arrays;
import java.util.Random;

/**
 *
 */
@SuppressWarnings("unchecked")
public class CandidateBits implements Comparable{
    private long[] values;
    private long[] costs;
    private int[] genes;
    private Random random;
    private double mutationRate;
    private double fitness;
    private int partitions;
    private EvalCounter ec;
     
    /**
     * Sets the genes of the current candidate to genes.
     * @param genes
     */
    public void setGenes(int[] genes){
        this.genes = Arrays.copyOf(genes, genes.length);
    }
    
    /**
     * This constructor creates a candidate to solve the problem with the given values, mutation rate number of partitions and evaluation counter
     * @param values
     * @param mutationRate
     * @param partitions
     * @param ec 
     */
    CandidateBits(long[] values, long[] costs, double mutationRate, int partitions, EvalCounter ec){
        this.partitions = partitions;
        this.ec = ec;
        this.mutationRate = mutationRate;
        this.random = new Random();
        this.genes = new int[values.length-1];
        this.values = values;
        this.costs = costs;
        for (int i=0; i < genes.length; ++i){
            genes[i] =random.nextInt(partitions);
        }
        this.fitness = -1;
    }
    
    /**
     * This method is responsible of crossing the current candidate with the given one.
     * @param s
     * @return candidate
     */
    public CandidateBits cross(CandidateBits s){
        int r = random.nextInt(3);
        if (r==0)
            return simpleCross(s);
        if (r==1)
            return multipointCross(s);
        return uniformCross(s);
    }
    
    /**
     * This method is responsible of crossing the current candidate with the given one by using a simple cross.
     * @param candidate
     * @return candidate
     */
    public CandidateBits simpleCross(CandidateBits candidate){
        CandidateBits ret = this.clone();
        for (int i=1+random.nextInt(this.genes.length-1); i < candidate.genes.length; ++i)
            ret.genes[i] = candidate.genes[i];
        ret.mutate(this.mutationRate);
        return ret;
    }
    
    /**
     * This method is responsible of crossing the current candidate with the given one by using a multiple point cross.
     * @param candidate
     * @return candidate
     */
    public CandidateBits multipointCross(CandidateBits candidate){
        CandidateBits ret = this.clone();
        int a = random.nextInt(this.genes.length);
        int b = a + random.nextInt(this.genes.length-a);
        for (int i=0; i < a; ++i)
            ret.genes[i] = candidate.genes[i];
        for (int i=b; i < this.genes.length; ++i)
            ret.genes[i] = candidate.genes[i];
        
        ret.mutate(this.mutationRate);
        return ret;
    }
    
    /**
     * This method is responsible of crossing the current candidate with the given one by using a uniform cross.
     * @param candidate
     * @return candidate
     */
    public CandidateBits uniformCross(CandidateBits candidate){
        CandidateBits ret = this.clone();
        for (int i=2; i < candidate.genes.length; ++i)
            if (i%2==0)
                ret.genes[i] = candidate.genes[i];
        ret.mutate(this.mutationRate);
        return ret;
    }
    
    /**
     * This method returns an exact copy of the candidate.
     * @return candidate
     */
    @Override
    public CandidateBits clone(){
        CandidateBits ret = new CandidateBits(this.values, this.costs, this.mutationRate, this.partitions, this.ec);
        for (int i=0; i < ret.genes.length; ++i)
            ret.genes[i] = this.genes[i];
        return ret;
    }
    
    /**
     * This method mutates the candidate with the given ratio.
     * @param ratio
     */
    public final void mutate(double ratio){
        for (int i=0; i < genes.length; ++i)
            if (Math.random() < ratio)
                this.genes[i] = (this.genes[i]+random.nextInt(this.partitions))%partitions;
    }
    
    /**
     * This method returns a double representing the fitness of the given individual
     * @return evaluation
     */
    public double eval(){
        if (this.fitness != -1)
            return this.fitness;
        ++this.ec.evals;
        
        double[] dif = new double[partitions];
        dif[0] = values[0] - costs[0];
        for (int i=0; i < genes.length; ++i){
            dif[genes[i]] += values[i+1];
            dif[genes[i]] -= costs[i+1];
        }
        
        double min = Double.MAX_VALUE;
        
        for (int j=0; j < dif.length; ++j)
            if (dif[j] < min)
                min = dif[j];

        
        for (int j=0; j < dif.length; ++j)
            dif[j] -= min;
        
        this.fitness = 0;
        for (int j=0; j < dif.length; ++j)
            this.fitness += dif[j];
        
        this.fitness = Math.abs(this.fitness);
        return this.fitness;
    }
    
    /**
     * This method is implemented in order to sort our candidates.
     * @param candidate
     * @return
     */
    public int compareTo(Object candidate) {
        return ((Double) this.eval()).compareTo((Double) ((CandidateBits) candidate).eval());
    }
    
    /**
     * This method is used to obtain a representation of the candidate.
     * @return representation
     */
    @Override
    public String toString(){
        String ret = "0";
        for (int i=0; i < this.genes.length; ++i)
            ret += Integer.toString(this.genes[i]);
        return ret;
    }
    
}
