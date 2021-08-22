package code_sharing_platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import code_sharing_platform.repositories.CodeRepository;
import code_sharing_platform.repositories.CodeSnippet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Optional<CodeSnippet> findById(UUID id) {
        return codeRepository.findById(id);
    }

    public CodeSnippet save(CodeSnippet codeSnippet) {
        return codeRepository.save(codeSnippet);
    }

    public List<CodeSnippet> loadLatestSnippets() {
        List<CodeSnippet> listOfSnippets = new ArrayList<>();
        codeRepository.findAll().forEach(listOfSnippets::add);
        listOfSnippets.removeIf(CodeSnippet::hasSecretTime);
        listOfSnippets.removeIf(CodeSnippet::hasSecretViews);
        listOfSnippets.sort(Comparator.comparing(CodeSnippet::getDate));
        Collections.reverse(listOfSnippets);
        if (listOfSnippets.size() > 10) {
            return listOfSnippets.subList(0, 10);
        } else {
            return listOfSnippets;
        }
    }

    public boolean isOutOfDate(CodeSnippet codeSnippet) {
        if (!codeSnippet.hasSecretTime()) {
            return false;
        } else {
            long timeBetweenDates = Duration.between(LocalDateTime.parse(codeSnippet.getDate(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.now()).getSeconds();
            if (timeBetweenDates > codeSnippet.getTime()) {
                codeRepository.delete(codeSnippet);
                return true;
            } else {
                codeSnippet.setTime((int) (codeSnippet.getTime() - timeBetweenDates));
                codeRepository.save(codeSnippet);
                return false;
            }
        }
    }

    public boolean isOutOfViews(CodeSnippet codeSnippet) {
        if (codeSnippet.hasSecretViews()) {
            if (codeSnippet.getViews() < 1) {
                codeRepository.delete(codeSnippet);
                return true;
            }
            codeSnippet.setViews(codeSnippet.getViews() - 1);
            codeRepository.save(codeSnippet);
        }
        return false;
    }
}
