package com.shadov.test.responsesnapshotting.snapshots;

public interface SnapshottedResponse<T> {
	T snapshotted(SnapshotId snapshotId);
}
