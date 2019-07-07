package com.shadov.test.responsesnapshotting.snapshots;

import com.shadov.generation.clients.brokeredProductBlikAS.queryBlikTransaction.response.QueryBlikTransactionResponse;
import com.shadov.test.responsesnapshotting.customer.CustomerResponseDTO;

public class Snapshots {
	public static final SnapshotType<CustomerResponseDTO> CUSTOMER = () -> CustomerResponseDTO.class;

	public static final SnapshotType<QueryBlikTransactionResponse> QUERY_BLIK = () -> QueryBlikTransactionResponse.class;
}
