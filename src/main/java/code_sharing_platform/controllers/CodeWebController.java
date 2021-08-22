package code_sharing_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import code_sharing_platform.repositories.CodeSnippet;
import code_sharing_platform.services.CodeService;

import java.util.List;
import java.util.UUID;

@Controller
public class CodeWebController {

    private final CodeService codeService;

    @Autowired
    public CodeWebController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/new")
    public String getCodeSnippet() {
        return "createCode";
    }

    @GetMapping("code/{id}")
    public String getCodeSnippetById(@PathVariable("id") UUID id, Model model) {
        CodeSnippet codeSnippet = codeService.findById(id).orElse(null);
        if (codeSnippet == null || codeService.isOutOfDate(codeSnippet) || codeService.isOutOfViews(codeSnippet)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        } else {
            model.addAttribute("codeSnippet", codeSnippet);
            return "getCodeById";
        }
    }

    @GetMapping("code/latest")
    public String getLatestCodeSnippets(Model model) {
        List<CodeSnippet> listOfSnippets = codeService.loadLatestSnippets();
        model.addAttribute("codeSnippetsList", listOfSnippets);
        return "getLatestCodes";
    }
}