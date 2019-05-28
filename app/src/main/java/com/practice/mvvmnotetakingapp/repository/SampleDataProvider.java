package com.practice.mvvmnotetakingapp.repository;

import com.practice.mvvmnotetakingapp.model.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SampleDataProvider {

    private static Date getDate(int amountOfTimeDiffer) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, amountOfTimeDiffer);

        return calendar.getTime();
    }

    public static ArrayList<NoteEntity> getSampleNotesData() {
        ArrayList<NoteEntity> arrayList = new ArrayList<>();

        String dummyNoteOne = "Dummy NoteEntity 1.";

        String dummyNoteTwo = "Dummy NoteEntity 2.Motion respects and reinforces the user as the prime mover. Primary user actions are \"\n" +
                "        \"inflection points that initiate motion, transforming the whole design.";

        String dummyNoteThree = "Dummy NoteEntity 3.A material metaphor is the unifying theory of a rationalized space and a system of motion.\"\n" +
                "        \"The material is grounded in tactile reality, inspired by the study of paper and ink, yet \"\n" +
                "        \"technologically advanced and open to imagination and magic.\\n\"\n" +
                "        \"Surfaces and edges of the material provide visual cues that are grounded in reality. The \"\n" +
                "        \"use of familiar tactile attributes helps users quickly understand affordances. Yet the \"\n" +
                "        \"flexibility of the material creates new affordances that supercede those in the physical \"\n" +
                "        \"world, without breaking the rules of physics.\\n\"\n" +
                "        \"The fundamentals of light, surface, and movement are key to conveying how objects move, \"\n" +
                "        \"interact, and exist in space and in relation to each other. Realistic lighting shows \"\n" +
                "        \"seams, divides space, and indicates moving parts.";

        arrayList.add(new NoteEntity( dummyNoteOne, getDate(0)));
        arrayList.add(new NoteEntity( dummyNoteTwo, getDate(-1)));
        arrayList.add(new NoteEntity(dummyNoteThree, getDate(-2)));

        return arrayList;
    }

}
