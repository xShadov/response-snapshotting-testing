package com.shadov.test.responsesnapshotting.customer.snapshot;

import com.shadov.generation.clients.brokeredProductBlikAS.queryBlikTransaction.response.QueryBlikTransactionResponse;
import com.shadov.test.responsesnapshotting.customer.CustomerResponseDTO;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.QueryBlikSnapshotHash;
import com.shadov.test.responsesnapshotting.customer.snapshot.persistence.QueryBlikSnapshotRepository;
import com.shadov.test.responsesnapshotting.snapshots.SnapshotId;
import com.shadov.test.responsesnapshotting.snapshots.Snapshotter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryBlikSnapshotter implements Snapshotter<CustomerResponseDTO, QueryBlikTransactionResponse> {
	@Autowired
	private QueryBlikSnapshotRepository snaphotRepository;

	@Override
	public CustomerResponseDTO clean(final CustomerResponseDTO response, final QueryBlikTransactionResponse toSnapshot) {
		final QueryBlikSnapshotHash snaphot = snaphotRepository.save(QueryBlikSnapshotHash.of(toSnapshot));

		return response.snapshotted(SnapshotId.of(snaphot.getId()));
	}
}
