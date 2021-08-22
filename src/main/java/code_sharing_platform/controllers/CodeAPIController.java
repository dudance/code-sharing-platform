package code_sharing_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import code_sharing_platform.services.CodeService;
import code_sharing_platform.repositories.CodeSnippet;

import java.util.List;
import java.util.UUID;

@RestController
public class CodeAPIController {

    private final CodeService codeService;

    @Autowired
    public CodeAPIController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("api/code/{id}")
    public CodeSnippet getCodeSnippetById(@PathVariable("id") UUID id) {
        CodeSnippet codeSnippet = codeService.findById(id).orElse(null);
        if (codeSnippet == null || codeService.isOutOfDate(codeSnippet) || codeService.isOutOfViews(codeSnippet)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        } else {
            return codeSnippet;
        }
    }

    @PostMapping("/api/code/new")
    public String postCodeSnippet(@RequestBody CodeSnippet codeBody) {
        CodeSnippet newCodeSnippet = new CodeSnippet();
        newCodeSnippet.setCode(codeBody.getCode());
        newCodeSnippet.setTime(codeBody.getTime());
        newCodeSnippet.setViews(codeBody.getViews());
        if (newCodeSnippet.getTime() > 0) {
            newCodeSnippet.setSecretTime(true);
        }
        if (newCodeSnippet.getViews() > 0) {
            newCodeSnippet.setSecretViews(true);
        }
        newCodeSnippet = codeService.save(newCodeSnippet);
        return "{ \"id\" : \"" + newCodeSnippet.getId() + "\" }";
    }

    @GetMapping("api/code/latest")
    public List<CodeSnippet> getLatestCodeSnippets() {
        return codeService.loadLatestSnippets();
    }
}