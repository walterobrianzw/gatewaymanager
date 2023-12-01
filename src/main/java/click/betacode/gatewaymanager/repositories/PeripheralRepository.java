package click.betacode.gatewaymanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import click.betacode.gatewaymanager.entities.Peripheral;

public interface PeripheralRepository extends JpaRepository<Peripheral,Integer>{

}
