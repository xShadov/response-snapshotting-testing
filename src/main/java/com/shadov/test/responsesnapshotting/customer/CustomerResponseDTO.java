package com.shadov.test.responsesnapshotting.customer;

import com.shadov.test.responsesnapshotting.snapshots.SnapshotId;
import com.shadov.test.responsesnapshotting.snapshots.SnapshottedResponse;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CustomerResponseDTO implements SnapshottedResponse<CustomerResponseDTO> {
	private String name;
	private Integer age;
	private AddressDTO address;
	private String customerSnapshot;

	@Override
	public CustomerResponseDTO snapshotted(final SnapshotId snapshotId) {
		return this.toBuilder().customerSnapshot(snapshotId.getId()).build();
	}

	@Value
	@Builder(toBuilder = true)
	public static class AddressDTO {
		private String street;
		private Integer phone;
	}
}
