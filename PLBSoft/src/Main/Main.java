package Main;

import conexao.HibernateConnection;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HibernateConnection hibernate = new HibernateConnection();
        hibernate.initSystem();


	}

}
