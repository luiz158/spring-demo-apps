/**
 * 
 */
package com.sivalabs.linkshare.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sivalabs.linkshare.entities.Tag;
import com.sivalabs.linkshare.services.TagService;

/**
 * @author siva
 *
 */
@Controller
public class TagController {

	@Autowired
	private TagService tagService;
	
	@RequestMapping(value="/admin/tags", produces="application/json")
	public String getTags(Model model, @RequestParam(value="query", required=false, defaultValue="") String query) 
	{
		List<Tag> tags = this.tagService.searchTags(query);
		for (Tag tag : tags) {
			tag.setLinks(null);//To avoid LazyLoading Exception while Marshalling into JSON format
		}
		model.addAttribute("TAGS_KEY", tags);
		return "admin/tags";		
	}
	
	@ResponseBody
	@RequestMapping(value="/searchTagsJson", produces="application/json")
	public List<Tag> searchTags(@RequestParam(value="query", required=false, defaultValue="") String query) 
	{
		List<Tag> tags = this.tagService.searchTags(query);
		for (Tag tag : tags) {
			tag.setLinks(null);//To avoid LazyLoading Exception while Marshalling into JSON format
		}
		return tags;		
	}
	
	//@ResponseBody
	@RequestMapping(value="/admin/createTag", 
					method=RequestMethod.POST)
	public String createTag(Model model, Tag tag) 
	{
		tag = this.tagService.createTag(tag);
		model.addAttribute("TAG_KEY", tag);
		return "redirect:/admin/tags";	
	}
	
	//@ResponseBody
	@RequestMapping(value="/admin/updateTag", 
			method=RequestMethod.POST)
	public String updateTag(Tag tag) 
	{
		tag = this.tagService.updateTag(tag);
		return "redirect:/admin/tags";	
	}
	
	//@ResponseBody
	@RequestMapping(value="/admin/deleteTag", 
			method=RequestMethod.POST)
	public String deleteTag(Tag tag) 
	{
		this.tagService.deleteTag(tag);
		return "redirect:/admin/tags";	
	}
}
