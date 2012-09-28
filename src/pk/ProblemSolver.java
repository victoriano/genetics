package pk;


public abstract class ProblemSolver {
    long[] values;
    long[] costs;
    long partitions;
    EvalCounter evalCounter;
    long optimum;
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
     * Builds a problem solver given an array of values and a number of partitions
     * @param values
     * @param partitions 
     */
    
    ProblemSolver(long[] values, long[]costs, long partitions){
        this.values = values;
        this.costs = costs;
        this.partitions = partitions;
        this.evalCounter = new EvalCounter();
        optimum = 0;
        for (int i=0; i < values.length; ++i)
            optimum = (optimum + (values[i]%partitions))%partitions;
        double sum = 0;
        for (int i=0; i < values.length; ++i)
            sum += values[i];
    }
    
    /**
     * Sets the number of partitions of the current problem solver to partitions.
     * @param partitions
     */
    public void setPartitions(int partitions){
        this.partitions = partitions;
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
