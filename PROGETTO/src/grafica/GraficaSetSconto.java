package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classi.GestioneStadi;
import classi.PartitaProgrammata;
import interfacce.OperazioniGestore;
import utils.Salvataggio;
import utils.Tipologia;

public class GraficaSetSconto extends JPanel {

	
private	JPanel pnlSelectTipo,pnlSelectPartita,pnlImposta;
private	JTextField txtImporto;
private	JComboBox<Tipologia> tipo;
JComboBox<PartitaProgrammata> partite;
private	JButton fatto;
	
	public GraficaSetSconto(OperazioniGestore op){
		
		pnlSelectTipo=new JPanel();
		pnlSelectTipo.add(new JLabel("seleziona tipologia"));
		
		tipo=new JComboBox<Tipologia>();
		tipo.addItem(Tipologia.Studente);
		tipo.addItem(Tipologia.Bambino);
		tipo.addItem(Tipologia.Pensionato);
		tipo.setSelectedIndex(-1);
		
		pnlSelectPartita=new JPanel();
		pnlSelectPartita.add(new JLabel("seleziona partita"));
		
		partite=new JComboBox<PartitaProgrammata>();
		for(PartitaProgrammata p:((GestioneStadi)op).getPartite())
			partite.addItem(p);
		
		partite.setSelectedIndex(-1);
		pnlSelectPartita.add(partite);
		
		
		pnlSelectTipo.add(tipo);
		
		pnlImposta=new JPanel();
		pnlImposta.add(new JLabel("inserisci importo %: "));
		txtImporto=new JTextField();
		txtImporto.setPreferredSize(new Dimension(40, 30));
		
		pnlImposta.add(txtImporto);
		
		fatto=new JButton("fatto");
		fatto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Avviso a ;
				if(tipo.getSelectedItem()!=null)
				{
					if(isGoodSconto(txtImporto.getText()))
					{  //ATTIVAZIONE SCONTO PER LA TIPOLOGIA DI UTENTE
					op.attivaSconto(Integer.parseInt(txtImporto.getText()),(Tipologia)tipo.getSelectedItem());  
					a=new Avviso("sconto impostato");
					}else
					{   a=new Avviso("percentuale non valida");
						txtImporto.setText("");
					}
				}
				else
					if(isGoodSconto(txtImporto.getText())) //ATTIVAZIONE SCONTO PER UNA SINGOLA PARTITA
					{op.attivaSconto(Integer.parseInt(txtImporto.getText()),(PartitaProgrammata) partite.getSelectedItem());
					a=new Avviso("sconto impostato");
					}else
					{	a=new Avviso("percentuale non valida");
					txtImporto.setText("");
					}
				
				Salvataggio.salva((GestioneStadi)op);
				
			}
		});
		
		JPanel pnlOk=new JPanel();
		pnlOk.add(fatto);
		
		
		setLayout(new GridLayout(4, 1));
		add(pnlSelectTipo);
		add(pnlSelectPartita);
		add(pnlImposta);
		add(pnlOk);
	}
	
	public boolean isGoodSconto(String num){
		  try{
			int x=Integer.parseInt(num);
		    if( x>0&& x<=100)
		    return true;
		    else
		    	return false;
		  }catch(Exception e){
		   
		      return false;
		    
		  }
		}
	
	
}
