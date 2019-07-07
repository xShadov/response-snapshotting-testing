package com.shadov.test.responsesnapshotting.snapshots;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class SnapshotHash {
	@Id
	private String id;
	private String content;
	private String type;

	private SnapshotHash(String id, String content, String type) {
		this.id = id;
		this.content = content;
		this.type = type;
	}

	public static SnapshotHash of(String content, Class<?> type) {
		return new SnapshotHash(null, content, type.getName());
	}

	public SnapshotId id() {
		return SnapshotId.of(id);
	}

	public String content() {
		return content;
	}

	public <T> boolean matches(final SnapshotType<T> other) {
		return type.equals(other.clazz().getName());
	}
}
