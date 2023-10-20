package com.vicayala.assets.application.services.interfaces;

import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;

public interface IResponsibleService {
    ResponsibleDTO getById(String id);
}
