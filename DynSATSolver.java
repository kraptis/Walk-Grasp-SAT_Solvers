//<editor-fold defaultstate="collapsed" desc=" changes 18/12/2009 gk ">
/**
 * πρσοσθήκη της συνάρτησης setParameters που θέτει τιμές στον walkSAT
 */
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc=" changes 5/12/2009 gk ">
/**
 * προσθήκη εύρεσης της λύσης του πρώτου προβλήματος
 * το πρόβλημα αυτό είναι που πρέπει να λύσει ο zChaff
 * η λύση διαβάζεται από ένα αρχείο με όνομα
 * όνομα_αρχείου_cnf + ".solution"
 */
//</editor-fold>
// <editor-fold defaultstate="collapsed" desc=" changes 4/12/2009 gk ">
/**
 * 4/12/2009
 * Αλλαγή της loadFiles ώστε να δέχεται ως είσοδο ένα array από αρχεία και
 * όχι ένα configuration file. Θα είναι πιο εύκολο να ορίζουμε με αυτόν τον
 * τρόπο το DynSAT
 */
// </editor-fold>
package msc.krr.dynsat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D9507C30-F9D1-0BC4-403F-4BCB28F97B70]
// </editor-fold>
/**
 * Αποτελεί αναπαράσταση του DynSAT
 * Διαβάζει από ένα configuration file τη λίστα με τα SAT προβλήματα που πρέπει
 * να λυθούν και τα επιλύει καλώντας την WalkSAT.
 *
 * @author gk
 */
public abstract class DynSATSolver extends SwingWorker<Void, Integer>{

	protected Vector<SATSolveResults> dynSAT;
	private JProgressBar progressSoFar = null;

	/**
	 * o local based sat solver που λύνει το κάθε instance του dynSAT
	 */
	// <editor-fold defaultstate="collapsed" desc=" UML Marker ">
	// #[regen=yes,id=DCE.83553932-8E6F-1810-C4EB-C7D9177DF1C6]
	// </editor-fold>
	public DynSATSolver() {
	}

	// <editor-fold defaultstate="collapsed" desc=" UML Marker ">
	// #[regen=yes,regenBody=yes,id=DCE.45BD9019-BBDA-352E-9385-0179C043220B]
	// </editor-fold>
	public Vector<SATSolveResults> getDynSAT() {
		return dynSAT;
	}

	/**
	 * δημιουργεί ένα dynSat από ένα σύνολο αρχείων cnf
	 * το πρώτο cnf αρχείο είναι το αρχικό πρόβλημα που θα λύσει ο zChaff
	 * @param files πίνακας με αρχεία που αποτελούν το DynSAT
	 */
	public void loadFiles(String[] files) {
		dynSAT = new Vector<SATSolveResults>(files.length);
		for (int i = 0; i < files.length; ++i) {
			String fName = files[i];
			SATSolveResults sat;
			CNF cnf = new CNF();
			cnf.loadFromFile(fName);
                        sat = new SATSolveResults(cnf, fName);
			dynSAT.add(sat);
		}
	}

	protected void getFirstSolution() throws Exception {
		SATSolveResults firstProblem = dynSAT.get(0);

		String fName = firstProblem.getfName() + ".solution";
		FileReader fr = new FileReader(new File(fName));
		BufferedReader br = new BufferedReader(fr);
		String firstline = br.readLine();
		if (firstline.equals("SATISFIABLE")) {
			String varAssignmentsStr = br.readLine().trim();
			String vars[] = varAssignmentsStr.split("\\s");
			Integer[] assignments = new Integer[vars.length+1];
			for (int i = 1; i < assignments.length; ++i) {
				int var = Integer.parseInt(vars[i-1]);
				assignments[Math.abs(var)] = var / (Math.abs(var)) == 1 ? CNF.TRUE : CNF.FALSE;
			}
			firstProblem.setSolved(true);
			CNF firstCnf = firstProblem.getCnf();
                        firstCnf.setAssignment(assignments);
			firstProblem.setCnf(firstCnf);
			firstProblem.setBestSolution(firstProblem.getCnf().getSolution());
		} else {
			firstProblem.setSolved(false);
			firstProblem.setBestSolution(null);
		}
	}

	abstract public void solve() throws Exception;

	/**
	 * @return the progressSoFar
	 */
	public JProgressBar getProgressSoFar() {
		return progressSoFar;
	}

	/**
	 * @param progressSoFar the progressSoFar to set
	 */
	public void setProgressSoFar(JProgressBar progressSoFar) {
		this.progressSoFar = progressSoFar;
	}

	@Override
	protected void process(List<Integer> pr) {
		if(progressSoFar==null) return;
		progressSoFar.setValue(pr.get(pr.size()-1));
	}

	@Override
	protected void done() {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DynSATStatistics(getDynSAT()).setVisible(true);
            }
        });
	}

}

