/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package msc.krr.dynsat;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JFrame;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ZoomableChart;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.*;
import info.monitorenter.gui.chart.traces.computing.Trace2DArithmeticMean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class MinimalStaticChart {
  public static ITrace2D trace = new Trace2DReplacing();
  public static ITrace2D change_problem_trace = new Trace2DReplacing();
  public static ZoomableChart chart = new ZoomableChart();
  public static JTextArea console = new JTextArea("", 15, 50);

  private MinimalStaticChart() {
    super();
  }

  public static void main(String[]args){
    // Create a chart:
    //chart = new Chart2D();
    // Create an ITrace:
    chart.setBackground(Color.BLACK);
    chart.setForeground(Color.WHITE);
  // Add the trace to the chart:
    trace.setColor(Color.RED);
    change_problem_trace.setColor(Color.GREEN);
    chart.addTrace(trace);
    chart.addTrace(change_problem_trace);
    chart.setAutoscrolls(true);
    
    // Make it visible:
    // Create a frame.
    JFrame frame = new JFrame("DynSAT Solver");
    JPanel contentPane;
    contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout());
    
    contentPane.add(chart);


    console.setLineWrap(true);
    JScrollPane consoleScroll = new JScrollPane(console);
    consoleScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    

    contentPane.add(consoleScroll, BorderLayout.PAGE_END);
    frame.setContentPane(contentPane);
    frame.setSize(1024,768);
    frame.addWindowListener(
        new WindowAdapter(){
          public void windowClosing(WindowEvent e){
              System.exit(0);
          }
        }
      );
    frame.show();
    WalkDynSAT d = new WalkDynSAT();

		//String s[] = {"/Users/kostas/Desktop/cnf/cnf1","/Users/kostas/Desktop/cnf/cnf2", "/Users/kostas/Desktop/cnf/cnf3"};
                d.setParameters(100, 10000, 3, 0.2f);

               //GraspDynSAT d = new GraspDynSAT();
                String s[] = {"/Users/kostas/Desktop/210120104rnd_1000_233_10_10/base",
                             "/Users/kostas/Desktop/210120104rnd_1000_233_10_10/0"};
                //d.setParameters(1000, 0.5f, 1000, 10, 0.8f);

		d.loadFiles(s);
		try {
			d.solve();
                        for(int j=0;j<d.getDynSAT().size(); ++j){
                            MinimalStaticChart.console.append(d.getDynSAT().get(j).isSolved() ? "==== SOLVED ====" : "==== NOT SOLVED ====");
                            MinimalStaticChart.console.append(d.getDynSAT().get(j).getBestSolution().toString());
                            MinimalStaticChart.console.append("=====================================");
                        }
		} catch ( Exception e ) {
			e.printStackTrace();
		}

    
      
    
  
  }
}