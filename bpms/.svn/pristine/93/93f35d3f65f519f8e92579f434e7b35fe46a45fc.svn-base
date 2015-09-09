package com.bpms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 资产表
 * 
 * @author liuhh
 *
 */
@Entity
@Table(name = "t_bp_assets")
public class Assets implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String assetId;
	private String realEstate;
	private String vehicle;
	private String theSameIndustry;

	public Assets() {
	}

	public Assets(String assetId) {
		this.assetId = assetId;
	}

	public Assets(String assetId, String realEstate, String vehicle,
			String theSameIndustry) {
		this.assetId = assetId;
		this.realEstate = realEstate;
		this.vehicle = vehicle;
		this.theSameIndustry = theSameIndustry;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "ASSET_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getAssetId() {
		return this.assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	@Column(name = "REAL_ESTATE", length = 200)
	public String getRealEstate() {
		return this.realEstate;
	}

	public void setRealEstate(String realEstate) {
		this.realEstate = realEstate;
	}

	@Column(name = "VEHICLE", length = 200)
	public String getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "THE_SAME_INDUSTRY", length = 200)
	public String getTheSameIndustry() {
		return this.theSameIndustry;
	}

	public void setTheSameIndustry(String theSameIndustry) {
		this.theSameIndustry = theSameIndustry;
	}

}
