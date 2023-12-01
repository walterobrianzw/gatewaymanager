package click.betacode.gatewaymanager.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.service.GatewayService;
import click.betacode.gatewaymanager.service.PeripheralService;
@RestController
public class DummyDataController {
	private PeripheralService peripheralService;
	private GatewayService gatewayService;
	DummyDataController(GatewayService gatewayService,PeripheralService peripheralService){
		this.gatewayService=gatewayService;
		this.peripheralService=peripheralService;
	}
	@GetMapping("/dummydata")
	public String setDummyData(){
		gatewayService.setDummyGateways();
		peripheralService.setDummyPeripherals();
		return "Dummy data imported Succesfully";

	}
}
