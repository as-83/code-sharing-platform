package platform.repos;

import org.springframework.data.repository.CrudRepository;
import platform.Code;

import java.util.List;
import java.util.Optional;

public interface CodeRepo extends CrudRepository<Code, Long> {

    Optional<Code> findByUUID(String uuid);
}
