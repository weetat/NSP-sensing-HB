package com.ncs.nsp.sensing.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="TB_KPI_STAT")
public class KPIStat implements Serializable {

	private static final long serialVersionUID = 8565893545138020388L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="TB_KPI_STAT_SEQ")        
	@SequenceGenerator(name="TB_KPI_STAT_SEQ",sequenceName="TB_KPI_STAT_SEQ",allocationSize = 1, initialValue= 1)
	private long id;
	
	@Column(insertable=true,updatable=false,nullable=false,columnDefinition="varchar2(100 char)")
	private String kpi_id;
	
	@Column(name="KPI_YR",nullable=false,columnDefinition="integer")
	private int kpi_yr;
	
	@Column(name="KPI_MTH",nullable=false,columnDefinition="integer")
	private int kpi_mth;
	
	@Column(name="KPI_STAT",nullable=false,columnDefinition="number")
	private BigDecimal kpi_stat;
	
	@Column(name = "KPI_DATE_MODIFIED")
	private Date kpi_date_modified;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="KPI_ID",referencedColumnName="KPI_ID",insertable=false,updatable=false)
	private KPI kpi;

	public KPI getKpi() {
		return kpi;
	}
	public void setKpi(KPI kpi) {
		this.kpi = kpi;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKpi_id() {
		return kpi_id;
	}
	public void setKpi_id(String kpi_id) {
		this.kpi_id = kpi_id;
	}
	public int getKpi_yr() {
		return kpi_yr;
	}
	public void setKpi_yr(int kpi_yr) {
		this.kpi_yr = kpi_yr;
	}
	public int getKpi_mth() {
		return kpi_mth;
	}
	public void setKpi_mth(int kpi_mth) {
		this.kpi_mth = kpi_mth;
	}
	public BigDecimal getKpi_stat() {
		return kpi_stat;
	}
	public void setKpi_stat(BigDecimal kpi_stat) {
		this.kpi_stat = kpi_stat;
	}
	public Date getKpi_date_modified() {
		return kpi_date_modified;
	}
	public void setKpi_date_modified(Date kpi_date_modified) {
		this.kpi_date_modified = kpi_date_modified;
	}
	
}
