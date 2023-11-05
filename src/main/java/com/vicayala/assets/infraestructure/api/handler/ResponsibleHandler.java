package com.vicayala.assets.infraestructure.api.handler;

import com.vicayala.assets.application.exceptions.NotFoundException;
import com.vicayala.assets.application.services.interfaces.IResponsibleService;
import com.vicayala.assets.domain.dtos.responsible.BranchResponsibleDTO;
import com.vicayala.assets.domain.dtos.responsible.EmployeeResponsibleDTO;
import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;
import com.vicayala.assets.infraestructure.api.vo.asset.ResponsibleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResponsibleHandler {

    private final IResponsibleService responsibleService;

    public ResponsibleVO createResponsibleVO(String id){
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
            default -> throw new NotFoundException("Responsible");
        }
        responsibleVO.setId(id);
        return responsibleVO;
    }
}
