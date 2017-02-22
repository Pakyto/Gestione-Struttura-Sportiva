package grafica;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import classi.GestioneStadi;

public class GraficaVisualizzaIncasso extends JPanel {
	
	private JLabel lbltot,lblstadi,lblstadio;
	private JComboBox<String> stadi;
	
	public GraficaVisualizzaIncasso(float incassoTot,GestioneStadi gs){
		
		setBorder(new TitledBorder("Incassi"));
		setLayout(new GridLayout(4, 1));
		lbltot=new JLabel("incasso totale: "+incassoTot);
		lblstadi=new JLabel("seleziona stadio");
		lblstadio=new JLabel();
		lblstadio.setVisible(false);
		
		stadi=new JComboBox<String>();
		for(String s:gs.getListStadi())
			stadi.addItem(s);
		
		add(lbltot);
		add(lblstadi);
		add(stadi);
		add(lblstadio);
		
	}
	
	public String getStadio(){
		return (String)stadi.getSelectedItem();
	}

	public void setLblIncasso(float incasso)
	{
		lblstadio.setText("incasso stadio: "+incasso);
		lblstadio.setVisible(true);
	}
}
