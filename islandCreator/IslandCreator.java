package islandCreator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Creates an instance of the IslandCreator class
 * 
 * @author Nick Pantuso
 */
public class IslandCreator {
	
	private int numIslands;
	private JTextPane islandTxt;
	
	/**
	 * Defines an empty IslandCreator with an empty JTextPane and numIslands at 0.
	 */
	public IslandCreator() {
		islandTxt = new JTextPane();
		numIslands = 0;
	}
	
	/**
	 * Defines an IslandCreator via its text (0s and 1s), and the number of islands it contains.
	 * Finds the number of islands using DepthFirstSearch.
	 * 
	 * @param txt
	 * @param grid
	 */
	public IslandCreator(JTextArea txt, char[][] grid) {
		islandTxt = format(islandTxt, txt.getText());
		numIslands = 0;
		int row = grid.length;
		int col = grid[0].length;
		for(int i = 0; i < row; i++) {
		    for(int j = 0; j < col; j++) {
		        if(grid[i][j] == '1') {
		        	numIslands++;
		        	DFS(grid, i, j, row, col);
		        }
		    }
		}
	}
	
	/**
	 * Checks all 8 squares around a character. If it's a 1, continue, else, return.
	 * 
	 * @param copy
	 * @param i
	 * @param j
	 * @param row
	 * @param col
	 */
	private void DFS(char[][] copy, int i, int j, int row, int col) {
		if (i < 0 || j < 0 || i > (row - 1) || j > (col - 1) || copy[i][j] != '1') {
			return;
	    }
	 
	    if (copy[i][j] == '1') {
		    copy[i][j] = '0';
		    DFS(copy, i + 1, j, row, col);
		    DFS(copy, i - 1, j, row, col);
		    DFS(copy, i, j + 1, row, col);
		    DFS(copy, i, j - 1, row, col);
		    DFS(copy, i + 1, j + 1, row, col);
		    DFS(copy, i - 1, j - 1, row, col);
		    DFS(copy, i + 1, j - 1, row, col);
		    DFS(copy, i - 1, j + 1, row, col);
	    }
	}
	
	public int getNumIslands() {
		return numIslands;
	}
	
	public JTextPane getMap() {
		return islandTxt;
	}
	
	/**
	 * Formats the characters that were in the 2d array so that the 1s are green and the 0s are blue.
	 * 
	 * @param pane
	 * @param txt
	 * @return pane
	 */
	public JTextPane format(JTextPane pane, String txt) {
		StyledDocument doc = new DefaultStyledDocument();
		pane = new JTextPane(doc);
		pane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pane.setEditable(false);
		pane.setText(txt);
		for(int i = 0; i < pane.getDocument().getLength(); i++) {
			SimpleAttributeSet set = new SimpleAttributeSet();
			StyleConstants.setAlignment(set, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), set, false);
			if(i % 19 == 0) { //i 19
				try {
					doc.insertString(i, "\n", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			if(pane.getText().charAt(i) == '1') {
				StyleConstants.setForeground(set, Color.GREEN);
				doc.setCharacterAttributes(i, 1, set, true);
			}
			else if(pane.getText().charAt(i) == '0') {
				StyleConstants.setForeground(set, Color.BLUE);
				doc.setCharacterAttributes(i, 1, set, true);
			}
		}
		pane.setBorder(new EmptyBorder(0,51,0,51));
		return pane;
	}
	
}
