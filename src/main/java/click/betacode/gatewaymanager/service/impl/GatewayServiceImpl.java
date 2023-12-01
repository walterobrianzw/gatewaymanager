package click.betacode.gatewaymanager.service.impl;

import java.util.List;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.entities.Peripheral;
import click.betacode.gatewaymanager.exceptions.AddPeripheralToGatewayException;
import click.betacode.gatewaymanager.exceptions.GatewayInvalidIpAddressException;
import click.betacode.gatewaymanager.exceptions.GatewayNotFoundException;
import click.betacode.gatewaymanager.exceptions.GatewayNotSavedException;
import click.betacode.gatewaymanager.repositories.GatewayRepository;
import click.betacode.gatewaymanager.service.GatewayService;

@Service
public class GatewayServiceImpl implements GatewayService {
	
	GatewayRepository gatewayRepo;
	public GatewayServiceImpl(GatewayRepository gatewayRepo) {
		this.gatewayRepo= gatewayRepo;
	}
	
	@Override
	public Gateway createGateway(Gateway gateway) {
		
		try {
			if(checkIpAddressValidity(gateway.getIp_address())==false) {
				throw new GatewayInvalidIpAddressException("IP Address entered is invalid");
			}
			gatewayRepo.save(gateway);
			return gateway;
		}catch(Exception e) {
			throw new GatewayNotSavedException(e.getMessage());
		
		}
	}

	@Override
	public String updateGateway(Gateway gateway) {
		try {
			
			gatewayRepo.save(gateway);
			return "SUCCESS";
		}catch(Exception e) {
			return "FAILED";
		}
	}

	@Override
	public Gateway getGateway(Integer gatewayID) {
		try {
		
				
				return gatewayRepo.findById(gatewayID).get();
		
		}catch(Exception e) {
				throw new GatewayNotFoundException("This Gateway was not found with id:"+gatewayID);
		}
	}
	@Override
	public List<Gateway> getAllGateways() {
		try {
				return gatewayRepo.findAll();
				}catch(Exception e) {
			return null;
		}
	}
	@Override
	public List<Peripheral> getAllPeripheralsOfGateway(Integer gatewayID) {
		try {
			return getGateway(gatewayID).getPeripherals();
		
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public String deleteGateway(Integer gatewayID) {
		try {
			Gateway gateway=getGateway(gatewayID);
			if(gateway!=null) {
				gatewayRepo.delete(gateway);
				return "SUCCESS";
			}
			return "FAILED";
		}catch(Exception e) {
			return "FAILED";
		}
	}

	@Override
	public boolean checkIpAddressValidity(String ipAddress) {
		
			InetAddressValidator validator = InetAddressValidator.getInstance();
			return validator.isValid(ipAddress);
			
	}

	@Override
	public void setDummyGateways() {
		try {
		createGateway(new Gateway(1,"YTSRE46765","Gateway1","127.0.0.1"));
		
		createGateway(new Gateway(2,"18273YRTED","Gateway1","127.0.1.1"));
	}catch(Exception e) {
		throw new GatewayNotSavedException(e.getMessage());

	}
		
	}
		 

}
