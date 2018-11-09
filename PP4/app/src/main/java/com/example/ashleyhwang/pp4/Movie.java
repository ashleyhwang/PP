package com.example.ashleyhwang.pp4;

import java.io.Serializable;


public class Movie implements Serializable{

        private String title;
        private String year;
        private String rated;
        private String releasedate;
        private String runtime;
        private String genre;
        private String director;
        private String actors;

        private String img_link;
        private boolean isSelected;

        public Movie(String title, boolean isSelected){
            this.title= title;
            this.isSelected = isSelected;
        }

        public Movie(){
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear(){return year;}
        public void setYear(String year){this.year = year;}

        public String getRated(){return rated;}
        public void setRated(String rated){this.rated = rated;}

        public String getReleasedate(){return releasedate;}
        public void setReleasedate(String releasedate){this.releasedate = releasedate;}

        public String getRuntime(){return runtime;}
        public void setRuntime(String runtime){this.runtime = runtime;
        }

        public String getGenre(){return genre;}
        public void setGenre(String genre){this.genre = genre;}

        public String getDirector(){return director;}
        public void setDirector(String director){this.director = director;}

        public String getActors(){return actors;}
        public void setActors(String actors){this.actors = actors;}

        public String getImg_link() {
            return img_link;
        }

        public void setImg_link(String img_link) {
            this.img_link = img_link;
        }


        public boolean isSelected() {

            return isSelected;
        }
        public void setSelected(boolean selected) {

            isSelected = selected;
        }

        @Override
        public String toString() {
            String msg = getTitle() + ", " + getGenre();
            return msg;
        }
    }


