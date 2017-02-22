package grafica;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classi.GestioneStadi;
import eccezioni.DatiNonValidiException;
import eccezioni.UtenteEsistenteException;
import interfacce.Sconto;
import utils.Salvataggio;
import utils.Tipologia;

public class Login {

	GestioneStadi stadi;
	JFrame finestra;
	JPanel contenitore,pnlUser,pnlReg,pnlTipo,pnlAccedi,pnlPassword;
	JLabel lblUser,lblPass;
	JButton btnAccedi,btnRegistrati;
	JTextField txtUsername;
	JPasswordField txtPassword;
	JComboBox<Tipologia> tipo;
	public static final int LARGHEZZA = 250;
	public static final int ALTEZZA = 300;
	
	public Login(GestioneStadi stadi){
		this.stadi=stadi;
		finestra=new JFrame("Login");
		finestra.setBounds(10, 20, LARGHEZZA, ALTEZZA);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setMinimumSize(new Dimension(LARGHEZZA, ALTEZZA));
		
		//dichiarazione
		
		contenitore=new JPanel(new GridLayout(5, 1));
		pnlUser=new JPanel();
		lblUser=new JLabel("username");
		txtUsername=new JTextField();
		txtUsername.setPreferredSize(new Dimension(200, 30));
		txtUsername.setToolTipText("username");
		pnlUser.add(lblUser);
		pnlUser.add(txtUsername);
		
		
		
		pnlPassword=new JPanel();
		lblPass=new JLabel("password");
		txtPassword=new JPasswordField();
		txtPassword.setEditable(true);
		txtPassword.setPreferredSize(new Dimension(200, 30));
		pnlPassword.add(lblPass);
		pnlPassword.add(txtPassword);
		
		pnlTipo=new JPanel();
		tipo=new JComboBox<Tipologia>();
		tipo.addItem(Tipologia.Normale);
		tipo.addItem(Tipologia.Bambino);
		tipo.addItem(Tipologia.Studente);
		tipo.addItem(Tipologia.Pensionato);
		tipo.setVisible(false);
		pnlTipo.add(tipo);
		
		
		pnlAccedi=new JPanel();
		btnAccedi=new JButton("Accedi");
		
		btnAccedi.addKeyListener(new KeyListener(){  //LISTENER QUANDO VIENE PREMUTO INVIO DA TASTIERA PER ACCEDERE
			
			public void keyPressed(KeyEvent e) {
				try {
					stadi.accedi(txtUsername.getText().toLowerCase(), String.valueOf(txtPassword.getPassword()).toLowerCase());
					if(stadi.getUtente(txtUsername.getText()).getTipologia().toLowerCase().equals("gestore"))
						{	new GraficaGestore(stadi,stadi.getUtente(txtUsername.getText()).getOperazioni(), txtUsername.getText(),stadi.getListStadi());
							finestra.dispose();
						}
					else
						{new GraficaCliente(stadi,stadi.getUtente(txtUsername.getText()).getOperazioni(), stadi.getListStadi(),txtUsername.getText());
						finestra.dispose();
						}
				} catch (DatiNonValidiException e1) {
					Avviso a=new Avviso("username o password non validi");
				}
			}

			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}	
		});
		
		
		btnAccedi.addActionListener(new ActionListener() {   //LISTENER SUL CLICK DEL PULSANTE ACCEDI
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stadi.accedi(txtUsername.getText().toLowerCase(), String.valueOf(txtPassword.getPassword()).toLowerCase());
					if(stadi.getUtente(txtUsername.getText()).getTipologia().toLowerCase().equals("gestore"))
						{	new GraficaGestore(stadi,stadi.getUtente(txtUsername.getText()).getOperazioni(), txtUsername.getText(),stadi.getListStadi());
							finestra.dispose();
						}
					else
						{
						new GraficaCliente(stadi,stadi.getUtente(txtUsername.getText()).getOperazioni(), stadi.getListStadi(),txtUsername.getText());
						finestra.dispose();
						}
				} catch (DatiNonValidiException e1) {
					Avviso a=new Avviso("username o password non validi");
				}
				
			}
		});
		pnlAccedi.add(btnAccedi);
		
		pnlReg=new JPanel();
		btnRegistrati=new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(!tipo.isVisible())
				tipo.setVisible(true);
				else
					try {
						stadi.registraUtente(txtUsername.getText(), String.valueOf(txtPassword.getPassword()), (Tipologia)tipo.getSelectedItem());
						Avviso f=new Avviso("Registrazione effettuata!");
					} catch (UtenteEsistenteException e1) {
						Avviso a=new Avviso("utente già registrato");
						txtUsername.setText("");
						txtPassword.setText("");
						tipo.setSelectedItem(Tipologia.Normale);
					}
			
					Salvataggio.salva(stadi);        //SALVATAGGIO DI UN UTENTE AD OGNI REGISTRAZIONE
			}
		});
		
		
		
		pnlReg.add(btnRegistrati);
		contenitore.add(pnlUser);
		contenitore.add(pnlPassword);
		contenitore.add(pnlTipo);
		contenitore.add(pnlAccedi);
		contenitore.add(pnlReg);
		
		finestra.add(contenitore);
		finestra.setVisible(true);
	}
	

	
}