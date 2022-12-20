package com.olusegun.data.dto;

//import javax.validation.constraints.NotNull;

import com.olusegun.data.model.Cd;
import org.jetbrains.annotations.NotNull;

public class CdDto {

    private Long id;
    private @NotNull String cdTitle;
    private @NotNull String cdArtist;
    private @NotNull double cdPrice;
    private @NotNull Long recordLabelId;

    public CdDto(Cd cd) {
        this.setId(cd.getId());
        this.setCdTitle(cd.getCdTitle());
        this.setCdArtist(cd.getCdArtist());
        this.setPrice(cd.getCdPrice());
        this.setRecordLabelId(cd.getRecordLabel().getId());
    }

    public CdDto(@NotNull String cdTitle,
                 @NotNull double cdPrice, @NotNull String cdArtist) {
        this.cdTitle = cdTitle;
        this.cdArtist = cdArtist;
        this.cdPrice = cdPrice;
    }

    public CdDto(){

    }

    public Long getId(){
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCdTitle() {
        return cdTitle;
    }

    public void setCdTitle(String cdTitle) {
        this.cdTitle = cdTitle;
    }

    public String getCdArtist() {
        return cdArtist;
    }

    public void setCdArtist(String cdArtist) {
        this.cdArtist = cdArtist;
    }

    public double getPrice() {
        return cdPrice;
    }

    public void setPrice(double cdPrice) {
        this.cdPrice = cdPrice;
    }


    public Long getRecordLabelID(){
        return recordLabelId;
    }

    public void setRecordLabelId(Long recordLabelId) {
        this.recordLabelId = recordLabelId;
    }

}
