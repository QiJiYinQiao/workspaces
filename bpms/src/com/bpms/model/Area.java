package com.bpms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 区域表实体
 * 
 * @author liuhh
 *
 */
@Entity
@Table(name = "t_area")
public class Area implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer areaId;
	private String parentId;
	private String areaName;
	private String pyName;
	private Integer areaType;
	private Integer zipcode;
	private Integer special;

	public Area() {
	}

	public Area(Integer areaId) {
		this.areaId = areaId;
	}

	public Area(Integer areaId, String parentId, String areaName,
			String pyName, Integer areaType, Integer zipcode, Integer special) {
		this.areaId = areaId;
		this.parentId = parentId;
		this.areaName = areaName;
		this.pyName = pyName;
		this.areaType = areaType;
		this.zipcode = zipcode;
		this.special = special;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(name = "AREA_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "PARENT_ID", length = 40)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "AREA_NAME", length = 50)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "PY_NAME", length = 50)
	public String getPyName() {
		return this.pyName;
	}

	public void setPyName(String pyName) {
		this.pyName = pyName;
	}

	@Column(name = "AREA_TYPE")
	public Integer getAreaType() {
		return this.areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	@Column(name = "ZIPCODE")
	public Integer getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "SPECIAL")
	public Integer getSpecial() {
		return this.special;
	}

	public void setSpecial(Integer special) {
		this.special = special;
	}

}
