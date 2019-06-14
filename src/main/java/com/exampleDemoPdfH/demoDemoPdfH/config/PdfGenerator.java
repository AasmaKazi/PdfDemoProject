package com.exampleDemoPdfH.demoDemoPdfH.config;

import com.exampleDemoPdfH.demoDemoPdfH.domain.UserDetails;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class PdfGenerator {
private static final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);


    public String generate(String fileName)throws IOException {
        File templateFile;
        File tempFile = null;
        String htmlString;

            templateFile = new ClassPathResource("templates/demo.html").getFile();
            tempFile = File.createTempFile(System.currentTimeMillis() + "", ".html");
            tempFile.getParentFile().mkdirs();
            File htmlTemplateFile = new File(templateFile.getAbsolutePath());
            htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");


            htmlString = htmlString.replace("$name","asd");
            htmlString = htmlString.replace("$address", "dddd");



        FileUtils.writeStringToFile(tempFile, htmlString, "UTF-8");

        ConverterProperties converterProperties = new ConverterProperties();
        try (FileOutputStream pdfFile = new FileOutputStream(fileName)) {
            HtmlConverter.convertToPdf(new FileInputStream(tempFile), pdfFile,
                    converterProperties);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        tempFile.deleteOnExit();
        return fileName;
    }



}
