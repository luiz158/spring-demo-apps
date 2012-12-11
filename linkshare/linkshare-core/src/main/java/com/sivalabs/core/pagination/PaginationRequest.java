/**
 * 
 */
package com.sivalabs.core.pagination;

/**
 * @author siva
 *
 */
public class PaginationRequest {
	public static final int DEFAULT_RECORDS_PER_PAGE = 10;
	
	private int page;
	private int recordsPerPage = DEFAULT_RECORDS_PER_PAGE;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	
}
