package br.com.Brasilprev.gateways.h2.gateways;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Brasilprev.gateways.h2.OrderGatewayImpl;
import com.Brasilprev.gateways.h2.OrderRepository;
import com.Brasilprev.gateways.h2.domains.Client;
import com.Brasilprev.gateways.h2.domains.Order;
import com.Brasilprev.gateways.h2.domains.Product;
import com.Brasilprev.gateways.http.jsons.requests.ClientRequest;
import com.Brasilprev.gateways.http.jsons.requests.OrderRequest;
import com.Brasilprev.gateways.http.jsons.requests.ProductRequest;

@ExtendWith(MockitoExtension.class)
public class OrderGatewayTest {

	@InjectMocks
	private OrderGatewayImpl orderGatewayImpl;

	@Mock
	OrderRepository orderRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSaveOrderSucess() {

		// GIVEN order
		Order order = getOrder();
		when(orderRepository.save(Mockito.any())).thenReturn(order);

		//WHEN order gateway save is call 
		Order orderResponseDomain = orderGatewayImpl.save(getClientRequest());

		//THEN compare if valid 
		assertEquals(1, orderResponseDomain.getIdOrder());
	}
	
	private OrderRequest getClientRequest() {
		
		ProductRequest buildProduto = 
				ProductRequest.builder().id(1).nameProduct("PEN").descriptionProduct("BLUE").quantity(100).build();
		ClientRequest clientRequest =
				ClientRequest.builder().cpf("098").id(1).name("").numberContact("").build();
		
		return OrderRequest.builder()
							.productsRequest(Collections.singleton(buildProduto))
							.ClientRequest(clientRequest)
							.totalValue(BigDecimal.valueOf(100))
						   .build();
	}

	private Order getOrder() {
		Order order = Order.builder()
							.client(getClient())
							.idOrder(1)
							.products(getProducts())
							.totalValue(BigDecimal.valueOf(100.00))
						   .build();
		
		return order;
	}

	private Set<Product> getProducts() {
		return Collections.singleton(Product.builder()
										     .idProduct(1)
										     .nameProduct("PEN")
										     .descriptionProduct("BLUE")
										     .quantity(100)
										    .build());
	}

	private Client getClient() {
		Client buildClient = Client.builder()
									.cpf("0970604401")
									.idClient(1)
									.nameClient("italo")
									.numberContact("0970123312")
									.build();

		return buildClient;
	}
}
