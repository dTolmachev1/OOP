package io.github.dtolmachev1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class TranscriptTest {
    @BeforeAll
    @SuppressWarnings("unchecked")
    static void setUp() {
        firstNames = new String[]{"Ivan", "Pyotr", "Alexey", "Fedor"};
        surnames = new String[]{"Petrov", "Alexiev", "Fedorov", "Ivanov"};
        patronymics = new String[]{"Alexeevitch", "Fedorovich", "Ivanovich", "Petrovich"};
        identifiers = new int[]{104857, 209715, 419430, 838860};
        department = "Computer science";
        subjects = (ArrayList<String>[]) new ArrayList[8];
        subjects[0] = new ArrayList<>(Arrays.asList("Introduction to algebra and analysis", "Introduction to discrete mathematics and mathematical logic", "Declarative programming", "Imperative programming", "Digital platforms", "English Language", "History", "Fundamentals of the culture of speech", "physical Culture and sport"));
                subjects[1] = new ArrayList<>(Arrays.asList("Introduction to algebra and analysis", "Introduction to discrete mathematics and mathematical logic", "Declarative programming", "Imperative programming", "Digital platforms", "English Language", "physical Culture and sport"));
        subjects[2] = new ArrayList<>(Arrays.asList("Differential equations and the theory of functions of a complex variable", "Computation models", "Theory of Probability and Mathematical Statistics", "Introduction to artificial intelligence", "Object Oriented Programming", "Operating systems", "Development of a software and hardware complex for solving scientific and applied problems (team project)", "English Language", "physical Culture and sport"));
        subjects[3] = new ArrayList<>(Arrays.asList("Computation models", "Object Oriented Programming", "Concurrency theory", "Introduction to computer networking", "Introduction to Analog Electronics and Measurement Engineering", "Built-in digital control systems", "Development of a software and hardware complex for solving scientific and applied problems (team project)", "English Language", "Philosophy", "physical Culture and sport"));
        subjects[4] = new ArrayList<>(Arrays.asList("Computational Mathematics", "Machine learning methods", "Information Security", "Introduction to mobile app development", "Software design", "Storage and processing of information", "Team development of a multifunctional hardware and software complex", "physical Culture and sport"));
        subjects[5] = new ArrayList<>(Arrays.asList("Machine learning methods", "Information Security", "Introduction to mobile app development", "Software design", "Storage and processing of information", "Team development of a multifunctional hardware and software complex", "Computational linguistics", "Bioinformatics", "physical Culture and sport"));
        subjects[6] = new ArrayList<>(Arrays.asList("Protection of information", "Distributed algorithms", "Management of the production process of software development", "Standardization of software documentation", "Computational linguistics", "Bioinformatics", "Innovative Economy and Technological Entrepreneurship"));
        subjects[7] = new ArrayList<>(Arrays.asList("Protection of information", "Distributed algorithms", "Management of the production process of software development", "Standardization of software documentation", "Innovative Economy and Technological Entrepreneurship"));
        thesis = new int[]{4, 5, 5, 5};
        transcripts = new Transcript[4];
    }

    @BeforeEach
    void prepare() {
        transcripts[0] = new Transcript(new PersonalName(firstNames[0], surnames[0], patronymics[0]), identifiers[0], department, 0);
        transcripts[1] = new Transcript(new PersonalName(firstNames[1], surnames[1], patronymics[1]), identifiers[1], department, 8);
        transcripts[2] = new Transcript(new PersonalName(firstNames[2], surnames[2], patronymics[2]), identifiers[2], department, subjects);
        transcripts[3] = new Transcript(new PersonalName(firstNames[3], surnames[3], patronymics[3]), identifiers[3], department, subjects);
    }

    @Test
    @DisplayName("Get personal name")
    void getPersonalName_Test() {
        for(int i = 0; i < 4; i++) {
            PersonalName personalName = transcripts[i].getPersonalName();
            Assertions.assertEquals(personalName.getFirstName(), firstNames[i]);
            Assertions.assertEquals(personalName.getSurname(), surnames[i]);
            Assertions.assertEquals(personalName.getPatronymic(), patronymics[i]);
        }
    }

    @Test
    @DisplayName("Get full name")
    void getFullName_Test() {
        for(int i = 0; i < 4; i++) {
            Assertions.assertEquals(transcripts[i].getFullName(), firstNames[i] + ' ' + surnames[i] + ' ' + patronymics[i]);
        }
    }

    @Test
    @DisplayName("Get identifier")
    void getIdentifier_Test() {
        for(int i = 0; i < 4; i++) {
            Assertions.assertEquals(transcripts[i].getIdentifier(), identifiers[i]);
        }
    }

    @Test
    @DisplayName("Get department")
    void getDepartment_Test() {
        for(int i = 0; i < 4; i++) {
            Assertions.assertEquals(transcripts[i].getDepartment(), department);
        }
    }

    @Test
    @DisplayName("Get grades")
    void getGrades_Test() {
        HashMap<String, Integer>[] grades = transcripts[3].getGrades();
        for(int i = 0; i < 8; i++) {
            for(String subject : subjects[i]) {
                Assertions.assertTrue(grades[i].containsKey(subject));
            }
        }
    }

    @Test
    @DisplayName("Add semester")
    void addSemester_Test() {
        for(int i = 0; i < 8; i++) {
            transcripts[0].addSemester(subjects[i]);
            HashMap<String, Integer> grades = transcripts[0].getSubjects(i + 1);
            for(String subject : subjects[i]) {
                Assertions.assertTrue(grades.containsKey(subject));
            }
        }
    }

    @Test
    @DisplayName("Get subjects")
    void getSubjects_Test() {
        for(int i = 0; i < 8; i++) {
            HashMap<String, Integer> grades = transcripts[3].getSubjects(i + 1);
            for(String subject : subjects[i]) {
                Assertions.assertTrue(grades.containsKey(subject));
            }
        }
    }

    @Test
    @DisplayName("Set subjects")
    void setSubjects_Test() {
        for(int i = 0; i < 8; i++) {
            transcripts[1].setSubjects(i + 1, subjects[i]);
            HashMap<String, Integer> grades = transcripts[1].getSubjects(i + 1);
            for(String subject : subjects[i]) {
                Assertions.assertTrue(grades.containsKey(subject));
            }
        }
    }

    @Test
    @DisplayName("Add subject")
    void addSubject_Test() {
        for(int i = 0; i < 8; i++) {
            for(String subject : subjects[i]) {
                transcripts[1].addSubject(i + 1, subject);
            }
            HashMap<String, Integer> grades = transcripts[1].getSubjects(i + 1);
            for(String subject : subjects[i]) {
                Assertions.assertTrue(grades.containsKey(subject));
            }
        }
    }

    @Test
    @DisplayName("Get grade")
    void getGrade_Test() {
        int grade = 2;
        setGrades(transcripts[2], grade);
        for(int i = 0; i < 8; i++) {
            for(String subject : subjects[i]) {
                Assertions.assertEquals(transcripts[2].getGrade(i + 1, subject), grade);
            }
        }
    }

    @Test
    @DisplayName("Set grade")
    void setGrade_Test() {
        int grade = 2;
        for(int i = 0; i < 8; i++) {
            for(String subject : subjects[i]) {
                Assertions.assertTrue(transcripts[2].setGrade(i + 1, subject, grade));
                Assertions.assertEquals(transcripts[2].getGrade(i + 1, subject), grade);
                Assertions.assertFalse(transcripts[2].setGrade(i + 1, subject, grade - 1));
                Assertions.assertEquals(transcripts[2].getGrade(i + 1, subject), grade);
            }
        }
    }

    @Test
    @DisplayName("Get supplement")
    void getSupplement_Test() {
        HashMap<String, Integer> supplement = transcripts[3].getSupplement();
        for(int i = 0; i < 8; i++) {
            for(String subject : subjects[i]) {
                Assertions.assertTrue(supplement.containsKey(subject));
            }
        }
    }

    @Test
    @DisplayName("Get thesis")
    void getThesis_Test() {
        for(int i = 0; i < 4; i++) {
            Assertions.assertTrue(transcripts[i].setThesis(thesis[i]));
            Assertions.assertEquals(transcripts[i].getThesis(), thesis[i]);
        }
    }

    @Test
    @DisplayName("Set thesis")
    void setThesis_Test() {
        for(int i = 0; i < 4; i++) {
            Assertions.assertTrue(transcripts[i].setThesis(thesis[i]));
            Assertions.assertEquals(transcripts[i].getThesis(), thesis[i]);
            Assertions.assertFalse(transcripts[i].setThesis(2));
            Assertions.assertEquals(transcripts[i].getThesis(), thesis[i]);
        }
    }

    @Test
    @DisplayName("Calculate grade point averages")
    void calculateGPA_Test() {
        for(int i = 0; i < 8; i++) {
            transcripts[0].addSemester(subjects[i]);
            transcripts[1].setSubjects(i + 1, subjects[i]);
        }
        for(int i = 2; i <= 5; i++) {
            setGrades(transcripts[i-2], i);
            Assertions.assertEquals(transcripts[i-2].calculateGPA(), i);
        }
    }

    @Test
    @DisplayName("Is honours degree")
    void isHonoursDegree_Test() {
        for(int i = 0; i < 8; i++) {
            transcripts[0].addSemester(subjects[i]);
            transcripts[1].setSubjects(i + 1, subjects[i]);
        }
        setGrades(transcripts[0], 3);
        setGrades(transcripts[1], 3);
        setGrades(transcripts[2], 4);
        setGrades(transcripts[3], 5);
        for(int i = 0; i < 4; i++) {
            transcripts[i].setThesis(thesis[i]);
        }
        Assertions.assertFalse(transcripts[0].isHonoursDegree());
        Assertions.assertFalse(transcripts[1].isHonoursDegree());
        Assertions.assertFalse(transcripts[2].isHonoursDegree());
        Assertions.assertTrue(transcripts[3].isHonoursDegree());
    }

    @Test
    @DisplayName("Is advanced scholarship")
    void isAdvancedScholarship_Test() {
        for(String subject : subjects[0]) {
            transcripts[3].setGrade(1, subject, 3);
        }
        for(String subject : subjects[1]) {
            transcripts[3].setGrade(2, subject, 4);
        }
        for(String subject : subjects[2]) {
            transcripts[3].setGrade(3, subject, 5);
        }
        Assertions.assertTrue(transcripts[3].isAdvancedScholarship(1));
        Assertions.assertFalse(transcripts[3].isAdvancedScholarship(2));
        Assertions.assertTrue(transcripts[3].isAdvancedScholarship(3));
        Assertions.assertTrue(transcripts[3].isAdvancedScholarship(4));
    }

    private static String[] firstNames;
    private static String[] surnames;
    private static String[] patronymics;
    private static int[] identifiers;
    private static String department;
    private static ArrayList<String>[] subjects;
    private static int[] thesis;
    private static Transcript[] transcripts;

    private void setGrades(Transcript transcript, int grade) {
        for(int i = 0; i < 8; i++) {
            for(String subject : subjects[i]) {
                transcript.setGrade(i + 1, subject, grade);
            }
        }
    }
}