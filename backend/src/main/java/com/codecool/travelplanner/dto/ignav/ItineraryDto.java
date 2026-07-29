package com.codecool.travelplanner.dto.ignav;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItineraryDto(
        PriceDto price,
        OutboundDto outbound,
        @JsonProperty("cabin_class") String cabinClass,
        @JsonProperty("requires_self_transfer") boolean requiresSelfTransfer,
        @JsonProperty("ignav_id") String ignavId) {
}
