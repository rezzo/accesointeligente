package org.accesointeligente.server;

import org.accesointeligente.model.Request;
import org.accesointeligente.server.robots.Robot;
import org.accesointeligente.shared.RequestStatus;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.TimerTask;

public class RequestCreationTask extends TimerTask {
	@Override
	public void run() {
		Session hibernate = HibernateUtil.getSession();

		try {
			hibernate.beginTransaction();
			Criteria criteria = hibernate.createCriteria(Request.class);
			criteria.setFetchMode("institution", FetchMode.JOIN);
			criteria.add(Restrictions.eq("status", RequestStatus.NEW));
			List<Request> newRequests = criteria.list();
			hibernate.getTransaction().commit();

			for (Request request : newRequests) {
				Robot robot = Robot.getRobot(request.getInstitution());

				if (robot != null) {
					request = robot.makeRequest(request);
					hibernate.beginTransaction();
					hibernate.update(request);
					hibernate.getTransaction().commit();
				}
			}
		} catch (Exception ex) {
			if (hibernate.isOpen() && hibernate.getTransaction().isActive()) {
				hibernate.getTransaction().rollback();
				ex.printStackTrace(System.err);
			}
		}
	}
}
