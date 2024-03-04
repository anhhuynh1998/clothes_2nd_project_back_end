package com.example.clothes_2nd.service.admin.file.request;

import com.example.clothes_2nd.model.emun.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileSaveRequest {
    private String url;
    private FileType fileType;
    private String publicId;
}
