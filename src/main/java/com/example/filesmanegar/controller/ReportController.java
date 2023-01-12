package com.example.filesmanegar.controller;

import com.example.filesmanegar.model.Report;
import com.example.filesmanegar.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@RestController
public class ReportController {

    private Logger logger=  LoggerFactory.getLogger(FileController.class);
    @Autowired
    private  ReportService ReportService;

//    public ReportController(com.example.filesmanegar.services.ReportService reportService) {
//        ReportService = reportService;
//    }


    @PostMapping("/creatReport")
    public String creatReport(@RequestBody Report request)
    {
        return ReportService.createReoprt(request);
    }

    @GetMapping("/user/reports/{fileName}")
    public List<Report> getUserReports(@PathVariable String fileName)
    {
        return ReportService.getUserReports(fileName);
    }
    @GetMapping("/file/reports/{fileName}")
    public List<Report> getFileReports(@PathVariable String fileName)
    {
        return ReportService.getFileReports(fileName);
    }


}
