/**
 * 
 */
package com.sivalabs.linkshare.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Siva
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LinksFeed
{
	private List<LinkFeedEntry> entries = new ArrayList<LinkFeedEntry>();
	
	public List<LinkFeedEntry> getEntries()
	{
		return entries;
	}
	public void setEntries(List<LinkFeedEntry> entries)
	{
		this.entries = entries;
	}
	public void addEntry(LinkFeedEntry entry)
	{
		this.entries.add(entry);
	}
}
