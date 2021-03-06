// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.resourcemanager.appservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The AzureActiveDirectoryValidation model. */
@JsonFlatten
@Fluent
public class AzureActiveDirectoryValidation extends ProxyOnlyResource {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(AzureActiveDirectoryValidation.class);

    /*
     * The jwtClaimChecks property.
     */
    @JsonProperty(value = "properties.jwtClaimChecks")
    private JwtClaimChecks jwtClaimChecks;

    /*
     * The allowedAudiences property.
     */
    @JsonProperty(value = "properties.allowedAudiences")
    private List<String> allowedAudiences;

    /**
     * Get the jwtClaimChecks property: The jwtClaimChecks property.
     *
     * @return the jwtClaimChecks value.
     */
    public JwtClaimChecks jwtClaimChecks() {
        return this.jwtClaimChecks;
    }

    /**
     * Set the jwtClaimChecks property: The jwtClaimChecks property.
     *
     * @param jwtClaimChecks the jwtClaimChecks value to set.
     * @return the AzureActiveDirectoryValidation object itself.
     */
    public AzureActiveDirectoryValidation withJwtClaimChecks(JwtClaimChecks jwtClaimChecks) {
        this.jwtClaimChecks = jwtClaimChecks;
        return this;
    }

    /**
     * Get the allowedAudiences property: The allowedAudiences property.
     *
     * @return the allowedAudiences value.
     */
    public List<String> allowedAudiences() {
        return this.allowedAudiences;
    }

    /**
     * Set the allowedAudiences property: The allowedAudiences property.
     *
     * @param allowedAudiences the allowedAudiences value to set.
     * @return the AzureActiveDirectoryValidation object itself.
     */
    public AzureActiveDirectoryValidation withAllowedAudiences(List<String> allowedAudiences) {
        this.allowedAudiences = allowedAudiences;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public AzureActiveDirectoryValidation withKind(String kind) {
        super.withKind(kind);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
        if (jwtClaimChecks() != null) {
            jwtClaimChecks().validate();
        }
    }
}
