package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classi.GestioneStadi;
import interfacce.OperazioniGestore;
import utils.Salvataggio;

public class GraficaSetPrezzo extends JPanel{
	
	private JLabel lblNewPrezzo,lblStadi;
	private JTextField txtPrezzo;
	private JPanel pnlNew;
	private JComboBox<String> stadi;
	
	public GraficaSetPrezzo(OperazioniGestore op,GestioneStadi g){
		
		setBorder(new TitledBorder("cambia prezzo"));
		
		setLayout(new GridLayout(3, 1));
		
		pnlNew=new JPanel();
		txtPrezzo=new JTextField();
		txtPrezzo.setPreferredSize(new Dimension(50, 20));
		lblNewPrezzo=new JLabel("nuovo prezzo: ");
		pnlNew.add(lblNewPrezzo);
		pnlNew.add(txtPrezzo);
		pnlNew.setVisible(true);
		
		lblStadi=new JLabel("seleziona stadio: ");
		
		stadi=new JComboBox<String>();
		stadi.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				
				pnlNew.setVisible(true);
			}
		});
		for(String s:g.getListStadi())
			stadi.addItem(s);
		
		JPanel pnlStadi=new JPanel();
		pnlStadi.add(lblStadi);
		pnlStadi.add(stadi);
		add(pnlStadi);
	
		JButton ok=new JButton("cambia");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Avviso a=new Avviso("Prezzo Cambiato");
				if(getPrezzo().equals(new Float(0)))
					a=new Avviso("inserire prezzo valido");
					op.assegnaPrezzo(getPrezzo() ,getNome());
					Salvataggio.salva((GestioneStadi)op);
			}
		
		});
	
		add(pnlNew);
		JPanel pnlOk=new JPanel();
		pnlOk.add(ok);
		add(pnlOk);
		
	}
	
	public boolean isFloat(String num){
		  try{
		    Float.parseFloat(num);
		      return true;
		    }catch(Exception c){
		      return false;
		    }
		
		}
	
	public Float getPrezzo(){
		if(isFloat(txtPrezzo.getText()))
			return Float.parseFloat(txtPrezzo.getText());
		
		
		return (float) 0.0;
	
		
	}
	
	public String getNome(){
		return (String) stadi.getSelectedItem();
	}


}
