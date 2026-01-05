package io;

import java.awt.Point;

import torre.Torre;
import torre.TorreBalista;
import torre.TorreCanhao;
import torre.TorreMacaco;
import torre.TorreMorteiro;
import torre.TorreNinja;
import torre.TorreOctogonal;
import torre.TorreSniper;
import torre.VisitorTorre;

public class GameWVisitor implements VisitorTorre {

    private String infosave;
    public String getInformacaosave() {
        return infosave;
    }

    @Override
    public void visita(Torre t) {
        infosave = ""; 
    }

    // --- Torres Simples ---
    @Override
    public void visita(TorreMacaco t) {
        infosave = "macaco";
    }

    @Override
    public void visita(TorreCanhao t) {
        infosave = "canhao";
    }

    @Override
    public void visita(TorreNinja t) {
        infosave = "ninja";
    }

    // --- Torres Complexas ---
    @Override
    public void visita(TorreOctogonal t) {
        infosave = "octo\t" + t.getComponente().getAngulo();
    }

    @Override
    public void visita(TorreBalista t) {
        infosave = "balista\t" + t.getComponente().getAngulo();
    }
    
    @Override
    public void visita(TorreSniper t) {
        infosave = "sniper\t" + t.getComponente().getAngulo();
    }

    @Override
    public void visita(TorreMorteiro t) {
        Point ataque = t.getAreaAlvo();
        infosave = "morteiro\t" + ataque.x + "\t" + ataque.y;
    }
}
    

