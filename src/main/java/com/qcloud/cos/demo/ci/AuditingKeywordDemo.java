package com.qcloud.cos.demo.ci;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ciModel.auditing.AuditingKeyword;
import com.qcloud.cos.model.ciModel.auditing.AuditingKeywordRequest;
import com.qcloud.cos.model.ciModel.auditing.AuditingKeywordResponse;
import com.qcloud.cos.utils.Jackson;

import java.util.List;

/**
 * 内容审核 自定义文本库关键词相关demo
 */
public class AuditingKeywordDemo {

    public static void main(String[] args) {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSClient client = ClientUtils.getTestClient();
        // 2 调用要使用的方法。
        deleteAuditingKeyWordList(client);
    }

    /**
     * addAuditingLibKeyWord 添加文本库关键词用于向指定的文本库中添加关键词
     * 该接口属于 POST 请求。
     */
    public static void addAuditingLibKeyWord(COSClient client) {
        AuditingKeywordRequest request = new AuditingKeywordRequest();
        request.setBucketName("demo-1234567890");
        request.setLibId("e469c64b-0b74-4c56-a35e-c5e8c08*****");
        List<AuditingKeyword> keywords = request.getKeywords();
        AuditingKeyword keyword = new AuditingKeyword();
        keyword.setContent("demoContent1");
        keyword.setRemark("remark1");
        keyword.setLabel("Ads");
        keywords.add(keyword);

        keyword = new AuditingKeyword();
        keyword.setContent("demoContent2");
        keyword.setRemark("remark2");
        keyword.setLabel("Ads");
        keywords.add(keyword);
        AuditingKeywordResponse response = client.addAuditingLibKeyWord(request);
        System.out.println(Jackson.toJsonString(response));
    }

    /**
     * describeAuditingKeyWordList 查询文本库关键词
     * 该接口属于 GET 请求。
     */
    public static void describeAuditingKeyWordList(COSClient client) {
        AuditingKeywordRequest request = new AuditingKeywordRequest();
        request.setBucketName("demo-1234567890");
        request.setLibId("e469c64b-0b74-4c56-a35e-c5e8c08*****");
        AuditingKeywordResponse response = client.describeAuditingKeyWordList(request);
        System.out.println(Jackson.toJsonString(response));
    }

    /**
     * deleteAuditingKeyWordList 批量删除文本库关键词
     * 该接口属于 DELETE 请求。
     */
    public static void deleteAuditingKeyWordList(COSClient client) {
        AuditingKeywordRequest request = new AuditingKeywordRequest();
        request.setBucketName("demo-1234567890");
        request.setLibId("e469c64b-0b74-4c56-a35e-c5e8c08*****");

        List<String> keywordIDs = request.getKeywordIDs();
        keywordIDs.add("10884372");
        keywordIDs.add("10884373");
        AuditingKeywordResponse response = client.deleteAuditingKeyWord(request);
        System.out.println(Jackson.toJsonString(response));
    }

}
