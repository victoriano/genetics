/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pk;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BruteForceMultiThreaded extends ProblemSolver{

    public BruteForceMultiThreaded(long[] values, int partitions) {
        super(values, values, partitions);
    }
    

    @SuppressWarnings("unchecked")
	CandidateBits solve() {
        int n= (int) partitions;
        BruteForceThread[] t = new BruteForceThread[n*n*n];
        List<CandidateBits> l = new LinkedList<CandidateBits>();
        int[] genes = new int[values.length-1];
        int it = 0;
        
        genes[0] = 0;
        genes[1] = 0;
        genes[2] = 0;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 0;
        genes[1] = 0;
        genes[2] = 1;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 0;
        genes[1] = 1;
        genes[2] = 0;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 0;
        genes[1] = 1;
        genes[2] = 1;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 1;
        genes[1] = 0;
        genes[2] = 0;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 1;
        genes[1] = 0;
        genes[2] = 1;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 1;
        genes[1] = 1;
        genes[2] = 0;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        genes[0] = 1;
        genes[1] = 1;
        genes[2] = 1;
        t[it++] = new BruteForceThread(values, genes, (int)partitions, 3);
        
        for (int i=0; i < t.length; ++i){
            t[i].start();
        }
        
        for (int i=0; i < t.length; ++i){
            try {
                t[i].join();
                l.add(t[i].solution);
            } catch (InterruptedException ex) {
                Logger.getLogger(BruteForceMultiThreaded.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Collections.min(l);
    }
}
