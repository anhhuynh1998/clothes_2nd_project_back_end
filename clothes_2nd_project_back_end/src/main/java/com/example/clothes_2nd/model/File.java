package com.example.clothes_2nd.model;

import com.example.clothes_2nd.model.emun.FileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_folder")
    private String fileFolder;

    @Enumerated(EnumType.STRING)
    private FileType fileType;
    @Column(name = "cloud_id")
    private String cloudId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
    private String publicId;


}
