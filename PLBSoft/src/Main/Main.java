package Main;

import GUI.JanelaPrincipalGUI;
import conexao.HibernateConnection;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//<<<<<<< HEAD
		
		HibernateConnection hibernate = new HibernateConnection();
        hibernate.initSystem();
        new JanelaPrincipalGUI().open();
        
//=======
//>>>>>>> github/master
//
//		HibernateConnection hibernate = new HibernateConnection();
//		hibernate.initSystem();
//		new JanelaPrincipalGUI().open();

	}

}
