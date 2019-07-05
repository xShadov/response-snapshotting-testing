package com.shadov.test.responsesnapshotting.customer;

import com.shadov.generation.clients.brokeredProductBlikAS.queryBlikTransaction.response.QueryBlikTransactionResponse;
import com.shadov.test.responsesnapshotting.customer.snapshot.CustomerSnapshotter;
import com.shadov.test.responsesnapshotting.customer.snapshot.QueryBlikSnapshotter;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.CustomerResponseHash;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.CustomerResponseSnapshotRepository;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.QueryBlikSnapshotHash;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.QueryBlikSnapshotRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private QueryBlikSnapshotter snapshotCleaner;
	@Autowired
	private CustomerSnapshotter customerSnapshotCleaner;
	@Autowired
	private CustomerResponseSnapshotRepository customerResponseSnapshotRepository;
	@Autowired
	private QueryBlikSnapshotRepository queryBlikSnapshotRepository;

	@GetMapping("/{id1}/{id2}")
	public List<Object> snapshots(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
		final Optional<CustomerResponseHash> res = customerResponseSnapshotRepository.findById(id1);

		final Optional<QueryBlikSnapshotHash> res2 = queryBlikSnapshotRepository.findById(id2);

		return List.of(res.orElse(new CustomerResponseHash()), res2.orElse(new QueryBlikSnapshotHash()));
	}

	@GetMapping("/2")
	public CustomerResponseDTO read2() {
		final CustomerResponseDTO customer = CustomerResponseDTO
				.builder()
				.name("MONICA")
				.age(33)
				.address(CustomerResponseDTO.AddressDTO.builder().street("wtf2").phone(9999).build())
				.build();

		return customerSnapshotCleaner.clean(customer);
	}

	@GetMapping("/1")
	public CustomerResponseDTO read1() {
		final EasyRandomParameters params = new EasyRandomParameters().collectionSizeRange(1, 2).stringLengthRange(1, 2);
		QueryBlikTransactionResponse queryBlikTransactionResponse = new EasyRandom(params).nextObject(QueryBlikTransactionResponse.class);

		final CustomerResponseDTO customer = CustomerResponseDTO
				.builder()
				.name("John")
				.age(15)
				.address(CustomerResponseDTO.AddressDTO.builder().street("wtf").phone(123).build())
				.build();

		return snapshotCleaner.clean(customer, queryBlikTransactionResponse);
	}
}
