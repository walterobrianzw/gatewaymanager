package click.betacode.gatewaymanager.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import click.betacode.gatewaymanager.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Peripheral {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="vendor")
	@NotNull(message="A vendor is required")
	@Size(min=1)
	private String vendor;
    @Enumerated(EnumType.STRING)
    @NotNull(message="A status is required.Either ONLINE or OFFLINE")
	@Column(name="status")
	private Status status;
	@Column(name="date_created")
	private LocalDateTime date_created;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Gateway gateway;
	
	public Peripheral() {
		
	}
	public Peripheral(Integer id,String vendor,Status status,LocalDateTime date_created){
		super();
		this.id=id;
		this.vendor=vendor;
		this.status=status;
		this.date_created=date_created;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public LocalDateTime getDateCreated() {
		return date_created;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.date_created = dateCreated;
	}
	
	public Status getstatus() {
		return status;
	}
	public void setstatus(Status status) {
		this.status = status;
	}
	public Gateway getGateway() {
		return gateway;
	}
	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}
	

}
