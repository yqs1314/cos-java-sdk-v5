package com.qcloud.cos.demo.ci;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ciModel.common.MediaOutputObject;
import com.qcloud.cos.model.ciModel.job.AudioConfig;
import com.qcloud.cos.model.ciModel.job.MediaJobResponse;
import com.qcloud.cos.model.ciModel.job.MediaJobsRequest;
import com.qcloud.cos.model.ciModel.job.VoiceSeparate;

/**
 * 媒体处理 人声分离job接口相关demo 详情见https://cloud.tencent.com/document/product/460/76918
 */
public class VoiceSeparateJobDemo {

    public static void main(String[] args) throws Exception {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSClient client = ClientUtils.getTestClient();
        // 2 调用要使用的方法。
        createMediaJobs(client);
    }

    /**
     * createMediaJobs 接口用于创建媒体任务
     * 人声分离 demo 使用自定义参数创建任务
     * 推荐使用模板创建任务
     *
     * @param client
     */
    public static void createMediaJobs(COSClient client) {
        //1.创建任务请求对象
        MediaJobsRequest request = new MediaJobsRequest();
        //2.添加请求参数 参数详情请见api接口文档
        request.setBucketName("demo-1234567890");
        request.setTag("VoiceSeparate");
        request.getInput().setObject("1.mp4");
        //2.1添加媒体任务操作参数
        VoiceSeparate voiceSeparate = request.getOperation().getVoiceSeparate();
        voiceSeparate.setAudioMode("AudioAndBackground");
        AudioConfig audioConfig = voiceSeparate.getAudioConfig();
        audioConfig.setBitrate("500");
        audioConfig.setSamplerate("44100");
        audioConfig.setCodec("mp3");
        audioConfig.setChannels("2");
        MediaOutputObject output = request.getOperation().getOutput();
        output.setBucket("demo-1234567890");
        output.setRegion("ap-chongqing");
        output.setObject("demo.mp3");
        output.setAuObject("au.mp3");
        request.setCallBack("https://cloud.tencent.com/xxx");
        //3.调用接口,获取任务响应对象
        MediaJobResponse response = client.createMediaJobs(request);
        System.out.println(response.getJobsDetail().getJobId());
    }

    /**
     * describeMediaJob 根据jobId查询任务信息
     *
     * @param client
     */
    public static void describeMediaJob(COSClient client) {
        //1.创建任务请求对象
        MediaJobsRequest request = new MediaJobsRequest();
        //2.添加请求参数 参数详情请见api接口文档
        request.setBucketName("demo-1234567890");
        request.setJobId("jccc9be6e7b7c11edb66a4b87d14*****");
        //3.调用接口,获取任务响应对象
        MediaJobResponse response = client.describeMediaJob(request);
        System.out.println(response);
    }
}
