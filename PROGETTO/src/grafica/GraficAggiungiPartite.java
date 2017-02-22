package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import classi.GestioneStadi;
import eccezioni.PartitaNonInseribileException;
import eccezioni.PartitaProgrammataEsistenteException;
import interfacce.OperazioniGestore;
import utils.Salvataggio;

public class GraficAggiungiPartite extends JPanel {
	
	 JTextField txtSq1,txtSq2,txtDay,txtMonth,txtYear, txtHour,txtMin;
	 JLabel lblSq1,lblSq2,lblDay,lblMonth, lblHour;
	 JComboBox<String>stadi;
	
	public GraficAggiungiPartite(GestioneStadi gs,OperazioniGestore op){
		setBorder(new TitledBorder("Aggiungi partite"));
		setLayout(new GridLayout(6, 1));
		
		
		txtSq1=new JTextField();
		txtSq1.setPreferredSize(new Dimension(200, 30));
		txtSq1.setEditable(true);
		lblSq1=new JLabel("nome prima squadra");
		
		JPanel pnlSq1=new JPanel();
		pnlSq1.add(txtSq1);
		pnlSq1.add(lblSq1);
		
		txtSq2=new JTextField();
		txtSq2.setPreferredSize(new Dimension(200, 30));
		txtSq2.setEditable(true);
		lblSq2=new JLabel("nome seconda squadra");
		
		JPanel pnlSq2=new JPanel();
		pnlSq2.add(txtSq2);
		pnlSq2.add(lblSq2);
		

		txtDay=new JTextField(String.valueOf(new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)));
		
		txtDay.setEditable(true);
		lblDay=new JLabel("/");
		
		txtMonth=new JTextField(String.valueOf(new GregorianCalendar().get(GregorianCalendar.MONTH)+1));
		
		txtMonth.setEditable(true);
		lblMonth=new JLabel("/");
		
		
		txtYear=new JTextField(String.valueOf(new GregorianCalendar().get(GregorianCalendar.YEAR)));
		
		txtYear.setEditable(true);
		
		TitledBorder titledBorder = new TitledBorder("Data");
		JPanel pnlDate=new JPanel();
		pnlDate.add(txtDay);
		pnlDate.add(lblDay);
		pnlDate.add(txtMonth);
		pnlDate.add(lblMonth);
		pnlDate.add(txtYear);
		pnlDate.setBorder(titledBorder);
		
		txtHour=new JTextField(String.valueOf(new GregorianCalendar().get(GregorianCalendar.HOUR_OF_DAY)));
		txtHour.setEditable(true);
		txtMin=new JTextField(String.valueOf(new GregorianCalendar().get(GregorianCalendar.MINUTE)));
		txtMin.setEditable(true);
		lblHour=new JLabel(":");
		JPanel pnlHour=new JPanel();
		pnlHour.add(txtHour);
		pnlHour.add(lblHour);
		pnlHour.add(txtMin);
		TitledBorder titledBorder2 = new TitledBorder("Ora");
		pnlHour.setBorder(titledBorder2);
		
		
		
		stadi=new JComboBox<>();
		for(String s:gs.getListStadi())
			stadi.addItem(s);
		
		JPanel pnlStadi=new JPanel();
		pnlStadi.add(stadi);
		
		
		
		add(pnlSq1);
		add(pnlSq2);
		add(pnlDate);
		add(pnlHour);
		add(pnlStadi);
		
		
		JButton ok=new JButton("fatto");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					op.inserisciPartita(getSq1(), getSq2(), getDate(),getStadio());
					Avviso a=new Avviso("partita inserita");
					Salvataggio.salva((GestioneStadi)op);
					
				} catch (PartitaProgrammataEsistenteException | PartitaNonInseribileException e1) {
					Avviso a=new Avviso("partita non inseribile");
				}
			
				
			}
		
		
		});
		JPanel pnlFatto=new JPanel();
		pnlFatto.add(ok);
		add(pnlFatto);
		
	}
	

	public String getSq1(){
		return txtSq1.getText();
		
	}
	
	public String getSq2(){
		return txtSq2.getText();
		
	}
	
	public GregorianCalendar getDate(){
			return new GregorianCalendar(Integer.parseInt(txtYear.getText()), Integer.parseInt(txtMonth.getText()), Integer.parseInt(txtDay.getText()), Integer.parseInt(txtHour.getText()), Integer.parseInt(txtMin.getText()));
		
	}
	public String getStadio(){
		return (String)stadi.getSelectedItem();
	}
	
	
	
	
}
