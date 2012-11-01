package pk;


import java.util.*;


public class Gen extends ProblemSolver{
  int populationSize;
  int tournamentSize;
  float best  = Float.MAX_VALUE;
  int evaluations;
  Random random;
  List<CandidateBits> population;
  int evaluationsLeft;
  
  /**
   * Creates a genetic solver with the given values, number of partitions, population size, tournament size and maximum number of evaluations
   * @param values
   * @param costs
   * @param knapsacks
   * @param populationSize
   * @param tournamentSize
   * @param evaluations 
   */
    Gen(long[] values, long[] costs, long[] constraints, int knapsacks,  double mutationRate, int populationSize, int tournamentSize, int evaluations){
        super(values, costs, constraints, knapsacks, mutationRate);
        random = new Random();
        this.populationSize = populationSize;
        this.tournamentSize = tournamentSize;
        this.evaluations = evaluations;
    }

    /**
     * This method is responsible of creating a new population from the given one.
     * the 
     * @param population
     * @param tournamentSize 
     */
    private void nextGen(List<CandidateBits> population, int tournamentSize){
        if (evaluationsLeft < 2000)
            nextGenByOne(population, tournamentSize);
        else if (evaluationsLeft < 5000)
            nextGenElitist(population, tournamentSize, 3); 
        else
            nextGenRoulette(population);
    }
    
    private void nextGenRoulette(List<CandidateBits> population){
        List<CandidateBits> newGen = new ArrayList<CandidateBits>();

        population.addAll(newGen);
        double[] roulette = rouletteArray();
        while(newGen.size()  < populationSize){
            CandidateBits e1 = roulette(roulette);
            CandidateBits e2 = roulette(roulette);
            newGen.add(e1.cross(e2));
        }
        population = newGen;
    }
    
    private double[] rouletteArray(){
        double[] fitness = new double[population.size()];
        double sum = 0;
        for (int i=0; i < population.size(); ++i){
            fitness[i] = population.get(i).eval();
            sum += fitness[i];
        }
        
        /*Normalize using 1-fitness*/
        for (int i=0; i < fitness.length; ++i){
            fitness[i] = 1.0 - (fitness[i]/sum);
        }
        return fitness;
    }
    
    private CandidateBits roulette(double[] fitness){
        double d = random.nextDouble();
        int i = 0;
        while (fitness[i] < d){
            d -= fitness[i++];
            if (i==fitness.length)
                i=0;
        }
        return population.get(i);
    }
    
    @SuppressWarnings("unchecked")
	private void nextGenElitist(List<CandidateBits> population, int tournamentSize, int elite){
        List<CandidateBits> newGen = new ArrayList<CandidateBits>();
        for (int i=0; i < elite; ++i){
            CandidateBits a = Collections.min(population);
            newGen.add(a);
            population.remove(a);
        }
        population.addAll(newGen);
        while (newGen.size() < populationSize){
            CandidateBits e1 = tournament(population, tournamentSize);
            CandidateBits e2 = tournament(population, tournamentSize);
            newGen.add(e1.cross(e2));
        }
        population = newGen;
    }
    
    @SuppressWarnings("unchecked")
	private void nextGenByOne(List<CandidateBits> population, int tournamentSize){
        CandidateBits e1 = tournament(population, tournamentSize);
        CandidateBits e2 = tournament(population, tournamentSize);
        population.add(e1.cross(e2));
        population.remove(Collections.max(population));
    }
    
    /**
     * This method creates an small subset of the population, sets a tournament between them and returns the winner
     * @param population
     * @param size
     * @return winner
     */
    @SuppressWarnings("unchecked")
	private CandidateBits tournament(List<CandidateBits> population, int size){
        List<CandidateBits> tau = new ArrayList<CandidateBits>();
        for (int i=0; i < size; ++i)
            tau.add(population.get(random.nextInt(population.size())));
        return Collections.min(tau);
    }

    /**
     * This method solves the problem by returning the best candidate it can find by means of a genetic algorithm
     * @return solution
     */
    @SuppressWarnings("unchecked")
	@Override
    public CandidateBits solve() {

      this.start = System.currentTimeMillis();
      population = new ArrayList<CandidateBits>();
      
      //Create initial population 
      for (int i=0; i < populationSize; ++i)
          population.add(new CandidateBits(values, costs, constraints,  mutationRate, (int)knapsacks, evalCounter));
      
      int nGenerations = 0;
      // Criteria to Stop either reach the total n¼ of evals passed or reach and optimum
      //while(evalCounter.evals < evaluations && (Collections.min(population).eval() > 0.0000000000001)){
      while(evalCounter.evals < evaluations ){
    	    //printPopulation();
    	  	//System.out.println("Generations " + nGenerations );
            nextGen(population, tournamentSize);
            Collections.sort(population);
    	  	
    	  	++nGenerations;
      }
      
      this.end = System.currentTimeMillis();
      return Collections.min(population);
    }
    
    
    public void printPopulation(){
    	//System.out.println("Generation n:" + evalCounter.evals + " sum: " + Collections.min(population).sum + " best fitness: " + Collections.min(population).eval());
    	//System.out.println("Solution: " + Collections.min(population).toString());
    	for (int i=0; i < population.size(); ++i){
            System.out.println(population.get(i).toString());
        }
    }
}
