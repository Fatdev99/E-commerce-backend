package com.fadev.antiantiApe.service.image;

import com.fadev.antiantiApe.dto.ImageDTO;
import com.fadev.antiantiApe.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface iImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file,  Long imageId);
}
