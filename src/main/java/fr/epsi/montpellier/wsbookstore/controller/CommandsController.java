package fr.epsi.montpellier.wsbookstore.controller;

import fr.epsi.montpellier.wsbookstore.models.Command;
import fr.epsi.montpellier.wsbookstore.repository.CommandRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommandsController {

    private CommandRepository commandRepository;

    public CommandsController(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    // Get all commands
        @GetMapping("/commands")
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

    @GetMapping("/command/commands")
    public Iterable<Command> getCommands() {
        return commandRepository.findAll();
    }



}
