package io.github.dtolmachev1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

class NotebookTest {
    @BeforeAll
    static void setUp() {
        formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        notes = new ArrayList<>(4);
        notes.add(new Note(LocalDateTime.parse("01.01.1601 00:00", formatter), LocalDateTime.parse("01.01.1601 00:00", formatter), "wStart", "Windows time starts here"));
        notes.add(new Note(LocalDateTime.parse("01.01.1601 00:00", formatter), LocalDateTime.parse("01.01.2020 06:00", formatter), "Change", "Time to modify something"));
        notes.add(new Note(LocalDateTime.parse("24.06.2021 11:11", formatter), LocalDateTime.parse("24.06.2021 11:11", formatter), "Note", "Just random note"));
        notes.add(new Note(LocalDateTime.parse("31.12.9999 23:59", formatter), LocalDateTime.parse("31.12.9999 23:59", formatter), "Never", "Some random time value"));
        List<Note> tmp = new ArrayList<>(4);
        tmp.add(new Note(LocalDateTime.parse("01.01.1970 00:00", formatter), LocalDateTime.parse("01.01.1970 00:00", formatter), "Beginning", "Time starts here"));
        tmp.add(new Note(LocalDateTime.parse("01.01.1970 00:00", formatter), LocalDateTime.parse("01.01.2000 12:00", formatter), "Modified", "It's time to change something"));
        tmp.add(new Note(LocalDateTime.parse("14.04.2014 00:00", formatter), LocalDateTime.parse("14.04.2014 00:00", formatter), "Test", "Just some text"));
        tmp.add(new Note(LocalDateTime.parse("19.01.2038 03:14", formatter), LocalDateTime.parse("19.01.2038 03:14", formatter), "Ending", "Time ends here"));
        notebook = new Notebook(tmp);
    }

    @Test
    @DisplayName("Sort by creation date")
    @SuppressWarnings("ConstantConditions")
    void sortByCreationDate_Test() {
        List<Note> src = notebook.sortByCreationDate().getNotes();
        List<Note> exp = notebook.getNotes();
        exp.sort(Comparator.comparing(Note::getCreationDate));
        ListIterator<Note> firstIt = src.listIterator();
        ListIterator<Note> secondIt = exp.listIterator();
        while(firstIt.hasNext() && secondIt.hasNext()) {
            Note firstNote = firstIt.next();
            Note secondNote = secondIt.next();
            Assertions.assertAll("Comparing src and exp", () -> Assertions.assertEquals(firstNote.getCreationDate(), secondNote.getCreationDate()), () -> Assertions.assertEquals(firstNote.getModificationDate(), secondNote.getModificationDate()), () -> Assertions.assertEquals(firstNote.getName(), secondNote.getName()), () -> Assertions.assertEquals(firstNote.getContent(), secondNote.getContent()));
        }
    }

    @Test
    @DisplayName("Sort by modification date")
    @SuppressWarnings("ConstantConditions")
    void sortByModificationDate_Test() {
        List<Note> src = notebook.sortByModificationDate().getNotes();
        List<Note> exp = notebook.getNotes();
        exp.sort(Comparator.comparing(Note::getModificationDate));
        ListIterator<Note> firstIt = src.listIterator();
        ListIterator<Note> secondIt = exp.listIterator();
        while(firstIt.hasNext() && secondIt.hasNext()) {
            Note firstNote = firstIt.next();
            Note secondNote = secondIt.next();
            Assertions.assertAll("Comparing src and exp", () -> Assertions.assertEquals(firstNote.getCreationDate(), secondNote.getCreationDate()), () -> Assertions.assertEquals(firstNote.getModificationDate(), secondNote.getModificationDate()), () -> Assertions.assertEquals(firstNote.getName(), secondNote.getName()), () -> Assertions.assertEquals(firstNote.getContent(), secondNote.getContent()));
        }
    }

    @Test
    @DisplayName("Sort by name")
    @SuppressWarnings("ConstantConditions")
    void sortByName_Test() {
        List<Note> src = notebook.sortByName().getNotes();
        List<Note> exp = notebook.getNotes();
        exp.sort(Comparator.comparing(Note::getName));
        ListIterator<Note> firstIt = src.listIterator();
        ListIterator<Note> secondIt = exp.listIterator();
        while(firstIt.hasNext() && secondIt.hasNext()) {
            Note firstNote = firstIt.next();
            Note secondNote = secondIt.next();
            Assertions.assertAll("Comparing src and exp", () -> Assertions.assertEquals(firstNote.getCreationDate(), secondNote.getCreationDate()), () -> Assertions.assertEquals(firstNote.getModificationDate(), secondNote.getModificationDate()), () -> Assertions.assertEquals(firstNote.getName(), secondNote.getName()), () -> Assertions.assertEquals(firstNote.getContent(), secondNote.getContent()));
        }
    }

    @Test
    @DisplayName("Filter by creation date")
    @SuppressWarnings("ConstantConditions")
    void filterByCreationDate_Test() {
        List<Note> src = notebook.filterByCreationDate("01.01.2000 00:00", "31.12.2020 23:59").getNotes();
        List<Note> exp = notebook.getNotes();
        exp.removeIf(note -> (note.getCreationDate().compareTo(LocalDateTime.parse("01.01.2000 00:00", formatter)) >= 0));
                exp.removeIf(note -> (note.getCreationDate().compareTo(LocalDateTime.parse("31.12.2020 23:59", formatter)) <= 0));
        ListIterator<Note> firstIt = src.listIterator();
        ListIterator<Note> secondIt = exp.listIterator();
        while(firstIt.hasNext() && secondIt.hasNext()) {
            Note firstNote = firstIt.next();
            Note secondNote = secondIt.next();
            Assertions.assertAll("Comparing src and exp", () -> Assertions.assertEquals(firstNote.getCreationDate(), secondNote.getCreationDate()), () -> Assertions.assertEquals(firstNote.getModificationDate(), secondNote.getModificationDate()), () -> Assertions.assertEquals(firstNote.getName(), secondNote.getName()), () -> Assertions.assertEquals(firstNote.getContent(), secondNote.getContent()));
        }
    }

    @Test
    @DisplayName("Filter by modification date")
    @SuppressWarnings("ConstantConditions")
    void filterByModificationDate_Test() {
        List<Note> src = notebook.filterByModificationDate("01.01.2000 00:00", "31.12.2020 23:59").getNotes();
        List<Note> exp = notebook.getNotes();
        exp.removeIf(note -> (note.getModificationDate().compareTo(LocalDateTime.parse("01.01.2000 00:00", formatter)) >= 0));
        exp.removeIf(note -> (note.getModificationDate().compareTo(LocalDateTime.parse("31.12.2020 23:59", formatter)) <= 0));
        ListIterator<Note> firstIt = src.listIterator();
        ListIterator<Note> secondIt = exp.listIterator();
        while(firstIt.hasNext() && secondIt.hasNext()) {
            Note firstNote = firstIt.next();
            Note secondNote = secondIt.next();
            Assertions.assertAll("Comparing src and exp", () -> Assertions.assertEquals(firstNote.getCreationDate(), secondNote.getCreationDate()), () -> Assertions.assertEquals(firstNote.getModificationDate(), secondNote.getModificationDate()), () -> Assertions.assertEquals(firstNote.getName(), secondNote.getName()), () -> Assertions.assertEquals(firstNote.getContent(), secondNote.getContent()));
        }
    }

    @Test
    @DisplayName("Filter by keywords")
    @SuppressWarnings("ConstantConditions")
    void filterByKeywords_Test() {
        List<Note> src = notebook.filterByKeywords(Arrays.asList("Beginning", "Ending")).getNotes();
        List<Note> exp = notebook.getNotes();
        exp.removeIf(note -> Arrays.stream(new String[]{"Beginning", "Ending"}).noneMatch(note.getName()::contains));
        ListIterator<Note> firstIt = src.listIterator();
        ListIterator<Note> secondIt = exp.listIterator();
        while(firstIt.hasNext() && secondIt.hasNext()) {
            Note firstNote = firstIt.next();
            Note secondNote = secondIt.next();
            Assertions.assertAll("Comparing src and exp", () -> Assertions.assertEquals(firstNote.getCreationDate(), secondNote.getCreationDate()), () -> Assertions.assertEquals(firstNote.getModificationDate(), secondNote.getModificationDate()), () -> Assertions.assertEquals(firstNote.getName(), secondNote.getName()), () -> Assertions.assertEquals(firstNote.getContent(), secondNote.getContent()));
        }
    }

    @Test
    @DisplayName("Get notes")
    void getNotes_Test() {
        List<Note> notes = notebook.getNotes();
        for(Note note : notes) {
            Assertions.assertTrue(notebook.noteContains(note.getName()));
        }
    }

    @Test
    @DisplayName("Add notes")
    void addNotes_Test() {
        Notebook tmp = new Notebook();
        tmp.addNotes(notes);
        for(Note note : notes) {
            Assertions.assertTrue(tmp.noteContains(note.getName()));
        }
    }

    @Test
    @DisplayName("Get note")
    @SuppressWarnings("ConstantConditions")
    void getNote_Test() {
        List<Note> notes = notebook.getNotes();
        for(Note note : notes) {
            Note other = notebook.getNote(note.getName());
            Assertions.assertAll("Comparing note and other", () -> Assertions.assertEquals(note.getCreationDate(), other.getCreationDate()), () -> Assertions.assertEquals(note.getModificationDate(), other.getModificationDate()), () -> Assertions.assertEquals(note.getName(), other.getName()), () -> Assertions.assertEquals(note.getContent(), other.getContent()));
        }
    }

    @Test
    @DisplayName("Note contains")
    void noteContains_Test() {
        List<Note> notes = notebook.getNotes();
        for(Note note : notes) {
            Assertions.assertTrue(notebook.noteContains(note.getName()));
        }
    }

    @Test
    @DisplayName("Add empty note")
    void addNote_EmptyNote() {
        Notebook tmp = new Notebook();
        tmp.addNote("Empty note");
        Assertions.assertTrue(tmp.noteContains("Empty note"));
        Assertions.assertEquals(tmp.getNote("Empty note").getContent(), "");
    }

    @Test
    @DisplayName("Add note with name and content")
    void addNote_NoteWithNameAndContent() {
        Notebook tmp = new Notebook();
        tmp.addNote("Test note", "Some text for testing");
        Assertions.assertTrue(tmp.noteContains("Test note"));
        Assertions.assertEquals(tmp.getNote("Test note").getContent(), "Some text for testing");
    }

    @Test
    @DisplayName("Add copy of already existing note")
    @SuppressWarnings("ConstantConditions")
    void addNote_CopyOfAlreadyExistingNote() {
        Notebook tmp = new Notebook();
        for(Note note : notes) {
            tmp.addNote(note);
            Assertions.assertTrue(tmp.noteContains(note.getName()));
            Note other = tmp.getNote(note.getName());
            Assertions.assertAll("Comparing note and other", () -> Assertions.assertEquals(note.getCreationDate(), other.getCreationDate()), () -> Assertions.assertEquals(note.getModificationDate(), other.getModificationDate()), () -> Assertions.assertEquals(note.getName(), other.getName()), () -> Assertions.assertEquals(note.getContent(), other.getContent()));
        }
    }

    @Test
    @DisplayName("Remove note")
    void removeNote_Test() {
        Notebook tmp = new Notebook(notes);
        for(Note note : notes) {
            tmp.removeNote(note.getName());
            Assertions.assertFalse(tmp.noteContains(note.getName()));
        }
    }

    @Test
    @DisplayName("Rename note")
    void renameNote_Test() {
        Notebook tmp = new Notebook();
        tmp.addNote("Some note");
        tmp.renameNote("Some note", "Other note");
        Assertions.assertFalse(tmp.noteContains("Some note"));
        Assertions.assertTrue(tmp.noteContains("Other note"));
    }

    @Test
    @DisplayName("Get note creation date")
    void getNoteCreationDate_Test() {
        List<Note> notes = notebook.getNotes();
        for(Note note : notes) {
            Assertions.assertEquals(notebook.getNoteCreationDate(note.getName()), note.getCreationDate());
        }
    }

    @Test
    @DisplayName("Get note modification date")
    void getNoteModificationDate_Test() {
        List<Note> notes = notebook.getNotes();
        for(Note note : notes) {
            Assertions.assertEquals(notebook.getNoteModificationDate(note.getName()), note.getModificationDate());
        }
    }

    @Test
    @DisplayName("Get note content")
    void getNoteContent_Test() {
        List<Note> notes = notebook.getNotes();
        for(Note note : notes) {
            Assertions.assertEquals(notebook.getNoteContent(note.getName()), note.getContent());
        }
    }

    @Test
    @DisplayName("Set note content")
    void setNoteContent_Test() {
        Notebook tmp = new Notebook();
        tmp.addNote("Test note", "Some text for testing");
        tmp.setNoteContent("Test note", "Other text for testing");
        Assertions.assertNotEquals(tmp.getNoteContent("Test note"), "Some text for testing");
        Assertions.assertEquals(tmp.getNoteContent("Test note"), "Other text for testing");
    }

    @Test
    @DisplayName("Example")
    void example_Test() {
        Notebook tmp = new Notebook();
        Note note = new Note(LocalDateTime.parse("15.12.2019 22:00", formatter), LocalDateTime.parse("15.12.2019 22:00", formatter), "My note", "My very important note");
        tmp.addNote(note);
        Assertions.assertTrue(tmp.noteContains(note.getName()));
        Note other = tmp.getNote(note.getName());
        Assertions.assertAll("Comparing note and other", () -> Assertions.assertEquals(note.getCreationDate(), other.getCreationDate()), () -> Assertions.assertEquals(note.getModificationDate(), other.getModificationDate()), () -> Assertions.assertEquals(note.getName(), other.getName()), () -> Assertions.assertEquals(note.getContent(), other.getContent()));
        tmp.removeNote(note.getName());
        Assertions.assertFalse(tmp.noteContains(note.getName()));
        List<Note> notes = tmp.sortByCreationDate().getNotes();
        Assertions.assertTrue(notes.isEmpty());
        notes = tmp.filterByCreationDate("14.12.2019 07:00", "17.12.2019 13:00").filterByKeywords(Arrays.asList("Mine", "Yours", "Me")).sortByCreationDate().getNotes();
        Assertions.assertTrue(notes.isEmpty());
    }

    private static Notebook notebook;  // notebook for testing
    private static List<Note> notes;  // list of notes
    private static DateTimeFormatter formatter;  // formatter for date and time
}
