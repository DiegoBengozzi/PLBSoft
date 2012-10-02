package conexao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class HibernateConnection {

	private static SessionFactory sf;
	private static Session session;
	private static Transaction tx;

	public void initSystem() {

		AnnotationConfiguration cfg = new AnnotationConfiguration();
		sf = cfg.configure().buildSessionFactory();
		create(cfg);
	}

	public static Session getSession() {
		if (session == null)
			openConnection();
		return session;
	}

	public static void create(AnnotationConfiguration cfg) {
		SchemaExport esquema = new SchemaExport(cfg);
		esquema.execute(true, true, false, true);
	}

	public static void openConnection() {
		session = sf.openSession();
		tx = session.beginTransaction();

	}

	public void merge(Object modelo) {
		getSession().merge(modelo);
		tx.commit();
		tx = session.beginTransaction();
	}

	public void persist(Object modelo) {
		getSession().persist(modelo);
		tx.commit();
		tx = session.beginTransaction();
	}
	
	public static void commit(){
		tx.commit();
		session.clear();
		tx = session.beginTransaction();
	}

	

}
