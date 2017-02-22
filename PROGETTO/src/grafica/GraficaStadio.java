package grafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import classi.GestioneStadi;
import classi.PartitaProgrammata;
import interfacce.OggettoProgrammato;
import interfacce.OperazioniCliente;

public class GraficaStadio extends JPanel{
	
	public JButton nord,sud,est,ovest;
	private ImageIcon immagine;
	private JLabel sfondo;
	private	PartitaProgrammata pa;
	private JPanel pnl;
	
	//CREAZIONE DELLA SAGOMA STADIO
	public GraficaStadio(GestioneStadi op,String nomeStadio,String user,PartitaProgrammata pa,Boolean prenotaAcquista){
		int capienza=op.getCapienza(nomeStadio);
		this.pa=pa;
		pnl=new JPanel(new BorderLayout());
		
		nord=new JButton("Nord");
		nord.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				openFrame(prenotaAcquista,op,nomeStadio,user,1, capienza/4);
				
			}
		});
		
		sud=new JButton("Sud");
		sud.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				openFrame(prenotaAcquista,op,nomeStadio,user,capienza/2 -1, capienza/2 -1+capienza/4);
				
			}
		});
		
		est=new JButton("Est");
		est.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				openFrame(prenotaAcquista,op,nomeStadio,user,capienza/4 +1, capienza/2 -2);
				
			}
		});
		
		ovest=new JButton("Ovest");
		ovest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				openFrame(prenotaAcquista,op,nomeStadio,user,capienza/2+capienza/4 , capienza-1);
				
			}
		});
		immagine=new ImageIcon("campo.png");
		sfondo=new JLabel(immagine);
		sfondo.setPreferredSize(new Dimension(200, 100));
		
		pnl.add(nord,BorderLayout.NORTH);
		pnl.add(sud,BorderLayout.SOUTH);
		pnl.add(est, BorderLayout.EAST);
		pnl.add(ovest, BorderLayout.WEST);
		pnl.add(sfondo,BorderLayout.CENTER);
		
		add(pnl);
	}
	
	//METODO CHE CREA I SETTORI DELLO STADIO
	public void openFrame(Boolean prenotaAcquista,GestioneStadi stadi,String nomeStadio,String username,int start,int end){
		GraficaSelezionaPosto frame=new GraficaSelezionaPosto(prenotaAcquista,stadi,nomeStadio,username,pa, start, end);
				
	}
}
