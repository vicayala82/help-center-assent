package com.vicayala.assets.infraestructure.api.handler;

import com.vicayala.assets.application.exceptions.NotFoundException;
import com.vicayala.assets.application.services.ResponsibleServiceImpl;
import com.vicayala.assets.application.services.interfaces.IResponsibleService;
import com.vicayala.assets.domain.dtos.responsible.BranchResponsibleDTO;
import com.vicayala.assets.domain.dtos.responsible.EmployeeResponsibleDTO;
import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;
import com.vicayala.assets.infraestructure.api.vo.asset.ResponsibleVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponsibleHandler {

    @Value("${responsible.url}")
    private String ResponsibleUrl;

    private static String RESPONSIBLE_URL;

    public static ResponsibleVO createResponsibleVO(String id){
        log.info("RESPONSIBLE URL : "+ RESPONSIBLE_URL);
        IResponsibleService responsibleService =
            new ResponsibleServiceImpl(RESPONSIBLE_URL);
        ResponsibleDTO responsibleDTO = responsibleService.getById(id);
        ResponsibleVO responsibleVO = ResponsibleVO.builder().build();

        switch (responsibleDTO){
            case EmployeeResponsibleDTO employee -> {
                responsibleVO.setPersonalEmail(employee.getPersonalEmail());
                responsibleVO.setFullName(employee.getFullName());
                responsibleVO.setCompany(employee.getCompany().getName());
            }
            case BranchResponsibleDTO branch -> {
                responsibleVO.setName(branch.getName());
                responsibleVO.setCity(branch.getCity());
            }
            default -> throw new NotFoundException("Reponsible");
        }
        return responsibleVO;
    }

    @Value("${responsible.url}")
    public void setNameStatic(String name) {
        ResponsibleHandler.RESPONSIBLE_URL = name;
    }
}
