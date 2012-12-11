/**
 * 
 */
package com.sivalabs.linkshare.services.jpa;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.core.email.Email;
import com.sivalabs.core.email.EmailService;
import com.sivalabs.core.logging.Slf4JLogger;
import com.sivalabs.linkshare.LinkShareException;
import com.sivalabs.linkshare.entities.UserAccount;
import com.sivalabs.linkshare.repositories.UserRepository;
import com.sivalabs.linkshare.services.UserAccountService;
import com.sivalabs.linkshare.utils.CommonUtils;

/**
 * @author siva
 *
 */
@Service
@Transactional
public class JPAUserAccountService implements UserAccountService 
{
	@Slf4JLogger
	private Logger logger;
	
	private UserRepository userRepository;
	private EmailService  emailService;
	
	@Autowired
	public JPAUserAccountService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setEmailService(EmailService emailService)
	{
		this.emailService = emailService;
	}
	
	@Override
	public UserAccount login(String userName, String pwd) {
		logger.debug("********************login()**********************");
		System.err.println(this.getClass()+":"+this.hashCode()+":"+this.toString());
		return userRepository.login(userName, pwd);
	}

	
	@Override
	public UserAccount createUser(UserAccount userAccount) {
		logger.debug("********************createUser()**********************");
		UserAccount userWithSameUserName = userRepository.findByUserName(userAccount.getUserName());
		if(userWithSameUserName !=null){
			throw new LinkShareException("UserName "+userAccount.getUserName()+" already in use.");
		}
		UserAccount userWithSameEmail = userRepository.findByEmailId(userAccount.getEmailId());
		if(userWithSameEmail !=null){
			throw new LinkShareException("Email "+userAccount.getEmailId()+" already in use.");
		}
		return userRepository.save(userAccount);
	}

	
	@Override
	public UserAccount getUser(Integer userId) {
		return userRepository.findOne(userId);
	}

	
	@Override
	public UserAccount updateUser(UserAccount userAccount) {
		return userRepository.save(userAccount);
	}

	
	@Override
	public boolean changePwd(Integer userId, String oldPwd, String newPwd) {
		boolean changed = false;
		int count = this.userRepository.changePwd(userId, oldPwd, newPwd);
		changed = count>0;
		return changed;
	}	

	@Override
	public boolean forgotPwd(String emailId)
	{
		String token = this.updatePwdRestToken(emailId);
		
		Email email = new Email();
		email.setFrom("sivaprasadreddy.k@gmail.com");
		email.setTo(emailId);
		email.setSubject("Password Recovery for LinkShare");
		email.setText("<h3>Click <a href='http://localhost:8080/linkshare/resetPwd.htm?emailId="
						+emailId+"&pwdRestToken="+token+"'>Reset Password</a> to reset your LinkShare password<h3>");
		try
		{
			emailService.sendEmail(email );
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public String updatePwdRestToken(String emailId)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 30);
		Date date = calendar.getTime();
		String token = CommonUtils.generateRandomString(emailId.length());
		int count = this.userRepository.updatePwdRestToken(emailId, token, date);
		if(count < 1){
			throw new LinkShareException("No UserAccount found with the given Email Id");
		}
		return token;
	}
	@Override
	public boolean validateResetPwdToken(String emailId, String pwdRestToken)
	{
		Date date = new Date();
		Long count = this.userRepository.validateResetPwdToken(emailId, pwdRestToken, date);
		return (count.intValue() > 0);
		
	}
	@Override
	public boolean resetPwd(String emailId, String newPwd)
	{
		return this.userRepository.resetPwd(emailId, newPwd);
	}

	@Override
	public UserAccount getUserByUserName(String userName)
	{
		return this.userRepository.findByUserName(userName);
	}

}
