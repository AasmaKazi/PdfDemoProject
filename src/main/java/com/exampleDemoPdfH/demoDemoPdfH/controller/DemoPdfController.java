package com.exampleDemoPdfH.demoDemoPdfH.controller;

import com.exampleDemoPdfH.demoDemoPdfH.config.PdfGenerator;
import com.exampleDemoPdfH.demoDemoPdfH.domain.UserDetails;
import com.exampleDemoPdfH.demoDemoPdfH.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.exampleDemoPdfH.demoDemoPdfH.config.Constatns.*;

@RestController
@RequestMapping("api/controller")
public class DemoPdfController {

    Map<String,Object> map=new HashMap<>();

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private PdfGenerator pdfGenerator;

    @PostMapping(value = "/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDetails userDetails){

        UserDetails add=userDetailsRepo.save(userDetails);
        map.put(MESSAGE,"user saved");
        map.put(STATUS,SUCCESS);
        map.put(RESPONSE,add);

        return ResponseEntity.ok(map);

    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String param) throws IOException {

//        File file=new File("text.txt");
        File file=new File(pdfGenerator.generate("textDemo.pdf"));
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=textDemo.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }



}
