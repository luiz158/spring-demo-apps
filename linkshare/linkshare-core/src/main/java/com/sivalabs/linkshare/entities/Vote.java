/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sivalabs.linkshare.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siva
 */
@Entity
@Table(name = "votes")
@XmlRootElement
public class Vote implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected VoteId voteId;
    
    @NotNull
    @Column(name = "vote_type")
    private char voteType;
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserAccount userAccount;
    
    @JoinColumn(name = "link_id", referencedColumnName = "link_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Link link;

    public Vote() {
    }

    public Vote(VoteId voteId) {
        this.voteId = voteId;
    }

    public Vote(VoteId voteId, char voteType) {
        this.voteId = voteId;
        this.voteType = voteType;
    }

    public Vote(int linkId, int userId) {
        this.voteId = new VoteId(linkId, userId);
    }

    public VoteId getVoteId() {
        return voteId;
    }

    public void setVotePK(VoteId voteId) {
        this.voteId = voteId;
    }

    public char getVoteType() {
        return voteType;
    }

    public void setVoteType(char voteType) {
        this.voteType = voteType;
    }

    public UserAccount getUser() {
        return userAccount;
    }

    public void setUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voteId != null ? voteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        if ((this.voteId == null && other.voteId != null) || (this.voteId != null && !this.voteId.equals(other.voteId))) {
            return false;
        }
        return true;
    }
    
}
