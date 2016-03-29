/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package msc.krr.dynsat;

import java.util.Vector;

/**
 *
 * @author gk
 */
public class testDynSAT {

	public static void main(String[] args) {
		WalkDynSAT d = new WalkDynSAT();

//		String s[] = {"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\base",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\0",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\1",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\2",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\3",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\4",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\5",
//			"C:\\Documents and Settings\\gk\\My Documents\\cnf\\240120100rnd_160_47_10_10\\6"};

		String s[] = {
			"/Users/kostas/Desktop/210120104rnd_1000_233_10_10/base",
			"/Users/kostas/Desktop/210120104rnd_1000_233_10_10/0"
		};
		d.setParameters(100, 100000, 10, 0.8f);
		d.loadFiles(s);
		try {
			d.solve();
			for (int j = 1; j < d.getDynSAT().size(); ++j) {
				Integer[] va = d.getDynSAT().get(j).getBestSolution().variableAssignment;
				System.out.println(".........PROBLEM " + j + ( d.getDynSAT().get(j).isSolved()?" SOLVED":" NOT SOLVED") + "............." );
				for (int i = 1; i < va.length; ++i) {
					System.out.print(" " + ((va[i]>0) ? (i) : (-i) ) );
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
