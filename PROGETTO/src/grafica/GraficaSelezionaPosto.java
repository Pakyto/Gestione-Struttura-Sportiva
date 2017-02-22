package grafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import classi.GestioneStadi;
import classi.PartitaProgrammata;
import classi.Prenotazione;
import eccezioni.PostoIndisponibileException;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.UtenteInesistenteException;
import interfacce.OggettoProgrammato;
import interfacce.OperazioniCliente;
import utils.Salvataggio;
import utils.StatoPosto;

public class GraficaSelezionaPosto extends JFrame {
	public static boolean aperta=true;
	public JPanel pnlSettore,pnlFatto;
	public ArrayList<Integer> posti=new ArrayList<Integer>();
		
	public GraficaSelezionaPosto(Boolean prenotaAcquista,GestioneStadi op, String nomeStadio, String username, PartitaProgrammata pa,int start,int end){
		
		setTitle("seleziona Posti");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pnlSettore=new JPanel(new GridLayout((int)((end-start)/2), (int)((end-start)/2)));
		JScrollPane scrollPosti=new JScrollPane(pnlSettore);
		ImageIcon img;
		for(int i=start;i<=end;i++){
			if(pa.getStatoPosto(i).equals(StatoPosto.Libero))
			{img=new ImageIcon("libero.png");}
			else
				if(pa.getStatoPosto(i).equals(StatoPosto.Prenotato))
				 img=new ImageIcon("prenotato.png");	
				else 
					 img=new ImageIcon("acquistato.png");	
				JButton posto=new JButton(String.valueOf(i),img);
				
				posto.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
					try{
					
					if(pa.getStatoPosto(Integer.parseInt(posto.getText())).equals(StatoPosto.Libero))
					{
						if(prenotaAcquista)
							posto.setIcon(new ImageIcon("prenotato.png"));	
						else
							posto.setIcon(new ImageIcon("acquistato.png"));	
						
						posti.add(Integer.parseInt(posto.getText()));
					}
						
					else
						if(pa.getStatoPosto(Integer.parseInt(posto.getText())).equals(StatoPosto.Acquistato))
							
							throw new PostoIndisponibileException();
							
							
						
					else 
						if(pa.getStatoPosto(Integer.parseInt(posto.getText())).equals(StatoPosto.Prenotato))
							
							if(prenotaAcquista)//se si sta prenotando
							{throw new PostoIndisponibileException();	
							}
							else //se si sta acquistando 
							{	ArrayList<Prenotazione> listaPrenotazioni=op.getPrenotazioni(username);
							
								if(listaPrenotazioni.contains(new Prenotazione(username, Integer.valueOf(posto.getText()), pa)))
									{posto.setIcon(new ImageIcon("acquistato.png"));	
									posti.add(Integer.parseInt(posto.getText()));
									}
								else throw new PostoIndisponibileException();
							}
					
					
					}
							catch (PostoIndisponibileException e1) {
								Avviso a=new Avviso("posto non selezionabile");
							}
					
					}});
			 pnlSettore.add(posto);

		}
		scrollPosti.createVerticalScrollBar();
		add(scrollPosti);
		
		pnlFatto=new JPanel();
		JButton fatto=new JButton("fatto");
		fatto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//se è 0 prenota altrimenti acquista
				if(prenotaAcquista)
				for(Integer i:posti)         //PRENOTAZIONE DEI POSTI
					try {	
					op.prenota(nomeStadio, username, i, pa.getsquadra1(), pa.getSquadra2(), pa.getData());
				}		
				
				catch (PrenotazioneEsistenteException e1) {
					
					Avviso a=new Avviso("hai già prenotato questo posto");
					
				}
				else
					for(Integer i:posti)       //ACQUISTO DEI POSTI
						try {
							op.acquista(nomeStadio, username, i, pa.getsquadra1(), pa.getSquadra2(), pa.getData());
						} catch (UtenteInesistenteException e1) {
							e1.printStackTrace();
						}
							
					
					
				Salvataggio.salva(op);
				dispose();
				
			}
		});
		pnlFatto.add(fatto);
		add(pnlFatto,BorderLayout.EAST);
		setVisible(true);
	}

}
