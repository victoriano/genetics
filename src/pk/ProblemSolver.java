package pk;


public abstract class ProblemSolver {
    long[] values;
    long[] costs;
    long[] constraints;
    long knapsacks;
    double mutationRate;
    EvalCounter evalCounter;
    long start;
    long end;

          
    /**
     * Returns the execution time of the algorithm
     * @return time
     */
    public long executionTime(){
        return end-start;
    }
    
    /**
     * Builds a problem solver given an array of values and a number of knapsacks
     * @param values
     * @param knapsacks 
     */
    
    ProblemSolver(long[]values, long[]costs, long[]constraints, long knapsacks, double mutationRate){
        this.values = values;
        this.costs = costs;
        this.constraints = constraints;
        this.knapsacks = knapsacks+1;
        this.mutationRate =  mutationRate ;
        this.evalCounter = new EvalCounter();
        double sum = 0;
        for (int i=0; i < values.length; ++i)
            sum += values[i];
    }
    
    /**
     * Sets the number of knapsacks of the current problem solver to partitions.
     * @param knapsacks
     */
    public void setPartitions(int knapsacks){
        this.knapsacks = knapsacks;
    }
        
    /**
     * Sets the values of the current problem solver to values.
     * @param values
     */
    public void setValues(long[] values){
        this.values = values;
    }
    
    /**
     * Returns the number of evaluations required to obtain the solution.
     * @return evaluations
     */
    public int evaluations(){
        return this.evalCounter.evals;
    }
    /**
     * Abstract method that each class has to implement to actually solve the problem
     * @return solution
     */
    abstract CandidateBits solve();
}
