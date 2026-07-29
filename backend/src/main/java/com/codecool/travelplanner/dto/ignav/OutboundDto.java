package com.codecool.travelplanner.dto.ignav;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OutboundDto(
        String carrier,
        @JsonProperty("duration_minutes") int durationMinutes,
        List<SegmentDto> segments) {
}
