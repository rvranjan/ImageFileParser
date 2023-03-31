package com.eviro.assessment.grad001.chenna.service.impl;

import com.eviro.assessment.grad001.chenna.entity.AccountProfile;
import com.eviro.assessment.grad001.chenna.repository.AccountProfileRepository;
import com.eviro.assessment.grad001.chenna.service.AccountProfileService;
import com.eviro.assessment.grad001.chenna.service.FileParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class AccountProfileServiceImpl implements AccountProfileService {

    private String fileBasePath= "/Users/raviranjan/Downloads/Upload/";

    @Autowired
    private FileParser fileParser;

    @Autowired
    private AccountProfileRepository accountProfileRepository;

    @Override
    public AccountProfile getAccountProfile(String name, String surname) {

        return accountProfileRepository.findByAccountHolderNameAndAccountHolderSurname(name,surname)
                .orElse(null);
    }

    @Override
    public void processUploadedFile(File csvFile) {

        try(CSVReader csvReader=new CSVReaderBuilder(new FileReader(csvFile))
                .withSkipLines(1) // skip the first line, header info
                .build()){
            String[] values;
            while((values=csvReader.readNext())!=null){
                String extension;
                String fileName= values[0]+values[1];
                switch (values[2]) {//check image's extension
                    case "image/jpeg":
                        extension = "jpeg";
                        break;
                    case "image/png":
                        extension = "png";
                        break;
                    default://should write cases for more images types
                        extension = "jpg";
                        break;
                }
                File outputImage= convertCSVDataToImage(values[3],fileName,extension);
                AccountProfile accountProfile= AccountProfile.builder()
                        .accountHolderName(values[0])
                        .accountHolderSurname(values[1])
                        .httpImageLink(fileParser.createImageLink(outputImage))
                        .build();
                accountProfile= accountProfileRepository.save(accountProfile);
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    private File convertCSVDataToImage(String base64ImageData, String fileName, String extension) {
        byte[] data= Base64.getDecoder().decode(base64ImageData);
        File outputFile= new File(fileBasePath+fileName+"."+extension);
        try(OutputStream stream= Files.newOutputStream(outputFile.toPath())){
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }
}
