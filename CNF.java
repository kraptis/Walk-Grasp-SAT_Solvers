

package msc.krr.dynsat;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;
import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;


public class CNF {
//    public static final boolean DEBUG = false;
//    public static final boolean SHOW_PROGRESS = false;
//    public static final boolean PLOT = false;

    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final int ANASSIGNED = -1;
	 
    private static final String CLAUSE_FORMAT = "\\-?\\b\\d+\\b";

    // Contains a double dimension vector for clauses and literals
    Vector<Vector<Integer>> clauses;

    // Contains a Vector for each literal i.e. X1 -X1 X2 -X2 s.t.:
    // X1 := [C1, C2, C3, C4]
    Dictionary<Integer,Vector<Integer>> literals;

    // The solution object
    Solution solution;

    // Assigned literals in each clause
    Vector <Integer> assignedLiteralsPerClause;

    String fileName = "";
    

    /* Functions responsible for loading and maintaining the structure */
    public CNF () {
        this.clauses = new Vector<Vector<Integer>>();
        this.literals = new Hashtable<Integer,Vector<Integer>>();
        
    }

    public Dictionary<Integer,Vector<Integer>> getLiterals () {
        return this.literals;
    }


    public void loadFromFile (String fileName) {
        // Populates the literals and clauses arrays
        
        this.fileName = fileName;


        try{
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            //int lcount = 0;
            int ccount = 0;
            int noOfVars = 0;
            this.clauses = new Vector<Vector<Integer>>();

            // Assigning clauses[0] to null
            this.clauses.add(null);
            
            this.literals = new Hashtable<Integer,Vector<Integer>>();
            this.assignedLiteralsPerClause = new Vector<Integer>();

            //System.out.println("======== LOADING: " + fileName + " ========");

            while ((strLine = br.readLine()) != null)
            {
                if(strLine.matches("^(c)\\s.*"))  // Skip comments...
                    continue;
                else if(strLine.matches("^(p)\\s.*"))
                {
                    noOfVars = Integer.parseInt(strLine.split(" ")[2]);
                    //System.out.println("Problem has " + noOfVars + " Variables ");
                    continue;
                }
                ccount++;


                this.clauses.add(new Vector<Integer>());
                Pattern p = Pattern.compile(CLAUSE_FORMAT);
                Matcher m = p.matcher(strLine); // get a matcher object
                while(m.find()) {
                    //lcount++;
                    int literal = Integer.parseInt(strLine.substring(m.start(),
                                                                     m.end()));
                    if(literal == 0)
                    {
                        continue;
                    }
                    
                    Vector<Integer> literal_appearences;
                    if(this.literals.get(literal) != null)
                    {
                        literal_appearences = this.literals.get(literal);
                    }
                    else
                    {
                        literal_appearences = new Vector<Integer>();
                    }
                    literal_appearences.add(ccount);
                    //System.out.println("Literal: " + literal + " appears in clause: " + ccount);
                    this.literals.put(literal, literal_appearences);
                    this.clauses.lastElement().add(literal);
                }

            }
            in.close();
            this.solution = new Solution(noOfVars, this.clauses.size()-1, this.clauses.get(1).size());
            
            for(int i=0; i<clauses.size(); i++)
            {
                this.assignedLiteralsPerClause.add(0);
            }
//            if(this.DEBUG)
//            {
//                MinimalStaticChart.console.append("====== LOADED "+fileName+" ======");
//                MinimalStaticChart.console.append(this.toString());
//                MinimalStaticChart.console.append("===============================================\n");
//            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }

    @Override
    public String toString()
    {
        StringBuffer output = new StringBuffer();        
        for(Vector<Integer> clause : this.clauses )
        {
            if(clause == null)
                continue;
            
            output.append("[CLAUSE] ");
            for(Integer literal : clause)
            {
                output.append(literal);
                output.append(" ");
            }
            output.append("\n");
        }

        output.append("\n[LITERALS]");
        Enumeration<Integer> literalsEnum = this.literals.keys();
        while(literalsEnum.hasMoreElements())
        {
            Integer key = literalsEnum.nextElement();
            output.append(String.format("\n[%d] appears in: ", key));
            for(Integer appearence : this.literals.get(key))
                output.append(String.format("%d ",appearence));

        }
      return output.toString();
    }

    /* Utility functions */
    public Integer[] getTrueLiteralsInClause () {
        return this.solution.trueLiteralsInClause;
    }

    public void setVar (Integer var, Integer value) {
        // Whenever a variable changes value, we have to iterate over all
        // the clauses that contain it and check what happens with their value
        // This means that we have to check all the other literals for their
        // value and update the number of literals that make the clause true.
        // if this number is greater than 0 then it is satisfied else it is not
//        if(this.DEBUG)
//        {
//            System.out.println(">>> Setting variable: "+var+ " to " + value);
//        }
	//System.out.println("Number of variables: "+solution.variableAssignment.length);
	//System.out.println("Length of signedLiteralsPerClause: " + this.assignedLiteralsPerClause.size());
        
        if(this.solution.variableAssignment[var] == value)
            return;
        else
        {
            boolean firstTimeSet = false;
            if(this.solution.variableAssignment[var] == -1)
            {
                firstTimeSet = true;
            }
            
            
            this.solution.variableAssignment[var] = value;
            int literalAssigned = Math.abs(var) * (value == 0 ? -1 : 1);

            // Check the clauses that contain this literal
            if(this.literals.get(literalAssigned) != null)
            for(Integer clauseNumber : this.literals.get(literalAssigned))
            {
                if(firstTimeSet)
                {
//                    if(this.DEBUG)
//                        System.out.println(">>>> Setting for first time variable: " + var + ", in: " + clauseNumber);
                    
                    this.assignedLiteralsPerClause.set(clauseNumber, this.assignedLiteralsPerClause.get(clauseNumber) + 1);
                }
                if(this.solution.trueLiteralsInClause[clauseNumber] < this.solution.literalsPerClause)
                {
                    this.solution.trueLiteralsInClause[clauseNumber] += 1;
                    if(this.solution.trueLiteralsInClause[clauseNumber] == 1)
                    {
                        //MARK IT AS SATISFIED
                        //this.solution.satClausesList.add(clauseNumber);
                        //this.solution.unSatClausesList.removeElement(clauseNumber);
                        
                        this.solution.satClauses += 1;
                        this.solution.unSatClauses -= 1;
                    }
                   // ADD ONE MORE TRUE LITERAL IN CLAUSE
                   // IF TRUE_LITERALS_IN_CLAUSE > 0 && OLD_TRUE_LITERALS_IN_CLAUSE == 0
                   //    SAT_CLAUSES += 1
                   //    UNSAT_CLAUSES -= 1
                   // END
                }
            }

            // CHECK THE CLAUSE THAT CONTAINS THE OPPOSITE LITERAL
            if(this.literals.get(literalAssigned*(-1)) != null)
            for(Integer clauseNumber : this.literals.get(literalAssigned*(-1)))
            {
                if(firstTimeSet)
                {
//                    if(this.DEBUG)
//                        System.out.println(">>>> Setting for first time variable: " + var + ", in: " + clauseNumber);

                    this.assignedLiteralsPerClause.set(clauseNumber, this.assignedLiteralsPerClause.get(clauseNumber) + 1);
                }
                if(this.solution.trueLiteralsInClause[clauseNumber] > 0)
                {
                    this.solution.trueLiteralsInClause[clauseNumber] -= 1;
                    if(this.solution.trueLiteralsInClause[clauseNumber] == 0)
                    {
                        // MARK IT AS UNSATISFIED
                        //this.solution.satClausesList.removeElement(clauseNumber);
                        //this.solution.unSatClausesList.add(clauseNumber);


                        this.solution.satClauses -= 1;
                        this.solution.unSatClauses += 1;
                    }
                }

//                if(this.SHOW_PROGRESS)
//                {
//                    MinimalStaticChart.console.append("["+this.fileName+"] SAT: "+((float)this.solution.satClauses/(float)this.solution.numOfClauses)*100+"%");
//                }
//                if(this.PLOT)
//                {
//                    MinimalStaticChart.trace.addPoint(MinimalStaticChart.trace.getSize()+1,this.solution.satClauses);
//                    MinimalStaticChart.change_problem_trace.addPoint(MinimalStaticChart.change_problem_trace.getSize()+1, 0);
//                }
               // REMOVE ONE MORE TRUE LITERAL IN CLAUSE
               // IF TRUE_LITERALS_IN_CLAUSE == 0 && OLD_TRUE_LITERALS_IN_CLAUSE < 0
               //    SAT_CLAUSES -= 1
               //    UNSAT_CLAUSES += 1
               // END
            }
        }

    }

    /**
     * In case of literal assignment this calculates the number of clauses
     * which are not satisfied.
     * @param literal
     * @return
     */
    public Integer calcLiteralNegativeGain (Integer literal) {
        int count = 0;
        if(this.literals.get(literal * -1) != null)
        {
            for(Integer clauseNumber : this.literals.get(literal*(-1)))
            {
                if(this.solution.trueLiteralsInClause[clauseNumber] == 1)
                {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * In case of variable value flip, this function calculates the
     * number of clauses which are not satisfied
     * @param variable
     * @return
     */
    public Integer calcVariableFlipNegativeGain (Integer variable) {
        if(this.solution.variableAssignment[variable] == 0)
        {
            return this.calcLiteralNegativeGain(variable);
        }
        else if (this.solution.variableAssignment[variable] == 1)
        {
            return this.calcLiteralNegativeGain(variable * -1);
        }

        // NO VALUE
        return -1;
        
        
    }

    /**
     * In case of literal assignment this calculates the number of clauses
     * which are satisfied.
     * @param literal
     * @return
     */
    public Integer calcLiteralPositiveGain (Integer literal)
    {
        int count = 0;
        if(this.literals.get(literal) != null)
        {
            for(Integer clauseNumber : this.literals.get(literal))
            {
                if(this.solution.trueLiteralsInClause[clauseNumber] == 0)
                {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * In case of variable value flip, this function calculates the number of
     * clauses which are satisfied
     * @param variable
     * @return
     */
    public Integer calcVariableFlipPositiveGain (Integer variable) {
        if(this.solution.variableAssignment[variable] == 0)
        {
            return this.calcLiteralPositiveGain(variable);
        }
        else if (this.solution.variableAssignment[variable] == 1)
        {
            return this.calcLiteralPositiveGain(variable * -1);
        }
        return 0;
    }
    
    public void flipVar (Integer var) {

        if(this.solution.variableAssignment[var]>0)
        {
            this.setVar(var, 0);
        }
        else if(this.solution.variableAssignment[var]==0)
        {
            this.setVar(var, 1);
        }
        else
        {
            System.out.println("Cannot flip unassigned variable...");
        }
    }

    public void setLiteralTrue (int literal)
    {
        this.setVar(Math.abs(literal), literal < 0 ? 0 : 1);
    }

    public void unassignAllVars()
    {
        if(this.solution != null)
        {
            this.solution.clean();
        }
		  for( int i = 0; i < assignedLiteralsPerClause.size(); ++i )
			  assignedLiteralsPerClause.set(i, 0);
    }

    public Vector<Integer> getSatClauses () {
        //return this.solution.satClausesList;
        
        Vector <Integer> satClauses = new Vector<Integer>();
        for(int i=1; i<this.solution.trueLiteralsInClause.length; i++)
        {
            if(this.solution.trueLiteralsInClause[i] > 0)
            {
                satClauses.add(i);
            }

        }
        return satClauses;
        
    }


    public Vector<Integer> getUnSatClauses () {
        //return this.solution.unSatClausesList;
        
		 Vector <Integer> unSatClauses = new Vector<Integer>();
        for(int i=1; i<this.solution.trueLiteralsInClause.length; i++)
        {
            if(this.solution.trueLiteralsInClause[i] == 0)
            {
                unSatClauses.add(i);
            }

        }
        return unSatClauses;
         
    }


    public Integer[] getVariableAssignment () {
        return this.solution.variableAssignment;
    }

    public Solution getSolution()
    {
        return this.solution;
    }

    public void setAssignment(Integer[] assignment)
    {
        //this.solution.clean();
        //this.solution.variableAssignment = assignment.clone();
		 unassignAllVars();
        for( int i = 1; i < assignment.length; ++i )
			  setVar(i, assignment[i]);


//        for(int i=1; i<this.clauses.size(); i++)
//        {
//            Vector <Integer> clause = this.clauses.get(i);
//            boolean hasTrueLiteral = false;
//            for(Integer literal : clause)
//            {
//                // IF LITERAL HAS NEGATIVE VALUE AND THIS LITERAL IS SET TO FALSE THEN IT IS TRUE
//
//                if(literal < 0 && this.solution.variableAssignment[Math.abs(literal)] == 0)
//                {
//                    this.solution.trueLiteralsInClause[i] += 1;
//                    hasTrueLiteral = true;
//                }
//                // IF LITERAL HAS POSITIVE VALUE AND THIS LITERAL IS SET TO TRUE THEN IT IS TRUE
//                else if(literal > 0)
//                {
//                    if(this.solution.variableAssignment[Math.abs(literal)] == 1 )
//                    {
//                        this.solution.trueLiteralsInClause[i] += 1;
//                        hasTrueLiteral = true;
//                    }
//                }
//
//            }
//            if(hasTrueLiteral)
//            {
//                this.solution.satClauses++;
//					 //added by giorgos
//					 this.solution.unSatClauses--;
//                //TODO: ADD TO SAT CLAUSES!!!!
//                //this.solution.satClausesList.add(i);
//                //this.solution.unSatClausesList.removeElement(i);
//            }
////            else
////            {
////                this.solution.unSatClausesList.add(i);
////                //this.solution.satClausesList.removeElement(i);
////                //this.solution.unSatClauses++;
////            }
//
//            //UPDATE TRUE LITERALS IN CLAUSE
//            //UPDATE SAT CLAUSES
//            if(this.PLOT)
//            {
//                MinimalStaticChart.change_problem_trace.addPoint(MinimalStaticChart.change_problem_trace.getSize()+1, 200);
//                MinimalStaticChart.trace.addPoint(MinimalStaticChart.trace.getSize() + 1, this.getSatClauses().size());
//            }
//        }
       
    }

    public Integer getRandomUnSatClause () {
        return 0;
    }

   

}

