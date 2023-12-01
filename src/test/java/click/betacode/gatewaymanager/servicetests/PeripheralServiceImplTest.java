package click.betacode.gatewaymanager.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import click.betacode.gatewaymanager.entities.Gateway;
import click.betacode.gatewaymanager.entities.Peripheral;
import click.betacode.gatewaymanager.enums.Status;
import click.betacode.gatewaymanager.exceptions.AddPeripheralToGatewayException;
import click.betacode.gatewaymanager.repositories.GatewayRepository;
import click.betacode.gatewaymanager.repositories.PeripheralRepository;
import click.betacode.gatewaymanager.service.GatewayService;
import click.betacode.gatewaymanager.service.PeripheralService;
import click.betacode.gatewaymanager.service.impl.GatewayServiceImpl;
import click.betacode.gatewaymanager.service.impl.PeripheralServiceImpl;

class PeripheralServiceImplTest {
	@Mock
	private PeripheralRepository peripheralRepo;
	@Mock
	private GatewayRepository gatewayRepo;
	@Mock
	private GatewayService gatewayService;
	private PeripheralService peripheralService;
	private Gateway gateway;
	private Gateway newGateway;
	private Peripheral newPeripheralObject;
	private List<Peripheral> peripheralList;
	private List<Peripheral> newPeripheralList;
	private AutoCloseable  autoCloseable;
	private AddPeripheralToGatewayException addPeripheralException;

	@BeforeEach
	void setUp() throws Exception {
		autoCloseable=MockitoAnnotations.openMocks(this);
	
		gatewayService=new GatewayServiceImpl(gatewayRepo);
		peripheralService=new PeripheralServiceImpl(gatewayService,peripheralRepo);
		gateway=new Gateway(3,"11234321","Gateway3","127.0.0.1");
		newGateway=new Gateway(20,"112335621","Gateway4","127.0.0.1");
		addPeripheralException=new AddPeripheralToGatewayException("Cant add more peripherals to this Gateway Peripheral size is equal or more than 10");
		peripheralList=new ArrayList<Peripheral>();
		newPeripheralList=new ArrayList<Peripheral>();
		newPeripheralObject=new Peripheral(200,"Amazon",Status.OFFLINE,LocalDateTime.now());
		peripheralList.add(new Peripheral(31,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(21,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(41,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(51,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(61,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(71,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(81,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(91,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(111,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		peripheralList.add(new Peripheral(121,"Amazon",Status.OFFLINE,LocalDateTime.now()));
		gateway.setPeripherals(peripheralList);
		newGateway.setPeripherals(newPeripheralList);
	
	}

	@AfterEach
	void tearDown() throws Exception {
		autoCloseable.close();
	}
	
	//Create peripheral when Gateway has 10 elements.Fail is expected 
	@Test
	void testCreatePeripheralFail() {
		mock(Peripheral.class);
		mock(GatewayRepository.class);
		when(gatewayRepo.findById(3)).thenReturn(Optional.ofNullable(gateway));
		assertThatThrownBy(() -> {
			peripheralService.createPeripheral(gateway.getId(),newPeripheralObject);
		}).isInstanceOf(AddPeripheralToGatewayException.class);
	}
	
	//Create peripheral when Gateway has less than 10 elements.Success is expected 
	@Test
	void testCreatePeripheral() {
		mock(Peripheral.class);
		mock(GatewayRepository.class);
		when(gatewayRepo.findById(20)).thenReturn(Optional.ofNullable(newGateway));
		assertThat(peripheralService.createPeripheral(newGateway.getId(),newPeripheralObject)).isEqualTo(newPeripheralObject);
		
	}
	@Test
	void testUpdatePeripheral() {
		mock(Peripheral.class);
		mock(PeripheralRepository.class);
		when(peripheralRepo.save(newPeripheralObject)).thenReturn(newPeripheralObject);
		assertThat(peripheralService.updatePeripheral(newPeripheralObject)).isEqualTo(newPeripheralObject);
	}
	@Test
	void testGetPeripheral() {
		mock(Peripheral.class);
		mock(PeripheralRepository.class);
		when(peripheralRepo.findById(200)).thenReturn((Optional.ofNullable(newPeripheralObject)));
		assertThat(peripheralService.getPeripheral(200)).isEqualTo(newPeripheralObject);
	}
}
