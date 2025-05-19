/*
 * Copyright 2010-2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.

 * According to cos feature, we modify some class，comment, field name, etc.
 */

package com.qcloud.cos.retry;

import java.io.IOException;
import java.util.Objects;

import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.CosHttpRequest;
import com.qcloud.cos.internal.CosServiceRequest;

import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadPartRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.CircularRedirectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PredefinedRetryPolicies {

    /**
     * No retry policy
     **/
    public static final RetryPolicy NO_RETRY_POLICY = new RetryPolicy() {
        @Override
        public <X extends CosServiceRequest> boolean shouldRetry(CosHttpRequest<X> request,
                HttpResponse response,
                Exception exception,
                int retryIndex) {
            return false;
        }
    };

    /**
     * SDK default retry policy
     */
    public static final RetryPolicy DEFAULT;

    static {
        DEFAULT = getDefaultRetryPolicy();
    }

    public static class SdkDefaultRetryPolicy extends RetryPolicy {
        private static final Logger log = LoggerFactory.getLogger(SdkDefaultRetryPolicy.class);
        @Override
        public <X extends CosServiceRequest> boolean shouldRetry(CosHttpRequest<X> request,
                HttpResponse response,
                Exception exception,
                int retryIndex) {
            if (RetryUtils.isRetryableServiceException(exception)) {
                if (request.getParameters().containsKey("preflight")) {
                    return false;
                }

                if (!isRetryAfterPreflight() && request.getOriginalRequest() != null && request.getOriginalRequest() instanceof PutObjectRequest && ((PutObjectRequest) request.getOriginalRequest()).hasDonePreflight()) {
                    if (((CosServiceException) exception).getStatusCode() == 503 && Objects.equals(((CosServiceException) exception).getErrorCode(), "SlowDown")) {
                        log.info("will not retry for 503 while putting object, because preflight request has been done");
                        return false;
                    }
                }

                if (!isRetryAfterPreflight() && request.getOriginalRequest() != null && request.getOriginalRequest() instanceof UploadPartRequest && ((UploadPartRequest) request.getOriginalRequest()).hasDonePreflight()) {
                    if (((CosServiceException) exception).getStatusCode() == 503 && Objects.equals(((CosServiceException) exception).getErrorCode(), "SlowDown")) {
                        log.info("will not retry for 503 while upload part, because preflight request has been done");
                        return false;
                    }
                }

                return true;
            }

            // Always retry on client exceptions caused by IOException
            if (exception.getCause() instanceof IOException) {
                if (exception.getCause().getCause() != null && exception.getCause().getCause() instanceof CircularRedirectException) {
                    log.error("CircularRedirectException will not retry");
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    /**
     * Returns the SDK default retry policy. This policy will honor the
     * maxErrorRetry set in ClientConfiguration.
     *
     * @see com.qcloud.cos.ClientConfig#setMaxErrorRetry(int)
     */
    public static RetryPolicy getDefaultRetryPolicy() {
        return new SdkDefaultRetryPolicy();
    }

}
