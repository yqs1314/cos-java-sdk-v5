package com.qcloud.cos.model.ciModel.auditing;


public class BatchImageJobDetail {
    private String dataId;
    private String jobId;
    private String category;
    private String label;
    private String result;
    private String object;
    private String score;
    private String subLabel;
    private String text;
    private String code;
    private String message;
    private String url;
    private String compressionResult;
    private PornInfo pornInfo;
    private TerroristInfo terroristInfo;
    private PoliticsInfo politicsInfo;
    private AdsInfo adsInfo;
    private UserInfo userInfo = new UserInfo();
    private ListInfo listInfo = new ListInfo();
    private OcrResults ocrResults = new OcrResults();

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSubLabel() {
        return subLabel;
    }

    public void setSubLabel(String subLabel) {
        this.subLabel = subLabel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public PornInfo getPornInfo() {
        if (pornInfo == null) {
            pornInfo = new PornInfo();
        }
        return pornInfo;
    }

    public void setPornInfo(PornInfo pornInfo) {
        this.pornInfo = pornInfo;
    }

    public TerroristInfo getTerroristInfo() {
        if (terroristInfo == null) {
            terroristInfo = new TerroristInfo();
        }
        return terroristInfo;
    }

    public void setTerroristInfo(TerroristInfo terroristInfo) {
        this.terroristInfo = terroristInfo;
    }

    public PoliticsInfo getPoliticsInfo() {
        if (politicsInfo == null) {
            politicsInfo = new PoliticsInfo();
        }
        return politicsInfo;
    }

    public void setPoliticsInfo(PoliticsInfo politicsInfo) {
        this.politicsInfo = politicsInfo;
    }

    public AdsInfo getAdsInfo() {
        if (adsInfo == null) {
            adsInfo = new AdsInfo();
        }
        return adsInfo;
    }

    public OcrResults getOcrResults() {
        return ocrResults;
    }

    public void setOcrResults(OcrResults ocrResults) {
        this.ocrResults = ocrResults;
    }

    public void setAdsInfo(AdsInfo adsInfo) {
        this.adsInfo = adsInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCompressionResult() {
        return compressionResult;
    }

    public void setCompressionResult(String compressionResult) {
        this.compressionResult = compressionResult;
    }

    public ListInfo getListInfo() {
        return listInfo;
    }

    public void setListInfo(ListInfo listInfo) {
        this.listInfo = listInfo;
    }

    @Override
    public String toString() {
        return "BatchImageJobDetail{" +
                "dataId='" + dataId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", category='" + category + '\'' +
                ", label='" + label + '\'' +
                ", result='" + result + '\'' +
                ", object='" + object + '\'' +
                ", score='" + score + '\'' +
                ", subLabel='" + subLabel + '\'' +
                ", text='" + text + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", pornInfo=" + pornInfo +
                ", terroristInfo=" + terroristInfo +
                ", politicsInfo=" + politicsInfo +
                ", adsInfo=" + adsInfo +
                ", userInfo=" + userInfo +
                ", ocrResults=" + ocrResults +
                '}';
    }
}
