package br.ies.aps.eightpuzzle.view.swing.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class TileView extends JLabel {

	private JLabel label;

	public TileView(Integer value) {
		this.label = new JLabel(value.toString());
		this.label.setForeground(Color.BLACK);
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.label.setFont(new Font("Open Sans", Font.BOLD, 72));
		this.label.setBackground(Color.WHITE);
		this.label.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(Integer value) {
		this.label.setText(value.toString());
	}

}
