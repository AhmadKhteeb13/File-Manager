package com.example.filesmanegar.services;

import com.example.filesmanegar.model.FileModel;
import com.example.filesmanegar.model.Report;
import com.example.filesmanegar.repository.FileRepository;
import com.example.filesmanegar.repository.reportRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReportService {

    @Autowired
     private reportRepository reportRepository;
    @Autowired
     private FileRepository FileRepository;
    @Autowired
     private FileService FileService;
    public String createReoprt(Report Report ) {
       Boolean result= FileService.isFileBooked(Report.getFileName());
       System.out.println("******************");
          // File file=FileRepository.findFileByName(Report.getFileName()).get();
           if(result){

               return " cant reserved because file is reserved already ";

           }
           else{

               FileModel file =FileRepository.findFileByName(Report.getFileName()).get();
               file.setBookedUp(true);
               FileRepository.save(file);
               Report.setFile(file);
               reportRepository.save(Report);
           }


        return "Report";

    }



    public List<Report> getUserReports(String UserName){

      List<Report>  reports= reportRepository.findAll();
        List<Report>  UserReport = new ArrayList<>();
      for(Report report: reports){
          if(report.getUserReservedName().equals(UserName)){
              UserReport.add(report);

          }
      }
     return UserReport;
    }
    public List<Report> getAllReport() {
        List<Report> Reports
                = reportRepository.findAll();

        return Reports;
    }
    public List<Report> getFileReports(String fileName){
        FileModel file= FileRepository.findFileByName(fileName).get();
        return file.getReports();

    }



    public Report getReportById(Long id) {
        Report Report = reportRepository.findById(id).get();
        return Report;
    }
    public Report updatReport(Long id, Report Report) {
        Report reporteEntity =reportRepository.findById(id).get();
        reporteEntity.setDateFileEdit(Report.getDateFileEdit());
        reporteEntity.setDateFileReserved(Report.getDateFileReserved());
        reporteEntity.setDateFileReservedCancel(Report.getDateFileReservedCancel());
        reporteEntity.setUserReservedName(Report.getUserReservedName());
        reportRepository.save(reporteEntity);
        return reporteEntity;
    }
}
