package msc.krr.dynsat;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.ListIterator;
import java.util.Random;


public class WalkSAT {

   
    private Integer noOfRestarts;

  
    private Integer noOfFlips;

   
    private Float p;

   
    private CNF aCNF;

    private Solution best;

    private Solution previousBest;


    private Integer tabuSize;

    private Vector<Integer> tabu;

    //add the variable which was  fliped to tabu
    private void addVarTabu(Integer Var){
        if(tabu.size()==getTabuSize()){
        tabu.remove(0);
        tabu.addElement(Var);
        }
        else tabu.addElement(Var);
    }

    //check if the variable exists in tabu,
    private Integer checkTabu(Integer Var){
        int j=0;
        int i=0;
        ListIterator iter=tabu.listIterator();
        while(iter.hasNext() && j==0){
            if (Var==iter.next()){
                i=1;
                j=1;
            }
        }
        return i;
    }

//return a sorted vector<litNG> with the negative gains
    private Vector<litNG> returnNGLiterals(Vector<Integer> literals){
        Vector<litNG> ngoflit = new Vector<litNG>();
        int temp;

        for(int l:literals){
            temp=aCNF.calcVariableFlipNegativeGain(Math.abs(l));
            litNG lng = new litNG(l,temp);
            ngoflit.add(lng);
        }

        Collections.sort(ngoflit, new Comparator<litNG>() {
            public int compare(litNG o1, litNG o2) {
                return o1.ng - o2.ng;
            }
        });
        
        return ngoflit;
    }

    //pick a random var from clause c
    private Integer getRandomL(Integer c){
        int l;
        Vector<Integer> literals;
        literals=aCNF.clauses.get(c);
        int d = rn.nextInt(literals.size());//l= random from c clause
        l=literals.get(d);
        return l;
    }

    public WalkSAT () {
   
    }

    public CNF getACNF () {
        return aCNF;
    }

    public void setACNF (CNF val) {
        this.aCNF = val;
    }

    public Integer getNoOfFlips () {
        return noOfFlips;
    }

    public void setNoOfFlips (Integer val) {
        this.noOfFlips = val;
    }

    public Integer getNoOfRestarts () {
        return noOfRestarts;
    }

  
    public void setNoOfRestarts (Integer val) {
        this.noOfRestarts = val;
    }

  
    public Float getP () {
        return p;
    }

   
    public void setP (Integer val) {
        this.setP(val);
    }

    public Integer gettabuSize(){
        return getTabuSize();
    }

    public void settabuSize(Integer val){
        this.setTabuSize(val);
    }
  
  
    public Solution getBest () {
        return best;
    }

    public void setBest (Solution val) {
        this.best = val;
    }

   
    public void setInitialAssignment (Integer[] assignment) {
        aCNF.setAssignment(assignment);
    }

    private final Random rn = new Random();


    public Boolean solve () {
        int i=0;
        int current_clause=0;
        int current_flips=0;
        int var_to_flip=0;
        int random_var_to_flip=0;
        int variable_is_in_tabu=0;
        int checktabu=0;
        int countloops=0;//counts the loops until it finds  a fitting literal
        int countloops2=0;//counts the loops until it finds an  unsat clause
        int l=0;//literal
        int l_ng=0;//the negative gain of literal l
        boolean solved= false;
        best = aCNF.getSolution();

        litNG lng = new litNG(0,0);
        Vector<Integer> literals = null;
        Vector<litNG> ngliterals = null;
        Vector<Integer> n = null;
		
        while(i<noOfRestarts && solved==false){
            countloops=0;
            countloops2=0;
            l=0;
            current_flips=0;
            if(i>0)setRandomInitialAssignment();
            while(current_flips<noOfFlips && aCNF.solution.unSatClauses != 0){
                //System.out.println("L: "+ l);
                if(countloops==0 && countloops2==0){
                    n=aCNF.getUnSatClauses();
                    Collections.shuffle(n);
                }


                if(l==0){
                    if(countloops2<n.size())
                        current_clause=n.elementAt(countloops2);
                    else
                        current_clause=-1;
                    
                    checktabu=current_clause;
                    if( checktabu!=-1) {
                        literals=aCNF.clauses.get(current_clause);
                        ngliterals=returnNGLiterals(literals);
                    }
                    countloops2++;
                }

                if(current_clause!=-1){
                lng=chooseliteral(countloops,ngliterals);
                l=lng.literals;
                l_ng=lng.ng;
                checktabu=current_clause;
                }
                if(l == 0)
                {
                    countloops = 0;
                }
                
                double d = rn.nextDouble();
                if(l_ng==0 && checktabu!=-1 && l!=0 ){
                    var_to_flip=l;// var with zero negative gain in c;
                    variable_is_in_tabu=checkTabu(var_to_flip);
                    countloops++;
                }
                else if(l_ng>0 && d < this.p && checktabu!=-1 && l!=0){
                    var_to_flip=l;// var with smallest negative gain in c;
                    variable_is_in_tabu=checkTabu(var_to_flip);
                    countloops++;
                }
                else if(l_ng>0 && d > this.p && checktabu!=-1 && l!=0){
                    var_to_flip=getRandomL(current_clause);// raandomly select a var in c;
                    variable_is_in_tabu=checkTabu(var_to_flip);
                    //countloops++;
                }
                else if(checktabu==-1 && l==0){
                    current_clause=n.elementAt(0);
                    random_var_to_flip=getRandomL(current_clause);//random in c;
                    aCNF.flipVar(Math.abs(random_var_to_flip));
                    addVarTabu(random_var_to_flip);
                    current_flips++;
                    countloops=0;
                    countloops2=0;
                    if( best.comparesolutions(aCNF.solution,getPreviousBest()) > 0 ) best = aCNF.solution;
                }
                
                if (variable_is_in_tabu==0 && checktabu!=-1 && l!=0){
                    //System.out.println("VAR TO FLIP: "+var_to_flip);
                    aCNF.flipVar(Math.abs(var_to_flip));
                    addVarTabu(var_to_flip);
                    current_flips++;
                    countloops=0;
                    countloops2=0;
                    l=0;
                    if( best.comparesolutions(aCNF.solution,getPreviousBest()) > 0 ) best = aCNF.solution;
                }
            }
            if(best.unSatClauses==0) solved=true;
            i++;
        }
        
        return solved;
    }

  



   
    private litNG chooseliteral(int countloops, Vector<litNG> ngliterals) {
        litNG ling = new litNG(0,0);
        
        if(countloops<ngliterals.size())
            ling=ngliterals.elementAt(countloops);

        return ling;
    }

    /**
     * @return the tabuSize
     */
    public Integer getTabuSize() {
        return tabuSize;
    }

    /**
     * @param tabuSize the tabuSize to set
     */
    public void setTabuSize(Integer tabuSize) {
        this.tabuSize = tabuSize;
        this.tabu = new Vector <Integer>(tabuSize);
    }

    /**
     * @param p the p to set
     */
    public void setP(Float p) {
        this.p = p;
    }

    /**
     * @return the previousBest
     */
    public Solution getPreviousBest() {
        return previousBest;
    }

    /**
     * @param previousBest the previousBest to set
     */
    public void setPreviousBest(Solution previousBest) {
        this.previousBest = previousBest;
    }

    private void setRandomInitialAssignment() {
        Integer [] A = new Integer[aCNF.solution.variableAssignment.length];
        int d=0;

        for(int i=1; i<aCNF.solution.variableAssignment.length;i++){
            d = rn.nextInt(2);
            A[i]=d;
        }
        aCNF.setAssignment(A);

    }


    private class litNG{
        int literals;
        int ng;//negative gain of literal

        private litNG(int l, int temp) {
            literals=l;
            ng=temp;
        }
    }
   

  


}

