package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sa_address")
public class AddressJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "address_id")
	private Integer addressId;
	
	@Column(name = "address_text")
	private String addressText;
	
	@Column(name = "area_name")
	private String areaName;
	
	@Column(name = "plot_no")
	private Integer plotNo;
	
	@Column(name = "sector_no")
	private Integer sectorNo;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "pin_code")
	private Integer pinCode;
	
	@Column(name = "state")
	private String state;
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPlotNo() {
		return plotNo;
	}
	public void setPlotNo(Integer plotNo) {
		this.plotNo = plotNo;
	}
	public Integer getSectorNo() {
		return sectorNo;
	}
	public void setSectorNo(Integer sectorNo) {
		this.sectorNo = sectorNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddressText() {
		return addressText;
	}
	public void setAddressText(String addressText) {
		this.addressText = addressText;
	}
}
