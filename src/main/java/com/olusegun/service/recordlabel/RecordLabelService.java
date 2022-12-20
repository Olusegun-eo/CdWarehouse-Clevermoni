package com.olusegun.service.recordlabel;

import com.olusegun.data.model.RecordLabel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface RecordLabelService {

        public List<RecordLabel> listRecordLabels();

        public void createRecordLabel(RecordLabel recordLabel);

        public Optional<RecordLabel> readRecordLabel(String recordLabel);

        public Optional<RecordLabel> readRecordLabel(Long id);

        public void updateRecordLabel(Long labelId, RecordLabel recordLabel);

}
