package com.qcloud.cos.model.ciModel.template;


import com.qcloud.cos.model.ciModel.job.MediaAudioMixObject;
import com.qcloud.cos.model.ciModel.job.MediaAudioObject;
import com.qcloud.cos.model.ciModel.job.MediaContainerObject;
import com.qcloud.cos.model.ciModel.job.MediaVideoObject;

import java.util.ArrayList;
import java.util.List;

public class MediaVideoMontageObject {
    public MediaVideoMontageObject(MediaTemplateRequest request) {
        container = request.getContainer();
        video = request.getVideo();
        duration = request.getDuration();
        audio = request.getAudio();
        audioMix = request.getAudioMix();
        scene = request.getScene();
        audioMixArray = request.getAudioMixArray();
    }

    public MediaVideoMontageObject() {

    }

    /**
     * 容器格式
     */
    private MediaContainerObject container;
    /**
     * 视频信息
     */
    private MediaVideoObject video;
    /**
     * 集锦时长
     * 1. 默认自动分析时长
     * 2. 单位为秒
     * 3. 支持 float 格式，执行精度精确到毫秒
     */
    private String duration;
    /**
     * 音频信息
     */
    private MediaAudioObject audio;
    /**
     * 混音参数
     */
    private MediaAudioMixObject audioMix;

    private String scene;

    private List<MediaAudioMixObject> audioMixArray;

    public MediaContainerObject getContainer() {
        if (container == null) {
            container = new MediaContainerObject();
        }
        return container;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public void setContainer(MediaContainerObject container) {
        this.container = container;
    }

    public MediaVideoObject getVideo() {
        if (video == null) {
            video = new MediaVideoObject();
        }
        return video;
    }

    public void setVideo(MediaVideoObject video) {
        this.video = video;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public MediaAudioObject getAudio() {
        if (audio == null) {
            audio = new MediaAudioObject();
        }
        return audio;
    }

    public void setAudio(MediaAudioObject audio) {
        this.audio = audio;
    }

    public MediaAudioMixObject getAudioMix() {
        if (audioMix == null) {
            audioMix = new MediaAudioMixObject();
        }
        return audioMix;
    }

    public void setAudioMix(MediaAudioMixObject audioMix) {
        this.audioMix = audioMix;
    }

    public List<MediaAudioMixObject> getAudioMixArray() {
        if (audioMixArray == null) {
            audioMixArray = new ArrayList<>();
        }
        return audioMixArray;
    }

    public void setAudioMixArray(List<MediaAudioMixObject> audioMixArray) {
        this.audioMixArray = audioMixArray;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MediaVideoMontageObject{");
        sb.append("container=").append(container);
        sb.append(", video=").append(video);
        sb.append(", duration='").append(duration).append('\'');
        sb.append(", audio='").append(audio).append('\'');
        sb.append(", audioMix=").append(audioMix);
        sb.append('}');
        return sb.toString();
    }
}
