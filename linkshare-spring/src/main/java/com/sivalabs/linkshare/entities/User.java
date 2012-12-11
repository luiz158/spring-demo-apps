/**
 * 
 */
package com.sivalabs.linkshare.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Siva
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	@Column(nullable=false, unique=true)
	private String userName;
	@Column(nullable=false)
	private String password;
	@Column(name="email_id", nullable=false, unique=true)
	private String emailId;
	@Column(name="receive_email_feed", nullable = false)
	private boolean receiveEmailFeed = true;
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "postedBy")
	@XmlTransient
	private List<Link> links;
	
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getEmailId()
	{
		return emailId;
	}
	public void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	public boolean isReceiveEmailFeed()
	{
		return receiveEmailFeed;
	}
	public void setReceiveEmailFeed(boolean receiveEmailFeed)
	{
		this.receiveEmailFeed = receiveEmailFeed;
	}
	public List<Link> getLinks()
	{
		if(links==null){
			links = new ArrayList<Link>();
		}
		return links;
	}
	public void setLinks(List<Link> links)
	{
		this.links = links;
	}
	
	
}
