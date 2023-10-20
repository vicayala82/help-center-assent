package com.vicayala.assets.infraestructure.api.handler;

import com.vicayala.assets.application.exceptions.NotFoundException;
import com.vicayala.assets.application.services.ResponsibleServiceImpl;
import com.vicayala.assets.application.services.interfaces.IResponsibleService;
import com.vicayala.assets.domain.dtos.responsible.BranchResponsibleDTO;
import com.vicayala.assets.domain.dtos.responsible.EmployeeResponsibleDTO;
import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;
import com.vicayala.assets.infraestructure.api.vo.asset.ResponsibleVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResponsibleHandler {

    public static ResponsibleVO createResponsibleVO(String id){
        IResponsibleService responsibleService =
            new ResponsibleServiceImpl("http://localhost:8079/responsible/");
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
}
