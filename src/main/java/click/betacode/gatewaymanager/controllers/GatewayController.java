package click.betacode.gatewaymanager.controllers;

import java.net.URI;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.InetAddressValidator;
//import org.apache.commons.validator.routines.InetAddressValidator;
//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
import click.betacode.gatewaymanager.exceptions.GatewayInvalidIpAddressException;
import click.betacode.gatewaymanager.exceptions.GatewayNotFoundException;
import click.betacode.gatewaymanager.exceptions.GatewayNotSavedException;
import click.betacode.gatewaymanager.service.GatewayService;

@RestController
public class GatewayController {
	
	private GatewayService gatewayService;
	GatewayController(GatewayService gatewayService){
		this.gatewayService=gatewayService;
	}
	
	@GetMapping("/gateways")
	public List<Gateway> getAllGateways(){
		return gatewayService.getAllGateways();
	}

	
	@GetMapping("/gateways/{gatewayID}/peripherals")
	public List<Peripheral> getAllPeripheralsForGateway(@PathVariable Integer gatewayID){
		return gatewayService.getAllPeripheralsOfGateway(gatewayID);
	}
	
//	@GetMapping("/gateways/{id}")
//	public EntityModel<Gateway> getGateway(@PathVariable Integer id ){
//		Optional<Gateway> gateway=gatewayRepo.findById(id);
//		if(gateway.isEmpty()) {
//			throw new GatewayNotFoundException("id"+id);
//		}
//		
//		EntityModel<Gateway> gatewayEntityModel = EntityModel.of(gateway.get());
//		
//		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).getAllGateways());
//		WebMvcLinkBuilder link1 = linkTo(methodOn(GatewayController.class)
//			      .getAllGateways();
//		gatewayEntityModel.add(link.withRel("all-users"));
//		
//		return gatewayEntityModel;
//	}
	@GetMapping("/gateways/{id}")
	public Gateway getGateway(@PathVariable Integer id ){
		Gateway gateway=gatewayService.getGateway(id);
	
		return gateway;
	}
	@PostMapping("/gateways")
	public ResponseEntity<Gateway> createGateway(@Validated @RequestBody Gateway gateway) {
		
		Gateway savedGateway=gatewayService.createGateway(gateway);
	
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedGateway.getId()).toUri();
		return ResponseEntity.created(location).build();
		
		
		
		
	}
//	@PutMapping("/gateways/{id}")
//	public void updateGateway(@PathVariable Integer id ) {
//		
//	}
	@DeleteMapping("/gateways/{gatewayID}")
	public void deleteGateway(@PathVariable Integer gatewayID ) {
		gatewayService.deleteGateway(gatewayID);
	
    }
}