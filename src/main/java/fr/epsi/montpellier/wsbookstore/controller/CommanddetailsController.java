package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Commanddetail;
import fr.epsi.montpellier.wsbookstore.repository.CommanddetailsRepository;
import fr.epsi.montpellier.wsshare.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CommanddetailsController extends BaseController {

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

    // Command par id
    @GetMapping("/commanddetails/command/{commandId}")
    public Iterable<Commanddetail> getCommanddetailsOfCommand(@PathVariable long commandId) {
        return commanddetailsRepository.findByCommandId(commandId);
    }


    @PostMapping("/commanddetails")
    public ResponseEntity<Void> addCommanddetails(@Validated @RequestBody Commanddetail commanddetail) {
        try {
            Commanddetail commanddetailCreated = commanddetailsRepository.save(commanddetail);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(commanddetailCreated.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception) {
            LogError(exception);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Commanddetails details: %s", commanddetail.toString())
            );
        }
    }

    @PutMapping("/commanddetails/{id}")
    public Commanddetail updateCommanddetails(@PathVariable long id, @Validated @RequestBody Commanddetail commanddetails) {
        // Vérification des paramètres
        if (id != commanddetails.getId()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "L'id de l'URL ne correspond pas à l'id du body"
            );
        }
        // Recherche de la ligne de commande
        Commanddetail commanddetailsUpdated = findById(id);
        // Nous ne modifions que la quantité
        commanddetailsUpdated.setQuantity(commanddetails.getQuantity());
        try {
            // Mise à jour
            commanddetailsUpdated = commanddetailsRepository.save(commanddetailsUpdated);
            // HTTP Status Code 200 (Ok) par défaut
            LogMessage(String.format("Ligne de commande mise à jour: %s", commanddetailsUpdated.toString()));
            return commanddetailsUpdated;
        } catch (Exception exception) {
            LogError(exception);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Commanddetails: %s", commanddetails.toString())
            );
        }
    }

    /** Supprime une ligne de commande
     *
     * @param id L'id de la ligne de commande à supprimer
     * @return HTTP Status Code 204 NO CONTENT(succès), Code 404 (ressource non trouvée)
     */
    @DeleteMapping("/commanddetails/{id}")
    public ResponseEntity<Void> deleteCommanddetails(@PathVariable long id) {
        boolean success = false;

        // Recherche de la ligne de commande
        Commanddetail commanddetail = findById(id);
        try {
            // Suppression
            commanddetailsRepository.delete(commanddetail);
            LogMessage(String.format("Ligne de commande supprimée: %s", commanddetail.toString()));
            success = true;
        } catch (Exception exception) {
            LogError(exception);
        }
        return (success ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build());
    }




}
