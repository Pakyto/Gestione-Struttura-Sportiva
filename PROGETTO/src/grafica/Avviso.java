package grafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Avviso {
	
	JFrame avviso;
	JButton okButton;
	JPanel btnPanel,lblPanel;
	JLabel lblAvviso;
	
	public Avviso(String title){
		avviso=new JFrame("Avviso");
		avviso.setSize(300, 200);
		avviso.setLayout(new GridLayout(2, 1));
		avviso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblAvviso=new JLabel(title);
		lblPanel=new JPanel();
		btnPanel=new JPanel();
		okButton=new JButton("ok");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				avviso.dispose();				
			}
		});
		btnPanel.add(okButton);
		lblPanel.add(lblAvviso);
		
		avviso.add(lblPanel);
		avviso.add(btnPanel);
		avviso.setVisible(true);
	
		
	}

}
