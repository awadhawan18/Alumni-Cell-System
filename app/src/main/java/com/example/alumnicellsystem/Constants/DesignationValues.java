package com.example.alumnicellsystem.Constants;

public class DesignationValues {


    public enum Designations {

        PRINCIPAL("PRINCIPAL"),
        ASSOCIATE_PROFESSOR("associate professor"),
        ASSISTANT_PROFESSOR("assistant professor"),
        PROFESSOR("professor"),
        OTHER("other");

        private final String text;

        Designations(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

    }
}
