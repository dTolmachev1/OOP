package io.github.dtolmachev1;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>Class for single note.</p>
 */
public class Note implements Cloneable {
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
     * <p>Checks if this object is equal to the specified one.</p>
     *
     * @param object Note instance to compare.
     * @return <code>true</code> if objects are equal or <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if(object == this) {
            return true;
        }
        if(!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        boolean creationDateEquals = (this.creationDate == null && other.creationDate == null)
                || (this.creationDate != null && this.creationDate.equals(other.creationDate));
        boolean modificationDateEquals = (this.modificationDate == null && other.modificationDate == null)
                || (this.modificationDate != null && this.modificationDate.equals(other.modificationDate));
        boolean nameEquals = (this.name == null && other.name == null)
                || (this.name != null && this.name.equals(other.name));
        boolean contentEquals = (this.content == null && other.content == null)
                || (this.content != null && this.content.equals(other.content));
        return creationDateEquals && modificationDateEquals && nameEquals && contentEquals;
    }

    /**
     * <p>A hash code for this note.</p>
     *
             * @return A suitable hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    /**
     * <p>Creates a copy of this note.</p>
     *
     * @return A copy of this note.
     */
    @Override
    public Note clone() {
        Note note = null;  // for storing cloned object
        try {
            note = (Note) super.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return note;
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
