package com.qcloud.cos.demo;

import java.util.List;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.AccessControlList;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.Grant;
import com.qcloud.cos.model.Owner;
import com.qcloud.cos.model.Permission;
import com.qcloud.cos.model.UinGrantee;
import com.qcloud.cos.region.Region;

public class SetGetObjectAclDemo {
    public static void main(String[] args) throws InterruptedException {
        setGetObjectAclTest();
    }

    private static void setGetObjectAclTest() {
        // 1 初始化用户身份信息(secretId, secretKey)
	COSCredentials cred = new BasicCOSCredentials("AKIDXXXXXXXX", "1A2Z3YYYYYYYYYY");
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-guangzhou"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket名需包含appid
        String bucketName = "mybucket-12500000000";
        String key = "aaa/bbb.txt";
        cosclient.putObject(bucketName, key, "data");

        // 设置对象的acl
        AccessControlList acl = new AccessControlList();
        Owner owner = new Owner();
        owner.setId("qcs::cam::uin/100000000001:uin/100000000001");
        acl.setOwner(owner);
        // 设置子账号100000000002具有WriteAcp权限
        String id = "qcs::cam::uin/100000000001:uin/100000000002";
        UinGrantee uinGrantee = new UinGrantee(id);
        uinGrantee.setIdentifier(id);
        // 设置子账号100000000003具有Read权限
        acl.grantPermission(uinGrantee, Permission.WriteAcp);
        String id1 = "qcs::cam::uin/100000000001:uin/100000000003";
        UinGrantee uinGrantee1 = new UinGrantee(id1);
        uinGrantee.setIdentifier(id1);
        acl.grantPermission(uinGrantee1, Permission.Read);
        cosclient.setObjectAcl(bucketName, key, acl);

        // 获取对象的acl
        AccessControlList aclGet = cosclient.getObjectAcl(bucketName, key);
        List<Grant> grants = aclGet.getGrantsAsList();

        // private object acl
        cosclient.setObjectAcl(bucketName, key, CannedAccessControlList.Private);
        AccessControlList accessControlList = cosclient.getObjectAcl(bucketName, key);
        System.out.println("private object acl:" + accessControlList.getCannedAccessControl());

        // public-read object acl
        cosclient.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
        accessControlList = cosclient.getObjectAcl(bucketName, key);
        System.out.println("public-read object acl:" + accessControlList.getCannedAccessControl());

        // default object acl
        cosclient.setObjectAcl(bucketName, key, CannedAccessControlList.Default);
        accessControlList = cosclient.getObjectAcl(bucketName, key);
        System.out.println("default object acl:" + accessControlList.getCannedAccessControl());

    }
}

