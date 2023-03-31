package com.eviro.assessment.grad001.chenna.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

public interface FileParser {

    File convertCSVDataToImage(String base64ImageData) throws FileNotFoundException;

    URI createImageLink(File fileImage);
}
