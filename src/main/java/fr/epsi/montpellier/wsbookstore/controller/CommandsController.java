package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Command;
import fr.epsi.montpellier.wsbookstore.repository.CommandsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommandsController {

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



}
