/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sivalabs.linkshare.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author siva
 */
@Entity
@Table(name = "user_preferences")
@XmlRootElement
public class UserPreferences implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "preference_id")
    private Integer preferenceId;

    @NotNull
    @Column(name = "subscribe_to_daily_link_feed")
    private boolean subscribeToDailyLinkFeed = false;

    public UserPreferences() {
    }

    public Integer getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(Integer preferenceId) {
		this.preferenceId = preferenceId;
	}

    public boolean getSubscribeToDailyLinkFeed() {
        return subscribeToDailyLinkFeed;
    }

    public void setSubscribeToDailyLinkFeed(boolean subscribeToDailyLinkFeed) {
        this.subscribeToDailyLinkFeed = subscribeToDailyLinkFeed;
    }
    
}
