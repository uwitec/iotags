package com.molavec.iotags.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the analog_data database table.
 * 
 */
@Entity
@Table(name="analog_data")
@NamedQuery(name="AnalogData.findAll", query="SELECT a FROM AnalogData a")
public class AnalogData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AnalogDataPK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private float value;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	@JoinColumn(name="tag")
	private Tag tagBean;

	public AnalogData() {
	}

	public AnalogDataPK getId() {
		return this.id;
	}

	public void setId(AnalogDataPK id) {
		this.id = id;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Tag getTagBean() {
		return this.tagBean;
	}

	public void setTagBean(Tag tagBean) {
		this.tagBean = tagBean;
	}

}