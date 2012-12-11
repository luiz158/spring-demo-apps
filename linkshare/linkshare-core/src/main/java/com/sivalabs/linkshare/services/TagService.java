/**
 * 
 */
package com.sivalabs.linkshare.services;

import java.util.List;

import com.sivalabs.linkshare.entities.Tag;

/**
 * @author siva
 *
 */
public interface TagService {

	public List<Tag> searchTags(String query);

	public Tag createTag(Tag tag);

	public Tag updateTag(Tag tag);

	public void deleteTag(Tag tag);
	
}
