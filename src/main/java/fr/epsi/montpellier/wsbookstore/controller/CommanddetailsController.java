package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Commanddetail;
import fr.epsi.montpellier.wsbookstore.repository.CommanddetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CommanddetailsController {

    private final CommanddetailsRepository commanddetailsRepository;

    public CommanddetailsController(CommanddetailsRepository commanddetailsRepository) {
        this.commanddetailsRepository = commanddetailsRepository;
    }

    @GetMapping("/commanddetails")
    public Iterable<Commanddetail> getCommanddetails() {
        return commanddetailsRepository.findAll();
    }

    @GetMapping("/commanddetails/{id}")
    public Commanddetail getCommanddetails(@PathVariable(value = "id") Long id) {
        return findById(id);
    }

    private Commanddetail findById(Long id) {
        return commanddetailsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Commande '%s' non trouvée", id))
                );
    }

}
