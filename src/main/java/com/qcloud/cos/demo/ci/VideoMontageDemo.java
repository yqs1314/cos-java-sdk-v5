package com.qcloud.cos.demo.ci;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ciModel.job.MediaAudioMixObject;
import com.qcloud.cos.model.ciModel.job.MediaAudioObject;
import com.qcloud.cos.model.ciModel.job.MediaJobOperation;
import com.qcloud.cos.model.ciModel.job.MediaJobResponse;
import com.qcloud.cos.model.ciModel.job.MediaJobsRequest;
import com.qcloud.cos.model.ciModel.job.MediaVideoObject;
import com.qcloud.cos.model.ciModel.template.MediaTemplateRequest;
import com.qcloud.cos.model.ciModel.template.MediaTemplateResponse;
import com.qcloud.cos.model.ciModel.template.MediaVideoMontageObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 媒体处理 VideoMontage job接口相关demo 详情见https://cloud.tencent.com/document/product/460/58325
 */
public class VideoMontageDemo {

    public static void main(String[] args) throws Exception {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSClient client = ClientUtils.getTestClient();
        // 2 调用要使用的方法。
        createMediaJobs(client);
    }

    /**
     * createMediaJobs 接口用于创建媒体任务。
     * demo 使用模板创建任务，如需自定义模板请先使用创建模板接口
     *
     * @param client
     */
    public static void createMediaJobs(COSClient client) throws UnsupportedEncodingException {
        //1.创建任务请求对象
        MediaJobsRequest request = new MediaJobsRequest();
        //2.添加请求参数 参数详情请见api接口文档
        request.setBucketName("demobucket-1234567890");
        request.setTag("VideoMontage");
        request.getInput().setObject("1.mp4");
        MediaJobOperation operation = request.getOperation();
        MediaVideoMontageObject videoMontage = operation.getVideoMontage();
        videoMontage.getContainer().setFormat("mp4");
        MediaVideoObject video = videoMontage.getVideo();
        video.setCodec("H.264");
        video.setBitrate("1000");
        video.setWidth("1280");
        MediaAudioObject audio = videoMontage.getAudio();
        audio.setCodec("aac");
        audio.setSamplerate("44100");
        audio.setBitrate("128");
        audio.setChannels("4");
        MediaAudioMixObject audioMix = videoMontage.getAudioMix();
        audioMix.setMixMode("Once");
        audioMix.setAudioSource("https://demobucket-1234567890.cos.ap-chongqing.myqcloud.com/1.mp3");
        audioMix.setReplace("true");
        operation.getOutput().setBucket("demobucket-1234567890");
        operation.getOutput().setRegion("ap-chongqing");
        operation.getOutput().setObject("Montage.mp4");
        //3.调用接口,获取任务响应对象
        MediaJobResponse response = client.createMediaJobs(request);
        System.out.println(response);
    }

    /**
     * CreateMediaTemplate 接口用于创建媒体任务模板。
     * demo 精彩集锦模板创建
     *
     * @param client
     */
    public static void createMediaTemplate(COSClient client) throws UnsupportedEncodingException {
        //1.创建模板请求对象
        MediaTemplateRequest request = new MediaTemplateRequest();
        //2.添加请求参数 参数详情请见api接口文档
        request.setBucketName("demobucket-1234567890");
        request.setTag("VideoMontage");
        request.setName("VideoMontage1");
        request.setScene("Video");
        request.getContainer().setFormat("mp4");
        MediaVideoObject video = request.getVideo();
        video.setCodec("H.264");
        video.setBitrate("1000");
        video.setWidth("1280");
        video.setFps("30");

        List<MediaAudioMixObject> audioMixArray = request.getAudioMixArray();
        MediaAudioMixObject mediaAudioMixObject = new MediaAudioMixObject();
        mediaAudioMixObject.setAudioSource("https://demobucket-1234567890.cos.ap-chongqing.myqcloud.com/1.mp3");
        mediaAudioMixObject.setReplace("true");
        audioMixArray.add(mediaAudioMixObject);

        mediaAudioMixObject = new MediaAudioMixObject();
        mediaAudioMixObject.setAudioSource("https://demobucket-1234567890.cos.ap-chongqing.myqcloud.com/1.mp3");
        mediaAudioMixObject.setReplace("true");
        audioMixArray.add(mediaAudioMixObject);
        //3.调用接口,获取模板响应对象
        MediaTemplateResponse response = client.createMediaTemplate(request);
        System.out.println(response);
    }
}
