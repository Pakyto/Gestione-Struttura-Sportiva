package grafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import classi.PartitaProgrammata;
import classi.Prenotazione;
import classi.GestioneStadi;
import classi.Stadio;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.PrenotazioneNonEsistenteException;
import eccezioni.UtenteInesistenteException;
import interfacce.OperazioniCliente;
import utils.Caricamento;
import utils.Comparatore;
import utils.Salvataggio;

public class GraficaCliente extends JComponent{
	private JFrame finestra,framePrenotazione;
	private JPanel pnlSelStadio,pnlOrder,pnlSelect,pnlVisualizzaP,pnlVisualizzaA,pnlPartite,partiteStadio;
	private GraficaStadio pnlStadio;
	private JLabel title;
	private JLabel selStadio;
	private JLabel orderby;
	private JButton prenotazioni;
	private JButton biglietti;
	private JButton prenota;
	private JButton acquista;
	public static final int LARGHEZZA = 850;
	public static final int ALTEZZA = 500;
	private OperazioniCliente op;
	private ArrayList<String>listaStadi;
	private String usernameCliente;
	private JPanel listaPrenotazioni,listaBiglietti;
	private GestioneStadi stadi;
	private ArrayList<PartitaProgrammata> partiteOrdinate;
	private JComboBox listastadi;
	private JScrollPane scrollFramePrenotazioni,scrollFrameBiglietti;
	
	public GraficaCliente(GestioneStadi stadi,OperazioniCliente op,ArrayList<String>listaStadi,String usernameCliente){    //VIENE PASSATO L'USERNAME DELL'UTENTE
		this.stadi=stadi;
		this.op=op;
		this.usernameCliente=usernameCliente;
		this.listaStadi=listaStadi;
		finestra=new JFrame();
		finestra.setExtendedState(finestra.MAXIMIZED_BOTH);     //FRAME A DIMENSIONI MASSIME
		finestra.setTitle("Cliente");
	
		create();
	}
	
	public void creaVistaPartite(){
		partiteStadio=new JPanel();
		pnlSelStadio=new JPanel(new GridLayout(2, 1));
		selStadio=new JLabel("Seleziona Stadio");
		partiteOrdinate=op.getPartiteNonIniziate();
		listastadi=new JComboBox();
		for(String s:listaStadi){   //LISTA STADI
			listastadi.addItem(s);
		}
		listastadi.setSelectedIndex(-1);
		pnlSelStadio.add(selStadio);
		pnlSelStadio.add(listastadi);
		listastadi.setSelectedIndex(-1);
		listastadi.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				partiteOrdinate=op.getPartite((String)listastadi.getSelectedItem());
				creaListaPartite(partiteOrdinate);    //CREAZIONE LISTA DI STADI CON LE PARTITE
			}});
			
	
	
	//panel ordinamento	
		pnlOrder=new JPanel(new GridLayout(2, 1));
		orderby=new JLabel("Ordina partite per");
		JComboBox ordine=new JComboBox();
		ordine.addItem("ordine cronologico");
		ordine.addItem("ordine alfabetico");
		ordine.addItem("ordine rispetto all'ID dello stadio");
		ordine.setSelectedIndex(-1);
	
		
		ordine.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				//ordinamento
				switch	(ordine.getSelectedIndex()){
				case 0:Collections.sort(partiteOrdinate,new Comparatore.DateComparator());
				creaListaPartite(partiteOrdinate);
					break;
				
				case 1: Collections.sort(partiteOrdinate,new Comparatore.LexicographicComparator());
				creaListaPartite(partiteOrdinate);
				break;
				case 2:
					Collections.sort(stadi.getStadi(),new Comparatore.StadioComparator());
					partiteOrdinate=new ArrayList<PartitaProgrammata>();
					for(Stadio s:stadi.getStadi())
						partiteOrdinate.addAll(stadi.getPartite(s.getNome()));
					creaListaPartite(partiteOrdinate);
				break;
				}
				
			}
		});
		
		pnlOrder.add(orderby);
		pnlOrder.add(ordine);
		
		pnlSelect=new JPanel();
		pnlSelect.add(pnlSelStadio);
		pnlSelect.add(pnlOrder);
		
		
		
		pnlPartite=new JPanel(new BorderLayout());
		pnlPartite.setBorder(new TitledBorder("visualizza partite"));
		pnlPartite.add(pnlSelect,BorderLayout.NORTH);
		pnlPartite.add(partiteStadio);
		
		
	}
	
	
	
		
		
		

	
	public void creaVistaPrenotazioniAcquisti(){
		prenotazioni=new JButton("Visualizza prenotazioni");
		biglietti=new JButton("Visualizza biglietti acquistati");
		listaPrenotazioni=new JPanel();
		prenotazioni.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				listaPrenotazioni.setBorder(new TitledBorder("lista prenotazioni"));
				riempiPanel();           		//CHIAMATA AL METODO PER RIEMPIRE IL PANEL ED EFFETTUARE L'AGGIORNAMENTO
				SwingUtilities.updateComponentTreeUI(finestra);
			}}
		);
		
		
		listaBiglietti=new JPanel();
		biglietti.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				riempiPanelAcquisti();
				SwingUtilities.updateComponentTreeUI(finestra);
			}
		});
		
		pnlVisualizzaP=new JPanel(new BorderLayout());
		pnlVisualizzaP.add(prenotazioni,BorderLayout.NORTH);
		
	
		scrollFramePrenotazioni = new JScrollPane(listaPrenotazioni);
		listaPrenotazioni.setAutoscrolls(true);
		pnlVisualizzaP.add(scrollFramePrenotazioni);
		
		pnlVisualizzaA=new JPanel(new BorderLayout());
		pnlVisualizzaA.add(biglietti,BorderLayout.NORTH);
		
		scrollFrameBiglietti = new JScrollPane(listaBiglietti);
		listaBiglietti.setAutoscrolls(true);
		pnlVisualizzaA.add(scrollFrameBiglietti);
		
		
		
	}

	
	
	public void create(){
		finestra.getContentPane().removeAll();
	

		creaVistaPartite();
		creaVistaPrenotazioniAcquisti();
		
		JPanel pnlContenitoreEst=new JPanel(new GridLayout(2, 1));
		pnlContenitoreEst.add(pnlVisualizzaP);
		pnlContenitoreEst.add(pnlVisualizzaA);
		
		
		finestra.add(pnlPartite,BorderLayout.CENTER);
		finestra.add(pnlContenitoreEst,BorderLayout.WEST);
		
		
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setVisible(true);
	}
		
	
	private void riempiPanel(){   
		//RIEMPIE PANNELLO LISTA PRENOTAZIONI
		listaPrenotazioni.removeAll();
		listaPrenotazioni.setLayout(new GridLayout(op.getPrenotazioni(usernameCliente).size(), 1));
		JPanel pan=new JPanel();
		for(Prenotazione pr: op.getPrenotazioni(usernameCliente)){
			JPanel p=new JPanel();
			JButton acquista=new JButton("Acquista");
			JButton cancella=new JButton("Cancella prenotazione");
			cancella.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						op.cancellaPrenotazione(pr);       //CANCELLAZIONE DI UNA PRENOTAZIONE
						Salvataggio.salva(stadi);
						riempiPanel();
					} catch (PrenotazioneNonEsistenteException e1) {
						e1.printStackTrace();
					}
				}
			}
			);
			
			acquista.addActionListener(new ActionListener(){     //ACQUISTO DI UN BIGLIETTO PRENOTATO
				public void actionPerformed(ActionEvent e){
					try {
						op.acquista(pr,pr.getId());
						Salvataggio.salva(stadi);
						riempiPanel();
					} catch (UtenteInesistenteException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			p.add(new JLabel(pr.toString()));
			p.add(acquista);
			p.add(cancella);
			listaPrenotazioni.add(p);
		}
		listaPrenotazioni.setVisible(false);
		listaPrenotazioni.setVisible(true);
		
		
		}
	
	
	private void creaListaPartite(ArrayList<PartitaProgrammata> partite){      //CREAZIONE LISTA PARTITE IN UNO STADIO
		partiteStadio.removeAll();		
		partiteStadio.setLayout(new GridLayout(partite.size(),1));
		partiteStadio.setBorder(new TitledBorder(""));
		
		
	
		for(PartitaProgrammata pa:partiteOrdinate){
			JPanel p=new JPanel();
			JButton prenotaPart=new JButton("Prenota");
			prenotaPart.addActionListener(new ActionListener(){    //LISTENER SUL PULSANTE PRENOTA
				public void actionPerformed(ActionEvent e){ 
					if(pnlStadio!=null)
					finestra.remove(pnlStadio);
					
					if((String)listastadi.getSelectedItem()==null)
					{
						Avviso a=new Avviso("seleziona stadio");
					}
					else
					{	pnlStadio=new GraficaStadio(stadi,(String)listastadi.getSelectedItem(),usernameCliente, pa,true);
					pnlStadio.setVisible(true);
					finestra.add(pnlStadio,BorderLayout.EAST);
					SwingUtilities.updateComponentTreeUI(finestra);
					}
				}
			});
			
			JButton acquistaPart=new JButton("Acquista");    //ACQUISTO DI UN BIGLIETTO SENZA PRENOTAZIONE
			acquistaPart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(pnlStadio!=null)
						finestra.remove(pnlStadio);

					if((String)listastadi.getSelectedItem()==null)
					{
						Avviso a=new Avviso("seleziona stadio");
					}
					else
					{	pnlStadio=new GraficaStadio(stadi,(String)listastadi.getSelectedItem(),usernameCliente, pa,false);
						pnlStadio.setVisible(true);
						finestra.add(pnlStadio,BorderLayout.EAST);
						SwingUtilities.updateComponentTreeUI(finestra); 
					}
				}
			});
			
			p.add(new JLabel(pa.toString()));
			p.add(prenotaPart);
			p.add(acquistaPart);
			partiteStadio.add(p);
		} 
		finestra.setVisible(true);
	}
	
	
	public void riempiPanelAcquisti(){                //RIEMPE LA LISTA DEI BIGLIETTI ACQUISTATI
		listaBiglietti.removeAll();
		try {
			listaBiglietti.setLayout(new GridLayout(op.getAcquisti(usernameCliente).size(), 1));
		} catch (UtenteInesistenteException e3) {
			e3.printStackTrace();
		}
	
		listaBiglietti.setBorder(new TitledBorder("lista biglietti"));
			try {
				for(String s:op.getAcquisti(usernameCliente)){
					listaBiglietti.add(new JLabel(s));
				}
			} catch (UtenteInesistenteException e1) {
				e1.printStackTrace();
			}
			
			listaBiglietti.setVisible(false);
			listaBiglietti.setVisible(true);
			
		}
	
}

