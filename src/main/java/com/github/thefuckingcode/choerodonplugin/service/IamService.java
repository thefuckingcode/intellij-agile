package com.github.thefuckingcode.choerodonplugin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.thefuckingcode.choerodonplugin.feign.ClientBuilder;
import com.github.thefuckingcode.choerodonplugin.feign.IamClientApi;
import com.github.thefuckingcode.choerodonplugin.util.ResponseUtil;
import com.github.thefuckingcode.choerodonplugin.vo.OrganizationVO;
import com.github.thefuckingcode.choerodonplugin.vo.UserVO;
import com.intellij.ide.util.PropertiesComponent;

import java.util.List;

public class IamService {

    public List<OrganizationVO> listOrganizations() {
        IamClientApi iamClientApi = ClientBuilder.buildClient(IamClientApi.class);
        return ResponseUtil.getResult(iamClientApi.selfTenants(PropertiesComponent.getInstance().getValue("accessToken")), new TypeReference<List<OrganizationVO>>() {
        });
    }

    public void currentUser() {
        IamClientApi iamClientApi = ClientBuilder.buildClient(IamClientApi.class);
        UserVO userVO = ResponseUtil.getResult(iamClientApi.self(PropertiesComponent.getInstance().getValue("accessToken")), UserVO.class);

        PropertiesComponent.getInstance().setValue("userId", userVO.getId());
        PropertiesComponent.getInstance().setValue("loginName", userVO.getLoginName());
        PropertiesComponent.getInstance().setValue("realName", userVO.getRealName());

    }
}
