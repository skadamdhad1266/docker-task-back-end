package com.KnowledgePioneer.backEndTask.service;

import com.KnowledgePioneer.backEndTask.entity.PatentInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public interface PatentService {

    PatentInfo savePatent(PatentInfo patentInfo);
    void deletePatentById(Long patentId);





}
