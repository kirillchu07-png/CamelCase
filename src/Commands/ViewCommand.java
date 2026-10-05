package Commands;

import Commands.InterfaceCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ViewCommand implements InterfaceCommand {
    @Override
    public String getName() {
        return "/view";
    }

    @Override
    public void execute() {
        String separator = File.separator;
        String path = "src" + separator + "data" + separator + "profils.json";
        File profils = new File(path);
        try {
            Scanner inputfile = new Scanner(profils);
            while (inputfile.hasNextLine()) {
                String line = inputfile.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл профилей не найден");
        }
    }
}
