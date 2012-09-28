package pk;


public class EvalCounter {
    /**
     * Integer storing all the evaluations done
     */
    public int evals;
    
    /**
     * It gives a fresh evaluation counter
     */
    public EvalCounter(){
        this(0);
    }
    
    /**
     * It creates an evaluation counter set at evals.
     * @param evals 
     */
    public EvalCounter(int evals){
        this.evals = evals;
    }
    
    /**
     * It creates an evaluation counter from the given one.
     * @param evalCounter 
     */
    public EvalCounter(EvalCounter evalCounter){
        this(evalCounter.evals);
    }
    
    
}
