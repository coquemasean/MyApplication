package com.coquema.sean.dev.mypokemonworld.Model;

/**
 * Created by Sean Coquema on 05/03/2019.
 *
 */

public class Evolution {

    public String num;
    public String name;

    public Evolution() {
    }

    public Evolution(String num, String name) {
        this.num = num;
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
