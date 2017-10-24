package com.vaibhav.scrape.utils;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * JobListingClusters entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.vaibhav.scrape.utils.JobListingClusters
 * @author MyEclipse Persistence Tools
 */
public class JobListingClustersDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(JobListingClustersDAO.class);
	// property constants
	public static final String URL = "url";
	public static final String CLUSTER_TYPE = "clusterType";
	public static final String CLUSTER_NAME = "clusterName";
	public static final String IS_PAGINATED = "isPaginated";

	public void save(JobListingClusters transientInstance) {
		log.debug("saving JobListingClusters instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(JobListingClusters persistentInstance) {
		log.debug("deleting JobListingClusters instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public JobListingClusters findById(java.lang.Integer id) {
		log.debug("getting JobListingClusters instance with id: " + id);
		try {
			JobListingClusters instance = (JobListingClusters) getSession()
					.get("com.vaibhav.scrape.utils.JobListingClusters", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<JobListingClusters> findByExample(JobListingClusters instance) {
		log.debug("finding JobListingClusters instance by example");
		try {
			List<JobListingClusters> results = (List<JobListingClusters>) getSession()
					.createCriteria("com.vaibhav.scrape.utils.JobListingClusters").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding JobListingClusters instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from JobListingClusters as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<JobListingClusters> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<JobListingClusters> findByClusterType(Object clusterType) {
		return findByProperty(CLUSTER_TYPE, clusterType);
	}

	public List<JobListingClusters> findByClusterName(Object clusterName) {
		return findByProperty(CLUSTER_NAME, clusterName);
	}

	public List<JobListingClusters> findByIsPaginated(Object isPaginated) {
		return findByProperty(IS_PAGINATED, isPaginated);
	}

	public List findAll() {
		log.debug("finding all JobListingClusters instances");
		try {
			String queryString = "from JobListingClusters";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public JobListingClusters merge(JobListingClusters detachedInstance) {
		log.debug("merging JobListingClusters instance");
		try {
			JobListingClusters result = (JobListingClusters) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(JobListingClusters instance) {
		log.debug("attaching dirty JobListingClusters instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(JobListingClusters instance) {
		log.debug("attaching clean JobListingClusters instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}