/**
 * 
 */
package com.sivalabs.linkshare.services;

import com.sivalabs.linkshare.entities.UserAccount;

/**
 * @author siva
 *
 */
public interface UserAccountService 
{
	
	public UserAccount login(String userName, String pwd);
	public UserAccount	createUser(UserAccount userAccount);
	public UserAccount getUser(Integer userId);
	public UserAccount updateUser(UserAccount userAccount);
	public boolean changePwd(Integer userId, String oldPwd, String newPwd);
	public boolean forgotPwd(String emailId);
	public String updatePwdRestToken(String emailId);
	public boolean validateResetPwdToken(String emailId, String pwdRestToken);
	public boolean resetPwd(String emailId, String newPwd);
	public UserAccount getUserByUserName(String username);
	
}
