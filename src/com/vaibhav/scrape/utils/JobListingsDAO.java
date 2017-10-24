package com.vaibhav.scrape.utils;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * JobListings entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vaibhav.scrape.utils.JobListings
 * @author MyEclipse Persistence Tools
 */
public class JobListingsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(JobListingsDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String IS_DOWNLOADED = "isDownloaded";
	public static final String IS_SKILL_PARSED = "isSkillParsed";

	public void save(JobListings transientInstance) {
		log.debug("saving JobListings instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(JobListings persistentInstance) {
		log.debug("deleting JobListings instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public JobListings findById(java.lang.Integer id) {
		log.debug("getting JobListings instance with id: " + id);
		try {
			JobListings instance = (JobListings) getSession().get("com.vaibhav.scrape.utils.JobListings", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<JobListings> findByExample(JobListings instance) {
		log.debug("finding JobListings instance by example");
		try {
			List<JobListings> results = (List<JobListings>) getSession()
					.createCriteria("com.vaibhav.scrape.utils.JobListings").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding JobListings instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from JobListings as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<JobListings> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<JobListings> findByIsDownloaded(Object isDownloaded) {
		return findByProperty(IS_DOWNLOADED, isDownloaded);
	}

	public List<JobListings> findByIsSkillParsed(Object isSkillParsed) {
		return findByProperty(IS_SKILL_PARSED, isSkillParsed);
	}

	public List findAll() {
		log.debug("finding all JobListings instances");
		try {
			String queryString = "from JobListings";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public JobListings merge(JobListings detachedInstance) {
		log.debug("merging JobListings instance");
		try {
			JobListings result = (JobListings) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(JobListings instance) {
		log.debug("attaching dirty JobListings instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(JobListings instance) {
		log.debug("attaching clean JobListings instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}