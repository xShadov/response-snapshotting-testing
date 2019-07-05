package com.shadov.test.responsesnapshotting.customer.snapshot.persistence;

import com.shadov.generation.clients.brokeredProductBlikAS.queryBlikTransaction.response.QueryBlikTransactionResponse;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class QueryBlikSnapshotHash {
	@Id
	private String id;
	private QueryBlikTransactionResponse snapshot;

	public static QueryBlikSnapshotHash of(QueryBlikTransactionResponse snapshot) {
		final QueryBlikSnapshotHash hash = new QueryBlikSnapshotHash();
		hash.setSnapshot(snapshot);
		return hash;
	}
}
