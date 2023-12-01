package click.betacode.gatewaymanager.controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.entities.Peripheral;
import click.betacode.gatewaymanager.exceptions.AddPeripheralToGatewayException;
import click.betacode.gatewaymanager.exceptions.GatewayNotFoundException;
import click.betacode.gatewaymanager.exceptions.PeripheralNotFoundException;
import click.betacode.gatewaymanager.repositories.GatewayRepository;
import click.betacode.gatewaymanager.repositories.PeripheralRepository;
import click.betacode.gatewaymanager.service.PeripheralService;

@RestController
public class PeripheralController {
	private PeripheralService peripheralService;
	PeripheralController(PeripheralService peripheralService){

		this.peripheralService=peripheralService;
	}


//	@GetMapping("/gateways/{gatewayID}/peripherals/{peripheralID}")
//	public Peripheral getPeripheralForGateway(@PathVariable Integer gatewayID ,@PathVariable Integer peripheralID){
//		return getPeripheral(peripheralID);
//	}
	@PostMapping("/gateways/{gatewayID}/peripherals")
	public ResponseEntity<Object> createPeripheralForGateway(@PathVariable Integer gatewayID,@Validated @RequestBody Peripheral peripheral) {
		Peripheral savedPeripheral=peripheralService.createPeripheral(gatewayID, peripheral);
	
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPeripheral.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	

//	@PutMapping("/gateways/{gatewayID}/peripherals/{peripheralID)")
//	public void updatePeripheral(@PathVariable Integer gatewayID ,@PathVariable Integer peripheralID) {
//		
//	}
	@DeleteMapping("/gateways/{gatewayID}/peripherals/{peripheralID}")
	public void deletePeripheral(@PathVariable Integer gatewayID ,@PathVariable Integer peripheralID) {
		peripheralService.deletePeripheral(peripheralID);
    }

}
