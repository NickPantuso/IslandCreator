package islandCreator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Application for the IslandCreator Project.
 * 
 * @author Nick Pantuso
 */
public class IslandCreatorApp extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea userInput;
	private JButton createBtn;
	private JTextPane islandTxt;
	private IslandCreator island;
	private IslandQueue q;
	public static LoadIslandsApp load;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IslandCreatorApp frame = new IslandCreatorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IslandCreatorApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//create background
		
		JPanel titlePanel = new JPanel();
		JLabel inputTxt = new JLabel("Input");
		JLabel outputTxt = new JLabel("Output");
		createTitlePanel(titlePanel, inputTxt, outputTxt);
		//create title panel
		
		JPanel islandPanel = new JPanel();
		userInput = new JTextArea();
		islandTxt = new JTextPane();
		JPanel createPanel = new JPanel();
		createBtn = new JButton("Create Map ->");
		JButton resetBtn = new JButton("Reset");
		JLabel guideTxt = new JLabel("Enter 1s and 0s under \"Input\" until you can't enter them anymore, then click \"Create Map\"");
		JLabel numTxt = new JLabel(" ");
		createIslandPanel(islandPanel, createPanel, resetBtn, guideTxt, numTxt);
		//create island panel
		
		JPanel buttonPanel = new JPanel();
		JButton saveBtn = new JButton("Save Island Map");
		JButton loadBtn = new JButton("Load Island Maps");
		createButtonPanel(buttonPanel, saveBtn, loadBtn);
		//create button panel
		
		island = new IslandCreator();
		q = new IslandQueue();
		
		/**
		 * When clicked, formats the user's input and counts the number of islands. The save button becomes available.
		 */
		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					for(int i = 0; i < userInput.getText().length(); i++) { //checks to see if anything other than a 0 or 1 was entered.
						if(userInput.getText().charAt(i) != '1' && userInput.getText().charAt(i) != '0') {
							guideTxt.setText("Only 1s and 0s will be accepted. Please try again.");
							resetBtn.doClick();
							return;
						}
					}
					islandTxt = island.format(islandTxt, userInput.getText());
					islandPanel.add(islandTxt, BorderLayout.EAST);
					//format the created island
					
					int c = 0;
					char[][] input = new char[8][18];
					for(int i = 0; i < input.length; i++) {
						for(int j = 0; j < input[i].length; j++) {
							input[i][j] = userInput.getText().charAt(c);
							c++;
						}
					}
					island = new IslandCreator(userInput, input);
					numTxt.setText("Number of islands: " + island.getNumIslands());
					//count islands
					
					saveBtn.setEnabled(true);
				}
			});
		
		/**
		 * When clicked, erases current text and disables the create and save button.
		 * User will be able to input another map.
		 */
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					numTxt.setText(" ");
					userInput.setText("");
					islandTxt.setText("");
					islandTxt.setBorder(new EmptyBorder(10,150,10,150));
					islandPanel.add(islandTxt, BorderLayout.EAST);
					createBtn.setEnabled(false);
					saveBtn.setEnabled(false);
					userInput.setEditable(true);
				}
			});
		
		/**
		 * When clicked, adds the created island to the queue, disables the save button, and enables the load button permanently.
		 */
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					q.enqueue(island);
					loadBtn.setEnabled(true);
					saveBtn.setEnabled(false);
				}
			});
		
		/**
		 * When clicked, open the LoadIslandsApp GUI.
		 */
		loadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					load = new LoadIslandsApp(q);
					load.setVisible(true);
				}
			});
		
		/**
		 * Mouse listeners for when the user hovers. Changes color of the button.
		 */
		createBtn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	createBtn.setBackground(Color.BLUE);
		    	createBtn.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	createBtn.setBackground(Color.GREEN);
		    	createBtn.setForeground(Color.BLACK);
		    }
		});
		
		resetBtn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	resetBtn.setBackground(Color.BLUE);
		    	resetBtn.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	resetBtn.setBackground(Color.GREEN);
		    	resetBtn.setForeground(Color.BLACK);
		    }
		});
		
		saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	saveBtn.setBackground(Color.RED);
		    	saveBtn.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	saveBtn.setBackground(Color.PINK);
		    	saveBtn.setForeground(Color.BLACK);
		    }
		});
		
		loadBtn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	loadBtn.setBackground(Color.RED);
		    	loadBtn.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	loadBtn.setBackground(Color.PINK);
		    	loadBtn.setForeground(Color.BLACK);
		    }
		});
	}	

	/**
	 * Creates the button panel at the bottom that holds the save and load buttons.
	 * 
	 * @param buttonPanel
	 * @param saveBtn
	 * @param loadBtn
	 */
	private void createButtonPanel(JPanel buttonPanel, JButton saveBtn, JButton loadBtn) {
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(new EmptyBorder(12,70,12,70));
		buttonPanel.setLayout(new BorderLayout(0, 0));

		saveBtn.setBorder(new EmptyBorder(15,35,15,35));
		saveBtn.setBackground(Color.PINK);
    		saveBtn.setForeground(Color.BLACK);
    		saveBtn.setEnabled(false);
		buttonPanel.add(saveBtn, BorderLayout.WEST);
		
		loadBtn.setBorder(new EmptyBorder(10,35,10,35));
		loadBtn.setBackground(Color.PINK);
    		loadBtn.setForeground(Color.BLACK);
    		loadBtn.setEnabled(false);
		buttonPanel.add(loadBtn, BorderLayout.EAST);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the island panel in the center that holds the user input, panel for the user's creation, and both the create and reset buttons.
	 * 
	 * @param islandPanel
	 * @param createPanel
	 * @param resetBtn
	 * @param guideTxt
	 * @param numTxt
	 */
	private void createIslandPanel(JPanel islandPanel, JPanel createPanel, JButton resetBtn, JLabel guideTxt, JLabel numTxt) {
		islandPanel.setBackground(Color.WHITE);
		islandPanel.setLayout(new BorderLayout(0, 0));
		islandPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		userInput.setBorder(new EmptyBorder(25,45,10,45));
		userInput.setColumns(12);
		userInput.setRows(0);
		userInput.addKeyListener(this);
		userInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userInput.setLineWrap(true);
		islandPanel.add(userInput, BorderLayout.WEST);
		
		islandTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		islandTxt.setEditable(false);
		islandTxt.setBorder(new EmptyBorder(10,150,10,150));
		islandPanel.add(islandTxt, BorderLayout.EAST);
		
		createPanel.setBackground(Color.WHITE);
		createPanel.setLayout(new BorderLayout(0, 0));
		createPanel.setBorder(new EmptyBorder(90,10,90,10));
		islandPanel.add(createPanel, BorderLayout.CENTER);
		
		createBtn.setBorder(new EmptyBorder(12,12,12,12));
		createBtn.setBackground(Color.GREEN);
		createBtn.setEnabled(false);
		createPanel.add(createBtn, BorderLayout.NORTH);
		
		resetBtn.setBorder(new EmptyBorder(12,12,12,12));
		resetBtn.setBackground(Color.GREEN);
		createPanel.add(resetBtn, BorderLayout.SOUTH);
		
		guideTxt.setHorizontalAlignment(SwingConstants.CENTER);
		islandPanel.add(guideTxt, BorderLayout.SOUTH);
		
		numTxt.setBorder(new EmptyBorder(0,505,0,0));
		islandPanel.add(numTxt, BorderLayout.NORTH);
		
		contentPane.add(islandPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the title panel at the top that holds the input and output texts.
	 * 
	 * @param titlePanel
	 * @param inputTxt
	 * @param outputTxt
	 */
	private void createTitlePanel(JPanel titlePanel, JLabel inputTxt, JLabel outputTxt) {
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setLayout(new BorderLayout(0, 0));
		titlePanel.setBorder(new EmptyBorder(7,10,7,10));
		
		inputTxt.setForeground(Color.WHITE);
		inputTxt.setOpaque(true);
		inputTxt.setBackground(Color.BLACK);
		inputTxt.setFont(new Font("Monospaced", Font.BOLD, 23));
		inputTxt.setBorder(new EmptyBorder(7,110,7,110));
		titlePanel.add(inputTxt, BorderLayout.WEST);
		
		outputTxt.setForeground(Color.WHITE);
		outputTxt.setOpaque(true);
		outputTxt.setBackground(Color.BLACK);
		outputTxt.setFont(new Font("Monospaced", Font.BOLD, 23));
		outputTxt.setBorder(new EmptyBorder(7,110,7,110));
		titlePanel.add(outputTxt, BorderLayout.EAST);
		
		contentPane.add(titlePanel, BorderLayout.NORTH);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * When the user presses a key, checks if they've reached the limit. Once they have, they will be unable to enter
	 * anymore characters, and the create button will become available.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		String data=userInput.getText();
        try{
            if(data.length() > 143){
              userInput.setEditable(false);  
              createBtn.setEnabled(true);
            }
        }
        catch(Exception ex){}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
