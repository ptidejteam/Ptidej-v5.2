/*
 * Created on 2004-10-05
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package dram.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JCheckBox;

/**
 * @author Samah
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CheckBoxListener implements ActionListener, ItemListener {
	static Vector vectorChoices = new Vector();
	public void itemStateChanged(ItemEvent evt) {
		
		JCheckBox source = (JCheckBox) evt.getSource();
		if (source.isSelected()) {
			vectorChoices.add(source.getText());
			System.out.println("selected " + source.getText());

		} else {
			vectorChoices.remove(source.getText());
			System.out.println("deselected " + source.getText());
		}
		for (int i = 0; i < vectorChoices.size(); i++) {
			System.out.print(" itemStateChanged   " + vectorChoices.elementAt(i));
		}
		System.out.println("\n");
	}

	public void actionPerformed(ActionEvent e) {
//		for (int i = 0; i < vectorChoices.size(); i++) {
//			System.out.println("eeee  " + vectorChoices.elementAt(i));
//		}

	}
	public Vector getvectorChoices() {
		return vectorChoices;
	}
	public void setvectorChoices(Vector v) {
		for (int i = 0; i < v.size(); i++) {
			System.out.println("eeee  " + v.elementAt(i));
		}
		vectorChoices = v;
	}
	public Vector removevectorChoices() {
		vectorChoices.removeAllElements();
		return vectorChoices;
	}
	
}
