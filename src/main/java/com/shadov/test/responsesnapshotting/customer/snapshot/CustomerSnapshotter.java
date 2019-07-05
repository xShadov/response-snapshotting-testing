package com.shadov.test.responsesnapshotting.customer.snapshot;

import com.shadov.test.responsesnapshotting.customer.CustomerResponseDTO;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.CustomerResponseHash;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.CustomerResponseSnapshotRepository;
import com.shadov.test.responsesnapshotting.snapshots.SelfSnaphotter;
import com.shadov.test.responsesnapshotting.snapshots.SnapshotId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerSnapshotter implements SelfSnaphotter<CustomerResponseDTO> {
	@Autowired
	private CustomerResponseSnapshotRepository snaphotRepository;

	@Override
	public CustomerResponseDTO clean(final CustomerResponseDTO response) {
		final CustomerResponseHash snaphot = snaphotRepository.save(CustomerResponseHash.of(response));

		return response.snapshotted(SnapshotId.of(snaphot.getId()));
	}
}
