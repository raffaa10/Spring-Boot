package fr.epsi.montpellier.wsbookstore.repository;

import fr.epsi.montpellier.wsbookstore.models.Commanddetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommanddetailsRepository extends CrudRepository<Commanddetail, Long> {
    // Obtient les détails d'une commande
    @Query("select detail from Commanddetail detail where detail.command.id = ?1")
    List<Commanddetail> findByCommandId(long commandId);

}
