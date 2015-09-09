package com.qqms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 地址详情表实体
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_bp_address")
public class Address implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String addrId;
	private Integer provinceId;
	private Integer cityId;
	private Integer areaId;
	private String addrDetails;
	private String addrType;
	private String hostType;
	private String hostId;

	public Address() {
	}

	public Address(String addrId, Integer provinceId, Integer cityId,
			Integer areaId) {
		this.addrId = addrId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
	}

	public Address(String addrId, Integer provinceId, Integer cityId,
			Integer areaId, String addrDetails, String addrType,
			String hostType, String hostId) {
		this.addrId = addrId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.addrDetails = addrDetails;
		this.addrType = addrType;
		this.hostType = hostType;
		this.hostId = hostId;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "ADDR_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getAddrId() {
		return this.addrId;
	}

	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}

	@Column(name = "PROVINCE_ID", nullable = false, length = 40)
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "CITY_ID", nullable = false, length = 40)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "AREA_ID", nullable = false, length = 40)
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "ADDR_DETAILS", length = 500)
	public String getAddrDetails() {
		return this.addrDetails;
	}

	public void setAddrDetails(String addrDetails) {
		this.addrDetails = addrDetails;
	}

	@Column(name = "ADDR_TYPE", length = 1)
	public String getAddrType() {
		return this.addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

	@Column(name = "HOST_TYPE", length = 1)
	public String getHostType() {
		return this.hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	@Column(name = "HOST_ID", length = 40)
	public String getHostId() {
		return this.hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

}
