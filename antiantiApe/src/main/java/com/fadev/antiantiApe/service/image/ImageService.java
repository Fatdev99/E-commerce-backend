package com.fadev.antiantiApe.service.image;

import com.fadev.antiantiApe.dto.ImageDTO;
import com.fadev.antiantiApe.exception.ResourceNotFoundException;
import com.fadev.antiantiApe.model.Image;
import com.fadev.antiantiApe.model.Product;
import com.fadev.antiantiApe.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.fadev.antiantiApe.repository.imageRepository;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements iImageService{
    private final imageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Image Found With ID:" + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id)
                .ifPresentOrElse(
                        imageRepository::delete,
                        () -> {
                            throw new ResourceNotFoundException("No Image Found With ID:" + id);
                        });
    }

    @Override
    public List<ImageDTO> saveImages(Long productId, List<MultipartFile> files) {
        Product product = productService.getProductById(productId);

        List<ImageDTO> savedImageDTO = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);

                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(savedImage.getId());
                imageDTO.setFileName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDTO.add(imageDTO);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return savedImageDTO;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);

        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
