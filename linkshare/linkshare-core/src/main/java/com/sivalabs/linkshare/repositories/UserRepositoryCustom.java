/**
 * 
 */
package com.sivalabs.linkshare.repositories;

import java.util.Date;


/**
 * @author siva
 *
 */
public interface UserRepositoryCustom 
{

	int changePwd(Integer userId, String oldPwd, String newPwd);
	
	int updatePwdRestToken(String emailId, String token, Date date);
	
	public boolean resetPwd(String emailId, String newPwd);
}
