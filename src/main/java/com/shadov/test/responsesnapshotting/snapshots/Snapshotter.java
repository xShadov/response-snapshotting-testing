package com.shadov.test.responsesnapshotting.snapshots;

public interface Snapshotter<T extends SnapshottedResponse<T>, C> {
	T clean(T response, C toSnapshot);
}
