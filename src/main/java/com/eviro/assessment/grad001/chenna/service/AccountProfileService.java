package com.eviro.assessment.grad001.chenna.service;

import com.eviro.assessment.grad001.chenna.entity.AccountProfile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AccountProfileService {

    AccountProfile getAccountProfile(String name, String surname);

    void processUploadedFile(File csvFile);
}
