/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package msc.krr.dynsat;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;


public class GRASP {

	private CNF cnf;
	private Solution previousBest;
	private Solution best;
	private int restarts;
	private float minMaxA;
	WalkSAT walkSat = new WalkSAT();

	public GRASP() {
	}

	public void setParameters(int r, float a, int flips, int tabusize, float p) {
		restarts = r;
		minMaxA = a;
		walkSat.setNoOfFlips(flips);
		walkSat.setTabuSize(tabusize);
		walkSat.setP(p);
		walkSat.setNoOfRestarts(1);
	}

	public boolean solve() {
		walkSat.setACNF(cnf);
		walkSat.setPreviousBest(previousBest);
		int rest = 0;
		best = null;
		while (rest <= restarts && cnf.solution.unSatClauses > 0) {
			rest++;
			if (rest == 1) {
				walkSat.setInitialAssignment(previousBest.variableAssignment);
			} else {
				constructInitialSolution();
			}
			//System.out.print(cnf.solution);
			//walkSat.setInitialAssignment(cnf.getSolution().variableAssignment);
			walkSat.solve();
			if (best == null) {
				best = walkSat.getBest();
			} else if (best.comparesolutions(walkSat.getBest(), previousBest) > 0) {
				best = walkSat.getBest();
			}
		}
		if (cnf.solution.unSatClauses == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void constructInitialSolution() {
		Random rnd = new Random();

		cnf.unassignAllVars();
		int assignedVars = 0;
		while (assignedVars < cnf.getSolution().variableAssignment.length - 1) {
			int literalToAssign = 0;
			//check if there are clauses with only one unassigned variable
			for (int clause : cnf.getUnSatClauses()) {
				//System.out.println(""+cnf.assignedLiteralsPerClause.get(clause));
				if (cnf.assignedLiteralsPerClause.get(clause) == cnf.clauses.get(clause).size() - 1) {
					for (int literal : cnf.clauses.get(clause)) {
						if (cnf.solution.variableAssignment[Math.abs(literal)] == CNF.ANASSIGNED) {
							literalToAssign = literal;
							break;
						}
					}
					if( literalToAssign != 0 ) break;
				}
			}
			//choose randomly from RCL
			if (literalToAssign == 0) {
				Hashtable<Integer, Integer> fValues = new Hashtable<Integer, Integer>();
				Vector<Integer> RCL = new Vector<Integer>();
				int fmax = 0;
				//System.out.println("...........calculating f's and fmax................");
				for (int i = 1; i < cnf.getSolution().variableAssignment.length; i++) {

					if (cnf.solution.variableAssignment[i] == CNF.ANASSIGNED) {
						int f = cnf.calcLiteralPositiveGain(i);
						fValues.put(i, f);
						//System.out.println("i:" + i + "  F: "+f);
						if (f > fmax) {
							fmax = f;
						}
						f = cnf.calcLiteralPositiveGain(-i);
						fValues.put(-i, f);
						//System.out.println("i:" + -i + "  F: "+f);
						if (f > fmax) {
							fmax = f;
						}
					}
				}
				//System.out.println("FMAX: "+fmax);
				//System.out.println("GET VALUE: "+getMinMaxA() * fmax);
				Enumeration<Integer> en = fValues.keys();
				while (en.hasMoreElements()) {
					int lit = en.nextElement();
					//System.out.print(" "+ lit);
					if (fValues.get(lit) >= (getMinMaxA() * fmax)) {
						RCL.add(lit);
					}
				}
				//System.out.println("RCL Size: "+RCL);
				literalToAssign = RCL.get(rnd.nextInt(RCL.size()));
			}
			//System.out.println(".................literal: "+literalToAssign + "...............");
			cnf.setLiteralTrue(literalToAssign);
			assignedVars++;

		}
		// System.out.println(cnf.solution);
	}

	/**
	 * @return the best
	 */
	public Solution getBest() {
		return best;
	}

	/**
	 * @return the cnf
	 */
	public CNF getCnf() {
		return cnf;
	}

	/**
	 * @param cnf the cnf to set
	 */
	public void setCnf(CNF cnf) {
		this.cnf = cnf;
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

	/**
	 * @return the minMaxA
	 */
	public float getMinMaxA() {
		return minMaxA;
	}

	/**
	 * @param minMaxA the minMaxA to set
	 */
	public void setMinMaxA(float minMaxA) {
		this.minMaxA = minMaxA;
	}
}
