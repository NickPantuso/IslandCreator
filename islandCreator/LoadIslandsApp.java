package islandCreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

/**
 * Creates the LoadIslandsApp GUI.
 * 
 * @author Nick Pantuso
 */
public class LoadIslandsApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private IslandCreator nextIsland;
	private JTextPane islandTxt;

	/**
	 * Create the frame.
	 */
	public LoadIslandsApp(IslandQueue q) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//set background
		
		Iterator<IslandCreator> i = q.iterator();
		nextIsland = new IslandCreator();
		if(i.hasNext()) {
			nextIsland = i.next();
		}
		//starts queue iterator
		
		JPanel outputPanel = new JPanel();
		islandTxt = new JTextPane();
		JLabel titleTxt = new JLabel("Output");
		JLabel numTxt = new JLabel("");
		createOutputPanel(outputPanel, titleTxt, numTxt);
		//create output panel
		
		JPanel btnPanel = new JPanel();
		JButton next = new JButton("Next Map ->");
		JButton back = new JButton("Go back to IslandCreatorApp");
		createBtnPanel(btnPanel, next, back);
		//create button panel
		
		/**
		 * When clicked, goes to the next map saved in the queue. If queue is empty, notify user.
		 */
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					if(i.hasNext()) {
						nextIsland = i.next();
						islandTxt.setText(nextIsland.getMap().getText());
						numTxt.setText("Number of Islands: " + nextIsland.getNumIslands());
					} else {
						next.setText("You've seen all of your saved maps!");
					}
				}
			});
		
		/**
		 * When clicked, closes LoadIslandsApp GUI and returns user to IslandCreatorApp.
		 */
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					IslandCreatorApp.load.setVisible(false);
				}
			});
		
		/**
		 * Mouse listeners for when the user hovers. Changes color of the button.
		 */
		next.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	next.setBackground(Color.BLUE);
		    	next.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	next.setBackground(Color.GREEN);
		    	next.setForeground(Color.BLACK);
		    }
		});
		
		back.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	back.setBackground(Color.BLUE);
		    	back.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	back.setBackground(Color.GREEN);
		    	back.setForeground(Color.BLACK);
		    }
		});
	}

	/**
	 * Creates the button panel on the right that holds the next and back buttons.
	 * 
	 * @param btnPanel
	 * @param next
	 * @param back
	 */
	private void createBtnPanel(JPanel btnPanel, JButton next, JButton back) {
		btnPanel.setBackground(Color.WHITE);
		btnPanel.setLayout(new BorderLayout(0, 0));
		btnPanel.setBorder(new EmptyBorder(100,35,250,185));
		
		next.setBorder(new EmptyBorder(12,12,12,12));
		next.setBackground(Color.GREEN);
		btnPanel.add(next, BorderLayout.NORTH);
		
		back.setBorder(new EmptyBorder(12,12,12,12));
		back.setBackground(Color.GREEN);
		btnPanel.add(back, BorderLayout.SOUTH);
		
		contentPane.add(btnPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the output panel on the left that holds the user's saved maps, and the output text at the top.
	 * 
	 * @param outputPanel
	 * @param titleTxt
	 * @param numTxt
	 */
	private void createOutputPanel(JPanel outputPanel, JLabel titleTxt, JLabel numTxt) {
		outputPanel.setBorder(new EmptyBorder(7,10,7,10));
		outputPanel.setLayout(new BorderLayout(0, 0));
		outputPanel.setBackground(Color.WHITE);
		
		titleTxt.setForeground(Color.WHITE);
		titleTxt.setOpaque(true);
		titleTxt.setBackground(Color.BLACK);
		titleTxt.setFont(new Font("Monospaced", Font.BOLD, 23));
		titleTxt.setBorder(new EmptyBorder(7,110,7,110));
		outputPanel.add(titleTxt, BorderLayout.NORTH);
		
		islandTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		islandTxt.setEditable(false);
		islandTxt.setBorder(new EmptyBorder(0,51,0,51));
		islandTxt.setText(nextIsland.getMap().getText());
		outputPanel.add(islandTxt, BorderLayout.CENTER);
		
		numTxt.setBorder(new EmptyBorder(0,55,125,0));
		numTxt.setText("Number of Islands: " + nextIsland.getNumIslands());
		outputPanel.add(numTxt, BorderLayout.SOUTH);
		
		contentPane.add(outputPanel, BorderLayout.WEST);
	}
	
	
}
