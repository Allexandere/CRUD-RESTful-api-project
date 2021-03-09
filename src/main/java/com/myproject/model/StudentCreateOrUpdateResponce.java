package com.myproject.model;

import lombok.Data;

@Data
public class StudentCreateOrUpdateResponce {
    private boolean isCan;
    private StringBuilder responce = new StringBuilder();

    public StudentCreateOrUpdateResponce(boolean isCan){
        this.isCan = isCan;
    }

    public void addError(String error){
        responce.append(error + "\n");
    }

    @Override
    public String toString(){
        return responce.toString();
    }
}
