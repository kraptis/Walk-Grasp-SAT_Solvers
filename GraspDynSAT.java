/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package msc.krr.dynsat;

import javax.swing.SwingConstants;

/**
 *
 * @author gk
 */
public class GraspDynSAT extends DynSATSolver {

	GRASP grasp = new GRASP();

	public void setParameters(int r, float a, int flips, int tabusize, float p) {
		grasp.setParameters(r, a, flips, tabusize, p);
	}

	@Override
	public void solve() throws Exception {
		//System.out.println("SOLVING GraspDynSat...");
		doInBackground();
	}

	@Override
	protected Void doInBackground() throws Exception {
		try {
		for (int i = 0; i < dynSAT.size(); ++i) {
			
			if (i == 0) {
				getFirstSolution();
			} else {
				grasp.setCnf(dynSAT.get(i).getCnf());
				grasp.setPreviousBest(dynSAT.get(i - 1).getBestSolution());
				long before = System.currentTimeMillis();
				boolean solved = grasp.solve();
				//System.out.println(solved ? "SOLVED ;)" : "NOT SOLVED... :(");

				long after = System.currentTimeMillis();
				dynSAT.get(i).setBestSolution(grasp.getBest());
				dynSAT.get(i).setSolved(solved);
				dynSAT.get(i).setMiliSeconds(after - before);
			}
			publish(i);
		}
		return null;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
