package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Command;
import fr.epsi.montpellier.wsbookstore.repository.CommandsRepository;
import fr.epsi.montpellier.wsshare.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommandsController extends BaseController {

    private final CommandsRepository commandsRepository;

    public CommandsController(CommandsRepository commandsRepository) {
        this.commandsRepository = commandsRepository;
    }

    // Get all commands
        @GetMapping("/commands1")
        public List<Command> getCommands1() {
            //List<Command> list = new ArrayList<>();
            //String pattern = "yyyy-MM-dd HH:mm:ss";
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            //list.add(new Command(1555, 22277, new Date("2021-02-23")));
            //return list;

            List<Command> commands = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.set(2021, Calendar.JANUARY, 31);
            commands.add(new Command(1l, 7l, calendar.getTime()));
            calendar.set(2021, Calendar.FEBRUARY, 7);
            commands.add(new Command(2l, 2l, calendar.getTime()));
            return commands;
        }

    @GetMapping("/commands")
    public Iterable<Command> getCommands() {
        return commandsRepository.findAll();
    }

    @GetMapping("/commands/{id}")
    public Command getCommands(@PathVariable(value = "id") Long id) {
        return findById(id);
    }

    private Command findById(Long id) {
        return commandsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Commande '%s' non trouvée", id))
                );
    }

    @PostMapping("/commands")
    public ResponseEntity<Void> addCommand(@Validated @RequestBody Command command) {
        try {
            Command commandCreated = commandsRepository.save(command);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(commandCreated.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception) {
            LogError(exception);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Command detail: %s", command.toString())
            );
        }
    }
    /* On omet cette partie car elle vient de la classe BaseController
    private void LogError(Exception exception) {
        System.err.printf("Error, Class=%s\n", this.getClass().getCanonicalName());
        exception.printStackTrace(System.err);
    }
    */
    @DeleteMapping("/commands/{id}")
    public Command deleteCommand(@PathVariable("id") Long id) {
        boolean success = false;
        // Recherche de la commande
        Command command = findById(id);
        try {
            // Suppression
            commandsRepository.delete(command);
            LogMessage(String.format("Commande supprimée: %s", command.toString()));
            success = true;
        } catch (Exception exception) {
            LogError(exception);
        }
        if (success) {
            // HTTP Status Code 200 (Ok) par défaut
            return command;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Commande '%s' non trouvée", id));
    }
    /* On omet cette partie parce que notre classe hérite de la classe BaseController

    private void LogMessage(String message) {
        System.err.printf("Message=%s, Class=%s\n", message, this.getClass().getCanonicalName());
    }
    */





}
