package com.vaibhav.scrape.utils;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Skills entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.vaibhav.scrape.utils.Skills
 * @author MyEclipse Persistence Tools
 */
public class SkillsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SkillsDAO.class);
	// property constants
	public static final String TITLE = "title";

	public void save(Skills transientInstance) {
		log.debug("saving Skills instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Skills persistentInstance) {
		log.debug("deleting Skills instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Skills findById(java.lang.Integer id) {
		log.debug("getting Skills instance with id: " + id);
		try {
			Skills instance = (Skills) getSession().get("com.vaibhav.scrape.utils.Skills", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Skills> findByExample(Skills instance) {
		log.debug("finding Skills instance by example");
		try {
			List<Skills> results = (List<Skills>) getSession().createCriteria("com.vaibhav.scrape.utils.Skills")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Skills instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Skills as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Skills> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findAll() {
		log.debug("finding all Skills instances");
		try {
			String queryString = "from Skills";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Skills merge(Skills detachedInstance) {
		log.debug("merging Skills instance");
		try {
			Skills result = (Skills) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Skills instance) {
		log.debug("attaching dirty Skills instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Skills instance) {
		log.debug("attaching clean Skills instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}