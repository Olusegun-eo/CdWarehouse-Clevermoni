package com.olusegun.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olusegun.data.dto.CdDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;


@Entity
@Table(name = "cds")
public class Cd{

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private @NotNull String  cdTitle;
        private @NotNull String cdArtist;
        private @NotNull double cdPrice;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "label_id", nullable = false)
        RecordLabel recordLabel;

    public Cd() {
    }
    public Cd(CdDto cdDto, RecordLabel recordLabel) {
            this.cdTitle = cdDto.getCdTitle();
            this.cdArtist = cdDto.getCdArtist();
            this.cdPrice = cdDto.getPrice();
            this.recordLabel = recordLabel;
        }

        public Cd(String cdTitle, String cdArtist, double cdPrice, RecordLabel recordLabel) {
            super();
            this.cdTitle = cdTitle;
            this.cdPrice = cdPrice;
            this.cdArtist = cdArtist;
            this.recordLabel = recordLabel;
        }

        public Long getId() {
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

        public double getCdPrice() {
            return cdPrice;
        }

        public String getCdArtist(){
        return cdArtist;
        }

        public void setCdArtist(String cdArtist) {
            this.cdArtist = cdArtist;
        }

        public void setCdPrice(double cdPrice) {
            this.cdPrice = cdPrice;
        }


        public RecordLabel getRecordLabel() {
            return recordLabel;
        }

        public void setRecordLabel(RecordLabel recordLabel) {
            this.recordLabel = recordLabel;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + cdTitle + '\'' +
                    ", imageURL='" + cdArtist + '\'' +
                    ", price=" + cdPrice +
                    ", description='" + '\'' +
                    '}';
        }

}


/*
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<WishList> wishListList;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;





    public Product(ProductDto productDto, Category category) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.category = category;
    }

    public Product(String name, String imageURL, double price) {
        super();
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

 }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
 */