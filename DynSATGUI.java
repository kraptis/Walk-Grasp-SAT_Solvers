/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DynSATGUI.java
 *
 * Created on 29 Ιαν 2010, 5:10:08 μμ
 */
package msc.krr.dynsat;

import java.awt.CardLayout;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author gk
 */
public class DynSATGUI extends javax.swing.JFrame {

	private DefaultListModel SATProblems = new DefaultListModel();

	/** Creates new form DynSATGUI */
	public DynSATGUI() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      jCAlgorithm = new javax.swing.JComboBox();
      jPCard = new javax.swing.JPanel();
      jPWalkParameters = new javax.swing.JPanel();
      jLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jTWRestarts = new javax.swing.JTextField();
      jLabel4 = new javax.swing.JLabel();
      jTWFlips = new javax.swing.JTextField();
      jLabel5 = new javax.swing.JLabel();
      jTWTabuSize = new javax.swing.JTextField();
      jLabel6 = new javax.swing.JLabel();
      jTWProbability = new javax.swing.JTextField();
      jPGraspParameters = new javax.swing.JPanel();
      jLabel7 = new javax.swing.JLabel();
      jLabel8 = new javax.swing.JLabel();
      jTGRestarts = new javax.swing.JTextField();
      jLabel9 = new javax.swing.JLabel();
      jTGFlips = new javax.swing.JTextField();
      jLabel10 = new javax.swing.JLabel();
      jTGTabuSize = new javax.swing.JTextField();
      jLabel11 = new javax.swing.JLabel();
      jTGProbability = new javax.swing.JTextField();
      jLabel12 = new javax.swing.JLabel();
      jTGMinMaxA = new javax.swing.JTextField();
      jLabel13 = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jLSATProblems = new javax.swing.JList(SATProblems);
      jButton1 = new javax.swing.JButton();
      jButton2 = new javax.swing.JButton();
      jButton3 = new javax.swing.JButton();
      jButton4 = new javax.swing.JButton();
      jButton5 = new javax.swing.JButton();
      jProgressBar = new javax.swing.JProgressBar();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("DynSAT Solver");

      jLabel1.setText("solving method");

      jCAlgorithm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "WalkSAT with tabu", "GRASP using WalkSAT" }));
      jCAlgorithm.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCAlgorithmActionPerformed(evt);
         }
      });

      jPCard.setLayout(new java.awt.CardLayout());

      jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      jLabel2.setText("WalkSAT Parameters");

      jLabel3.setText("restarts");

      jTWRestarts.setText("100");

      jLabel4.setText("flips");

      jTWFlips.setText("1000");

      jLabel5.setText("tabu size");

      jTWTabuSize.setText("5");

      jLabel6.setText("probability p");

      jTWProbability.setText("0.5");

      javax.swing.GroupLayout jPWalkParametersLayout = new javax.swing.GroupLayout(jPWalkParameters);
      jPWalkParameters.setLayout(jPWalkParametersLayout);
      jPWalkParametersLayout.setHorizontalGroup(
         jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPWalkParametersLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
               .addGroup(jPWalkParametersLayout.createSequentialGroup()
                  .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel3)
                     .addComponent(jLabel4))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(jTWFlips)
                     .addComponent(jTWRestarts, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                  .addGap(105, 105, 105)
                  .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel5)
                     .addComponent(jLabel6))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(jTWProbability)
                     .addComponent(jTWTabuSize, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))))
            .addContainerGap())
      );
      jPWalkParametersLayout.setVerticalGroup(
         jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPWalkParametersLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel3)
               .addComponent(jTWRestarts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel5)
               .addComponent(jTWTabuSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPWalkParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel4)
               .addComponent(jTWFlips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel6)
               .addComponent(jTWProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(43, Short.MAX_VALUE))
      );

      jPCard.add(jPWalkParameters, "card2");

      jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
      jLabel7.setText("GRASP Parameters");

      jLabel8.setText("restarts");

      jTGRestarts.setText("100");

      jLabel9.setText("flips");

      jTGFlips.setText("1000");

      jLabel10.setText("tabu size");

      jTGTabuSize.setText("10");

      jLabel11.setText("probability p");

      jTGProbability.setText("0.5");

      jLabel12.setText("MinMaxA");

      jTGMinMaxA.setText("0.5");

      javax.swing.GroupLayout jPGraspParametersLayout = new javax.swing.GroupLayout(jPGraspParameters);
      jPGraspParameters.setLayout(jPGraspParametersLayout);
      jPGraspParametersLayout.setHorizontalGroup(
         jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPGraspParametersLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
               .addGroup(jPGraspParametersLayout.createSequentialGroup()
                  .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                     .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPGraspParametersLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTGTabuSize))
                     .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPGraspParametersLayout.createSequentialGroup()
                        .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                           .addComponent(jLabel8)
                           .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                           .addComponent(jTGFlips)
                           .addComponent(jTGRestarts, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))))
                  .addGap(87, 87, 87)
                  .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel11)
                     .addComponent(jLabel12))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(jTGMinMaxA)
                     .addComponent(jTGProbability, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))))
            .addContainerGap())
      );
      jPGraspParametersLayout.setVerticalGroup(
         jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPGraspParametersLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel8)
               .addComponent(jTGRestarts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(jLabel11)
               .addComponent(jTGProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(jPGraspParametersLayout.createSequentialGroup()
                  .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel9)
                     .addComponent(jTGFlips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                  .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel10)
                     .addComponent(jTGTabuSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGroup(jPGraspParametersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(jLabel12)
                  .addComponent(jTGMinMaxA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
      );

      jPCard.add(jPGraspParameters, "card3");

      jLabel13.setText("SAT problems");

      jLSATProblems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
      jScrollPane1.setViewportView(jLSATProblems);

      jButton1.setText("-");
      jButton1.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
         }
      });

      jButton2.setText("+");
      jButton2.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
         }
      });

      jButton3.setText("SOLVE");
      jButton3.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
         }
      });

      jButton4.setText("^");
      jButton4.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
         }
      });

      jButton5.setText("ˇ");
      jButton5.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
         }
      });

      jProgressBar.setStringPainted(true);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jLabel1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jCAlgorithm, 0, 298, Short.MAX_VALUE))
               .addComponent(jPCard, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addContainerGap()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                     .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                     .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
               .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
               .addGroup(layout.createSequentialGroup()
                  .addContainerGap()
                  .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(jCAlgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel13)
               .addComponent(jButton1)
               .addComponent(jButton2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(jButton4)
                  .addGap(18, 18, 18)
                  .addComponent(jButton5))
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(jButton3)
            .addGap(18, 18, 18)
            .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

	 private void jCAlgorithmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCAlgorithmActionPerformed
		 CardLayout cl = (CardLayout) jPCard.getLayout();
		 if (jCAlgorithm.getSelectedIndex() == 0) {
			 cl.show(jPCard, "card2");
		 } else if (jCAlgorithm.getSelectedIndex() == 1) {
			 cl.show(jPCard, "card3");
		 }
	 }//GEN-LAST:event_jCAlgorithmActionPerformed

	 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		 JFileChooser fc = new JFileChooser();
		 fc.setMultiSelectionEnabled(true);
		 fc.showOpenDialog(this);
		 File[] f = fc.getSelectedFiles();
		 for (File f1 : f) {
			 SATProblems.addElement(f1.getAbsolutePath());
		 }
	 }//GEN-LAST:event_jButton2ActionPerformed

	 private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		 int indx = jLSATProblems.getSelectedIndex();
		 SATProblems.remove(indx);
		 if( indx < SATProblems.size() )
			 jLSATProblems.setSelectedIndex(indx);
	 }//GEN-LAST:event_jButton1ActionPerformed

	 private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		 int pos = jLSATProblems.getSelectedIndex();
		 if (pos == 0) {
			 return;
		 }
		 String f = (String) SATProblems.getElementAt(pos);
		 SATProblems.remove(pos);
		 SATProblems.add(pos - 1, f);
		 jLSATProblems.setSelectedIndex(pos - 1);

	 }//GEN-LAST:event_jButton4ActionPerformed

	 private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		 int pos = jLSATProblems.getSelectedIndex();
		 if (pos == SATProblems.size() - 1) {
			 return;
		 }
		 String f = (String) SATProblems.getElementAt(pos);
		 SATProblems.remove(pos);
		 SATProblems.add(pos + 1, f);
		 jLSATProblems.setSelectedIndex(pos + 1);
	 }//GEN-LAST:event_jButton5ActionPerformed

	 private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		 System.gc();
		 jProgressBar.setMinimum(0);
		 jProgressBar.setMaximum(SATProblems.size() - 1);

		 String[] files = new String[SATProblems.size()];
		 for (int i = 0; i < SATProblems.size(); ++i) {
			 files[i] = (String) SATProblems.getElementAt(i);
		 }

		 DynSATSolver s;
		 if (jCAlgorithm.getSelectedIndex() == 0) {
			 s = new WalkDynSAT();
			 ((WalkDynSAT)s).setParameters(Integer.valueOf(jTWRestarts.getText()),
						Integer.valueOf(jTWFlips.getText()),
						Integer.valueOf(jTWTabuSize.getText()),
						Float.valueOf(jTWProbability.getText()));
		 } else {
			 s = new GraspDynSAT();
			 ((GraspDynSAT)s).setParameters(Integer.valueOf(jTGRestarts.getText()),
						Float.valueOf(jTGMinMaxA.getText()),
						Integer.valueOf(jTGFlips.getText()),
						Integer.valueOf(jTGTabuSize.getText()),
						Float.valueOf(jTGProbability.getText()));

		 }
		 s.setProgressSoFar(jProgressBar);
		 s.loadFiles(files);
		 s.execute();

	 }//GEN-LAST:event_jButton3ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new DynSATGUI().setVisible(true);
			}
		});
	}
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton2;
   private javax.swing.JButton jButton3;
   private javax.swing.JButton jButton4;
   private javax.swing.JButton jButton5;
   private javax.swing.JComboBox jCAlgorithm;
   private javax.swing.JList jLSATProblems;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel10;
   private javax.swing.JLabel jLabel11;
   private javax.swing.JLabel jLabel12;
   private javax.swing.JLabel jLabel13;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JLabel jLabel7;
   private javax.swing.JLabel jLabel8;
   private javax.swing.JLabel jLabel9;
   private javax.swing.JPanel jPCard;
   private javax.swing.JPanel jPGraspParameters;
   private javax.swing.JPanel jPWalkParameters;
   private javax.swing.JProgressBar jProgressBar;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTextField jTGFlips;
   private javax.swing.JTextField jTGMinMaxA;
   private javax.swing.JTextField jTGProbability;
   private javax.swing.JTextField jTGRestarts;
   private javax.swing.JTextField jTGTabuSize;
   private javax.swing.JTextField jTWFlips;
   private javax.swing.JTextField jTWProbability;
   private javax.swing.JTextField jTWRestarts;
   private javax.swing.JTextField jTWTabuSize;
   // End of variables declaration//GEN-END:variables
}
