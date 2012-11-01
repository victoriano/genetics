package pk;

import java.util.Arrays;
import java.util.Random;


@SuppressWarnings("unchecked")
public class CandidateBits implements Comparable{
	private long[] values;
    private long[] costs;
    private long[] constraints;
    private int[] genes;
    private Random random;
    private double mutationRate;
    private double fitness;
    private int knapsacks;
    double sum;
    private EvalCounter ec;
     
    /**
     * Sets the genes of the current candidate to genes.
     * @param genes
     */
    public void setGenes(int[] genes){
        this.genes = Arrays.copyOf(genes, genes.length);
    }
    
    /**
     * This constructor creates a candidate to solve the problem with the given values, mutation rate number of knapsacks and evaluation counter
     * @param values
     * @param mutationRate
     * @param knapsacks
     * @param ec 
     */
    CandidateBits(long[] values, long[] costs, long[] constraints, double mutationRate, int knapsacks, EvalCounter ec){
        this.knapsacks = knapsacks;
        this.ec = ec;
        this.mutationRate = mutationRate;
        this.random = new Random();
        this.genes = new int[values.length];
        this.constraints = constraints;
        this.values = values;
        this.costs = costs;
        for (int i=0; i < genes.length; ++i){
            genes[i] =random.nextInt(knapsacks);
        }
        this.fitness = -1;
        this.sum = 0;
        
        /* System.out.println("New individual! ");
        for (int k=0; k < genes.length; ++k){
            System.out.println("Added: " + genes[k]);
        } */ 
                
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
        CandidateBits ret = new CandidateBits(this.values, this.costs, this.constraints, this.mutationRate, this.knapsacks, this.ec);
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
                this.genes[i] = (this.genes[i]+random.nextInt(this.knapsacks))%knapsacks;
    }
    
    /**
     * This method returns a double representing the fitness of the given individual
     * @return evaluation
     */
    
    public double eval(){
    	
        if (this.fitness != -1){
        	return this.fitness;     	
        }
            
        ++this.ec.evals;
        
        /* We compute the accumulated diff of
         * values - costs of the
         * different knapsacks 
         * dif[0] = not present in any bag 
         */
        
        double[] dif = new double[knapsacks];
        for (int i=0; i < genes.length; ++i){
        //System.out.println("Gen " + this.toString() + " cat: " + genes[i] + " value: " + values[i] + " - cost: " + costs[i]  );
            dif[genes[i]] += values[i];
            dif[genes[i]] -= costs[i];
        }
        
        /* We sum the items in each bag 
         * dif[0] are the class for items not loaded to any bag
         * */
        sum = 0;
        
        this.fitness = 0;
        for (int j=1; j < dif.length; ++j){
        	 // simple strategy for broken constraints
        	 if(dif[j]> constraints[j-1]){
        		 //System.out.println();
        		 return this.fitness = 10000;
        	 }else{
        	 sum += dif[j];}
        }
            
        this.fitness = sum;
        this.fitness = 1/this.fitness;

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
        String ret = "";
        for (int i=0; i < this.genes.length; ++i)
            ret += Integer.toString(this.genes[i]);
        return ret;
    }
    
}
