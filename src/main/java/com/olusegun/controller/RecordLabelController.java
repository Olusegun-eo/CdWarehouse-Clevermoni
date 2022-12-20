package com.olusegun.controller;

import com.olusegun.api.ApiResponse;
import com.olusegun.data.model.RecordLabel;
import com.olusegun.service.recordlabel.RecordLabelService;
import com.olusegun.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class RecordLabelController {

        @Autowired
        private RecordLabelService recordLabelService;

        @GetMapping("/")
        public ResponseEntity<List<RecordLabel>> listRecordLabels() {
            List<RecordLabel> body = recordLabelService.listRecordLabels();
            return new ResponseEntity<List<RecordLabel>>(body, HttpStatus.OK);
        }

        @PostMapping("/create")
        public ResponseEntity<ApiResponse> createRecordLabel( @RequestBody RecordLabel reportLabel) {
            if (Helper.notNull(recordLabelService.readRecordLabel(reportLabel.getRecordName()))) {
                return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Record Label already exists"), HttpStatus.CONFLICT);
            }
            recordLabelService.createRecordLabel(reportLabel);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the Record Label"), HttpStatus.CREATED);
        }

        @PostMapping("/update/{recordLabelId}")
        public ResponseEntity<ApiResponse> updateRecordLabel(@PathVariable("recordLabelId") Long recordLabelId, @RequestBody RecordLabel reportLabel) {
            if (Helper.notNull(recordLabelService.readRecordLabel(recordLabelId))) {
                recordLabelService.updateRecordLabel(recordLabelId, reportLabel);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "updated the RecordLabel"), HttpStatus.OK);
            }
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }
}
