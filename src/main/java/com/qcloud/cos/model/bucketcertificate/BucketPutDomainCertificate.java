/*
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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
package com.qcloud.cos.model.bucketcertificate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class BucketPutDomainCertificate implements Serializable {
    private BucketDomainCertificateInfo bucketDomainCertificateInfo;
    private List<String> DomainList = new LinkedList<>();


    public void setBucketDomainCertificateInfo(BucketDomainCertificateInfo bucketDomainCertificateInfo) {
        this.bucketDomainCertificateInfo = bucketDomainCertificateInfo;
    }

    public void setDomainList(List<String> domainList) {
        DomainList = domainList;
    }

    public BucketDomainCertificateInfo getBucketDomainCertificateInfo(){
        return this.bucketDomainCertificateInfo;
    }

    public List<String> getDomainList(){
        return this.DomainList;
    }
}
