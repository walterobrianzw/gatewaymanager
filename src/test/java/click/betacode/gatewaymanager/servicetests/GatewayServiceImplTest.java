package click.betacode.gatewaymanager.servicetests;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.repositories.GatewayRepository;
import click.betacode.gatewaymanager.service.GatewayService;
import click.betacode.gatewaymanager.service.impl.GatewayServiceImpl;

class GatewayServiceImplTest {
	@Mock
	private GatewayRepository gatewayRepo;
	private GatewayService gatewayService;
	private Gateway gateway;
	private AutoCloseable  autoCloseable;
	
	@BeforeEach
	void setUp() throws Exception {
		autoCloseable=MockitoAnnotations.openMocks(this);
		gatewayService=new GatewayServiceImpl(gatewayRepo);
		gateway=new Gateway(2,"11234321","Gateway3","127.0.0.1");
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}

	@Test
	void testCreateGateway() {
		mock(Gateway.class);
		mock(GatewayRepository.class);
		when(gatewayRepo.save(gateway)).thenReturn(gateway);
		assertThat(gatewayService.createGateway(gateway)).isEqualTo(gateway);
	}
	@Test
	void testUpdateGateway() {
		mock(Gateway.class);
		mock(GatewayRepository.class);
		when(gatewayRepo.save(gateway)).thenReturn(gateway);
		assertThat(gatewayService.updateGateway(gateway)).isEqualTo("SUCCESS");
	}
	@Test
	void testGetGateway() {
		mock(Gateway.class);
		mock(GatewayRepository.class);
		when(gatewayRepo.findById(2)).thenReturn((Optional.ofNullable(gateway)));
		assertThat(gatewayService.getGateway(2)).isEqualTo(gateway);
	}
	@Test
	//SUCCESS CASE
	void testCheckIpAddressValidityCorrectIPAddress() {
		
		assertThat(gatewayService.checkIpAddressValidity("127.0.0.1")).isEqualTo(true);
	}
	
	@Test
	//FAILURE CASE
	void testCheckIpAddressValidityInCorrectIpAddress() {
		
		assertThat(gatewayService.checkIpAddressValidity("127.01.0.1")).isEqualTo(false);
	}

}
