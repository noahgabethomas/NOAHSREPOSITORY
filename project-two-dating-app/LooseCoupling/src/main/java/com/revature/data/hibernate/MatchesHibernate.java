package com.revature.data.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.beans.Matches;
import com.revature.beans.Users;
import com.revature.data.MatchesDAO;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class MatchesHibernate implements MatchesDAO{
	private HibernateUtil hu = HibernateUtil.getInstance();

	public Integer addMatch(Matches m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(m);
			tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LogUtil.logException(e, MatchesHibernate.class);
		} finally {
			s.close();
		}
		return m.getMatchesId();
	}

	public Matches getMatchById(int id) {
		Session s = hu.getSession();
		Matches m = s.get(Matches.class, id);
		s.close();
		return m;
	}

	public Set<Matches> getAllMatches() {
		Session s = hu.getSession();
		String query = "FROM Matches";
		Query<Matches> q = s.createQuery(query, Matches.class);
		List<Matches> matchList = q.getResultList();
		Set<Matches> matchSet = new HashSet<Matches>();
		matchSet.addAll(matchList);
		s.close();
		return matchSet;
	}

	public Set<Matches> getMatchesByUser(Users u) {
		Session s = hu.getSession();
		String query = "FROM Matches m where :users = some elements(m.users)";
		Query<Matches> q = s.createQuery(query, Matches.class);
		q.setParameter("users", u);
		List<Matches> matchList = q.getResultList();
		Set<Matches> matchSet = new HashSet<Matches>();
		matchSet.addAll(matchList);
		s.close();
		return matchSet;
	}

	public void updateMatch(Matches m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(m);
			tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LogUtil.logException(e, MatchesHibernate.class);
		} finally {
			s.close();
		}

	}

	public void deleteMatch(Matches m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(m);
			tx.commit();
		} catch(Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			LogUtil.logException(e, MatchesHibernate.class);
		} finally {
			s.close();
		}
	}

}
