package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.FileResponse;
import com.huseynov.announcementbackend.entity.File;
import org.mapstruct.Mapper;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResponse toFileResponse(File file, byte[] data);
}
