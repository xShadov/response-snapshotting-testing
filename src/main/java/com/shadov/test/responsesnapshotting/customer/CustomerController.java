package com.shadov.test.responsesnapshotting.customer;

import com.shadov.generation.clients.brokeredProductBlikAS.queryBlikTransaction.response.QueryBlikTransactionResponse;
import com.shadov.test.responsesnapshotting.snapshots.SnapshotApi;
import com.shadov.test.responsesnapshotting.snapshots.SnapshotId;
import com.shadov.test.responsesnapshotting.snapshots.SnapshotRequest;
import com.shadov.test.responsesnapshotting.snapshots.Snapshots;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private SnapshotApi snapshotApi;

	@GetMapping("/{id1}/{id2}")
	public List<Object> snapshots(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
		final CustomerResponseDTO res = snapshotApi
				.find(SnapshotId.of(id1), Snapshots.CUSTOMER)
				.getOrElseThrow(ex -> new RuntimeException("Error retrieving snapshot", ex));

		final QueryBlikTransactionResponse res2 = snapshotApi
				.find(SnapshotId.of(id2), Snapshots.QUERY_BLIK)
				.getOrElseThrow(ex -> new RuntimeException("Error retrieving snapshot", ex));

		return List.of(res, res2);
	}

	@GetMapping("/2")
	public CustomerResponseDTO read2() {
		final CustomerResponseDTO customer = CustomerResponseDTO
				.builder()
				.name("MONICA")
				.age(33)
				.address(CustomerResponseDTO.AddressDTO.builder().street("wtf2").phone(9999).build())
				.build();

		final SnapshotId id = snapshotApi
				.snapshot(SnapshotRequest.of(customer, Snapshots.CUSTOMER))
				.getOrElseThrow(ex -> new RuntimeException("Error creating snapshot", ex));

		return customer.snapshotted(id);
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

		final SnapshotId id = snapshotApi
				.snapshot(SnapshotRequest.of(queryBlikTransactionResponse, Snapshots.QUERY_BLIK))
				.getOrElseThrow(ex -> new RuntimeException("Error creating snapshot", ex));

		return customer.snapshotted(id);
	}
}
