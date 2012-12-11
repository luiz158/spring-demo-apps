/**
 * 
 */
package com.sivalabs.linkshare.repositories;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author siva
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int changePwd(Integer userId, String oldPwd, String newPwd) {
		Query query = em.createQuery("update UserAccount u set u.password=?1 where u.userId=?2 and u.password=?3");
		query.setParameter(1, newPwd);
		query.setParameter(2, userId);
		query.setParameter(3, oldPwd);
		return query.executeUpdate();
	}

	@Override
	public int updatePwdRestToken(String emailId, String token, Date date)
	{
		Query query = em.createQuery("update UserAccount u set u.pwdRestToken=?1, u.pwdRestTokenTimeOut=?2 where u.emailId=?3");
		query.setParameter(1, token);
		query.setParameter(2, date);
		query.setParameter(3, emailId);
		return query.executeUpdate();
		
	}

	@Override
	public boolean resetPwd(String emailId, String newPwd)
	{
		Query query = em.createQuery("update UserAccount u set u.password=?1, u.pwdRestToken=null, u.pwdRestTokenTimeOut=null where u.emailId=?2");
		query.setParameter(1, newPwd);
		query.setParameter(2, emailId);
		
		return (query.executeUpdate() > 0);
	}

}
