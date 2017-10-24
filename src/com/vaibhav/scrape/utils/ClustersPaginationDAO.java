package com.vaibhav.scrape.utils;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClustersPagination entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vaibhav.scrape.utils.ClustersPagination
 * @author MyEclipse Persistence Tools
 */
public class ClustersPaginationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ClustersPaginationDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String IS_SCRAPED = "isScraped";

	public void save(ClustersPagination transientInstance) {
		log.debug("saving ClustersPagination instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClustersPagination persistentInstance) {
		log.debug("deleting ClustersPagination instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClustersPagination findById(java.lang.Integer id) {
		log.debug("getting ClustersPagination instance with id: " + id);
		try {
			ClustersPagination instance = (ClustersPagination) getSession()
					.get("com.vaibhav.scrape.utils.ClustersPagination", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ClustersPagination> findByExample(ClustersPagination instance) {
		log.debug("finding ClustersPagination instance by example");
		try {
			List<ClustersPagination> results = (List<ClustersPagination>) getSession()
					.createCriteria("com.vaibhav.scrape.utils.ClustersPagination").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ClustersPagination instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ClustersPagination as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ClustersPagination> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<ClustersPagination> findByIsScraped(Object isScraped) {
		return findByProperty(IS_SCRAPED, isScraped);
	}

	public List findAll() {
		log.debug("finding all ClustersPagination instances");
		try {
			String queryString = "from ClustersPagination";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClustersPagination merge(ClustersPagination detachedInstance) {
		log.debug("merging ClustersPagination instance");
		try {
			ClustersPagination result = (ClustersPagination) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClustersPagination instance) {
		log.debug("attaching dirty ClustersPagination instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClustersPagination instance) {
		log.debug("attaching clean ClustersPagination instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}