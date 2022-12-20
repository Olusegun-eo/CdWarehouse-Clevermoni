package com.olusegun.service.recordlabel;


import com.olusegun.data.model.RecordLabel;
import com.olusegun.repository.RecordLabelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecordLabelServiceImpl implements RecordLabelService{

    private final RecordLabelRepository recordLabelRepository;

    public RecordLabelServiceImpl(RecordLabelRepository recordLabelRepository) {
        this.recordLabelRepository = recordLabelRepository;
    }

    public List<RecordLabel> listRecordLabels() {
            return recordLabelRepository.findAll();
        }

    public void createRecordLabel(RecordLabel recordLabel) {
        recordLabelRepository.save(recordLabel);
        }

    public RecordLabel readRecordLabel(String recordName){
        return recordLabelRepository.findByCategoryName(recordName);
        }


    public Optional<RecordLabel> readRecordLabel(Long id) {
            return recordLabelRepository.findById(id);
    }


    public void updateRecordLabel(Long labelId, RecordLabel recordLabel){
        RecordLabel label = recordLabelRepository.findById(labelId).get();
            label.setRecordName(recordLabel.getRecordName());
            label.setDescription(recordLabel.getDescription());
            label.setCds(recordLabel.getCds());
        recordLabelRepository.save(label);
        }
}







