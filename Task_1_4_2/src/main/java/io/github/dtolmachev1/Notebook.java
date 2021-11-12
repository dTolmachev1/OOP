package io.github.dtolmachev1;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Class for notebook.</p>
 */
public class Notebook {
    /**
     * <p>Default constructor to create empty notebook with initialized fields.</p>
     */
    public Notebook() {
        this("dd.MM.yyyy HH:mm");
    }

    /**
     * <p>Constructor to create empty notebook with given date and time format pattern.</p>
     *
     * @param pattern Date and time format pattern.
     */
    public Notebook(String pattern) {
        this(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * <p>Constructor to create empty notebook with given date and time formatter.</p>
     *
     * @param formatter Date and time formatter.
     */
    public Notebook(DateTimeFormatter formatter) {
        this.notes = new ArrayList<>();
        this.formatter = formatter;
    }

    /**
     * <p>Constructor to create empty notebook with specified list of notes.</p>
     *
     * @param notes List of notes to copy into newly created notebook.
     */
    public Notebook(Collection<Note> notes) {
        this();
        notes.forEach(note -> this.notes.add(new Note(note)));
    }

    /**
     * <p>Constructor to create notebook with given date and time format pattern and specified list of notes.</p>
     *
     * @param notes List of notes to copy into newly created notebook.
     * @param pattern Date and time format pattern.
     */
    public Notebook(Collection<Note> notes, String pattern) {
        this(pattern);
        notes.forEach(note -> this.notes.add(new Note(note)));
    }

    /**
     * <p>Constructor to create empty notebook with given date and time formatter and specified list of notes.</p>
     *
     * @param notes List of notes to copy into newly created notebook.
     * @param formatter Date and time formatter.
     */
    public Notebook(Collection<Note> notes, DateTimeFormatter formatter) {
        this(formatter);
        notes.forEach(note -> this.notes.add(new Note(note)));
    }

    /**
     * <p>Sorts notes list by theirs creation dates.</p>
     *
     * @return Notebook with notes sorted by creation date.
     */
    public Notebook sortByCreationDate() {
        return new Notebook(this.notes.stream().sorted(Comparator.comparing(Note::getCreationDate)).map(Note::new).collect(Collectors.toList()), this.formatter);
    }

    /**
     * <p>Sorts notes list by theirs modification dates.</p>
     *
     * @return Notebook with notes sorted by modification date.
     */
    public Notebook sortByModificationDate() {
        return new Notebook(this.notes.stream().sorted(Comparator.comparing(Note::getModificationDate)).map(Note::new).collect(Collectors.toList()), this.formatter);
    }

    /**
     * <p>Sorts notes list by theirs names.</p>
     *
     * @return Notebook with notes sorted by name.
     */
    public Notebook sortByName() {
        return new Notebook(this.notes.stream().sorted(Comparator.comparing(Note::getName)).map(Note::new).collect(Collectors.toList()), this.formatter);
    }

    /**
     * <p>Returns list of notes from the specified range of dates.</p>
     *
     * @param from Beginning date (could be <code>null</code>).
     * @param to Ending date (could be <code>null</code>).
     * @return Notebook with notes from the specified period.
     */
    public Notebook filterByCreationDate(String from, String to) {
        Stream<Note> stream = this.notes.stream();  // stream of notes
        if(from != null) {
            stream = stream.filter(note -> (note.getCreationDate().compareTo(LocalDateTime.parse(from, formatter)) >= 0));
        }
        if(to != null) {
            stream = stream.filter(note -> (note.getCreationDate().compareTo(LocalDateTime.parse(to, formatter)) <= 0));
        }
        return new Notebook(stream.map(Note::new).collect(Collectors.toList()), this.formatter);
    }

    /**
     * <p>Returns list of notes from the specified range of dates.</p>
     *
     * @param from Beginning date (could be <code>null</code>).
     * @param to Ending date (could be <code>null</code>).
     * @return Notebook with notes from the specified period.
     */
    public Notebook filterByModificationDate(String from, String to) {
        Stream<Note> stream = this.notes.stream();  // stream of notes
        if(from != null) {
            stream = stream.filter(note -> (note.getModificationDate().compareTo(LocalDateTime.parse(from, formatter)) >= 0));
        }
        if(to != null) {
            stream = stream.filter(note -> (note.getModificationDate().compareTo(LocalDateTime.parse(to, formatter)) <= 0));
        }
        return new Notebook(stream.map(Note::new).collect(Collectors.toList()), this.formatter);
    }

    /**
     * <p>Returns list of notes which names contains any of specified keywords.</p>
     *
     * @param keywords Keywords for searching.
     * @return Notebook notes which names contain any of specified keywords.
     */
    public Notebook filterByKeywords(List<String> keywords) {
        return new Notebook(this.notes.stream().filter(note -> keywords.stream().anyMatch(note.getName()::contains)).map(Note::new).collect(Collectors.toList()), this.formatter);
    }

    /**
     * <p>Returns list of all notes from this notebook.</p>
     *
     * @return list with all notes from this notebook.
     */
    public List<Note> getNotes() {
        return this.notes.stream().map(Note::new).collect(Collectors.toList());
    }

    /**
     * <p>Adds specified notes to the list.</p>
     *
     * @param notes List of notes to be copied.
     */
    public void addNotes(Collection<Note> notes) {
        notes.forEach(note -> this.notes.add(new Note(note)));
    }

    /**
     * <p>Finds note by it's name.</p>
     *
     * @param name Note for searching.
     * @return Note with specified name or <code>null</code> if it doesn't exist.
     */
    public Note getNote(String name) {
        return this.notes.stream().filter(note -> (note.getName().compareTo(name) == 0)).map(Note::new).findFirst().orElse(null);
    }

    /**
     * <p>Checks if note with specified name already in the list.</p>
     *
     * @param name Note name for searching.
     * @return <code>true</code> if note with specified name contains in the list or <code>false</code> otherwise.
     */
    public boolean noteContains(String name) {
        return this.notes.stream().anyMatch(note -> (note.getName().compareTo(name) == 0));
    }

    /**
     * <p>Adds empty note to the list.</p>
     *
     * @param name Name of note to be added.
     */
    public void addNote(String name) {
        this.notes.add(new Note(name));
    }

    /**
     * <p>Adds new note to the list.</p>
     *
     * @param name Name of note to be added.
     * @param content Text of note to be added.
     */
    public void addNote(String name, String content) {
        this.notes.add(new Note(name, content));
    }

    /**
     * <p>Adds copy of a given note to the list.</p>
     *
     * @param note Note to be copied.
     */
    public void addNote(Note note) {
        this.notes.add(new Note(note));
    }

    /**
     * <p>Removes note by it's name (if possible).</p>
     *
     * @param name Note name to be removed.
     * @return <code>true</code> if note list was changed (note with specified name exists) or <code>false</code> otherwise.
     */
    public boolean removeNote(String name) {
        return this.notes.removeIf(note -> (note.getName().compareTo(name) == 0));
    }

    /**
     * <p>Renames specified note (if possible).</p>
     *
     * @param from Old name.
     * @param to New name.
     * @return <code>true</code> if note was changed (note with specified name exists) or <code>false</code> otherwise.
     */
    public boolean renameNote(String from, String to) {
        Note noteReference = this.notes.stream().filter(note -> (note.getName().compareTo(from) == 0)).findFirst().orElse(null);  // note to be renamed
        if(noteReference != null) {
            noteReference.rename(to);
            return true;
        }
        return false;
    }

    /**
     * <p>Returns creation date for the specified note (if possible).</p>
     *
     * @param name Note name for searching.
     * @return Note's creation date (if note with specified name exists) or <code>null</code> otherwise.
     */
    public LocalDateTime getNoteCreationDate(String name) {
        Note noteReference = this.notes.stream().filter(note -> (note.getName().compareTo(name) == 0)).findFirst().orElse(null);  // searching for the specified note
        return noteReference != null ? noteReference.getCreationDate() : null;
    }

    /**
     * <p>Returns modification date for the specified note (if possible).</p>
     *
     * @param name Note name for searching.
     * @return Note's modification date (if note with specified name exists) or <code>null</code> otherwise.
     */
    public LocalDateTime getNoteModificationDate(String name) {
        Note noteReference = this.notes.stream().filter(note -> (note.getName().compareTo(name) == 0)).findFirst().orElse(null);  // searching for the specified note
        return noteReference != null ? noteReference.getModificationDate() : null;
    }

    /**
     * <p>Returns text for the specified note (if possible).</p>
     *
     * @param name Note name for searching.
     * @return Note's text (if note with specified name exists) or <code>null</code> otherwise.
     */
    public String getNoteContent(String name) {
        Note noteReference = this.notes.stream().filter(note -> (note.getName().compareTo(name) == 0)).findFirst().orElse(null);  // searching for the specified note
        return noteReference != null ? noteReference.getContent() : null;
    }

    /**
     * <p>Changes text of the specified note (if possible).</p>
     *
     * @param name Note name for searching.
     * @param content Note text to be changed.
     * @return <code>true</code> if note was changed (note with specified name exists) or <code>false</code> otherwise.
     */
    public boolean setNoteContent(String name, String content) {
        Note noteReference = this.notes.stream().filter(note -> (note.getName().compareTo(name) == 0)).findFirst().orElse(null);  // note to be changed
        if(noteReference != null) {
            noteReference.setContent(content);
            return true;
        }
        return false;
    }

    private final List<Note> notes;  // list of notes
    private final DateTimeFormatter formatter;  // formatter for date and time
}
