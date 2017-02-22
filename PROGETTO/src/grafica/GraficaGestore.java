package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import classi.GestioneStadi;
import classi.PartitaProgrammata;
import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
import eccezioni.StadioEsistentException;
import interfacce.OggettoProgrammato;
import interfacce.OperazioniGestore;
import utils.Caricamento;
import utils.Comparatore;
import utils.Salvataggio;

public class GraficaGestore {
	public static final int LARGHEZZA = 600;
	public static final int ALTEZZA = 500;
	private OperazioniGestore op;
	private JFrame finestra;
	private JPanel contenitore,contenitorePrincipale;
	private GraficAggiungiPartite addP;
	private JMenu menu,menuVisualizza,menuModifica;
	private JMenuItem item,addStadi,showIncasso,showList,modCapienza,modPrezzo,modSconto;
	private JComboBox<String> ordine;
	private GestioneStadi gs;
	private JLabel lblOrdine;
	
	public GraficaGestore(GestioneStadi s,OperazioniGestore op,String nome,ArrayList<String>stadi)
	{	this.gs=s;
		this.op=op;
		finestra=new JFrame("Gestore "+nome);
		finestra.setBounds(10, 20, LARGHEZZA, ALTEZZA);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setMinimumSize(new Dimension(LARGHEZZA, ALTEZZA));
		
		
		
		contenitorePrincipale=new JPanel();
		contenitore=new JPanel();
		
		//menu aggiungi
		menu=new JMenu("aggiungi");
		item=new JMenuItem("Aggiungi partite");
		addStadi=new JMenuItem("Aggiungi stadio");
		
		addStadi.addActionListener(new ActionListener() {           
			
			public void actionPerformed(ActionEvent e) {
				gs=Caricamento.Carica();
				GraficAggiungiStadio pnl=new GraficAggiungiStadio(op);     //INSERIMENTO DI UNO STADIO
				contenitorePrincipale.removeAll();
				contenitore.removeAll();
				contenitorePrincipale.add(pnl);
				contenitore.add(contenitorePrincipale);
				finestra.add(contenitore);
				SwingUtilities.updateComponentTreeUI(finestra);
			}
		});
		
		item.addActionListener(new ActionListener() {     //INSERIMENTO DI UNA PARTITA
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gs=Caricamento.Carica();
				addP=new GraficAggiungiPartite(gs,op);
				contenitorePrincipale.removeAll();
				contenitore.removeAll();
				contenitorePrincipale.add(addP);
				contenitore.add(contenitorePrincipale);
				finestra.add(contenitore);
				SwingUtilities.updateComponentTreeUI(finestra);
			}
		});
		
		menu.add(item);
		menu.add(addStadi);
		
		//menu visualizza
		
		menuVisualizza=new JMenu("visualizza");
		showIncasso=new JMenuItem("visualizza incasso");
		showIncasso.addActionListener(new ActionListener() {    //LISTENER SULLA VISUALIZZAZIONE DELL'INCASSO
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gs=Caricamento.Carica();        //CARICAMENTO LISTA STADI DAL FILE
				GraficaVisualizzaIncasso incasso=new GraficaVisualizzaIncasso(op.getIncassoTotale(),gs);
				contenitorePrincipale.removeAll();
				contenitore.removeAll();
				contenitorePrincipale.add(incasso);
				contenitore.add(contenitorePrincipale);
			
				JButton ok=new JButton("visualizza");
				ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
							incasso.setLblIncasso(op.getIncassoStadio(incasso.getStadio()));
						 
						
					}
				
				
				});
				contenitore.add(ok);
				finestra.add(contenitore);
				SwingUtilities.updateComponentTreeUI(finestra);
				
			}
		});
		
		menuVisualizza.add(showIncasso);		
		
		
		showList=new JMenuItem("visualizza partite");
		showList.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				
				lblOrdine=new JLabel("ordina per: ");
				ordine=new JComboBox<String>();
				ordine.addItem("capienza stadi");
				ordine.addItem("data");
				JPanel pnlOrdine=new JPanel();
				pnlOrdine.add(lblOrdine);
				pnlOrdine.add(ordine);
				JPanel pnlLista=new JPanel();
				ordine.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						pnlLista.removeAll();
						ArrayList<PartitaProgrammata> partite=op.getPartite();
						if(ordine.getSelectedIndex()==1)
							Collections.sort(partite,new Comparatore.DateComparator());
						pnlLista.setLayout(new GridLayout(partite.size(), 1));
						for(OggettoProgrammato p:partite)
						{
							JButton b=new JButton(p.getTitle());
							pnlLista.add(b);
						}
						SwingUtilities.updateComponentTreeUI(finestra);
					}
					
				});
				
				JPanel pnl=new JPanel(new GridLayout(2, 1));
				pnl.setBorder(new TitledBorder("partite programmate"));
				pnl.add(pnlOrdine);
				pnl.add(pnlLista);
				
				contenitorePrincipale.removeAll();
				contenitore.removeAll();
				contenitorePrincipale.add(pnl);
				contenitore.add(contenitorePrincipale);
				finestra.add(contenitore);
				SwingUtilities.updateComponentTreeUI(finestra);
			
			}
		});
		
		
		menuVisualizza.add(showList);
	
		//menu imposta prezzo
		menuModifica=new JMenu("modifica");
		modPrezzo=new JMenuItem("imposta prezzo");
		modPrezzo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gs=Caricamento.Carica();                  //CARICAMENTO LISTA STADI DAL FILE
				GraficaSetPrezzo set=new GraficaSetPrezzo(op,gs);
				contenitorePrincipale.removeAll();
				contenitore.removeAll();
				contenitorePrincipale.add(set);
				contenitore.add(contenitorePrincipale);
			
				
			
				finestra.add(contenitore);
				SwingUtilities.updateComponentTreeUI(finestra);
								
			}
		});
	
		// MODIFICA DELLA CAPIENZA
		modCapienza=new JMenuItem("modifica capienza");
		modCapienza.addActionListener(new ActionListener() {
	
	
	public void actionPerformed(ActionEvent e) {
		gs=Caricamento.Carica();                //CARICAMENTO LISTA STADI DAL FILE
		GraficaSetCapienza pnlC=new GraficaSetCapienza(op,gs);
		contenitorePrincipale.removeAll();
		contenitore.removeAll();
		contenitorePrincipale.add(pnlC);
		contenitore.add(contenitorePrincipale);
		
		finestra.add(contenitore);
		SwingUtilities.updateComponentTreeUI(finestra);
		
	}
});
	
			modSconto=new JMenuItem("modifica sconto");
			modSconto.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					GraficaSetSconto pnlC=new GraficaSetSconto(op);
					contenitorePrincipale.removeAll();
					contenitore.removeAll();
					contenitorePrincipale.add(pnlC);
					contenitore.add(contenitorePrincipale);
					
					finestra.add(contenitore);
					SwingUtilities.updateComponentTreeUI(finestra);
					
				}
			});
		
			menuModifica.add(modPrezzo);
			menuModifica.add(modCapienza);
			menuModifica.add(modSconto);
			JMenuBar menubar=new JMenuBar();
			menubar.add(menu);
			menubar.add(menuVisualizza);
			menubar.add(menuModifica);
		
			finestra.setJMenuBar(menubar);
			finestra.setVisible(true);
		}

}
