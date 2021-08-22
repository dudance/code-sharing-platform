package code_sharing_platform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<CodeSnippet, UUID> {
}
