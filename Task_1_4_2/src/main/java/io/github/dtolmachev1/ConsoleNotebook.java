package io.github.dtolmachev1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * <p>Class for simple console notebook.</p>
 */
public class ConsoleNotebook {
    public static void main(String[] args) {
        init();  // initializing internal fields
        try {  // trying to parse command line arguments
            CommandLine cmd = parser.parse(options, args);  // parsing command line
            if(cmd.hasOption("file")) {
                processFile(cmd);
            }
            if(cmd.hasOption("add")) {
                processAdd(cmd);
            } else if(cmd.hasOption("rm")) {
                processRemove(cmd);
            } else if(cmd.hasOption("show")) {
                processShow(cmd);
            } else if(cmd.hasOption("rem")) {
                processRename(cmd);
            } else if(cmd.hasOption("get")) {
                processGet(cmd);
            } else if(cmd.hasOption("set")) {
                processSet(cmd);
            } else if(cmd.hasOption("h") || cmd.hasOption("help")) {
                processHelp();
            } else processUsage();
        } catch(ParseException e) {
            System.out.println("Invalid argument!");
        }
    }

    /* initializes list of available options */
    private static void init() {
        Option file = Option.builder().longOpt("file").argName("file").hasArg().desc("file for loading and storing notebook content").build();
        Option add = Option.builder().longOpt("add").argName("name> <text").hasArgs().numberOfArgs(2).desc("add new note with specified name and text").build();
        Option remove = Option.builder().longOpt("rm").argName("name").hasArg().desc("remove note by it's name").build();
        Option show = Option.builder().longOpt("show").argName("start> <end> <keywords").optionalArg(true).numberOfArgs(Option.UNLIMITED_VALUES).desc("show notes from specified period containing given keywords").build();
        Option rename = Option.builder().longOpt("rem").argName("from> <to").hasArgs().numberOfArgs(2).desc("renames specified note").build();
        Option get = Option.builder().longOpt("get").argName("name").hasArg().desc("get note by it's name").build();
        Option set = Option.builder().longOpt("set").argName("name> <text").hasArgs().numberOfArgs(2).desc("change text of specified note").build();
        Option help = new Option("h", "help", false, "print help information");
        options = new Options();
        options.addOption(file);
       options.addOption(add);
       options.addOption(remove);
       options.addOption(show);
        options.addOption(rename);
       options.addOption(get);
       options.addOption(set);
       options.addOption(help);
       parser = new DefaultParser();
       formatter = new HelpFormatter();
       filePath = Paths.get("Notebook.json");
       GsonBuilder builder = new GsonBuilder();
       builder.setPrettyPrinting();
       gson = builder.create();
    }

    /* Sets new file path */
    private static void processFile(CommandLine cmd) {
        filePath = Paths.get(cmd.getOptionValue("file"));
    }

    /* adds new note */
    private static void processAdd(CommandLine cmd) {
        loadNotebookFromFile();
        String[] values = cmd.getOptionValues("add");
        notebook.addNote(values[0], values[1]);
        storeNotebookToFile();
    }

    /* removes note with the specified name */
    private static void processRemove(CommandLine cmd) {
        loadNotebookFromFile();
        if(notebook.removeNote(cmd.getOptionValue("rm"))) {
            storeNotebookToFile();
        } else System.out.println("Note not found");
    }

    /* show notes from the specified period which names contain given keywords */
    private static void processShow(CommandLine cmd) {
        loadNotebookFromFile();
        String[] values = cmd.getOptionValues("show");
        Notebook tmp = values != null ? notebook.filterByCreationDate(values[0], values[1]).filterByKeywords(Arrays.asList(values).subList(2, values.length)) : notebook;
        tmp.sortByCreationDate().getNotes().forEach(note -> System.out.println("\"" + note.getName() + "\" \"" + note.getContent() + "\""));
    }

    /* renames specified note to the given name */
    private static void processRename(CommandLine cmd) {
        loadNotebookFromFile();
        String[] values = cmd.getOptionValues("rem");
        if(notebook.renameNote(values[0], values[1])) {
            storeNotebookToFile();
        } else System.out.println("Note not found");
    }

    /* prints text of the specified note */
    private static void processGet(CommandLine cmd) {
        loadNotebookFromFile();
        String content = notebook.getNoteContent(cmd.getOptionValue("get"));
        if(content != null) {
            System.out.println("\"" + content + "\"");
        } else System.out.println("Note not found");
    }

    /* changes text of the specified note */
    private static void processSet(CommandLine cmd) {
        loadNotebookFromFile();
        String[] values = cmd.getOptionValues("set");
        if(notebook.setNoteContent(values[0], values[1])) {
            storeNotebookToFile();
        } else System.out.println("Note not found");
    }

    /* prints help information */
    private static void processHelp() {
        formatter.printHelp("ConsoleNotebook", options);
    }

    private static void processUsage() {
        final PrintWriter writer = new PrintWriter(System.out, true);
        formatter.printUsage(writer, 80, "ConsoleNotebook", options);
        writer.close();
    }

    /* initializes notebook from the specified file */
    private static void loadNotebookFromFile() {
        if(!Files.exists(filePath)) {
            notebook = new Notebook();
        } else try {  // trying to open input file
            BufferedReader reader = Files.newBufferedReader(filePath, CHARSET);
            notebook = new Notebook(Arrays.asList(gson.fromJson(reader, Note[].class)));
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /* Writes notebook content to the specified file */
    private static void storeNotebookToFile() {
        try {  // trying to open output file
            if(!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            BufferedWriter writer = Files.newBufferedWriter(filePath, CHARSET);
            gson.toJson(notebook.getNotes().toArray(), writer);
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static Notebook notebook;
    private static Options options;  // list of available options
    private static CommandLineParser parser;  // parser for command line
    private static HelpFormatter formatter;  // for usage and help
    private static Path filePath;  // default notebook file
    private static Gson gson;  // for json serialization
    private static final Charset CHARSET = StandardCharsets.UTF_8;
}
