package com.sivalabs.springdemo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */

@Entity
@Table(name = "preferences")
@XmlRootElement
public class Preference implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_id")
    private Integer preferenceId;
    
    private String name;
    
    private String  value;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
	public Integer getPreferenceId()
	{
		return preferenceId;
	}
	public void setPreferenceId(Integer preferenceId)
	{
		this.preferenceId = preferenceId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}

}
