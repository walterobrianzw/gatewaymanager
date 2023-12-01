package click.betacode.gatewaymanager.service;

import java.util.List;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.entities.Peripheral;

public interface PeripheralService {
	Peripheral createPeripheral(Integer gatewayID,Peripheral peripheral);
	Peripheral getPeripheral(Integer peripheralID);
	String deletePeripheral(Integer gatewayID);
	Peripheral updatePeripheral(Peripheral peripheral);
	void setDummyPeripherals();

}
