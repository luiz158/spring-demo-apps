/**
 * 
 */
package com.sivalabs.linkshare.services.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.linkshare.entities.Tag;
import com.sivalabs.linkshare.repositories.TagRepository;
import com.sivalabs.linkshare.services.TagService;

/**
 * @author siva
 *
 */
@Service
@Transactional
@Repository
public class JPATagService implements TagService{

	@Autowired
	private TagRepository tagRepository;
	
	@Override
	public List<Tag> searchTags(String query) {
		return tagRepository.findByLabelStartingWith(query+"%");
	}

	@Override
	public Tag createTag(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag updateTag(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public void deleteTag(Tag tag) {
		tagRepository.delete(tag);
	}
}
