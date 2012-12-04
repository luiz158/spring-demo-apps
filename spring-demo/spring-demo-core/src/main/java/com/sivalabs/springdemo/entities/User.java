package com.sivalabs.springdemo.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Siva
 */
@Entity
@Table(name = "users")
@XmlRootElement
public class User implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "username", unique=true, nullable=false)
    private String userName;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "firstname", nullable=false)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
    
    @Column(name = "email", unique=true, nullable=false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
    private Set<Role> roles = new HashSet<Role>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="creadtedBy")
    private Set<Link> submittedLinks = new HashSet<Link>();
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "users_tags",
	    joinColumns = {@JoinColumn(name="user_id")},
	    inverseJoinColumns = {@JoinColumn(name="tag_id")}
    )
    private Set<Tag> favoriteTags = new HashSet<Tag>();
    
    @OneToMany(mappedBy="user")
    private Set<Preference> preferences = new HashSet<Preference>();
    
    public User() 
    {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

	public User(Integer userId, String userName, String password,
			String firstName, String lastName, String email, Gender gender)
	{
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
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

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public Gender getGender()
	{
		return gender;
	}
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public Set<Link> getSubmittedLinks()
	{
		return submittedLinks;
	}

	public void setSubmittedLinks(Set<Link> submittedLinks)
	{
		this.submittedLinks = submittedLinks;
	}

	public Set<Tag> getFavoriteTags()
	{
		return favoriteTags;
	}

	public void setFavoriteTags(Set<Tag> favoriteTags)
	{
		this.favoriteTags = favoriteTags;
	}

	public Set<Preference> getPreferences()
	{
		return preferences;
	}
	public void setPreferences(Set<Preference> preferences)
	{
		this.preferences = preferences;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

}
