package com.vicayala.assets.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vicayala.assets.application.exceptions.ResponsibleServiceException;
import com.vicayala.assets.application.services.interfaces.IResponsibleService;
import com.vicayala.assets.domain.dtos.responsible.ResponsibleDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class ResponsibleServiceImpl implements IResponsibleService {

    private String getByIdUrl;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public ResponsibleServiceImpl(String url){
        this.getByIdUrl = url;
    }
    @Override
    public ResponsibleDTO getById(String id) {
        this.mapper.registerModule(new JavaTimeModule());
        String url = getByIdUrl+id;
        log.info("Responsible Service : "+ url);
        try {
            ResponseEntity<ResponsibleDTO> responsibleDTO =
                    restTemplate.getForEntity(url,ResponsibleDTO.class);
            log.info("Response Responsible Service : " + mapper.writeValueAsString(responsibleDTO));
            return responsibleDTO.getBody();
        }catch (Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
            throw new ResponsibleServiceException();
        }
    }
}
