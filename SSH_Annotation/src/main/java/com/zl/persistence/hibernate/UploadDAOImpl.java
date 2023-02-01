package com.zl.persistence.hibernate;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zl.dto.domain.Category;
import com.zl.dto.domain.FileSource;
import com.zl.persistence.hibernate.dao.UploadDAO;
@Repository("uploadDAO")
public class UploadDAOImpl implements UploadDAO {
	@Autowired
	SessionFactory sessionFactory;
	Session session ;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void saveFileInfo(FileSource f) {
		session = sessionFactory.getCurrentSession();
		session.save(f);
	}
	@Override
	public List<Category> getCategoryAll() {
		session=sessionFactory.getCurrentSession();
		String hql= "from Category";
		Query query = session.createQuery(hql);
		List<Category> categoryList = query.list();
		return categoryList;
	}

}
