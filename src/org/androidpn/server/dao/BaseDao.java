package org.androidpn.server.dao;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport {

	private Session session;

	public Session getSession() {
		if (session == null) {
			session = this.getSessionFactory().openSession();
		}
		return session;
	}

}
