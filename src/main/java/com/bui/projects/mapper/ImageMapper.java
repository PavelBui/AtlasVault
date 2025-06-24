package com.bui.projects.mapper;

import com.bui.projects.dto.ImageDto;
import com.bui.projects.entity.ImageEntity;
import com.bui.projects.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImageMapper {

    private ImageRepository imageRepository;

    public Set<ImageEntity> dtoToEntity(List<ImageDto> imageDtoList) throws IOException {
        // ToDo добавить сохранение файлов в рабочую папку
        for (ImageDto imageDto: imageDtoList) {
            File file = new File(imageDto.getPath());

            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                boolean dirsCreated = parentDir.mkdirs();
                if (!dirsCreated) {
                    throw new IOException("Failed to create parent directories for: " + imageDto.getPath());
                }
            }

            // Сохраняем данные в файл
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(imageDto.getImageBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imageDtoList.stream()
                .map(dto -> imageRepository.findByPath(dto.getPath())
                        .orElse(new ImageEntity(dto.getName(), dto.getPath()))).collect(Collectors.toSet());
    }

    public List<ImageDto> entityToDto(Set<ImageEntity> imageEntities) {
        // ToDo добавить загрузку файлов из рабочей папки
        return List.of();
    }
}
