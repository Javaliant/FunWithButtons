/* Author: Luigi Vincent
Circle Transformations portrayed with key, mouse, and button events
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CircleEvents extends JFrame {
	private JButton enlarge = new JButton("Enlarge");
	private JButton shrink = new JButton("Shrink");
	private JButton status = new JButton("Status");

	private CirclePanel canvas = new CirclePanel();

	public CircleEvents() {
		JPanel panel = new JPanel();
		panel.add(enlarge);
		panel.add(shrink);
		panel.add(status);

		this.add(canvas, BorderLayout.CENTER);
		this.add(panel, BorderLayout.SOUTH);

		ActionListener listener1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == enlarge) canvas.enlargeX();
				else if (e.getSource() == shrink) canvas.shrinkX();
				else if (e.getSource() == status) JOptionPane.showMessageDialog(null, "Radius size: " + canvas.getRadius() +"\n Color: " + canvas.getColor(), "Current Status", JOptionPane.INFORMATION_MESSAGE);
				canvas.requestFocusInWindow();
				repaint();
			}
		};

		enlarge.addActionListener(listener1);
		shrink.addActionListener(listener1);
		status.addActionListener(listener1);

		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) canvas.swapColor();
				else if (e.getButton() == MouseEvent.BUTTON3) canvas.resetColor();
				repaint();
			}
		});

		canvas.setFocusable(true);

		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) canvas.animateUp();
				else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) canvas.animateDown();
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) canvas.animateRight();
				else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) canvas.animateLeft();
				repaint();
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new CircleEvents();
		frame.setTitle("All three event types");
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	class CirclePanel extends JPanel {
		private int radius = 5, x = 0, y = 0, z = 0;
		private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.WHITE, Color.MAGENTA, Color.BLACK, Color.PINK};
		private String[] colorNames  = {"Red", "Green", "Blue", "Yellow", "Orange", "White", "Magenta", "Black", "Pink"};

		/*** Circle Transformations Proceed ***/
		// Change Size
		public void enlarge(){ radius++; }
		public void shrink(){ if (radius > 0) radius--; }
		public void enlargeX(){ radius += 5; }
		public void shrinkX(){ radius = Math.max(1, radius - 5); }
		// Change Color
		public void swapColor(){ if (z + 1 < colors.length) z++; }
		public void resetColor(){ z = 0; }
		// Change Position -- binded to arrow keys
		public void animateRight() { x += 10; }
		public void animateLeft(){ x -= 10; }
		public void animateUp(){ y -= 10; }
		public void animateDown(){ y += 10; }
		// returns radius value
		public int getRadius(){ return this.radius; }
		// returns current color
		public String getColor(){ return colorNames[z]; }

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(colors[z]);
			g.fillOval((getWidth() / 2 - radius) + x, (getHeight() / 2 - radius) + y, 2 * radius, 2 * radius);
		}
	}
}
