/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msc.krr.dynsat;

/**
 *
 * @author kostas
 */
public class Main {
    public static void main(String[] args){
		WalkDynSAT d = new WalkDynSAT();

		//String s[] = {"/Users/kostas/Desktop/cnf/cnf1","/Users/kostas/Desktop/cnf/cnf2", "/Users/kostas/Desktop/cnf/cnf3"};
                d.setParameters(1000, 1000, 3, 0.2f);
                
               //GraspDynSAT d = new GraspDynSAT();
                String s[] = {"/Users/kostas/Desktop/diaolos/base","/Users/kostas/Desktop/diaolos/1","/Users/kostas/Desktop/diaolos/2",
                "/Users/kostas/Desktop/diaolos/3","/Users/kostas/Desktop/diaolos/4"};
                //d.setParameters(1000, 0.5f, 1000, 10, 0.8f);
                
		d.loadFiles(s);
		try {
			d.solve();
                        for(int j=0;j<d.getDynSAT().size(); ++j){
                            System.out.println(d.getDynSAT().get(j).isSolved() ? "==== SOLVED ====" : "==== NOT SOLVED ====");
                            System.out.println(d.getDynSAT().get(j).getBestSolution());
                            System.out.println("=====================================");
                        }
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
