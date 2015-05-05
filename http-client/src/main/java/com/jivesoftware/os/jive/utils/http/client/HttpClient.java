/*
 * Copyright 2013 Jive Software, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jivesoftware.os.jive.utils.http.client;

import java.util.Map;

public interface HttpClient {
    //TODO need to create request object to pass as param to these methods.

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse get(String path) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse get(String path, Map<String, String> headers) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpStreamResponse getStream(String path) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse get(String path, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse get(String path, Map<String, String> headers, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpStreamResponse getStream(String path, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse delete(String path) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse delete(String path, Map<String, String> headers) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpStreamResponse deleteStream(String path) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse delete(String path, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse delete(String path, Map<String, String> headers, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpStreamResponse deleteStream(String path, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse postJson(String path, String postJsonBody) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse postJson(String path, String postJsonBody, Map<String, String> headers) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse postJson(String path, String postJsonBody, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse postJson(String path, String postJsonBody, Map<String, String> headers, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse postBytes(String path, byte[] postBytes) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse postBytes(String path, byte[] postBytes, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse putJson(String path, String putJsonBody) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse putJson(String path, String putJsonBody, Map<String, String> headers) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse putJson(String path, String putJsonBody, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse putJson(String path, String putJsonBody, Map<String, String> headers, int timeoutMillis) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse putBytes(String path, byte[] putBytes) throws HttpClientException;

    /**
     * @param path everything but the leading "http/s://host:port"
     */
    HttpResponse putBytes(String path, byte[] putBytes, int timeoutMillis) throws HttpClientException;

}