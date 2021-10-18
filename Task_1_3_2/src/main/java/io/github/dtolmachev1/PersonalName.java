package io.github.dtolmachev1;

/**
 * <p>Class for representing personal name.</p>
 */
public class PersonalName {
    /**
     * <p>Constructor which creates new personal name with given values.</p>
     *
     * @param firstName student's first name.
     * @param surname student's surname.
     * @param patronymic student's patronymic.
     */
    PersonalName(String firstName, String surname, String patronymic) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    /**
     * <p>Returns student's first name.</p>
     *
     * @return student's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * <p>Returns student's surname.</p>
     *
     * @return student's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * <p>Returns student's patronymic.</p>
     *
     * @return student's patronymic.
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * <p>Returns student's full name.</p>
     *
     * @return student's full name.
     */
    public String getFullName() {
        return firstName + ' ' + surname + ' ' + patronymic;
    }

    private final String firstName;  // for storing first name
    private final String surname;  // for storing surname
    private final String patronymic;  // for storing patronymic
}
