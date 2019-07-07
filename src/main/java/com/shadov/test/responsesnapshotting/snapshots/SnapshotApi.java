package com.shadov.test.responsesnapshotting.snapshots;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnapshotApi {
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private SnapshotRepository snapshotRepository;

	public <T> Try<SnapshotId> snapshot(SnapshotRequest<T> request) {
		return Try.of(() -> {
			final SnapshotHash hash = snapshotRepository.save(
					SnapshotHash.of(
							mapper.writeValueAsString(request.getContent()), request.getType().clazz()
					)
			);

			return hash.id();
		});
	}

	public <T> Try<T> find(SnapshotId id, SnapshotType<T> type) {
		return Try.of(() -> {
			final String content = snapshotRepository
					.findById(id.getId())
					.filter(snapshot -> snapshot.matches(type))
					.map(SnapshotHash::content)
					.orElseThrow(() -> new IllegalStateException("Nothing found"));

			return mapper.readValue(content, type.clazz());
		});
	}
}
