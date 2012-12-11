
package com.sivalabs.linkshare.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author siva
 */
@Embeddable
public class VoteId implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
    @NotNull
    @Column(name = "link_id")
    private int linkId;
    
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public VoteId() {
    }

    public VoteId(int linkId, int userId) {
        this.linkId = linkId;
        this.userId = userId;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) linkId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoteId)) {
            return false;
        }
        VoteId other = (VoteId) object;
        if (this.linkId != other.linkId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    
}
