package com.society.model.domain;

public class MaintenacneNoteDomain {
	
	private Integer noteId;
	
	private String noteText;

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

}
