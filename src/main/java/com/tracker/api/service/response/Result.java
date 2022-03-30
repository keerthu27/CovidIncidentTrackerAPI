package com.tracker.api.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Model class representing response
 * @author Keerthana
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {
	private String landkreise;
	private double cases7Per100k;
}
