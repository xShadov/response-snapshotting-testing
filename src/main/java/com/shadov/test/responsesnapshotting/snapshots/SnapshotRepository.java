package com.shadov.test.responsesnapshotting.snapshots;

import org.springframework.data.repository.CrudRepository;

public interface SnapshotRepository extends CrudRepository<SnapshotHash, String> {

}
