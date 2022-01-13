package com.KnowledgePioneer.backEndTask.controller;

import com.KnowledgePioneer.backEndTask.entity.AbstractInfo;
import com.KnowledgePioneer.backEndTask.entity.PatentInfo;
import com.KnowledgePioneer.backEndTask.entity.QuestelPatentDocument;
import com.KnowledgePioneer.backEndTask.service.PatentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import java.util.zip.ZipOutputStream;

import static com.KnowledgePioneer.backEndTask.service.PatentServiceImplementation.unzipFolder;


@Component
@PropertySource("classpath:application.properties")
@RestController
public class PatentController {


    @Value("${app.archivePath}")
    private String archivePath;



    @Value("${app.destinationPath}")
    private String destinationPath;

    @Autowired private PatentService patentService;




    @GetMapping("/patents")
    public String savePatent() {


        PatentInfo patentInfo = null;

        try {
            Path source = Paths.get(archivePath);
            Path target = Paths.get(destinationPath);



            unzipFolder(source, target);
            System.out.println("Done");

             } catch (IOException e) {
                    e.printStackTrace();
                }


            File dir = new File(destinationPath);
            File[] directoryListing = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".xml");
                }
            });
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    patentInfo = null;
                    try {
                        JAXBContext jaxbContext = JAXBContext.newInstance(QuestelPatentDocument.class);
                        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();


                        QuestelPatentDocument questelPatentDocument = (QuestelPatentDocument) jaxbUnmarshaller.unmarshal((child));

                        //System.out.println(value.getNum());

                        patentInfo = new PatentInfo();
                        patentInfo.setPatentTitle(questelPatentDocument.getBibliographicData().getInventionTitle().getValue());

                        if (questelPatentDocument.getBibliographicData().getApplicationReference().getDocumentId() != null) {
                            QuestelPatentDocument.BibliographicData.ApplicationReference.DocumentId documentId = questelPatentDocument.getBibliographicData().getApplicationReference().getDocumentId().get(0);
                            patentInfo.setPatentId(documentId.getDocNumber());
                            String dateParse = String.valueOf(documentId.getDate());
                            if (dateParse != null) {
                                patentInfo.setPatentYear(dateParse.substring(0, 4));
                            }
                        }

                        StringBuilder a = new StringBuilder();
                        //patentInfo.setPatentId(questelPatentDocument.getBibliographicData().getApplicationReference().getDocumentId());
                        if (questelPatentDocument.getAbstract() != null) {
                            List<Serializable> abstractData = questelPatentDocument.getAbstract().getP().getContent();

                            for (Serializable temp : abstractData) {
                                if (temp instanceof String) {

                                    a.append(temp);
                                }
                            }
                        }


                        StringBuilder textcom = new StringBuilder();
                        if (questelPatentDocument.getDescription() != null) {
                            List<Object> text = questelPatentDocument.getDescription().getPOrHeading();


                            for (Object o : text) {
                                if (o instanceof QuestelPatentDocument.Description.P) {
                                    QuestelPatentDocument.Description.P p = (QuestelPatentDocument.Description.P) o;
                                    List<Serializable> textlist = p.getContent();

                                    for (Serializable testdata : textlist) {
                                        if (testdata instanceof String) {
                                            textcom.append(testdata);
                                        }
                                    }
                                }
                                if (o instanceof String) {
                                    textcom.append(o);
                                }


                            }
                        }


                        patentInfo.setPatentAbstract(String.valueOf(a));
                        patentInfo.setPatentText(String.valueOf(textcom));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    patentService.savePatent(patentInfo);

                }
            }
            //System.out.println(employee);

        if (patentInfo != null) {
            return "Data added succesfully";
        }
        else
        {
            return "Sorry! something went wrong!";
        }

    }




    @DeleteMapping("/patents/{id}")

    public String deletePatenetsById(@PathVariable("id")

                                               Long departmentId)

    {
        try {
            patentService.deletePatentById(

                    departmentId);
            return "Succesfully deleted!";
        }
        catch (Exception e){
            return "sorry! something went wrong.";
        }

    }


}
