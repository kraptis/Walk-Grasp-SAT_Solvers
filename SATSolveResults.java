/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msc.krr.dynsat;

/**
 * κρατάει τα αποτελέσματα της επίλυσης ενός SAT προβλήματος
 * @author gk
 */
public class SATSolveResults {

	private CNF cnf = null;
	private String fName = null;
	private Solution bestSolution = null;
	private Boolean solved = null;
	private Long miliSeconds = null;

	public SATSolveResults(){}

	public SATSolveResults( CNF cnf, String fName ) {
		setCnf(cnf);
		setfName(fName);
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
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the bestVarAssignment
	 */
	public Solution getBestSolution() {
		return bestSolution;
	}

	/**
	 * @param bestVarAssignment the bestVarAssignment to set
	 */
	public void setBestSolution(Solution bestSolution) {
		this.bestSolution = bestSolution;
	}

	/**
	 * @return the solved
	 */
	public Boolean isSolved() {
		return solved;
	}

	/**
	 * @param solved the solved to set
	 */
	public void setSolved(Boolean solved) {
		this.solved = solved;
	}

	/**
	 * @return the miliSeconds
	 */
	public Long getMiliSeconds() {
		return miliSeconds;
	}

	/**
	 * @param miliSeconds the miliSeconds to set
	 */
	public void setMiliSeconds(Long miliSeconds) {
		this.miliSeconds = miliSeconds;
	}
	
}
