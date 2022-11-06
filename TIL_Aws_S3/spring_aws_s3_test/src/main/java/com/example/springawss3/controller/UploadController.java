package com.example.springawss3.controller;

@RestController
@RequestMapping(value = "/upload", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UploadController {
        private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileDetail> post(
            @RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(fileUploadService.save(multipartFile));
    }
}