package com.shadov.test.responsesnapshotting.customer.snapshot.persistence;

import org.springframework.data.repository.CrudRepository;

public interface CustomerResponseSnapshotRepository extends CrudRepository<CustomerResponseHash, String> {
}
