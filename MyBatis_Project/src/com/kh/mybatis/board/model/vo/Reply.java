package com.kh.mybatis.board.model.vo;

import java.sql.Date;

public class Reply {
	
	// 필드부
	private int replyNo; //	  REPLY_NO NUMBER PRIMARY KEY,
	private String replyContent; //	  REPLY_CONTENT VARCHAR2(400),
	private int refBoardNo; //	  REF_BNO NUMBER,
	private String replyWriter; //	  REPLY_WRITER NUMBER,
	private Date createDate; //	  CREATE_DATE DATE DEFAULT SYSDATE,
	private String status; //	  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),

	// 생성자부
	public Reply() { }

	public Reply(int replyNo, String replyContent, int refBoardNo, String replyWriter, Date createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBoardNo = refBoardNo;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
	}

	// 메소드부
	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBoardNo() {
		return refBoardNo;
	}

	public void setRefBoardNo(int refBoardNo) {
		this.refBoardNo = refBoardNo;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
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
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBoardNo=" + refBoardNo
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
}