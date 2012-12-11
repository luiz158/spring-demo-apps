/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sivalabs.linkshare.entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author siva
 */
@Entity
@Table(name = "links")
@XmlRootElement
public class Link implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "link_id")
    private Integer linkId;
    
    @NotEmpty(message="Title Should not be empty.")
    @Column(name = "title")
    private String title;
    
    @NotEmpty(message="URL Should not be empty.")
    @Column(name = "url")
    private String url;
    
    @Size(max = 512, message="Description should be maximum of 512 chars.")
    @Column(name = "description")
    private String description;
    
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    
    
    @JoinTable(name = "links_tags", joinColumns = {
        @JoinColumn(name = "link_id", referencedColumnName = "link_id")}, 
        inverseJoinColumns = {
        @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    @ManyToMany()
    private List<Tag> tags;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "link")
    private List<Vote> votes;
    
    @JoinColumn(name = "posted_by", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserAccount postedBy;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "link")
    private List<Comment> comments;

    public Link() {
    }

    public Link(Integer linkId) {
        this.linkId = linkId;
    }

    public Link(Integer linkId, String title, String url) {
        this.linkId = linkId;
        this.title = title;
        this.url = url;
        this.createdOn = new Date();
    }
    public Link(Integer linkId, String title, String url, Date createdOn) {
        this.linkId = linkId;
        this.title = title;
        this.url = url;
        this.createdOn = createdOn;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @XmlTransient
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Vote> getVotes() {
    	if(votes == null){
    		votes = new ArrayList<Vote>();
    	}
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public UserAccount getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(UserAccount postedBy) {
        this.postedBy = postedBy;
    }

    public List<Comment> getComments() {
    	if(comments == null){
    		comments = new ArrayList<Comment>();
    	}
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getUpVotesCount()
    {
    	int count = 0;
    	if(getVotes()!=null){
    		for (Vote vote : votes) 
    		{
				if(vote.getVoteType()=='U'){
					count++;
				}
			}
    	}
    	return count;
    }
    
    public int getDownVotesCount()
    {
    	int count = 0;
    	if(getVotes()!=null){
    		for (Vote vote : votes) 
    		{
				if(vote.getVoteType()=='D'){
					count++;
				}
			}
    	}
    	return count;
    }
    
    public List<UserAccount> getUpVoters()
    {
    	List<UserAccount> userAccounts = new ArrayList<UserAccount>();
    	
    	if(getVotes()!=null){
    		for (Vote vote : votes) 
    		{
				if(vote.getVoteType()=='U'){
					userAccounts.add(vote.getUser());
				}
			}
    	}
    	return userAccounts;
    }
    
    public List<UserAccount> getDownVoters()
    {
    	List<UserAccount> userAccounts = new ArrayList<UserAccount>();
    	
    	if(getVotes()!=null){
    		for (Vote vote : votes) 
    		{
				if(vote.getVoteType()=='D'){
					userAccounts.add(vote.getUser());
				}
			}
    	}
    	return userAccounts;
    }
    
}
