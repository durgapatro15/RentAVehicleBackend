package com.rent.rentavehicle.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rent.rentavehicle.entity.VehicleDocument;
import com.rent.rentavehicle.service.S3StorageService;
import com.rent.rentavehicle.service.VehicleDocumentService;

@RestController
@RequestMapping("/admin/vehicle-documents")
public class VehicleDocumentController {

    private final VehicleDocumentService vehicleDocumentService;
    private final S3StorageService s3StorageService;
    
    public VehicleDocumentController(VehicleDocumentService vehicleDocumentService, S3StorageService s3StorageService) {
        this.vehicleDocumentService = vehicleDocumentService;
        this.s3StorageService = s3StorageService;
    }

    // // Upload a new vehicle document
    // @PostMapping
    // public ResponseEntity<VehicleDocument> uploadDocument(@RequestBody VehicleDocument document) {
    //     VehicleDocument savedDocument = vehicleDocumentService.uploadDocument(document);
    //     return ResponseEntity.ok(savedDocument);
    // }


    // Upload a new vehicle document
        @PostMapping("/upload")
        public ResponseEntity<VehicleDocument> uploadVehicleDocument(
                @RequestParam("file") MultipartFile file,
                @RequestParam("vehicleId") Long vehicleId,
                @RequestParam("documentType") VehicleDocument.DocumentType documentType) {

            try {
                String fileUrl = s3StorageService.uploadFile(file); // Upload to S3
                VehicleDocument savedDocument = vehicleDocumentService.uploadDocument(vehicleId, documentType, fileUrl);
                return ResponseEntity.ok(savedDocument);
            } catch (IOException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }


    // Get all documents for a specific vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<VehicleDocument>> getDocumentsByVehicle(@PathVariable Long vehicleId) {
        List<VehicleDocument> documents = vehicleDocumentService.getDocumentsByVehicle(vehicleId);
        return ResponseEntity.ok(documents);
    }

    // Delete a document by ID
    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        vehicleDocumentService.deleteDocument(documentId);
        return ResponseEntity.ok("Document deleted successfully.");
    }
}