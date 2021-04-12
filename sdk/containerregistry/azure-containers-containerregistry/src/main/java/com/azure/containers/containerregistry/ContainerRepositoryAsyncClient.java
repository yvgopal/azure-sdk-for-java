// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry;

import com.azure.containers.containerregistry.implementation.ContainerRegistriesImpl;
import com.azure.containers.containerregistry.implementation.ContainerRegistryImpl;
import com.azure.containers.containerregistry.implementation.ContainerRegistryImplBuilder;
import com.azure.containers.containerregistry.implementation.ContainerRegistryRepositoriesImpl;
import com.azure.containers.containerregistry.implementation.Utils;
import com.azure.containers.containerregistry.implementation.models.TagAttributesBase;
import com.azure.containers.containerregistry.models.ContentProperties;
import com.azure.containers.containerregistry.models.DeleteRepositoryResult;
import com.azure.containers.containerregistry.models.ListRegistryArtifactOptions;
import com.azure.containers.containerregistry.models.ListTagsOptions;
import com.azure.containers.containerregistry.models.RegistryArtifactProperties;
import com.azure.containers.containerregistry.models.RepositoryProperties;
import com.azure.containers.containerregistry.models.TagProperties;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceClient;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.azure.core.util.FluxUtil.monoError;
import static com.azure.core.util.FluxUtil.withContext;

/** Initializes a new instance of the asynchronous container repository client.
 * This client is used for interacting with the repository.
 * */
@ServiceClient(builder = ContainerRepositoryClientBuilder.class, isAsync = true)
public final class ContainerRepositoryAsyncClient {
    private final ContainerRegistryRepositoriesImpl serviceClient;
    private final ContainerRegistriesImpl registriesImplClient;
    private final String repositoryName;
    private final String endpoint;
    private final String apiVersion;

    private final ClientLogger logger = new ClientLogger(ContainerRepositoryAsyncClient.class);

    ContainerRepositoryAsyncClient(String repositoryName, HttpPipeline httpPipeline, String endpoint, String apiVersion) {
        if (repositoryName == null) {
            throw logger.logExceptionAsError(new NullPointerException("'repositoryName' can't be null"));
        }

        ContainerRegistryImpl registryImpl = new ContainerRegistryImplBuilder()
            .pipeline(httpPipeline)
            .url(endpoint).buildClient();

        this.endpoint = endpoint;
        this.repositoryName = repositoryName;
        this.registriesImplClient = registryImpl.getContainerRegistries();
        this.serviceClient = registryImpl.getContainerRegistryRepositories();
        this.apiVersion = apiVersion;
    }

    /**
     * Get endpoint associated with the class.
     * @return String the endpoint associated with this client.
     */
    public String getEndpoint() {
        return this.endpoint;
    }


    /**
     * Get the registry associated with the client.
     * @return Return the registry name.
     */
    public String getRegistry() {
        return this.endpoint;
    }

    /**
     * Get repository associated with the class.
     * @return Return the repository name.
     * */
    public String getRepository() {
        return this.repositoryName;
    }

    /**
     * Delete the repository.
     *
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given name was not found.
     * @return deleted repository properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DeleteRepositoryResult>> deleteWithResponse() {
        return withContext(context -> deleteWithResponse(context));
    }

    Mono<Response<DeleteRepositoryResult>> deleteWithResponse(Context context) {
        try {
            return this.registriesImplClient.deleteRepositoryWithResponseAsync(repositoryName, context)
                .map(res -> Utils.mapResponse(res, Utils::mapDeleteRepositoryResult))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    /**
     * Delete the repository.
     *
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given name was not found.
     * @return deleted repository properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DeleteRepositoryResult> delete() {
        return this.deleteWithResponse().flatMap(FluxUtil::toMono);
    }

    /**
     * Delete registry artifact.
     *
     * @param digest the digest to delete.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @throws NullPointerException thrown if digest is null.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteRegistryArtifactWithResponse(String digest) {
        return withContext(context -> this.deleteRegistryArtifactWithResponse(digest, context));
    }

    Mono<Response<Void>> deleteRegistryArtifactWithResponse(String digest, Context context) {
        try {
            if (digest == null) {
                return monoError(logger, new NullPointerException("'digest' cannot be null"));
            }

            return this.serviceClient.deleteManifestWithResponseAsync(repositoryName, digest, context)
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    /**
     * Delete registry artifact.
     *
     * @param digest digest to delete.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @throws NullPointerException thrown if digest is null.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteRegistryArtifact(String digest) {
        return this.deleteRegistryArtifactWithResponse(digest).flatMap(FluxUtil::toMono);
    }

    /**
     * Delete tag.
     *
     * @param tag tag to delete.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given tag was not found.
     * @throws NullPointerException thrown if tag is null.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteTagWithResponse(String tag) {
        return withContext(context -> this.deleteTagWithResponse(tag, context));
    }

    Mono<Response<Void>> deleteTagWithResponse(String tag, Context context) {
        try {
            if (tag == null) {
                return monoError(logger, new NullPointerException("'digest' cannot be null"));
            }

            return this.serviceClient.deleteTagWithResponseAsync(repositoryName, tag, context)
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    /**
     * Delete tag.
     *
     * @param tag tag to delete
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @throws NullPointerException thrown if tag is null.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteTag(String tag) {
        return this.deleteTagWithResponse(tag).flatMap(FluxUtil::toMono);
    }

    /**
     * Get repository properties.
     *
     * @return repository properties.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<RepositoryProperties>> getPropertiesWithResponse() {
        return withContext(context -> this.getPropertiesWithResponse(context));
    }

    Mono<Response<RepositoryProperties>> getPropertiesWithResponse(Context context) {
        try {
            if (context == null) {
                return monoError(logger, new NullPointerException("'context' cannot be null."));
            }
            return this.serviceClient.getPropertiesWithResponseAsync(repositoryName, context)
                .map(res -> Utils.mapResponse(res, Utils::mapRepositoryProperties))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    /**
     * Get repository properties.
     *
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given repository was not found.
     * @return repository properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RepositoryProperties> getProperties() {
        return this.getPropertiesWithResponse().flatMap(FluxUtil::toMono);
    }

    /**
     * <p>Get registry artifact properties.</p>
     *
     * <p>This method can take in both a digest as well as a tag.<br>
     * In case a tag is provided it calls the service to get the digest associated with it.</p>
     * @param tagOrDigest tag or digest associated with the artifact.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @return registry artifact properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<RegistryArtifactProperties>> getRegistryArtifactPropertiesWithResponse(String tagOrDigest) {
        return withContext(context -> this.getRegistryArtifactPropertiesWithResponse(tagOrDigest, context));
    }

    Mono<Response<RegistryArtifactProperties>> getRegistryArtifactPropertiesWithResponse(String tagOrDigest, Context context) {
        try {
            Mono<String> getTagMono = tagOrDigest.contains(":")
                ? Mono.just(tagOrDigest)
                : this.getTagProperties(tagOrDigest).map(a -> a.getDigest());

            return getTagMono
                .flatMap(digest -> this.serviceClient.getRegistryArtifactPropertiesWithResponseAsync(repositoryName, digest))
                .map(res -> Utils.mapResponse(res, Utils::mapArtifactProperties))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    /**
     * <p>Get registry artifact properties.</p>
     *
     * <p>This method can take in both a digest as well as a tag.<br>
     * In case a tag is provided it calls the service to get the digest associated with it.</p>
     * @param tagOrDigest tag or digest associated with the artifact.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @return registry artifact properties.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RegistryArtifactProperties> getRegistryArtifactProperties(String tagOrDigest) {
        return this.getRegistryArtifactPropertiesWithResponse(tagOrDigest).flatMap(FluxUtil::toMono);
    }

    /**
     * List registry artifacts of a repository.
     *
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @return manifest attributes.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<RegistryArtifactProperties> listRegistryArtifacts() {
        return listRegistryArtifacts(null);
    }

    /**
     * List registry artifacts of a repository.
     *
     * @param options the options associated with the list registry operation.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @return manifest attributes.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<RegistryArtifactProperties> listRegistryArtifacts(ListRegistryArtifactOptions options) {
        return new PagedFlux<>(
            (pageSize) -> withContext(context -> listRegistryArtifactsSinglePageAsync(pageSize, options, context)),
            (token, pageSize) -> withContext(context -> listRegistryArtifactsNextSinglePageAsync(token, context)));
    }

    Mono<PagedResponse<RegistryArtifactProperties>> listRegistryArtifactsSinglePageAsync(Integer pageSize, ListRegistryArtifactOptions options, Context context) {
        try {
            if (pageSize != null && pageSize < 0) {
                return monoError(logger, new IllegalArgumentException("'pageSize' cannot be negative."));
            }

            String orderBy = null;
            if (options != null && options.getRegistryArtifactOrderBy() != null) {
                orderBy = options.getRegistryArtifactOrderBy().toString();
            }

            return this.serviceClient.getManifestsSinglePageAsync(repositoryName, null, pageSize, orderBy, context)
                .map(res -> Utils.getPagedResponseWithContinuationToken(res, Utils::getRegistryArtifactsProperties))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }

    Mono<PagedResponse<RegistryArtifactProperties>> listRegistryArtifactsNextSinglePageAsync(String nextLink, Context context) {
        try {
            return this.serviceClient.getManifestsNextSinglePageAsync(nextLink, context)
                .map(res -> Utils.getPagedResponseWithContinuationToken(res, Utils::getRegistryArtifactsProperties))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }


    /**
     * Get tag properties
     *
     * @param tag name of the tag.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given tag was not found.
     * @return tag properties by tag.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TagProperties>> getTagPropertiesWithResponse(String tag) {
        return withContext(context -> getTagPropertiesWithResponse(tag, context));
    }

    Mono<Response<TagProperties>> getTagPropertiesWithResponse(String tag, Context context) {
        try {
            if (tag == null) {
                return monoError(logger, new NullPointerException("'tag' cannot be null."));
            }

            return this.serviceClient.getTagPropertiesWithResponseAsync(repositoryName, tag, context)
                .map(res -> Utils.mapResponse(res, Utils::mapTagProperties))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException ex) {
            return monoError(logger, ex);
        }
    }

    /**
     * Get tag attributes.
     *
     * @param tag name of the tag.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given tag was not found.
     * @return tag properties by tag.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TagProperties> getTagProperties(String tag) {
        return this.getTagPropertiesWithResponse(tag).flatMap(FluxUtil::toMono);
    }

    /**
     * List tags of a repository.
     *
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @return list of tag details.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<TagProperties> listTags() {
        return listTags(null);
    }

    /**
     * List tags of a repository.
     *
     * @param options tagOptions to be used for the given operation.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @return list of tag details.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    public PagedFlux<TagProperties> listTags(ListTagsOptions options) {
        return new PagedFlux<>(
            (pageSize) -> withContext(context -> listTagsSinglePageAsync(pageSize, options, context)),
            (token, pageSize) -> withContext(context -> listTagsNextSinglePageAsync(token, context)));
    }

    Mono<PagedResponse<TagProperties>> listTagsSinglePageAsync(Integer pageSize, ListTagsOptions options, Context context) {
        try {
            if (pageSize != null && pageSize < 0) {
                return monoError(logger, new IllegalArgumentException("'pageSize' cannot be negative."));
            }

            String orderBy = null;
            if (options != null && options.getTagOrderBy() != null) {
                orderBy = options.getTagOrderBy().toString();
            }

            return this.serviceClient.getTagsSinglePageAsync(repositoryName, null, pageSize, orderBy, null, context)
                .map(res -> Utils.getPagedResponseWithContinuationToken(res, this::getTagProperties))
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }

    private List<TagProperties> getTagProperties(List<TagAttributesBase> baseValues) {
        Objects.requireNonNull(baseValues);

        return baseValues.stream().map(value -> new TagProperties(
            value.getName(),
            repositoryName,
            value.getDigest(),
            value.getWriteableProperties(),
            value.getCreatedOn(),
            value.getLastUpdatedOn()
        )).collect(Collectors.toList());
    }

    Mono<PagedResponse<TagProperties>> listTagsNextSinglePageAsync(String nextLink, Context context) {
        try {
            return this.serviceClient.getTagsNextSinglePageAsync(nextLink, context)
                .map(res -> Utils.getPagedResponseWithContinuationToken(res, this::getTagProperties));
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }

    /**
     * Update the content properties of the repository.
     *
     * @param value Content properties to be set.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> setPropertiesWithResponse(ContentProperties value) {
        return withContext(context -> this.setPropertiesWithResponse(value, context));
    }

    Mono<Response<Void>> setPropertiesWithResponse(ContentProperties value, Context context) {
        try {
            if (value == null) {
                return monoError(logger, new NullPointerException("'value' cannot be null."));
            }

            return this.serviceClient.setPropertiesWithResponseAsync(repositoryName, value, context)
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }

    /**
     * Update the content properties of the repository.
     *
     * @param value Content properties to be set.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> setProperties(ContentProperties value) {
        return this.setPropertiesWithResponse(value).flatMap(FluxUtil::toMono);
    }

    /**
     * Update tag properties.
     *
     * @param tag Name of the tag.
     * @param value content properties to be set.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given tag was not found.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> setTagPropertiesWithResponse(
            String tag, ContentProperties value) {
        return withContext(context -> this.setTagPropertiesWithResponse(tag, value, context));
    }

    Mono<Response<Void>> setTagPropertiesWithResponse(
        String tag, ContentProperties value, Context context) {
        try {
            if (tag == null) {
                return monoError(logger, new NullPointerException("'tag' cannot be null."));
            }

            if (value == null) {
                return monoError(logger, new NullPointerException("'value' cannot be null."));
            }

            return this.serviceClient.updateTagAttributesWithResponseAsync(repositoryName, tag, value, context)
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }

    /**
     * Update tag properties.
     *
     * @param tag Name of the tag.
     * @param value content properties to be set.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given tag was not found.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> setTagProperties(String tag, ContentProperties value) {
        return this.setTagPropertiesWithResponse(tag, value).flatMap(FluxUtil::toMono);
    }

    /**
     * Update properties of a manifest.
     *
     * @param digest digest.
     * @param value content properties to be set.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> setManifestPropertiesWithResponse(
            String digest, ContentProperties value) {
        return withContext(context -> this.setManifestPropertiesWithResponse(digest, value, context));
    }

    Mono<Response<Void>> setManifestPropertiesWithResponse(String digest, ContentProperties value, Context context) {
        try {
            if (digest == null) {
                return monoError(logger, new NullPointerException("'digest' cannot be null."));
            }

            if (value == null) {
                return monoError(logger, new NullPointerException("'value' cannot be null."));
            }

            return this.serviceClient.updateManifestAttributesWithResponseAsync(repositoryName, digest, value, context)
                .onErrorMap(Utils::mapException);
        } catch (RuntimeException e) {
            return monoError(logger, e);
        }
    }

    /**
     * Update properties of a manifest.
     *
     * @param digest digest.
     * @param value content properties to be set.
     * @throws ClientAuthenticationException thrown if the client's credentials do not have access to modify the namespace.
     * @throws ResourceNotFoundException thrown if the given digest was not found.
     * @return the completion.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> setManifestProperties(String digest, ContentProperties value) {
        return this.setManifestPropertiesWithResponse(digest, value).flatMap(FluxUtil::toMono);
    }
}
