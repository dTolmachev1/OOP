package io.github.dtolmachev1;

import java.time.LocalDateTime;

/**
 * <p>Class for single note.</p>
 */
public class Note {
    /**
     * <p>Constructor to create new empty note.</p>
     *
     * @param name Name of note to be created.
     */
    public Note(String name) {
        this(name, "");
    }

    /**
     * <p>Constructor to create new note with specified name and content.</p>
     *
     * @param name Name of newly created note.
     * @param content Text of newly created note.
     */
    public Note(String name, String content) {
        this(LocalDateTime.now(), LocalDateTime.now(), name, content);
    }

    /**
     * <p>Constructor to copy an already existing note.</p>
     *
     * @param note Note to be copied.
     */
    public Note(Note note) {
        this(note.creationDate, note.modificationDate, note.name, note.content);
    }

    /**
     * <p>Constructor to create new note with specified creation date, modification date, name and content.</p>
     *
     * @param creationDate Creation date of newly created note.
     * @param modificationDate Modification date of newly created note.
     * @param name Name of newly created note.
     * @param content Text of newly created note.
     */
    public Note(LocalDateTime creationDate, LocalDateTime modificationDate, String name, String content) {
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.name = name;
        this.content = content;
    }

    /**
     * <p>Renames this note to the specified name.</p>
     *
     * @param name To be renamed.
     */
    public void rename(String name) {
        this.modificationDate = LocalDateTime.now();
        this.name = name;
    }

    /**
     * <p>Returns creation date of this note.</p>
     *
     * @return Note's creation date.
     */
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * <p>Returns modification date of this note.</p>
     *
     * @return Note's modification date.
     */
    public LocalDateTime getModificationDate() {
        return this.modificationDate;
    }

    /**
     * <p>Returns name of this note.</p>
     *
     * @return Note's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>Returns text of this note.</p>
     *
     * @return Note's text.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * <p>Changes note's content.</p>
     *
     * @param content To be changed.
     */
    public void setContent(String content) {
        this.modificationDate = LocalDateTime.now();
        this.content = content;
    }

    private String name;  // name of note
    private final LocalDateTime creationDate;  // date of note creation
    private LocalDateTime modificationDate;  // date of note modification
    private String content;  // note text
}
