/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DynSATStatistics.java
 *
 * Created on 29 Ιαν 2010, 7:38:32 μμ
 */
package msc.krr.dynsat;

import java.awt.Color;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gk
 */
public class DynSATStatistics extends javax.swing.JFrame {

	private static class ClauseRenderer extends DefaultTableCellRenderer {
		private ClauseRenderer() {
			super();
		}

		@Override
		public void setValue( Object value ) {
			if( value == null || value.equals("")) {
				setText("");
				return;
			}
			ClauseDataForTable c = (ClauseDataForTable) value;
			if( c.sat ) {
				setForeground(Color.BLUE);
			} else {
				setForeground(Color.red);
			}
			setText(c.clause);
			setToolTipText(c.tooltip);
		}
	}

	private static class BoolRenderer extends DefaultTableCellRenderer {

		private BoolRenderer() {
			super();
		}

		@Override
		public void setValue(Object value) {
			if (value.toString().equals("true")) {
				setForeground(Color.BLUE);
				setText("true");
			} else {
				setForeground(Color.red);
				setText("false");
			}
		}
	}

	private static class ClauseDataForTable {
		private String clause;
		private boolean sat;
		private String tooltip;

		private ClauseDataForTable( String c, boolean b, String tt ) {
			clause = c;
			sat = b;
			tooltip = tt;
		}
	}

	Vector<SATSolveResults> dynSat = null;
	DefaultTableModel globalViewModel, varViewModel, clauseViewModel;

	/** Creates new form DynSATStatistics */
	public DynSATStatistics(Vector<SATSolveResults> d) {
		dynSat = d;

		initComponents();
		initTableModels();

	}

	private String formatRunningTime(long milis) {

		String s = milis / 3600000 + "h:";
		milis = milis % 3600000;
		s += milis / 60000 + "m:";
		milis = milis % 60000;
		s += milis / 1000 + "s:";
		milis = milis % 1000;
		s += milis + "ms";
		return s;
	}

	private void initTableModels() {
		SwingWorker<Void, Void> work = new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {

				//model for global view
				String[] cn = {
					"A/A", "File Name", "Solved", "Time"
				};
				Vector<Vector<Object>> data = new Vector<Vector<Object>>();

				for (int i = 0; i < dynSat.size(); ++i) {
					Vector<Object> row = new Vector<Object>();
					SATSolveResults sr = dynSat.get(i);
					row.add(i + 1);
					row.add(sr.getfName());
					row.add(new Boolean(sr.isSolved()));
					if (sr.getMiliSeconds() != null) {
						row.add(sr.getMiliSeconds());//row.add(formatRunningTime(sr.getMiliSeconds()));
					}
					data.add(row);
				}
				globalViewModel = new DefaultTableModel(data, new Vector<String>(Arrays.asList(cn)));

				//model for variable view
				cn = new String[dynSat.size() + 1];
				cn[0] = "var";
				for (int i = 1; i < cn.length; ++i) {
					cn[i] = "" + i;
				}

				data = new Vector<Vector<Object>>();
				for (int i = 0; i < dynSat.size(); ++i) {
					Integer[] ass = dynSat.get(i).getCnf().solution.variableAssignment;

					for (int j = 1; j < ass.length; ++j) {
						if (data.size() < j) {
							Vector<Object> row = new Vector<Object>();
							row.add(j);
							data.add(row);
						}
						data.get(j - 1).add(ass[j] > 0 ? true : false);
					}
				}
				varViewModel = new DefaultTableModel(data, new Vector<String>(Arrays.asList(cn)));

				//model for clause view
				cn[0] = "clause";
				data = new Vector<Vector<Object>>();
				for( int i = 0; i < dynSat.size(); ++i ) {
					int cnt = 1;
					for( Vector<Integer> lits : dynSat.get(i).getCnf().clauses ) {
						if( lits == null) continue;
						if( data.size() < cnt  ) {
							Vector<Object> row = new Vector<Object>();
							row.add(cnt);
							data.add(row);
						}
						while( data.get(cnt-1).size() <= i )
							data.get(cnt-1).add(null);
						boolean sat = false;
						String tooltip = "[ ";
						for( Integer lit1 : lits ) {
							Integer ass = dynSat.get(i).getBestSolution().variableAssignment[Math.abs(lit1)];
							if( lit1 < 0 && ass == 0 ) {
								sat = true;
							}
							if( lit1 > 0 && ass > 0 ) {
								sat = true;
							}
							tooltip += ass > 0 ? "T " : "F ";
						}
						data.get(cnt-1).add( new ClauseDataForTable(lits.toString(), sat, tooltip + "]"));
						cnt++;
					}
				}

				clauseViewModel = new DefaultTableModel(data, new Vector<String>(Arrays.asList(cn)));

				return null;
			}

			@Override
			public void done() {
				jTGlobalView.setModel(globalViewModel);
				jTVarView.setModel(varViewModel);
				jTClauseView.setModel(clauseViewModel);

				jTGlobalView.getColumn("Solved").setCellRenderer(new BoolRenderer());

				BoolRenderer br = new BoolRenderer();
				ClauseRenderer cr = new ClauseRenderer();
				for (int i = 1; i < jTVarView.getColumnCount(); ++i) {
					jTVarView.getColumn(jTVarView.getColumnName(i)).setCellRenderer(br);
					jTClauseView.getColumn(jTClauseView.getColumnName(i)).setCellRenderer(cr);
				}
			}
		};

		work.execute();

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jTClauseViewTab = new javax.swing.JTabbedPane();
      jPGlobalView = new javax.swing.JPanel();
      jScrollPane2 = new javax.swing.JScrollPane();
      jTGlobalView = new javax.swing.JTable();
      jPVarView = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jTVarView = new javax.swing.JTable();
      jPanel1 = new javax.swing.JPanel();
      jScrollPane3 = new javax.swing.JScrollPane();
      jTClauseView = new javax.swing.JTable();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      jTGlobalView.setSelectionBackground(new java.awt.Color(204, 204, 255));
      jTGlobalView.setSelectionForeground(new java.awt.Color(0, 0, 0));
      jScrollPane2.setViewportView(jTGlobalView);

      javax.swing.GroupLayout jPGlobalViewLayout = new javax.swing.GroupLayout(jPGlobalView);
      jPGlobalView.setLayout(jPGlobalViewLayout);
      jPGlobalViewLayout.setHorizontalGroup(
         jPGlobalViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPGlobalViewLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2)
            .addContainerGap())
      );
      jPGlobalViewLayout.setVerticalGroup(
         jPGlobalViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPGlobalViewLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
            .addContainerGap())
      );

      jTClauseViewTab.addTab("Global View", jPGlobalView);

      jTVarView.setSelectionBackground(new java.awt.Color(204, 204, 255));
      jTVarView.setSelectionForeground(new java.awt.Color(0, 0, 0));
      jScrollPane1.setViewportView(jTVarView);

      javax.swing.GroupLayout jPVarViewLayout = new javax.swing.GroupLayout(jPVarView);
      jPVarView.setLayout(jPVarViewLayout);
      jPVarViewLayout.setHorizontalGroup(
         jPVarViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPVarViewLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1)
            .addContainerGap())
      );
      jPVarViewLayout.setVerticalGroup(
         jPVarViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPVarViewLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
            .addContainerGap())
      );

      jTClauseViewTab.addTab("Variable View", jPVarView);

      jTClauseView.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
      jTClauseView.setSelectionBackground(new java.awt.Color(204, 204, 255));
      jTClauseView.setSelectionForeground(new java.awt.Color(0, 0, 0));
      jTClauseView.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      jScrollPane3.setViewportView(jTClauseView);

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
            .addContainerGap())
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
            .addContainerGap())
      );

      jTClauseViewTab.addTab("Clause View", jPanel1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTClauseViewTab)
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTClauseViewTab)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new DynSATStatistics(null).setVisible(true);
			}
		});
	}
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JPanel jPGlobalView;
   private javax.swing.JPanel jPVarView;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JTable jTClauseView;
   private javax.swing.JTabbedPane jTClauseViewTab;
   private javax.swing.JTable jTGlobalView;
   private javax.swing.JTable jTVarView;
   // End of variables declaration//GEN-END:variables
}
