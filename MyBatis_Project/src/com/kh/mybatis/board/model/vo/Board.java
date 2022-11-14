package com.kh.mybatis.board.model.vo;

import java.sql.Date;

public class Board {
	
	// 필드부
	private int boardNo; 		 //	  BOARD_NO NUMBER PRIMARY KEY,
	private String boardTitle;   //	  BOARD_TITLE VARCHAR2(100) NOT NULL,
	private String boardContent; //	  BOARD_CONTENT VARCHAR2(4000) NOT NULL,
	private String boardWriter;  //	  BOARD_WRITER NUMBER,
	private int count;			 //	  COUNT NUMBER DEFAULT 0,
	private Date createDate; 	 //	  CREATE_DATE DATE DEFAULT SYSDATE,
	private String status; 		 //	  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))

	// 생성자부
	public Board() {
		super();
	}

	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, int count, Date createDate,
			String status) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}
	
	// 메소드부
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", count=" + count + ", createDate=" + createDate + ", status="
				+ status + "]";
	}
	
}