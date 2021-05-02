package com.example.wing_project_android_app.methods.ai;

public class AI_Experience {
    String experience_str;
    Class experience_class;

    public AI_Experience(String experience_str, Class experience_class){
        this.experience_str = experience_str;
        this.experience_class = experience_class;
    }

    public String getExperience_str(){
        return experience_str;
    }

    public Class getExperience_class() {
        return experience_class;
    }

    public void setExperience_str(String experience_str){
        this.experience_str = experience_str;
    }

    public void setExperience_class(Class experience_class) {
        this.experience_class = experience_class;
    }
}
