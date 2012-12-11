/**
 * 
 */
package com.sivalabs.core.pagination;


/**
 * @author siva
 *
 */
public class PaginatedResult<T> 
{
	private int totalRecords;
	private T results;
	private int currentPage;
	private int recordsPerPage;
	private int totalPages;
	
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public T getResults() {
		return results;
	}
	public void setResults(T results) {
		this.results = results;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	
}
