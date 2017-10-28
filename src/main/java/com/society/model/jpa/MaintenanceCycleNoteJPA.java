package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_cycle_note")
public class MaintenanceCycleNoteJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "note_id")
	private Integer noteId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cycle_id")
	private MaintenanceCycleJPA cycle;
	
	@Column(name = "note_text")
	private String noteText;

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public MaintenanceCycleJPA getCycle() {
		return cycle;
	}

	public void setCycle(MaintenanceCycleJPA cycle) {
		this.cycle = cycle;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
}
