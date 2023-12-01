package click.betacode.gatewaymanager.entities;


import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Gateway {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="serial_number")
	@Size(min=1)
	@NotNull(message="Serial Number is required")
	private String serial_number;
	@Column(name="name")
	@Size(min=1)
	@NotNull(message="Name is required")
	private String name;
	@Column(name="ip_address")
	@Size(min=1)
	@NotNull(message="IP Address is required")
	private String ip_address;
	@OneToMany(mappedBy="gateway")

	private List<Peripheral> peripherals;
	public Gateway() {
		
	}
	
	public Gateway(Integer id,String serial_number,String name,String ip_address){
		super();
		this.id=id;
		this.serial_number=serial_number;
		this.name=name;
		this.ip_address=ip_address;
		this.peripherals=new ArrayList<Peripheral>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public List<Peripheral>  getPeripherals() {
		return peripherals;
	}
	public void setPeripherals(List<Peripheral> peripherals) {
		this.peripherals = peripherals;
	}
	
	
	
	
	
}
