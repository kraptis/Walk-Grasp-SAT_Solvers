/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msc.krr.dynsat;

import java.util.Vector;

/**
 *
 * @author kostas
 */
public class Solution {

    // This is an array of integers containing the variable assignment
    // -1 for unassigned, 1 for true, 0 for false
    public Integer[] variableAssignment;

    // Number of satisfied clauses
    public Integer satClauses;

    //public Vector<Integer> satClausesList = new Vector<Integer>();
    //public Vector<Integer> unSatClausesList = new Vector<Integer>();

    // Number of unsatisfied clauses
    public Integer unSatClauses;

    // Number of literals that make the clause true for each clause
    public Integer[] trueLiteralsInClause;

    public Integer literalsPerClause;

    public int numOfClauses;

    //epistrefei - an to this kaliteri apo other,0 an isa, + an other kalitero
    public Integer comparesolutions(Solution other,Solution previousbest){
        int diff = unSatClauses - other.unSatClauses;
        if(diff!=0) return diff;

        int thisFlips = 0;
        int otherFlips = 0;
        for( int i = 1; i < variableAssignment.length; ++i ) {
            if( other.variableAssignment[i]!=previousbest.variableAssignment[i]) otherFlips++;
            if( variableAssignment[i]!=previousbest.variableAssignment[i]) thisFlips++;
        }
        return thisFlips - otherFlips;
    }

    public Solution(int numOfVars, int numOfClauses, int literalsPerClause)
    {
        this.variableAssignment = new Integer[numOfVars+1];
        this.trueLiteralsInClause = new Integer[numOfClauses+1];
        this.satClauses = 0;
        this.unSatClauses = numOfClauses;

        this.literalsPerClause = literalsPerClause;
        this.numOfClauses = numOfClauses;
        
        this.clean();
    }

    public void clean()
    {
        this.variableAssignment = new Integer[this.variableAssignment.length];
        this.trueLiteralsInClause = new Integer[this.trueLiteralsInClause.length];
        this.satClauses = 0;
        this.unSatClauses = this.numOfClauses;

        //this.satClausesList = new Vector<Integer>();

        for(int i=1; i<this.variableAssignment.length; i++)
        {
            this.variableAssignment[i] = -1;
        }

        for(int i=1; i<this.trueLiteralsInClause.length; i++)
        {
            this.trueLiteralsInClause[i] = 0;
            
        }

//        for(int i=1; i<=this.numOfClauses;i++)
//        {
//            this.unSatClausesList.add(i);
//        }
    }

    @Override
    public String toString()
    {
        StringBuffer output = new StringBuffer();
        
        output.append("VALUES\n");
        for(int i=1; i<this.variableAssignment.length; i++)
        {
            output.append("["+i+": "+this.variableAssignment[i]+"]  ");
             if(i%5 == 0)
            {
                output.append("\n");
            }
        }

        output.append("\nTRUE LITERALS IN EACH CLAUSE\n");
        for(int i=1; i<this.trueLiteralsInClause.length; i++)
        {
            output.append("["+i+": "+this.trueLiteralsInClause[i]+""+(this.trueLiteralsInClause[i] == 0 ? " (N/F)" : "") +"]  ");

            if(i%5 == 0)
            {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
