package fr.epsi.montpellier.wsbookstore.repository;

import fr.epsi.montpellier.wsbookstore.models.Command;
import org.springframework.data.repository.CrudRepository;

public interface CommandRepository extends CrudRepository<Command, Long > {
}
