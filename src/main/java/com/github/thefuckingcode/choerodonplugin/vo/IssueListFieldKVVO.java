package com.github.thefuckingcode.choerodonplugin.vo;

import com.github.thefuckingcode.choerodonplugin.ui.WorkHourDialog;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IssueListFieldKVVO {


    private String issueId;

    private String issueNum;

    private String typeCode;

    private String summary;

    private Long assigneeId;

    private Long reporterId;

    private Long projectId;


    private Long issueTypeId;

    private String assigneeName;

    private String assigneeLoginName;

    private String assigneeRealName;

    private List<String> userLabels;

    private String reporterName;

    private String reporterLoginName;


    private String reporterRealName;


    private String reporterImageUrl;


    private String assigneeImageUrl;


    private String epicName;


    private Long epicId;


    private String epicColor;


    private BigDecimal storyPoints;


    private String featureName;


    private Long featureId;


    private String featureColor;


    private String featureType;


    private Boolean addIssue;


    private BigDecimal remainingTime;


    private StatusVO statusMapVO;


    private IssueTypeVO issueTypeVO;


    private Date creationDate;


    private Date lastUpdateDate;


    private Long parentId;


    private Map<String, Object> foundationFieldValue;


    private ProjectVO projectVO;

    private Date estimatedStartTime;

    private Date estimatedEndTime;

    private Date actualStartTime;

    private Date actualEndTime;


    private String projectName;


    private List<ProjectVO> featureTeams;


    private Boolean starBeacon;


    private Long mainResponsibleId;

    private String environment;


    private String environmentName;


    private BigDecimal spentWorkTime;


    private BigDecimal allEstimateTime;


    private List<ProjectVO> projectVOList;

    private String epicSelfName;


    private BigDecimal estimateTime;


    private BigDecimal workTime;


    private BigDecimal cumulativeWorkTime;


    private BigDecimal deviationRate;


    private Integer progress;


    private Integer totalSubIssues;


    private Integer completedSubIssues;

    public static List<IssueVO> toIssueVO(List<IssueListFieldKVVO> listFieldKVVOList) {
        return listFieldKVVOList.stream().map(issueListFieldKVVO -> {
            IssueVO issueVO = new IssueVO();
            issueVO.setIssueNum(issueListFieldKVVO.getIssueNum());
            issueVO.setSpentWorkTime(issueListFieldKVVO.getSpentWorkTime());
            issueVO.setRemainingTime(issueListFieldKVVO.getRemainingTime());
            issueVO.setStatus(issueListFieldKVVO.getStatusMapVO().getName());
            issueVO.setSummary(issueListFieldKVVO.getSummary());
            issueVO.setIssueId(issueListFieldKVVO.getIssueId());
            issueVO.getSubmitWorkTimeButton().addActionListener(e -> {
                final Disposable disposable = Disposer.newDisposable();
                new WorkHourDialog(disposable, issueListFieldKVVO.getProjectId(), issueListFieldKVVO.getIssueId()).showDialog();
            });
            return issueVO;
        }).collect(Collectors.toList());
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getIssueTypeId() {
        return issueTypeId;
    }

    public void setIssueTypeId(Long issueTypeId) {
        this.issueTypeId = issueTypeId;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getAssigneeLoginName() {
        return assigneeLoginName;
    }

    public void setAssigneeLoginName(String assigneeLoginName) {
        this.assigneeLoginName = assigneeLoginName;
    }

    public String getAssigneeRealName() {
        return assigneeRealName;
    }

    public void setAssigneeRealName(String assigneeRealName) {
        this.assigneeRealName = assigneeRealName;
    }

    public List<String> getUserLabels() {
        return userLabels;
    }

    public void setUserLabels(List<String> userLabels) {
        this.userLabels = userLabels;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterLoginName() {
        return reporterLoginName;
    }

    public void setReporterLoginName(String reporterLoginName) {
        this.reporterLoginName = reporterLoginName;
    }

    public String getReporterRealName() {
        return reporterRealName;
    }

    public void setReporterRealName(String reporterRealName) {
        this.reporterRealName = reporterRealName;
    }

    public String getReporterImageUrl() {
        return reporterImageUrl;
    }

    public void setReporterImageUrl(String reporterImageUrl) {
        this.reporterImageUrl = reporterImageUrl;
    }

    public String getAssigneeImageUrl() {
        return assigneeImageUrl;
    }

    public void setAssigneeImageUrl(String assigneeImageUrl) {
        this.assigneeImageUrl = assigneeImageUrl;
    }

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }

    public String getEpicColor() {
        return epicColor;
    }

    public void setEpicColor(String epicColor) {
        this.epicColor = epicColor;
    }

    public BigDecimal getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(BigDecimal storyPoints) {
        this.storyPoints = storyPoints;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getFeatureColor() {
        return featureColor;
    }

    public void setFeatureColor(String featureColor) {
        this.featureColor = featureColor;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public Boolean getAddIssue() {
        return addIssue;
    }

    public void setAddIssue(Boolean addIssue) {
        this.addIssue = addIssue;
    }

    public BigDecimal getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(BigDecimal remainingTime) {
        this.remainingTime = remainingTime;
    }

    public StatusVO getStatusMapVO() {
        return statusMapVO;
    }

    public void setStatusMapVO(StatusVO statusMapVO) {
        this.statusMapVO = statusMapVO;
    }

    public IssueTypeVO getIssueTypeVO() {
        return issueTypeVO;
    }

    public void setIssueTypeVO(IssueTypeVO issueTypeVO) {
        this.issueTypeVO = issueTypeVO;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Map<String, Object> getFoundationFieldValue() {
        return foundationFieldValue;
    }

    public void setFoundationFieldValue(Map<String, Object> foundationFieldValue) {
        this.foundationFieldValue = foundationFieldValue;
    }

    public ProjectVO getProjectVO() {
        return projectVO;
    }

    public void setProjectVO(ProjectVO projectVO) {
        this.projectVO = projectVO;
    }

    public Date getEstimatedStartTime() {
        return estimatedStartTime;
    }

    public void setEstimatedStartTime(Date estimatedStartTime) {
        this.estimatedStartTime = estimatedStartTime;
    }

    public Date getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(Date estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<ProjectVO> getFeatureTeams() {
        return featureTeams;
    }

    public void setFeatureTeams(List<ProjectVO> featureTeams) {
        this.featureTeams = featureTeams;
    }

    public Boolean getStarBeacon() {
        return starBeacon;
    }

    public void setStarBeacon(Boolean starBeacon) {
        this.starBeacon = starBeacon;
    }

    public Long getMainResponsibleId() {
        return mainResponsibleId;
    }

    public void setMainResponsibleId(Long mainResponsibleId) {
        this.mainResponsibleId = mainResponsibleId;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public BigDecimal getSpentWorkTime() {
        return spentWorkTime;
    }

    public void setSpentWorkTime(BigDecimal spentWorkTime) {
        this.spentWorkTime = spentWorkTime;
    }

    public BigDecimal getAllEstimateTime() {
        return allEstimateTime;
    }

    public void setAllEstimateTime(BigDecimal allEstimateTime) {
        this.allEstimateTime = allEstimateTime;
    }

    public List<ProjectVO> getProjectVOList() {
        return projectVOList;
    }

    public void setProjectVOList(List<ProjectVO> projectVOList) {
        this.projectVOList = projectVOList;
    }

    public String getEpicSelfName() {
        return epicSelfName;
    }

    public void setEpicSelfName(String epicSelfName) {
        this.epicSelfName = epicSelfName;
    }

    public BigDecimal getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(BigDecimal estimateTime) {
        this.estimateTime = estimateTime;
    }

    public BigDecimal getWorkTime() {
        return workTime;
    }

    public void setWorkTime(BigDecimal workTime) {
        this.workTime = workTime;
    }

    public BigDecimal getCumulativeWorkTime() {
        return cumulativeWorkTime;
    }

    public void setCumulativeWorkTime(BigDecimal cumulativeWorkTime) {
        this.cumulativeWorkTime = cumulativeWorkTime;
    }

    public BigDecimal getDeviationRate() {
        return deviationRate;
    }

    public void setDeviationRate(BigDecimal deviationRate) {
        this.deviationRate = deviationRate;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getTotalSubIssues() {
        return totalSubIssues;
    }

    public void setTotalSubIssues(Integer totalSubIssues) {
        this.totalSubIssues = totalSubIssues;
    }

    public Integer getCompletedSubIssues() {
        return completedSubIssues;
    }

    public void setCompletedSubIssues(Integer completedSubIssues) {
        this.completedSubIssues = completedSubIssues;
    }
}
