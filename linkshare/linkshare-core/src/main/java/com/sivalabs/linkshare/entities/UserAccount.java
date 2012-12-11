/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sivalabs.linkshare.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author siva
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
public class UserAccount implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    
    @NotEmpty(message="UserName Should not be empty.")
    @Column(name = "username", unique=true)
    private String userName;
    
    @NotEmpty(message="Password Should not be empty.")
    @Column(name = "password")
    private String password;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    @NotEmpty(message="EmailId Should not be empty.")
    @Column(name = "email_id", unique=true)
    private String emailId;
    
    @NotNull
    @Column(name = "disabled")
    private boolean disabled;
    
    @Column(name = "pwd_rest_token")
    private String pwdRestToken;
    
    @Column(name = "pwd_rest_token_timeout")
    private Date pwdRestTokenTimeOut;
    
	@JoinTable(name = "users_roles", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, 
        inverseJoinColumns = {
        @JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    @ManyToMany
    private List<Role> roles;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount")
    private List<Vote> votes;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postedBy")
    @OrderBy("createdOn desc")
    private List<Link> links;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="preferences_id")
    private UserPreferences userPreferences;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postedBy")
    private List<Comment> comments;

    public UserAccount() {
    }

    public UserAccount(Integer userId) {
        this.userId = userId;
    }

    public UserAccount(Integer userId, String userName, String password, String emailId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
    }
    public UserAccount(Integer userId, String userName, String password, String emailId, boolean disabled) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
        this.disabled = disabled;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public UserPreferences getUserPreferences() {
    	if(userPreferences == null){
    		userPreferences = new UserPreferences();
    	}
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    @XmlTransient
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

	public String getPwdRestToken()
	{
		return pwdRestToken;
	}

	public void setPwdRestToken(String pwdRestToken)
	{
		this.pwdRestToken = pwdRestToken;
	}

    public Date getPwdRestTokenTimeOut()
	{
		return pwdRestTokenTimeOut;
	}

	public void setPwdRestTokenTimeOut(Date pwdRestTokenTimeOut)
	{
		this.pwdRestTokenTimeOut = pwdRestTokenTimeOut;
	}
    
}
