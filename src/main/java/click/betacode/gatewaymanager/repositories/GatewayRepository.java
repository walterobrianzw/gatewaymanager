package click.betacode.gatewaymanager.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import click.betacode.gatewaymanager.entities.Gateway;



public interface GatewayRepository extends JpaRepository<Gateway,Integer> {

}
