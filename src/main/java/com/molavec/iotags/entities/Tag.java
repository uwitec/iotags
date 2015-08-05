package com.molavec.iotags.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Lob
	private String config;

	@Column(name="data_type")
	private String dataType;

	private String name;

	private String protocol;

	//bi-directional many-to-one association to AnalogData
	@OneToMany(mappedBy="tagBean")
	private List<AnalogData> analogData;

	//bi-directional many-to-one association to DigitalData
	@OneToMany(mappedBy="tagBean")
	private List<DigitalData> digitalData;

	//bi-directional many-to-one association to Session
	@ManyToOne
	private Session session;

	public Tag() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConfig() {
		return this.config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public List<AnalogData> getAnalogData() {
		return this.analogData;
	}

	public void setAnalogData(List<AnalogData> analogData) {
		this.analogData = analogData;
	}

	public AnalogData addAnalogData(AnalogData analogData) {
		getAnalogData().add(analogData);
		analogData.setTagBean(this);

		return analogData;
	}

	public AnalogData removeAnalogData(AnalogData analogData) {
		getAnalogData().remove(analogData);
		analogData.setTagBean(null);

		return analogData;
	}

	public List<DigitalData> getDigitalData() {
		return this.digitalData;
	}

	public void setDigitalData(List<DigitalData> digitalData) {
		this.digitalData = digitalData;
	}

	public DigitalData addDigitalData(DigitalData digitalData) {
		getDigitalData().add(digitalData);
		digitalData.setTagBean(this);

		return digitalData;
	}

	public DigitalData removeDigitalData(DigitalData digitalData) {
		getDigitalData().remove(digitalData);
		digitalData.setTagBean(null);

		return digitalData;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}