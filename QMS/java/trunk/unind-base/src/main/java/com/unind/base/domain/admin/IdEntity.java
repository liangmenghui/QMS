package com.unind.base.domain.admin;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


@MappedSuperclass
public abstract class IdEntity implements Serializable {
	private static final long serialVersionUID = 5391836388143717010L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
//	@Id
//	@SequenceGenerator(name="sequenceGenerator",sequenceName="ACTIVITIESSCOPE_SEQ")
//	@GeneratedValue(generator="sequenceGenerator",strategy=GenerationType.SEQUENCE)
//	protected Long id;

	@Version
	protected Integer bsVersion;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getBsVersion() {
		return bsVersion;
	}
	public void setBsVersion(Integer bsVersion) {
		this.bsVersion = bsVersion;
	}
}
