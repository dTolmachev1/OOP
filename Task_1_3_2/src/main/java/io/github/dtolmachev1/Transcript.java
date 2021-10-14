package io.github.dtolmachev1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Class for representing student's transcript.</p>
 */
public class Transcript {
    /**
     * <p>Constructor which creates new transcript with given values.</p>
     *
     * @param personalName student's full name.
     * @param identifier unique transcript's identifier.
     * @param department student's department.
     */
    Transcript(PersonalName personalName, int identifier, String department) {
        this.personalName = personalName;
        this.identifier = identifier;
        this.department = department;
        this.grades = new ArrayList<>();
        this.thesis = null;
    }

    /**
     * <p>Constructor which creates new transcript with given values.</p>
     *
     * @param firstName student's first name.
     * @param surname student's surname.
     * @param patronymic student's patronymic.
     * @param identifier unique transcript's identifier.
     * @param department student's department.
     */
    Transcript(String firstName, String surname, String patronymic, int identifier, String department) {
        this.personalName = new PersonalName(firstName, surname, patronymic);
        this.identifier = identifier;
        this.department = department;
        this.grades = new ArrayList<>();
        this.thesis = null;
    }

    /**
     * <p>Constructor which creates new transcript with given values.</p>
     *
     * @param personalName student's full name.
     * @param identifier unique transcript's identifier.
     * @param department student's department.
     * @param semesterCount number of semesters.
     */
    Transcript(PersonalName personalName, int identifier, String department, int semesterCount) {
        this.personalName = personalName;
        this.identifier = identifier;
        this.department = department;
        this.grades = new ArrayList<>(semesterCount);
        for(int i = 0; i < semesterCount; i++) {
            grades.add(new HashMap<>());
        }
        this.thesis = null;
    }

    /**
     * <p>Constructor which creates new transcript with given values.</p>
     *
     * @param firstName student's first name.
     * @param surname student's surname.
     * @param patronymic student's patronymic.
     * @param identifier unique transcript's identifier.
     * @param department student's department.
     * @param semesterCount number of semesters.
     */
    Transcript(String firstName, String surname, String patronymic, int identifier, String department, int semesterCount) {
        this.personalName = new PersonalName(firstName, surname, patronymic);
        this.identifier = identifier;
        this.department = department;
        this.grades = new ArrayList<>(semesterCount);
        for(int i = 0; i < semesterCount; i++) {
            grades.add(new HashMap<>());
        }
        this.thesis = null;
    }

    /**
     * <p>Constructor which creates new transcript with given values.</p>
     *
     * @param personalName student's full name.
     * @param identifier unique transcript's identifier.
     * @param department student's department.
     * @param subjects <code>Collection</code> containing subjects to be added.
     */
    Transcript(PersonalName personalName, int identifier, String department, Collection<String>[] subjects) {
        this.personalName = personalName;
        this.identifier = identifier;
        this.department = department;
        this.grades = new ArrayList<>(subjects.length);
        for(int i = 0; i < subjects.length; i++) {
            grades.add(new HashMap<>(subjects[i].size()));
            for(String subject : subjects[i]) {
                grades.get(i).put(subject, null);
            }
        }
        this.thesis = null;
    }

    /**
     * <p>Constructor which creates new transcript with given values.</p>
     *
     * @param firstName student's first name.
     * @param surname student's surname.
     * @param patronymic student's patronymic.
     * @param identifier unique transcript's identifier.
     * @param department student's department.
     * @param subjects <code>Collection</code> containing subjects to be added.
     */
    Transcript(String firstName, String surname, String patronymic, int identifier, String department, Collection<String>[] subjects) {
        this.personalName = new PersonalName(firstName, surname, patronymic);
        this.identifier = identifier;
        this.department = department;
        this.grades = new ArrayList<>(subjects.length);
        for(int i = 0; i < subjects.length; i++) {
            grades.add(new HashMap<>(subjects[i].size()));
            for(String subject : subjects[i]) {
                grades.get(i).put(subject, null);
            }
        }
        this.thesis = null;
    }

    /**
     * <p>Returns student's full name.</p>
     *
     * @return student's full name.
     */
    public PersonalName getPersonalName() {
        return personalName;
    }

    /**
     * <p>Returns student's full name.</p>
     *
     * @return student's full name.
     */
    public String getFullName() {
        return personalName.getFullName();
    }

    /**
     * <p>Returns unique transcript's identifier.</p>
     *
     * @return unique transcript's identifier.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * <p>Returns student's department.</p>
     *
     * @return student's department.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * <p>Returns pairs like &lt;subject, grade&gt; separately for each semester.</p>
     *
     * @return array containing corresponding <code>HashMap</code> for each semester.
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, Integer>[] getGrades() {
        return grades.toArray((HashMap<String, Integer>[]) new HashMap[]{});
    }

    /**
     * <p>Increases number of semesters by one.</p>
     */
    public void addSemester() {
        grades.add(new HashMap<>());
    }

    /**
     * <p>Adds new semester with specified subjects.</p>
     *
     * @param subjects <code>Collection</code> with subjects to be added.
     */
    public void addSemester(Collection<String> subjects) {
        grades.add(new HashMap<>(subjects.size()));
        for(String subject : subjects) {
            grades.get(grades.size() - 1).put(subject, null);
        }
    }

    /**
     * <p>Returns pairs like &lt;subject, grade&gt; for specified semester.</p>
     *
     * @param semester which grades should be returned.
     * @return <code>HashMap</code> containing grades for specified semester.
     * @throws IndexOutOfBoundsException if <code>semester</code> greater than total number of semesters.
     */
    public HashMap<String, Integer> getSubjects(int semester) throws IndexOutOfBoundsException {
        return new HashMap<>(grades.get(semester - 1));
    }

    /**
     * <p>Adds all given subjects to the specified semester.</p>
     *
     * @param semester to adding subjects.
     * @param subjects <code>Collection</code> with subjects to be added.
     * @throws IndexOutOfBoundsException if <code>semester</code> greater than total number of semesters.
     */
    public void setSubjects(int semester, Collection<String> subjects) throws IndexOutOfBoundsException {
        for(String subject : subjects) {
            grades.get(semester - 1).put(subject, null);
        }
    }

    /**
     * <p>Adds new subject to the transcript.</p>
     *
     * @param semester in which this subject is studied.
     * @param subject name of the subject to be added.
     * @throws IndexOutOfBoundsException if <code>semester</code> greater than total number of semesters.
     */
    public void addSubject(int semester, String subject) throws IndexOutOfBoundsException {
        grades.get(semester - 1).put(subject, null);
    }

    /**
     * <p>Returns grade associated with specified subject if it exists.</p>
     *
     * @param semester in which this subject is studied.
     * @param subject which grade should be gotten.
     * @return grade associated with specified subject or <code>null</code> if it doesn't exist.
     * @throws IndexOutOfBoundsException if <code>semester</code> greater than total number of semesters.
     */
    public Integer getGrade(int semester, String subject) throws IndexOutOfBoundsException {
        return grades.get(semester - 1).get(subject);
    }

    /**
     * <p>Sets specified grade for given subject if it's unspecified.</p>
     *
     * @param semester in which this subject is studied.
     * @param subject which grade should be set.
     * @param grade to be set.
     * @return <code>true</code> if specified grade was successfully set to a given subject, or <code>false</code> if this transcript doesn't contain given subject, or it's grade is already specified.
     * @throws IndexOutOfBoundsException if <code>semester</code> greater than total number of semesters.
     */
    public boolean setGrade(int semester, String subject, int grade) throws IndexOutOfBoundsException {
        return grades.get(semester - 1).replace(subject, null, grade);
    }

    /**
     * <p>Returns student's diploma supplement.</p>
     *
     * @return student's diploma supplement.
     */
    public HashMap<String, Integer> getSupplement() {
        HashMap<String, Integer> supplement = new HashMap<>();
        for(HashMap<String, Integer> subjects : grades) {
            supplement.putAll(subjects);
        }
        return supplement;
    }

    /**
     * <p>Returns thesis grade.</p>
     *
     * @return thesis grade.
     */
    public int getThesis() {
        return thesis;
    }

    /**
     * <p>Sets thesis grade if its unspecified.</p>
     *
     * @param thesis grade to be set.
     * @return <code>true</code> if thesis grade was successfully set or <code>false</code> otherwise.
     */
    public boolean setThesis(int thesis) {
        if(this.thesis == null) {
            this.thesis = thesis;
            return true;
        }
        return false;
    }

    /**
     * <p>Calculates grade point averages.</p>
     *
     * @return grade point averages.
     */
    public double calculateGPA() {
        int count = 0;
        int total = 0;
        for(HashMap<String, Integer> subjects : grades) {
            for(Map.Entry<String, Integer> subject : subjects.entrySet()) {
                if(subject.getValue() != null) {
                    total += subject.getValue();
                    count++;
                }
            }
        }
        return (double) total / count;
    }

    /**
     * <p>Checks weather student is able to have honours degree.</p>
     *
     * @return <code>true</code> if student is able to have honours degree or <code>false</code> otherwise.
     */
    public boolean isHonoursDegree() {
        if(thesis == 5) {
            HashMap<String, Integer> supplement = getSupplement();
            int totalCount = 0;
            int excellentCount = 0;
            for (Map.Entry<String, Integer> subject : supplement.entrySet()) {
                if (subject.getValue() != null) {
                    if (subject.getValue() < 4) {
                        return false;
                    }
                    if (subject.getValue() == 5) {
                        excellentCount++;
                    }
                    totalCount++;
                }
            }
            return (double) excellentCount / totalCount >= 0.75;
        }
        return false;
    }

    /**
     * <p>Checks weather student is able to have advanced scholarship in specified semester.</p>
     *
     * @param semester to be checked.
     * @return <code>true</code> if student is able to have advanced scholarship or <code>false</code> otherwise.
     * @throws IndexOutOfBoundsException if <code>semester</code> greater than total number of semesters.
     */
    public boolean isAdvancedScholarship(int semester) throws IndexOutOfBoundsException {
        if(semester != 1) {
            for(Map.Entry<String, Integer> subject : grades.get(semester - 2).entrySet()) {
                if((subject.getValue() != null) && (subject.getValue() < 4)) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    private final PersonalName personalName;  // student's name
    private final int identifier;  // unique identifier for transcript
    private final String department;  // student's department
    private final ArrayList<HashMap<String, Integer>> grades;  // for storing pairs like <subject, grade> separately for each semester
    private Integer thesis;  // for storing thesis grade

    /**
     * <p>Class for representing personal name.</p>
     */
    public static class PersonalName {
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
}
