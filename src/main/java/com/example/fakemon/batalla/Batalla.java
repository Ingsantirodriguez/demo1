package com.example.fakemon.batalla;

import com.example.fakemon.*;
import com.example.fakemon.fakemons.*;


import java.util.HashMap;
import java.util.Random;

public class Batalla extends Torneo implements Observer {
    private HashMap<String, Fakemon> fakemons;
    private float currentUsrLife;
    private float currentBotLife;

    private Boolean usrTurn;
    private Fakemon winner;


    public Batalla(){
        this.fakemons = new HashMap<>();
        this.usrFakemon = null;
        this.botFakemon = null;
        this.usrTurn = true;
        this.winner = null;
        RandomTurno();
    }

    public Fakemon getWinner(){
        if(winner!=null){
            return winner;
        }else{
            System.out.println("No hay ganador aun");
            return null;
        }
    }

    private void RandomTurno() {
        Random rd = new Random();
        int value = rd.nextInt(2);
        if (value == 0) {
            this.usrTurn = false;
        } else {
            this.usrTurn = true;
        }
    }

    public void setUsrTurn(Boolean t){
        this.usrTurn = t;
    }
    public void setUsrFakemon(String fakemon){
        this.usrFakemon = fakemons.get(fakemon);
        fakemons.remove(this.usrFakemon.getName());     // elimino el user fakemon de la lista
        this.currentUsrLife = usrFakemon.getCurrentLife();
        usrFakemon.addObserver(this);
    }
    public void setWinner(boolean usr){
        if(usr){
            this.winner = usrFakemon;
            this.vsUsrWin.add(botFakemon);
        }else{
            this.winner = botFakemon;
            this.vsUsrLoose.add(botFakemon);
        }
        //this.torneo.nextBattle();
        if(this.battle_n == 5){
            showResults();
        }else{
            selecRandomFakemon();
            RandomTurno();
        }
        printResults();
    }

    public void printResults(){
        System.out.println("\n*****  GANADOR: "+this.winner.getName()+"  *****"
        + "\nVida Restante: " + this.winner.getCurrentLife()
        + "\nAtaque: " + this.winner.getAttackDamage());
    }

    public void setBotFakemon(String fakemon){
        this.botFakemon = fakemons.get(fakemon);
        this.currentBotLife = usrFakemon.getCurrentLife();
        botFakemon.addObserver(this);
    }

    public Fakemon getUsrFakemon(){
            return usrFakemon;
    }

    public Fakemon getBotFakemon(){
            return botFakemon;
    }

    public Float getCurrentUsrLife(){ return this.currentUsrLife; }
    public Float getCurrentBotLife(){ return this.currentBotLife; }

    public Boolean usrTurno(){
        return usrTurn;
    }

    public void selecRandomFakemon(){
        Random rd = new Random();                       // elijo un numero random
        int value = rd.nextInt(fakemons.size());        // entre 1 y n
        int cont = 0;                                   //
        for(Fakemon f: fakemons.values()){              // recorro el hash map hasta que value==cont
            if(value==cont){
                this.setBotFakemon(f.getName());        // obtengo el fakemon bot (rival)
                fakemons.remove(f.getName());           // elimino el rival de la lista
                break;                                  // salgo del for
            }
            cont++;                                     // si no encontre el rival, sigo buscando
        }
    }

    public void emptyFakemons(){

        if (!fakemons.isEmpty()){
            fakemons.clear();
        }
    }
    public void fillFakemons(){
        Fakemon[] val = {new Bulbasaur(), new Charmander(), new Jigglypuff()
                ,new Pidgey(), new Squirtle(), new Pikachu()};
        String[] key = {"Bulbasaur", "Charmander", "Jigglypuff", "Pidgey", "Squirtle", "Pikachu"};

        for(int i = 0; i<6; i++){
            fakemons.put(key[i], val[i]);
        }
    }

    public void botTurn(){                     // tampoco que vamos a hacer una AI....
        Random rd = new Random();
        int val = rd.nextInt(4) + 1;    // random number between 1-4

        switch (val){
            case 1:
                ataque();
                break;
            case 2:
                debilitar();
                break;
            case 3:
                potenciar();
                break;
            case 4:
                // no tiene sentido regenerar vida cuando la suya es maxima...
                if(getBotFakemon().getCurrentLife() == getBotFakemon().getBasicLife()){
                    botTurn();
                }else{
                    regenerar();
                }
                break;
            default:
                System.out.println("error generando numero random");
                break;
        }
    }

    public void ataque() {
        System.out.println("\nbot --> attack --> usr..");
        new Atacar().actuar(getBotFakemon(), getUsrFakemon());
        System.out.println("new usr life: " + getUsrFakemon().getCurrentLife());
        setUsrTurn(true);
    }

    private void debilitar() {
        System.out.println("\nbot --> weaken --> usr..");
        new Debilitar().actuar(getBotFakemon(), getUsrFakemon());
        System.out.println("new usr attack: " + getUsrFakemon().getAttackDamage());
        setUsrTurn(true);
    }

    public void regenerar() {
        System.out.println("\nbot --> regenerate life");
        new Regenerar().actuar(getBotFakemon(), getUsrFakemon());
        System.out.println("new bot life: " + getBotFakemon().getCurrentLife());
        setUsrTurn(true);
    }

    public void potenciar() {
        System.out.println("\nbot --> maximize attack");
        new Potenciar().actuar(getBotFakemon(),getUsrFakemon());
        System.out.println("new bot attack: " + getBotFakemon().getAttackDamage());
        setUsrTurn(true);
    }

    @Override
    public void actualizar() {
        System.out.println("holis");
    }
}
