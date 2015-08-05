package com.molavec.iotags.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the digital_data database table.
 * 
 */
@Entity
@Table(name="digital_data")
@NamedQuery(name="DigitalData.findAll", query="SELECT d FROM DigitalData d")
public class DigitalData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DigitalDataPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private byte value;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="tag")
	private Tag tagBean;

	public DigitalData() {
	}

	public DigitalDataPK getId() {
		return this.id;
	}

	public void setId(DigitalDataPK id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public byte getValue() {
		return this.value;
	}

	public void setValue(byte value) {
		this.value = value;
	}

	public Tag getTagBean() {
		return this.tagBean;
	}

	public void setTagBean(Tag tagBean) {
		this.tagBean = tagBean;
	}

}