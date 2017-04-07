/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.test.builders;

import org.keycloak.representations.idm.ClientRepresentation;

import java.util.Collections;

/**
 * @author <a href="mailto:bruno@abstractj.org">Bruno Oliveira</a>
 */
public class ClientBuilder {

    private ClientRepresentation rep;

    public static ClientBuilder create(String clientId) {
        ClientRepresentation rep = new ClientRepresentation();
        rep.setEnabled(Boolean.TRUE);
        rep.setClientId(clientId);
        return new ClientBuilder(rep);
    }

    private ClientBuilder(ClientRepresentation rep) {
        this.rep = rep;
    }

    public ClientRepresentation bearerOnly(boolean bearerOnly) {
        rep.setBearerOnly(bearerOnly);
        return rep;
    }

    public ClientBuilder rootUrl(String rootUrl) {
        rep.setRootUrl(rootUrl);
        return this;
    }

    public ClientBuilder redirectUri(String redirectUri) {
        rep.setRedirectUris(Collections.singletonList(redirectUri));
        return this;
    }

    public ClientBuilder baseUrl(String baseUrl) {
        rep.setBaseUrl(baseUrl);
        return this;
    }

    public ClientBuilder adminUrl(String adminUrl) {
        rep.setAdminUrl(adminUrl);
        return this;
    }

    public ClientRepresentation publicClient(boolean publicClient) {
        rep.setFullScopeAllowed(true);
        rep.setPublicClient(publicClient);
        rep.setDirectAccessGrantsEnabled(true);
        rep.setAdminUrl(rep.getRootUrl());

        if (rep.getRedirectUris() == null && rep.getRootUrl() != null)
            rep.setRedirectUris(Collections.singletonList(rep.getRootUrl().concat("/*")));
        return rep;
    }

}
