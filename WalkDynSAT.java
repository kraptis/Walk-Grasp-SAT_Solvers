/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package msc.krr.dynsat;

/**
 *
 * @author gk
 */
public class WalkDynSAT extends DynSATSolver {

	protected WalkSAT walkSAT = new WalkSAT();

	public void DynSAT() {
	}

	public void setParameters(int restarts, int flips, int tabusize, float p) {
		;
		walkSAT.setNoOfRestarts(restarts);
		walkSAT.setNoOfFlips(flips);
		walkSAT.setTabuSize(tabusize);
		walkSAT.setP(p);
	}

	@Override
	public void solve() throws Exception {
		doInBackground();
	}

	@Override
	protected Void doInBackground() throws Exception {
		for (int i = 0; i < dynSAT.size(); ++i) {
			
			if (i == 0) {
				getFirstSolution();
			} else {
				walkSAT.setACNF(dynSAT.get(i).getCnf());
				walkSAT.setInitialAssignment(dynSAT.get(i - 1).getBestSolution().variableAssignment);
				walkSAT.setPreviousBest(dynSAT.get(i - 1).getBestSolution());
				long before = System.currentTimeMillis();
				boolean solved = walkSAT.solve();
				long after = System.currentTimeMillis();
				dynSAT.get(i).setBestSolution(walkSAT.getBest());
				dynSAT.get(i).setSolved(solved);
				dynSAT.get(i).setMiliSeconds(after - before);
			}
			publish(i);
		}
		return null;
	}
}
