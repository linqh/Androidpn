/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.androidpn.server.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.androidpn.server.dao.BaseDao;
import org.androidpn.server.dao.UserDao;
import org.androidpn.server.model.User;
import org.androidpn.server.service.UserNotFoundException;
import org.hibernate.Session;

/**
 * This class is the implementation of UserDAO using Spring's HibernateTemplate.
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class UserDaoHibernate extends BaseDao implements UserDao {

	public User getUser(Long id) {
		return (User) this.getSession().get(User.class, id);
	}

	public User saveUser(User user) {
		Session session = this.getSession();
		user.setCreatedDate(new Date());
		session.saveOrUpdate(user);
		session.flush();
		return user;
	}

	public void removeUser(Long id) {
		Session session = this.getSession();
		session.delete(getUser(id));
		session.flush();
	}

	public boolean exists(Long id) {
		User user = (User) this.getSession().get(User.class, id);
		return user != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return this
				.getSessionFactory()
				.openSession()
				.createSQLQuery(
						"SELECT * FROM apn_user a ORDER BY a.created_date DESC")
				.addEntity(User.class).list();
	}

	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) throws UserNotFoundException {
		List<User> users = (List<User>)getSession().createSQLQuery("SELECT * FROM apn_user a WHERE a.`name` = '"+username+"'");
		if (users == null || users.isEmpty()) {
			throw new UserNotFoundException("User '" + username + "' not found");
		} else {
			return (User) users.get(0);
		}
	}

	// @SuppressWarnings("unchecked")
	// public User findUserByUsername(String username) {
	// List users = getHibernateTemplate().find("from User where username=?",
	// username);
	// return (users == null || users.isEmpty()) ? null : (User) users.get(0);
	// }

}
