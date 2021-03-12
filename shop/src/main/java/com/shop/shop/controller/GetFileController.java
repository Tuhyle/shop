package com.shop.shop.controller;

import com.shop.shop.service.Impl.PhotoProductServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lê Thị Thúy
 */
@RestController
@RequestMapping("/api/")
public class GetFileController {
    private final PhotoProductServiceImpl photoProductService;

    public GetFileController(PhotoProductServiceImpl photoProductService) {
        this.photoProductService = photoProductService;
    }

    @GetMapping("download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws Exception {
        Resource resource = photoProductService.loadFileAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
