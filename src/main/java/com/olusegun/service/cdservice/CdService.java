package com.olusegun.service.cdservice;

import com.olusegun.data.dto.CdDto;
import com.olusegun.data.model.Cd;
import com.olusegun.data.model.RecordLabel;
import com.olusegun.exceptions.CdNotExistException;

import java.util.List;


public interface CdService {
    List<CdDto> listOfAllCds();
    Cd findByCdTitle(Long cdId) throws CustomerDoesNotExistException;
    Cd createCustomer(Cd cdDto) throws BusinessLogicException;
    void addCd(CdDto cdDto, RecordLabel recordLabel);
    Cd getCdById(Long cdId) throws CdNotExistException;
    Cd updateCustomerDetails(Long cdId, CdDto cdDto, RecordLabel recordLabel);
}
