package com.sivalabs.jbb.services.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sivalabs.jbb.entities.Category;
import com.sivalabs.jbb.entities.Forum;
import com.sivalabs.jbb.entities.User;

/**
 * @author Siva
 *
 */
public class TestDataProvider
{
	public static Set<Category> getCategories()
	{
		Set<Category> categories = new HashSet<Category>();
		
		Category categoryJava = new Category();
		categoryJava.setCategoryName("Java");
		categoryJava.setDescription("Java Programming");
		categoryJava.setDisplayOrder(1);
		categoryJava.setForums(null);
		categoryJava.setParentCategory(null);
		
		categories.add(categoryJava);
		
			Category categoryJavaSE = new Category();
			categoryJavaSE.setCategoryName("JavaSE");
			categoryJavaSE.setDescription("CoreJava Programming");
			categoryJavaSE.setDisplayOrder(1);
			categoryJavaSE.setParentCategory(categoryJava);
					Forum forumCoreJava = new Forum();
					forumCoreJava.setForumName("CoreJava");
					forumCoreJava.setDescription("Core Java");
					forumCoreJava.setDisplayOrder(1);
					forumCoreJava.setCategory(categoryJavaSE);
					
					Forum forumThreads = new Forum();
					forumThreads.setForumName("Threads");
					forumThreads.setDescription("Threads");
					forumThreads.setDisplayOrder(2);
					forumThreads.setCategory(categoryJavaSE);
					
					categoryJavaSE.addForum(forumCoreJava);		
					categoryJavaSE.addForum(forumThreads);
					
			Category categoryJavaEE = new Category();
			categoryJavaEE.setCategoryName("JavaEE");
			categoryJavaEE.setDescription("Java Enterprise Development");
			categoryJavaEE.setDisplayOrder(2);
			categoryJavaEE.setParentCategory(categoryJava);
					Forum forumServletsJSP = new Forum();
					forumServletsJSP.setForumName("ServletsJSP");
					forumServletsJSP.setDescription("Servlets and JSP");
					forumServletsJSP.setDisplayOrder(1);
					forumServletsJSP.setCategory(categoryJavaEE);
					
					Forum forumEJB = new Forum();
					forumEJB.setForumName("EJB");
					forumEJB.setDescription("Enterprise Java Beans");
					forumEJB.setDisplayOrder(2);
					forumEJB.setCategory(categoryJavaEE);
					
					categoryJavaEE.addForum(forumServletsJSP);		
					categoryJavaEE.addForum(forumEJB);
					
			Category categoryFrameworks = new Category();
			categoryFrameworks.setCategoryName("Frameworks");
			categoryFrameworks.setDescription("Frameworks");
			categoryFrameworks.setDisplayOrder(3);
			categoryFrameworks.setParentCategory(categoryJava);
					
					Forum forumSpring = new Forum();
					forumSpring.setForumName("Spring");
					forumSpring.setDescription("Spring Framework");
					forumSpring.setDisplayOrder(1);
					forumSpring.setCategory(categoryFrameworks);
					
					Forum forumHibernate = new Forum();
					forumHibernate.setForumName("Hibernate");
					forumHibernate.setDescription("Hibernate");
					forumHibernate.setDisplayOrder(2);
					forumHibernate.setCategory(categoryFrameworks);
			
					categoryFrameworks.addForum(forumSpring);		
					categoryFrameworks.addForum(forumHibernate);
					
			categoryJava.addCategory(categoryJavaSE);
			categoryJava.addCategory(categoryJavaEE);
			categoryJava.addCategory(categoryFrameworks);
		
		
		Category categoryDatabases = new Category();
		categoryDatabases.setCategoryName("Databases");
		categoryDatabases.setDescription("Databases");
		categoryDatabases.setDisplayOrder(2);
		categoryDatabases.setForums(null);
		categoryDatabases.setParentCategory(null);
		
		categories.add(categoryDatabases);
		
			Category categorySQL = new Category();
			categorySQL.setCategoryName("SQL");
			categorySQL.setDescription("Structured Query Language");
			categorySQL.setDisplayOrder(1);
			categorySQL.setParentCategory(categoryDatabases);
			
					Forum forumOracle = new Forum();
					forumOracle.setForumName("Oracle");
					forumOracle.setDescription("Oracle Database");
					forumOracle.setDisplayOrder(1);
					forumOracle.setCategory(categorySQL);
					
					Forum forumMySQL = new Forum();
					forumMySQL.setForumName("MySQL");
					forumMySQL.setDescription("MySQL Database");
					forumMySQL.setDisplayOrder(2);
					forumMySQL.setCategory(categorySQL);
			
					categorySQL.addForum(forumOracle);		
					categorySQL.addForum(forumMySQL);
					
			Category categoryNoSQL = new Category();
			categoryNoSQL.setCategoryName("NoSQL");
			categoryNoSQL.setDescription("NoSQL Databases");
			categoryNoSQL.setDisplayOrder(2);
			categoryNoSQL.setParentCategory(categoryDatabases);
				
					Forum forumMongoDB = new Forum();
					forumMongoDB.setForumName("MongoDB");
					forumMongoDB.setDescription("MongoDB NoSQL Database");
					forumMongoDB.setDisplayOrder(1);
					forumMongoDB.setCategory(categoryNoSQL);
					
					
					Forum forumNeo4J = new Forum();
					forumNeo4J.setForumName("Neo4J");
					forumNeo4J.setDescription("Neo4J Graph Database");
					forumNeo4J.setDisplayOrder(2);
					forumNeo4J.setCategory(categoryNoSQL);
					
					categoryNoSQL.addForum(forumMongoDB);		
					categoryNoSQL.addForum(forumNeo4J);
					
				categoryDatabases.addCategory(categorySQL);
				categoryDatabases.addCategory(categoryNoSQL);
				
		return categories;
	}
	
	public static Set<Forum> getForums()
	{
		Set<Forum> forums = new HashSet<Forum>();
		
		Forum forum = new Forum();
		forum.setForumName("CoreJava");
		forum.setDescription("Core Java");
		forum.setDisplayOrder(1);
		
		
		return forums;
	}
	
	public static Set<User> getUsers()
	{
		Set<User> users = new HashSet<User>();
		
		User userAdmin = new User();
		userAdmin.setUserName("admin");
		userAdmin.setPassword("admin");
		userAdmin.setName("Administrator");
		userAdmin.setEmail("admin@gmail.com");
		userAdmin.setCreatedOn(new Date());
		
		User userSiva = new User();
		userSiva.setUserName("siva");
		userSiva.setPassword("siva");
		userSiva.setName("Siva Prasad");
		userSiva.setEmail("siva@gmail.com");
		userSiva.setCreatedOn(new Date());
		
		users.add(userAdmin);
		users.add(userSiva);
		
		return users;
		
	}
}
