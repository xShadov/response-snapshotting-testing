package com.shadov.test.responsesnapshotting.snapshots;

import lombok.Value;

@Value
public class SnapshotRequest<T> {
	private T content;
	private SnapshotType<T> type;

	private SnapshotRequest(final T content, final SnapshotType<T> type) {
		this.content = content;
		this.type = type;
	}

	public static <T> SnapshotRequest<T> of(T content, SnapshotType<T> type) {
		return new SnapshotRequest<>(content, type);
	}
}
