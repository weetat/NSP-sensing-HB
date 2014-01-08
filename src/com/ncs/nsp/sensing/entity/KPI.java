package com.ncs.nsp.sensing.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;
@Entity
@Table(name="TB_KPI")
public class KPI implements Serializable{

	private static final long serialVersionUID = -7375527132175892168L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="TB_KPI_FINANCIALYEAR_SEQ")        
	@SequenceGenerator(name="TB_KPI_FINANCIALYEAR_SEQ",sequenceName="TB_KPI_FINANCIALYEAR_SEQ",allocationSize = 1, initialValue= 1)
	private long id;
	
	@Column(nullable=false,columnDefinition="varchar2(100 char)")
	private String kpi_id;
	
	@Column(nullable=false,columnDefinition="varchar2(100 char)")
	private String kpi_fy;
	
	@Column(nullable=false,columnDefinition="varchar2(1000 char)")
	private String kpi_desc;
	
	@OneToMany(mappedBy="kpi",targetEntity=KPIStat.class,fetch=FetchType.LAZY)
    private Collection<KPIStat> kpis;
	
	public Collection<KPIStat> getKpis() {
		return kpis;
	}
	public void setKpis(Collection<KPIStat> kpis) {
		this.kpis = kpis;
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
	public String getKpi_fy() {
		return kpi_fy;
	}
	public void setKpi_fy(String kpi_fy) {
		this.kpi_fy = kpi_fy;
	}
	public String getKpi_desc() {
		return kpi_desc;
	}
	public void setKpi_desc(String kpi_desc) {
		this.kpi_desc = kpi_desc;
	}
	
	
}
