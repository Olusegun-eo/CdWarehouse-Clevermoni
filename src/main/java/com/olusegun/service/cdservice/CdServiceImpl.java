package com.olusegun.service.cdservice;

import com.olusegun.data.dto.CdDto;
import com.olusegun.data.model.Cd;
import com.olusegun.data.model.RecordLabel;
import com.olusegun.exceptions.CdNotExistException;
import com.olusegun.exceptions.CustomerDoesNotExistException;
import com.olusegun.repository.CdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CdServiceImpl implements CdService{

    @Autowired
    private CdRepository cdRepository;

    @Override
    public List<CdDto> listOfAllCds() {
        List<Cd> cds = cdRepository.findAll();
        List<CdDto> cdDtos = new ArrayList<>();
        for(Cd cd : cds) {
            CdDto cdDto = getDtoFromCd(cd);
            cdDtos.add(cdDto);
        }
        return cdDtos;
    }

    public static CdDto getDtoFromCd(Cd cd) {
        CdDto cdDto = new CdDto(cd);
        return cdDto;
    }

    public static Cd getCdFromDto(CdDto cdDto, RecordLabel recordLabel) {
        Cd cd = new Cd(cdDto, recordLabel);
        return cd;
    }

    @Override
    public Cd findByCdTitle(Long cdId) throws CustomerDoesNotExistException {
        return null;
    }

    public void addCd(CdDto cdtDto, RecordLabel recordLabel) {
        Cd cd = getCdFromDto(cdtDto, recordLabel);
        cdRepository.save(cd);
    }

    public void updateProduct(Long cdID, CdDto cdDto, RecordLabel recordLabel) {
        Cd cd = getCdFromDto(cdDto, recordLabel);
        cd.setId(cdID);
        cdRepository.save(cd);
    }


    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public Cd getCdById(Long cdId) throws CdNotExistException {
        Optional<Cd> cd = cdRepository.findById(cdId);
        if (!cd.isPresent())
            throw new CdNotExistException("Cd id is invalid " + cdId);
        return cd.get();
    }


}
