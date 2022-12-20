package com.olusegun.repository;

import com.olusegun.data.model.RecordLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordLabelRepository extends JpaRepository<RecordLabel, Long> {
    RecordLabel findByCategoryName(String recordLabel);

}
