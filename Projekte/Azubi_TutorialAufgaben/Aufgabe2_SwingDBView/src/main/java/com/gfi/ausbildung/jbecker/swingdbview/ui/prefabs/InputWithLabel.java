package com.gfi.ausbildung.jbecker.swingdbview.ui.prefabs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputWithLabel extends JPanel{
	
	public static InputWithLabel createWithOffsets(String labelText, int between){
		InputWithLabel input = new InputWithLabel();
		
		input.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		
		input.label = new JLabel(labelText);
		gbc.gridx = 0;
		gbc.ipadx = between;
		input.add(input.label, gbc);
		
		input.inputField = new JTextField();
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		input.add(input.inputField, gbc);
		
		return input;
	}
	
	private JLabel label;
	private JTextField inputField;
	
	private InputWithLabel(){}
	
	public String getInputText(){
		return inputField.getText();
	}
	
	public void addActionListener(ActionListener listener){
		inputField.addActionListener(listener);
	}

}
