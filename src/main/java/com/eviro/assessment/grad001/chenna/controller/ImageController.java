package com.eviro.assessment.grad001.chenna.controller;

import com.eviro.assessment.grad001.chenna.entity.AccountProfile;
import com.eviro.assessment.grad001.chenna.service.AccountProfileService;
import com.eviro.assessment.grad001.chenna.service.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.eviro.assessment.grad001.chenna.constants.ApplicationConstant.FILE_BASE_PATH;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {



    @Autowired
    private AccountProfileService accountProfileService;

    @Autowired
    private FileParser fileParser;

    @GetMapping(value = "/{name}/{surname}")
    public ResponseEntity<AccountProfile> getHttpImageLink(@PathVariable String name, @PathVariable String surname){

        AccountProfile accountProfile=accountProfileService.getAccountProfile(name,surname);
        return ResponseEntity.ok(accountProfile);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadToFileSystem(@RequestParam("file") MultipartFile inputFile) {
        String fileName = StringUtils.cleanPath(inputFile.getOriginalFilename());
        Path path = Paths.get(FILE_BASE_PATH + fileName);
        try {
            Files.copy(inputFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file= new File(path.toString());
        accountProfileService.processUploadedFile(file);
        return ResponseEntity.ok(file.getName());
    }

    @GetMapping(value="/file/download/{filename}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource downloadFile(@PathVariable String filename){
        try {
            String path = FILE_BASE_PATH + filename; //path of your file
            return new FileSystemResource(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
