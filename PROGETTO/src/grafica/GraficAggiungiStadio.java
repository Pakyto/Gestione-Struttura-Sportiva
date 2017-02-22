package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classi.GestioneStadi;
import eccezioni.StadioEsistentException;
import interfacce.OperazioniGestore;
import utils.Salvataggio;

public class GraficAggiungiStadio extends JPanel{
	
	private JTextField txtNome,txtCapienza,txtPrezzo;

	private JLabel lblNome,lblCapienza,lblPrezzo;
	
	
	public GraficAggiungiStadio(OperazioniGestore op){
		
		setLayout(new GridLayout(3, 1));
		
		txtNome=new JTextField();
		txtNome.setEditable(true);
		txtNome.setPreferredSize(new Dimension(50, 20));
		lblNome=new JLabel("nome stadio: ");
		JPanel pnlNome=new JPanel();
		pnlNome.add(lblNome);
		pnlNome.add(txtNome);
		
		add(pnlNome);
		
		txtCapienza=new JTextField();
		txtCapienza.setEditable(true);
		txtCapienza.setPreferredSize(new Dimension(20, 20));
		lblCapienza=new JLabel("capienza stadio: ");
		JPanel pnlCapienza=new JPanel();
		pnlCapienza.add(lblCapienza);
		pnlCapienza.add(txtCapienza);
		
		add(pnlCapienza);
		
		txtPrezzo=new JTextField();
		txtPrezzo.setEditable(true);
		txtPrezzo.setPreferredSize(new Dimension(20, 20));
		lblPrezzo=new JLabel("prezzo: ");
		JPanel pnlPrezzo=new JPanel();
		pnlPrezzo.add(lblPrezzo);
		pnlPrezzo.add(txtPrezzo);
		
		add(pnlPrezzo);
		
		setBorder(new TitledBorder("aggiungi stadio"));
		
		
		JButton ok=new JButton("fatto");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Avviso a;
			
					try {
						if((txtNome==null)||(txtCapienza==null)||(txtPrezzo==null))
							{a= new Avviso("riempi tutti i campi");
							
							}
						else
							if((isFloatOrInt(txtCapienza.getText()))&&(isFloatOrInt(txtPrezzo.getText())))
						{
							op.aggiungiStadio(txtNome.getText(), Integer.parseInt(txtCapienza.getText()),  Float.parseFloat(txtPrezzo.getText()));
						Salvataggio.salva((GestioneStadi)op);
						a=new Avviso("Stadio aggiunto");
						}
							else
							{ a=new Avviso("inserisci valori validi");
							txtCapienza.setText("");
							txtPrezzo.setText("");
							}	
					} catch (StadioEsistentException e1) {
						
						 a=new Avviso("stadio già esistente");
						
					}
				
				
			}
		
		
		});
		add(ok);
	}
	
	

	
	public boolean isFloatOrInt(String num){
		  try{
		    Integer.parseInt(num);
		    return true;
		  }catch(Exception e){
		    try{
		    Float.parseFloat(num);
		      return true;
		    }catch(Exception c){
		      return false;
		    }
		  }
		}
	
	


	
}
