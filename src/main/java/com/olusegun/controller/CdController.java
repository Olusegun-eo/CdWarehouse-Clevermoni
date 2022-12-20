package com.olusegun.controller;

import com.olusegun.api.ApiResponse;
import com.olusegun.data.dto.CdDto;
import com.olusegun.data.model.RecordLabel;
import com.olusegun.service.cdservice.CdService;
import com.olusegun.service.recordlabel.RecordLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cd")
public class CdController {

        @Autowired
        CdService cdService;
        @Autowired
        RecordLabelService recordLabelService;

        @GetMapping("/")
        public ResponseEntity<List<CdDto>> getAllCds() {
            List<CdDto> body = cdService.listOfAllCds();
            return new ResponseEntity<List<CdDto>>(body, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addCd(@RequestBody CdDto cdDto) {
            Optional<RecordLabel> optionalCategory = recordLabelService.readRecordLabel(cdDto.getId());
            if (!optionalCategory.isPresent()) {
                return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
            }
            RecordLabel category = optionalCategory.get();
            cdService.addCd(cdDto, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
        }

        @PostMapping("/update/{cdID}")
        public ResponseEntity<ApiResponse> updateProduct(@PathVariable("cdID") Long cdID, @RequestBody CdDto cdDto) {
            Optional<RecordLabel> optionalCategory = recordLabelService.readRecordLabel(cdDto.getCdTitle());
            if (!optionalCategory.isPresent()) {
                return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Recordlabel is invalid"), HttpStatus.CONFLICT);
            }
            RecordLabel recordLabel = optionalCategory.get();
            cdService.updateCustomerDetails(cdID, cdDto, recordLabel);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Cd has been updated"), HttpStatus.OK);
        }

}
