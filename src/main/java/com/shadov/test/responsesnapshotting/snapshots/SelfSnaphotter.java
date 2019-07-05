package com.shadov.test.responsesnapshotting.snapshots;

public interface SelfSnaphotter<T extends SnapshottedResponse<T>> extends Snapshotter<T, T> {
	T clean(T content);

	@Override
	default T clean(T response, T toSnapshot) {
		return clean(toSnapshot);
	}
}
