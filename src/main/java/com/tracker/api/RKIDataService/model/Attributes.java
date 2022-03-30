
package com.tracker.api.RKIDataService.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author Keerthana
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "GEN", "cases7_per_100k", "county", "last_update", "DEBKG_ID" })
@Generated("jsonschema2pojo")
public class Attributes {

	@JsonProperty("GEN")
	private String gen;
	@JsonProperty("cases7_per_100k")
	private Double cases7Per100k;
	@JsonProperty("county")
	private String county;
	@JsonProperty("last_update")
	private String lastUpdate;
	@JsonProperty("DEBKG_ID")
	private String debkgId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("GEN")
	public String getGen() {
		return gen;
	}

	@JsonProperty("GEN")
	public void setGen(String gen) {
		this.gen = gen;
	}

	@JsonProperty("cases7_per_100k")
	public Double getCases7Per100k() {
		return cases7Per100k;
	}

	@JsonProperty("cases7_per_100k")
	public void setCases7Per100k(Double cases7Per100k) {
		this.cases7Per100k = cases7Per100k;
	}

	@JsonProperty("county")
	public String getCounty() {
		return county;
	}

	@JsonProperty("county")
	public void setCounty(String county) {
		this.county = county;
	}

	@JsonProperty("last_update")
	public String getLastUpdate() {
		return lastUpdate;
	}

	@JsonProperty("last_update")
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@JsonProperty("DEBKG_ID")
	public String getDebkgId() {
		return debkgId;
	}

	@JsonProperty("DEBKG_ID")
	public void setDebkgId(String debkgId) {
		this.debkgId = debkgId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
