/**
 * 
 */
package com.sivalabs.linkshare.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sivalabs.linkshare.entities.UserAccount;

/**
 * @author siva
 *
 */
public interface UserRepository extends JpaRepository<UserAccount, Integer>, UserRepositoryCustom
{

	@Query("select u from UserAccount u where userName=?1 and password=?2")
	UserAccount login(String userName, String pwd);
	
	UserAccount findByEmailId(String email);
	
	UserAccount findByUserName(String userName);

	@Query("select u from UserAccount u where u.userPreferences.subscribeToDailyLinkFeed = true")
	List<UserAccount> findBySubscribeToLinkFeed(boolean subscribed);
	
	@Query("select count(u) from UserAccount u where u.emailId=?1 and u.pwdRestToken=?2 and u.pwdRestTokenTimeOut >= ?3")
	Long validateResetPwdToken(String emailId, String pwdRestToken, Date date);
	

}
