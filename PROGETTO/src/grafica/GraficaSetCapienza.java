package grafica;

import java.awt.Dimension;
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

public class GraficaSetCapienza extends JPanel{
	
	private JComboBox<String> stadi;
	private JTextField txtCapienza;
	private JLabel lblCapienza,lblSelect;
	
	public GraficaSetCapienza(OperazioniGestore op,GestioneStadi gs){
		
		setBorder(new TitledBorder("modifica capienza"));
		lblCapienza=new JLabel("nuova capienza");
		txtCapienza=new JTextField();
		txtCapienza.setPreferredSize(new Dimension(30, 30));
	
		JPanel pnlC=new JPanel();
		pnlC.add(lblCapienza);
		pnlC.add(txtCapienza);
	
		
		add(pnlC);
		
		stadi=new JComboBox<String>();
		for(String s:gs.getListStadi())
			stadi.addItem(s);
		
		
	
		lblSelect=new JLabel("seleziona stadio: ");
		JPanel pnlStadi=new JPanel();
		pnlStadi.add(lblSelect);
		pnlStadi.add(stadi);
		
		add(pnlStadi);
		

		JButton ok=new JButton("modifica");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 Avviso a=new Avviso("Capienza modificata");
			op.setCapienzaStadio(getStadio(),getCapienza());
			Salvataggio.salva((GestioneStadi)op);	
			}
		
		
		});
		
		JPanel pnlOk=new JPanel();
		pnlOk.add(ok);
		add(pnlOk);
	}
	public int getCapienza(){
		return Integer.parseInt(txtCapienza.getText());
	}
	public String getStadio(){
		return (String) stadi.getSelectedItem();
	}

}
