/* Author: Luigi Vincent
This is a loan calculator application
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LoanCal extends JFrame {
	// TextFields for parameters
	private JTextField interest = new JTextField(); // Annually
	private JTextField years = new JTextField();
	private JTextField loan = new JTextField();
	private JTextField payment = new JTextField(); // Monthly
	private JTextField total = new JTextField();

	// Button to calculate payment
	private JButton compute = new JButton("Compute Payment");

	public LoanCal() {
		// Panel for labels and text fields
		JPanel p1 = new JPanel(new GridLayout(5, 2));
		// Populate panel
		p1.add(new JLabel("Annual Interest Rate"));
		p1.add(interest);

		p1.add(new JLabel("Number Of Years"));
		p1.add(years);

		p1.add(new JLabel("Loan Amount"));
		p1.add(loan);

		p1.add(new JLabel("Monthly Payment"));
		p1.add(payment);

		p1.add(new JLabel("Total Payment"));
		p1.add(total);

		p1.setBorder(new TitledBorder("Enter loan amount, interest rate and years"));

		// Panel for button
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// add button to panel
		p2.add(compute);

		// add panels to frame
		add(p1, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);

		// define and register action listener
		compute.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// retreive text field values
				double rate = Double.parseDouble(interest.getText());
				int year = Integer.parseInt(years.getText());
				double loanAmount = Double.parseDouble(loan.getText());

				Loan loan = new Loan(rate, year, loanAmount);

				//Display monthly and total payment
				payment.setText(String.format("%.2f", loan.getMonthlyPayment()));
				total.setText(String.format("%.2f", loan.getTotalPayment()));
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new LoanCal();
		frame.pack();
		frame.setTitle("Loan Calculator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
