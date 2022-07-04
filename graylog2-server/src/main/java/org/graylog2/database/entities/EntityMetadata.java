/*
 * Copyright (C) 2020 Graylog, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package org.graylog2.database.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@AutoValue
@JsonAutoDetect
@JsonDeserialize(builder = EntityMetadata.Builder.class)
public abstract class EntityMetadata {
    public static final String DEFAULT_VERSION = "1";
    public static final long DEFAULT_REV = 1;
    public static final String DEFAULT_SCOPE = "default";

    public static final String VERSION = "v";
    public static final String SCOPE = "scope";
    public static final String REV = "rev";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    @JsonProperty(VERSION)
    public abstract String version();

    @JsonProperty(SCOPE)
    public abstract String scope();

    @JsonProperty(REV)
    public abstract long rev();

    @JsonProperty(CREATED_AT)
    public abstract ZonedDateTime createdAt();

    @JsonProperty(UPDATED_AT)
    public abstract ZonedDateTime updatedAt();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return Builder.create();
    }

    public static EntityMetadata createDefault() {
        return Builder.create().build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        @JsonCreator
        public static Builder create() {
            return new AutoValue_EntityMetadata.Builder()
                    .version(DEFAULT_VERSION)
                    .rev(DEFAULT_REV)
                    .scope(DEFAULT_SCOPE)
                    .createdAt(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC))
                    .updatedAt(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC));
        }

        @JsonProperty(VERSION)
        public abstract Builder version(String version);

        @JsonProperty(SCOPE)
        public abstract Builder scope(String scope);

        @JsonProperty(REV)
        public abstract Builder rev(long rev);

        @JsonProperty(CREATED_AT)
        public abstract Builder createdAt(ZonedDateTime createdAt);

        @JsonProperty(UPDATED_AT)
        public abstract Builder updatedAt(ZonedDateTime updatedAt);

        public abstract EntityMetadata build();
    }
}