package click.betacode.gatewaymanager.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.entities.Peripheral;
import click.betacode.gatewaymanager.enums.Status;
import click.betacode.gatewaymanager.exceptions.AddPeripheralToGatewayException;
import click.betacode.gatewaymanager.repositories.PeripheralRepository;
import click.betacode.gatewaymanager.service.GatewayService;
import click.betacode.gatewaymanager.service.PeripheralService;

@Service
public class PeripheralServiceImpl implements PeripheralService{
	private PeripheralRepository peripheralRepo;
	private GatewayService gatewayService;
	public PeripheralServiceImpl(GatewayService gatewayService,PeripheralRepository peripheralRepo){
		this.gatewayService=gatewayService;
		this.peripheralRepo=peripheralRepo;
	}
	@Override
	public Peripheral createPeripheral(Integer gatewayID,Peripheral peripheral) {
		try {
			Gateway gateway=gatewayService.getGateway(gatewayID);
			if(gateway.getPeripherals().size()<10) {
				peripheral.setGateway(gateway);
				peripheral.setDateCreated(LocalDateTime.now());

				peripheralRepo.save(peripheral);
				return peripheral;
			}else {
				
				throw new AddPeripheralToGatewayException("Cant add more peripherals to this Gateway Peripheral size is equal or more than 10");
		
			}
		
		}catch(Exception e) {
			throw new AddPeripheralToGatewayException(e.getMessage());

		}
		
	}

	@Override
	public Peripheral updatePeripheral(Peripheral peripheral) {
		try {
				peripheralRepo.save(peripheral);
				return peripheral;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Peripheral getPeripheral(Integer peripheralID) {
		try {
			
			return peripheralRepo.findById(peripheralID).get();
	
	}catch(Exception e) {
		return null;
	}
	}

	@Override
	public String deletePeripheral(Integer peripheralID) {
		try {
			Peripheral peripheral=getPeripheral(peripheralID);
			if(peripheral!=null) {
				peripheralRepo.delete(peripheral);
				return "SUCCESS";
			}
			return "FAILED";
		}catch(Exception e) {
			return "FAILED";
		}
	}
	@Override
	public void setDummyPeripherals() {
		try {
			//Peripheral p1=;
			createPeripheral(1,new Peripheral(1,"Amazon",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(2,"Game",Status.OFFLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(3,"Sony",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(4,"LG",Status.OFFLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(5,"Samsung",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(6,"Amazon",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(7,"Amazon",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(8,"Amazon",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(9,"Amazon",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(1,new Peripheral(10,"Amazon",Status.ONLINE,LocalDateTime.now()));
			createPeripheral(2,new Peripheral(11,"Amazon",Status.ONLINE,LocalDateTime.now()));
		
		}catch(Exception e) {
			throw new AddPeripheralToGatewayException("Hello "+e.getMessage());

		}
	}

}
