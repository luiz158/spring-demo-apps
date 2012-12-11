package com.sivalabs.jbb.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Siva
 *
 */
@Entity
@Table(name="categories")
public class Category
{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="category_name", nullable=false, unique=true)
	private String categoryName;
	
	@ManyToOne()
	@JoinColumn(name="parent_category_id")
    private Category parentCategory;
	
    @OneToMany(cascade=CascadeType.ALL, mappedBy="parentCategory")
    private Set<Category> subCategories;
	
	private String description;
	
	@Column(name="display_order")
	private int displayOrder;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="category")
	private Set<Forum> forums = new HashSet<Forum>();
	
	public Integer getCategoryId()
	{
		return categoryId;
	}
	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}
	public String getCategoryName()
	{
		return categoryName;
	}
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public int getDisplayOrder()
	{
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder)
	{
		this.displayOrder = displayOrder;
	}
	@JsonIgnore
	public Set<Forum> getForums()
	{
		if(forums == null){
			forums = new HashSet<Forum>();
		}
		return forums;
	}
	public void setForums(Set<Forum> forums)
	{
		this.forums = forums;
	}
	@JsonIgnore
	public Category getParentCategory()
	{
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory)
	{
		this.parentCategory = parentCategory;
	}
	@JsonIgnore
	public Set<Category> getSubCategories()
	{
		if(subCategories == null){
			subCategories = new HashSet<Category>();
		}
		return subCategories;
	}
	public void setSubCategories(Set<Category> subCategories)
	{
		this.subCategories = subCategories;
	}
	public void addCategory(Category category)
	{
		getSubCategories().add(category);
	}
	public void addForum(Forum forum)
	{
		this.getForums().add(forum);
	}
	
	
}
