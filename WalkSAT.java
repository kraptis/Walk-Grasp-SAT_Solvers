package msc.krr.dynsat;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.ListIterator;
import java.util.Random;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.CBD36D6E-08CF-4723-0616-CFCEF1110C1D]
// </editor-fold> 
public class WalkSAT {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C315636C-DAC5-EAAE-81A1-82AA33024608]
    // </editor-fold> 
    private Integer noOfRestarts;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9D173483-E814-7A51-4121-5D767BF3AE8D]
    // </editor-fold> 
    private Integer noOfFlips;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BF062A53-26ED-43D1-C472-880F97B89AC1]
    // </editor-fold> 
    private Float p;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.85DCDF73-3A98-9539-6387-158660E366B6]
    // </editor-fold> 
    private CNF aCNF;

    private Solution best;

    private Solution previousBest;


    private Integer tabuSize;

    private Vector<Integer> tabu;

    //prosthetei tin metavliti pou egine flip ston tabu
    private void addVarTabu(Integer Var){
        if(tabu.size()==getTabuSize()){
        tabu.remove(0);
        tabu.addElement(Var);
        }
        else tabu.addElement(Var);
    }

    //elegxei an iparxei i metavliti pou tha lisei ston tabu,
    //i=1 iparxei,i=0 den iparxei
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

//epistrefei taksinomineno vector<litNG> me tis metavlites kai to
// antistiho negative gain tis
//
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

    //dialegei mia random metavliti apo to clause c
    private Integer getRandomL(Integer c){
        int l;
        Vector<Integer> literals;
        literals=aCNF.clauses.get(c);
        int d = rn.nextInt(literals.size());//l= random apo c clause
        l=literals.get(d);
        return l;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E356CB0C-0343-2FA4-91D9-01521B0EB358]
    // </editor-fold> 
    public WalkSAT () {
   
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.900ADE01-C375-01A7-693B-27ABC591AD79]
    // </editor-fold> 
    public CNF getACNF () {
        return aCNF;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.4E19D015-376A-D25D-5731-2564AC16E3AA]
    // </editor-fold> 
    public void setACNF (CNF val) {
        this.aCNF = val;
    }


    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E06CA75B-064D-8724-5B1A-5A5F04B35188]
    // </editor-fold> 
    public Integer getNoOfFlips () {
        return noOfFlips;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.601360AB-FBE0-6244-FAAD-38AF8DA06BAC]
    // </editor-fold> 
    public void setNoOfFlips (Integer val) {
        this.noOfFlips = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E57E3B27-2C15-83C7-A438-B4C93B742424]
    // </editor-fold> 
    public Integer getNoOfRestarts () {
        return noOfRestarts;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3059F8F3-59E5-6DE2-7A01-BC69BDB28F39]
    // </editor-fold> 
    public void setNoOfRestarts (Integer val) {
        this.noOfRestarts = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.569C89CC-D8BA-140A-5923-9923ED0567E7]
    // </editor-fold> 
    public Float getP () {
        return p;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1E9E9345-FF00-743E-60B9-6B3E04550C05]
    // </editor-fold> 
    public void setP (Integer val) {
        this.setP(val);
    }

    public Integer gettabuSize(){
        return getTabuSize();
    }

    public void settabuSize(Integer val){
        this.setTabuSize(val);
    }
  

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.525E908D-7B1B-1D7F-D636-0AB615D0E679]
    // </editor-fold> 
    public Solution getBest () {
        return best;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.CA913E99-66FA-41C9-F33A-651889C8A738]
    // </editor-fold> 
    public void setBest (Solution val) {
        this.best = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1A16E291-09DC-C152-3DA0-6625107F80A3]
    // </editor-fold> 
    public void setInitialAssignment (Integer[] assignment) {
        //TODO: REVERT BACK
        //for(int i=1;i<assignment.length;i++)
        //    aCNF.setVar(i, assignment[i]);
        aCNF.setAssignment(assignment);
    }

    private final Random rn = new Random();

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EEE03736-88F2-1EF4-7DD1-36399763294B]
    // </editor-fold> 
    public Boolean solve () {
        int i=0;
        int current_clause=0;
        int current_flips=0;
        int var_to_flip=0;
        int random_var_to_flip=0;
        int variable_is_in_tabu=0;
        int checktabu=0;
        int countloops=0;//metraei tis epanalipseis mexri na vrei katalilo literal
        int countloops2=0;//metraei tis epanalipseis mexri na vrei unsat clause
        int l=0;//literal
        int l_ng=0;//negative gain tou literal l
        boolean solved= false;
        best = aCNF.getSolution();

        litNG lng = new litNG(0,0);
        Vector<Integer> literals = null;
        Vector<litNG> ngliterals = null;
        Vector<Integer> n = null;
		  //TODO
		  //να υλοποιηθεί με while να μην κάνει άσκοπα restarts
		  //επίσης μετά από κάθε restart θα πρέπει η ανάθεση τιμών στις μεταβλητές
		  //να γίνει ίση με την αρχική...
		  //να επιστρέφει true αν λύθηκε ή false αν δεν λύθηκε...
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
                
                double d = rn.nextDouble();//pernei times apo 0.0-1.0
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

  

//epistrefei ena random unsat clause,-1 an den dialeksei kanena
   /* private int getRandomUnSatClause(int countloops2,Vector<Integer> n) {
        //Vector<Integer> unsc;
       // unsc=aCNF.getUnSatClauses();

        //System.out.println("UNSAT CLAUSES: "+unsc.size() + " LOOP COUNT: "+countloops2);
        //if(unsc.size()==0)
        //{
         //   System.out.println(aCNF.getSolution());
        //}

        //Collections.shuffle(n);
        if(countloops2<n.size())
            return n.elementAt(countloops2);
        
        return -1;

    }*/

    //dialegei to literal pou pithanon na ginei flip,epistrefei kai to
    //negative gain tou
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
        int ng;//negative gain tou literal

        private litNG(int l, int temp) {
            literals=l;
            ng=temp;
        }
    }
   

  


}

