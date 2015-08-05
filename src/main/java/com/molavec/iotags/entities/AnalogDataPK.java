package com.molavec.iotags.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the analog_data database table.
 * 
 */
@Embeddable
public class AnalogDataPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(insertable=false, updatable=false)
	private int tag;

	public AnalogDataPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTag() {
		return this.tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AnalogDataPK)) {
			return false;
		}
		AnalogDataPK castOther = (AnalogDataPK)other;
		return 
			(this.id == castOther.id)
			&& (this.tag == castOther.tag);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.tag;
		
		return hash;
	}
}