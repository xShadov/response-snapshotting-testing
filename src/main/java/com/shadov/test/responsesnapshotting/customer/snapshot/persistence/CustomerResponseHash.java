package com.shadov.test.responsesnapshotting.customer.snapshot.persistence;

import com.shadov.test.responsesnapshotting.customer.CustomerResponseDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class CustomerResponseHash {
	@Id
	private String id;
	private CustomerResponseDTO snapshot;

	public static CustomerResponseHash of(CustomerResponseDTO snapshot) {
		final CustomerResponseHash hash = new CustomerResponseHash();
		hash.setSnapshot(snapshot);
		return hash;
	}
}
