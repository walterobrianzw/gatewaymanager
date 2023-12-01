package click.betacode.gatewaymanager.service;

import java.util.List;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.entities.Peripheral;

public interface GatewayService {
	Gateway createGateway(Gateway gateway);
	String updateGateway(Gateway gateway);
	Gateway getGateway(Integer gatewayID);
	String deleteGateway(Integer gatewayID);
	boolean checkIpAddressValidity(String IpAddress);
	List<Peripheral> getAllPeripheralsOfGateway(Integer gatewayID);
	List<Gateway> getAllGateways();
	void setDummyGateways();
}
