package game.manipulator;

import torre.Torre;
import torre.TorreBalista;
import torre.TorreCanhao;
import torre.TorreMacaco;
import torre.TorreMorteiro;
import torre.TorreNinja;
import torre.TorreOctogonal;
import torre.TorreSniper;
import torre.VisitorTorre;

public class ManipuladorVisitor implements VisitorTorre {
     private ManipuladorTorre manipulador;

    public ManipuladorTorre criarManipulador(Torre t) {
        t.aceitar(this);
        return manipulador;
    }

    @Override
    public void visita(Torre t) {
        manipulador = new ManipuladorVazio(t);
    }

    @Override public void visita(TorreSniper t) { manipulador = new ManipuladorVazio(t); }

    @Override public void visita(TorreBalista t) { manipulador = new ManipuladorBalista(t); }

    @Override public void visita(TorreMorteiro t) { manipulador = new ManipuladorMorteiro(t);}

    @Override public void visita(TorreOctogonal t) { manipulador = new ManipuladorOcto(t);}

    @Override public void visita(TorreMacaco t) { manipulador = new ManipuladorVazio(t); }

    @Override public void visita(TorreCanhao t) { manipulador = new ManipuladorVazio(t);}

    @Override public void visita(TorreNinja t) {manipulador = new ManipuladorVazio(t);}

}
