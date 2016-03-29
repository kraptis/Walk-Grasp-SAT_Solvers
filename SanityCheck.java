/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msc.krr.dynsat;

import java.util.Vector;

/**
 *
 * @author
 * 
 * This class does a sanity check on solutions of problems.
 */
public class SanityCheck {

    public CNF problem;
    public Solution solution;

    public Vector<Integer> satClauses;
    public Vector<Integer> unSatClauses;

    public SanityCheck(CNF problem, Solution solution){
        this.problem = problem;
        this.solution = solution;

        this.satClauses = this.problem.getSatClauses();
        this.unSatClauses = this.problem.getUnSatClauses();
    }

    public boolean check() {
        System.out.println("Checking...");
        boolean isSane = true;
        Integer clause_index = 0;
        for(Vector <Integer> clause : this.problem.clauses)
        {
            if(clause_index == 0)
            {
                clause_index++;
                continue;
            }
            
            boolean clause_is_satisfied = false;
            for(Integer literal : clause)
            {
                if(literal < 0)
                {
                    // Look if the correspending variable has value = 0
                    if(this.solution.variableAssignment[Math.abs(literal)] == 0)
                    {
                        clause_is_satisfied = true;
                        continue;
                    }
                }
                else if(literal > 0)
                {
                    // Look if the corresponding variable has value = 1
                    if(this.solution.variableAssignment[Math.abs(literal)] == 1)
                    {
                        clause_is_satisfied = true;
                        continue;
                    }
                }
            }


            //Check that the clause is in the sat ones and not in the unsat ones
            if(clause_is_satisfied)
            {
                if(this.satClauses.contains(clause_index) &&
                   !this.unSatClauses.contains(clause_index))
                {
                    ;
                }
                else
                {
                    isSane = false;
                    System.err.println("WARNING: SOLUTION IS NOT CORRECT!\n "
                                      + "SAT clause is not marked as SAT.");
                }
            }
            //Check that the clause is in the unsat ones and not in the sat ones
            else
            {
                if(this.unSatClauses.contains(clause_index) &&
                   !this.satClauses.contains(clause_index))
                {
                    ;
                }
                else
                {
                    isSane = false;
                    System.err.println(String.format("[%s]", clause.toString()));
                    System.err.println("WARNING: SOLUTION IS NOT CORRECT!\n "
                                      + "UNSAT clause is not marked as UNSAT.");
                }

            }

            if(this.unSatClauses.size() != this.solution.unSatClauses ||
               this.satClauses.size() != this.solution.satClauses)
            {
                isSane = false;
                System.err.println("WARNING: SOLUTION IS NOT CORRECT!\n "
                                   + "Clauses size discrepancy!");
            }

            clause_index++;
        }

        

        if(! isSane)
        {
            System.err.println(this.solution);
        }
        return isSane;
    }

}
