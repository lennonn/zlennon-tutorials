package com.zl.persistence.hibernate;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zl.dto.domain.User;
import com.zl.persistence.hibernate.dao.UserDAO;
@Repository
public class UserDAOImpl implements UserDAO {
	Session session;
	SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void saveRegisterInfo(User u) {
		session = sessionFactory.getCurrentSession();
		session.save(u);
	}
	@Override
	public User userLogin(User u) {
		session = sessionFactory.getCurrentSession();
		String username=u.getName();
		String password=u.getPassword();
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("from User where name=");
		sBuffer.append("'"+username+"'"+" ");
		sBuffer.append("and password=");
		sBuffer.append(" "+"'"+password+"'");
		Query query = session.createQuery(sBuffer.toString());
		User user =(User) query.uniqueResult();
		return user;
	}

}
