package com.example.dataservise.Controller;

import com.example.dataservise.DasturchiRepozitory.DasturchiRepo;
import com.example.dataservise.Entity.DasturchiEntity;
import com.example.dataservise.Payload.ApiResponsD;
import com.example.dataservise.ServiceD.DasturchiServise;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Dasturchi")
public class DasturchiController {

    @Autowired
    DasturchiServise dasturchiServise;
    @PostMapping("/joylash")
    public HttpEntity<?> joylash(@RequestBody DasturchiEntity dasturchi){
        ApiResponsD apiResponsD=dasturchiServise.addDasturchi(dasturchi);
        return ResponseEntity.status(apiResponsD.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponsD.getXabar());
    }
  @PutMapping("/dTahrirlash/{id}")
    public HttpEntity<?> DasturchiTahrirlash(@PathVariable Integer id, @RequestBody DasturchiEntity dasturchi){
      ApiResponsD apiResponse=dasturchiServise.editDasturchi(id,dasturchi);
        return ResponseEntity.status((apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND)).body(apiResponse.getXabar());
    }
    @GetMapping("/dOqishId/{id}")
    public HttpEntity<?> DasturchiOqish(@PathVariable Integer id){
        ApiResponsD apiResponse=dasturchiServise.idreadDasturchi(id);
        return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @GetMapping("/dOqish")
    public HttpEntity<?> DasturchiOqishIdlab(){
        ApiResponsD apiResponse=dasturchiServise.readDasturchi();
        return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/dOchirish/{id}")
    public HttpEntity<?> DasturchiOchirish(@PathVariable Integer id){
        ApiResponsD apiResponse=dasturchiServise.delDasturchi(id);
        return ResponseEntity.status(apiResponse.isXolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
}
