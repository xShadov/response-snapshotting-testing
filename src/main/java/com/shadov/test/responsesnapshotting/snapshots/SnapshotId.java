package com.shadov.test.responsesnapshotting.snapshots;

import lombok.Value;

@Value(staticConstructor = "of")
public class SnapshotId {
	private String id;
}
