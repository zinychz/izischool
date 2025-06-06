package com.izi.izischool.chemistry.common;

public class Constants {

    private Constants() {
    }

    public static class URL {

        private URL() {
        }

        public static final String ROOT_URL = "/";
        public static final String ID = "/{id}";
    }

    public static class PathVariable {

        private PathVariable() {
        }

        public static final String ID = "id";
    }

    public static class Spliterator {

        private Spliterator() {
        }

        public static final String IZI_ID = ":izi_id:";
        public static final String IZI_TYPE = ":izi_type:";
        public static final String IZI_FORMAT = ":izi_format:";
    }

    public static class ROLE {

        private ROLE() {
        }

        public static final String ROLE_ANONYMOUS = "ANONYMOUS";
        public static final String ROLE_USER = "USER";
    }
}
