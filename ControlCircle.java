/* Author: Luigi Vincent
Program displays a circle and four buttons to enlarge or shrink it, and enlarge or shrink it significantly.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlCircle extends JFrame {
	private JButton nlrg_button = new JButton("Enlarge");
	private JButton shrink_button = new JButton("Shrink");
	private JButton supershrinker_button = new JButton("Augment");
	private JButton superenlarger_button = new JButton("Diminish");
	private CirclePanel canvas = new CirclePanel();

	public ControlCircle() {
		// Panels to group buttons
		JPanel p = new JPanel();
		p.add(nlrg_button);
		p.add(shrink_button);

		JPanel p2 = new JPanel();
		p2.add(superenlarger_button);
		p2.add(supershrinker_button);

		// add canvas center frame
		this.add(canvas, BorderLayout.CENTER); 
		// Adding the buttons to frame
		this.add(p, BorderLayout.SOUTH);
		this.add(p2, BorderLayout.NORTH);
		
		CircleListener listener = new CircleListener();

		// Bind buttons with  eventlistener class
		nlrg_button.addActionListener(listener);
		shrink_button.addActionListener(listener);
		superenlarger_button.addActionListener(listener);
		supershrinker_button.addActionListener(listener);
	}

	public static void main(String[] args) {
		JFrame frame = new ControlCircle();
		frame.setTitle("Interactive Buttons");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// Event Listener class; checks which button was clicked and responds accordingly
	class CircleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nlrg_button) canvas.enlarge();
			else if (e.getSource() == shrink_button) canvas.shrink();
			else if (e.getSource() == superenlarger_button) canvas.enlargeAlot();
			else if (e.getSource() == supershrinker_button) canvas.shrinkAlot();
		}
	}

	class CirclePanel extends JPanel {
		// Default Circle size
		private int radius = 5;

		// Several circle transformation methods
		public void enlarge() {
			radius++;
			repaint();
		}
		public void shrink() {
			radius--;
			repaint();
		}
		public void enlargeAlot() {
			radius += 5;
			repaint();
		}
		public void shrinkAlot() {
			radius -= 5;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
		}
	}
}
