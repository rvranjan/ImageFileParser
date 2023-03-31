package com.eviro.assessment.grad001.chenna.service.impl;

import com.eviro.assessment.grad001.chenna.service.FileParser;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

@Service
public class FileParserImpl implements FileParser {

    @Override
    public File convertCSVDataToImage(String base64ImageData) throws FileNotFoundException {
        return null;
    }

    @Override
    public URI createImageLink(File fileImage) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/api/image/file/download/"+fileImage.getName())
                .toUriString();
        return URI.create(fileDownloadUri);
    }
}
