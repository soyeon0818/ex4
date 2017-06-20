package com.soyeon.util;

public class ListInfo {
	private Integer curPage;
	private Integer perPage;
	
	// Row
	private String search;
	private String find;
	private int startRow;
	private int lastRow;
	
	// Page
	private int curBlock;
	private int totalBlock;
	private int startNum;
	private int lastNum;
	
	
	
	
	public int getCurBlock() {
		return curBlock;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public int getStartNum() {
		return startNum;
	}
	public int getLastNum() {
		return lastNum;
	}

	
	
	
	public Integer getCurPage() {
		if(curPage == null) {
			curPage = 1;
		}
		
		return curPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public Integer getPerPage() {
		if(perPage == null) {
			perPage = 10;
		}
		
		return perPage;
	}
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}
	
	public void setRow(){
		startRow = (this.getCurPage() - 1) * this.getPerPage() + 1;
		lastRow = this.getCurPage() * this.getPerPage();
	}
	
	public void makePage(int totalCount) {
		int totalPage = 0;
		int perBlock = 5;
		
		if(totalCount % this.getPerPage() == 0){
			totalPage = totalCount / this.getPerPage();
		} else {
			totalPage = totalCount / this.getPerPage() + 1;
		}
		
		if(totalPage % perBlock == 0) {
			totalBlock = totalPage / perBlock;
		} else {
			totalBlock = totalPage / perBlock + 1;
		}
		
		if(this.getCurPage() % perBlock == 0) {
			this.curBlock = this.getCurPage() / perBlock;
		} else {
			this.curBlock = this.getCurPage() / perBlock + 1;
		}
		
		startNum = (curBlock - 1) * perBlock + 1;
		lastNum = curBlock * perBlock;
		
		if(curBlock == totalBlock) {
			lastNum = totalPage;
		}
	}
}