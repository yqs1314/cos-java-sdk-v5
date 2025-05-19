package com.qcloud.cos.demo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.ResponseHeaderOverrides;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.utils.DateUtils;

/**
 * GeneratePresignedUrlDemo展示了生成预签名的下载链接与上传连接的使用示例.
 * 用于可将生成的连接分发给移动端或者他人, 即可实现在签名有效期内上传或者下载文件.
 */
public class GeneratePresignedUrlDemo {
    private static String secretId = "AKIDXXXXXXXX";
    private static String secretKey = "1A2Z3YYYYYYYYYY";
    private static String bucketName = "examplebucket-12500000000";
    private static String region = "ap-guangzhou";
    private static COSClient cosClient = createCli();
    public static void main(String[] args) {
        try {
            generatePresignedUploadUrl();
        } catch (Exception e) {
            cosClient.shutdown();
        }
    }

    private static COSClient createCli() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);

        return cosclient;
    }

    // 获取下载的预签名连接
    private static void generateSimplePresignedDownloadUrl() {
        String key = "aaa.txt";
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置则默认使用ClientConfig中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        req.setExpiration(expirationDate);

        // 填写本次请求的参数
        req.addRequestParameter("param1", "value1");

        // 填写本次请求的头部。Host 头部会自动补全，不需要填写
        req.putCustomRequestHeader("header1", "value1");

        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());
    }

    // 获取预签名的下载链接, 并设置返回的content-type, cache-control等http头
    private static void generatePresignedDownloadUrlWithOverrideResponseHeader() {
        String key = "aaa.txt";
        GeneratePresignedUrlRequest req =
                new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
        // 设置下载时返回的http头
        ResponseHeaderOverrides responseHeaders = new ResponseHeaderOverrides();
        String responseContentType = "image/x-icon";
        String responseContentLanguage = "zh-CN";
        String responseContentDispositon = "filename=\"abc.txt\"";
        String responseCacheControl = "no-cache";
        String cacheExpireStr =
                DateUtils.formatRFC822Date(new Date(System.currentTimeMillis() + 24 * 3600 * 1000));
        responseHeaders.setContentType(responseContentType);
        responseHeaders.setContentLanguage(responseContentLanguage);
        responseHeaders.setContentDisposition(responseContentDispositon);
        responseHeaders.setCacheControl(responseCacheControl);
        responseHeaders.setExpires(cacheExpireStr);
        req.setResponseHeaders(responseHeaders);
        // 设置签名过期时间(可选), 若未进行设置则默认使用ClientConfig中的签名过期时间(1小时)
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        req.setExpiration(expirationDate);

        // 填写本次请求的参数
        req.addRequestParameter("param1", "value1");

        // 填写本次请求的头部。Host 头部会自动补全，不需要填写
        req.putCustomRequestHeader("header1", "value1");

        URL url = cosClient.generatePresignedUrl(req);
        System.out.println(url.toString());
    }

    // 生成预签名的上传连接
    private static void generatePresignedUploadUrl() {
        String key = "aaa.txt";
        Date expirationTime = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
        // 填写本次请求的 header。Host 头部会自动补全，只需填入其他头部
        Map<String, String> headers = new HashMap<String,String>();
        // 填写本次请求的 params。
        Map<String, String> params = new HashMap<String,String>();

        URL url = cosClient.generatePresignedUrl(bucketName, key, expirationTime, HttpMethodName.PUT, headers, params);
        System.out.println(url.toString());
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            // 写入要上传的数据 
            out.write("This text uploaded as object.");
            out.close();
            int responseCode = connection.getResponseCode();
            System.out.println("Service returned response code " + responseCode);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
