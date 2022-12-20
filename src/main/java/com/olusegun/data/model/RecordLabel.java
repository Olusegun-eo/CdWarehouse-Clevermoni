package com.olusegun.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Table(name = "record_label")
public class RecordLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "record_name")
    private @NotBlank String recordName;

    private @NotBlank String description;

    // add imageURL here

    @OneToMany(mappedBy = "recordLabel", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Set<Cd> cds;

    public RecordLabel() {
    }

    public RecordLabel(@NotBlank String recordName, @NotBlank String description) {
        this.recordName = recordName;
        this.description = description;
    }

    public Category(@NotBlank String recordName, @NotBlank String description) {
        this.recordName = recordName;
        this.description = description;
    }

    public String getRecordName() {
        return this.recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Cd> getCdLabels() {
        return cds;
    }

    public void setProducts(Set<Cd> cds) {
        this.cds = cds;
    }

    @Override
    public String toString() {
        return "User {category id=" + id + ", category name='" + recordName + "', description='" + description + "'}";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
