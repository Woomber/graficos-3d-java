package animation;

import figuras.Figura;
import matrices.MatrizTransformacion;
import projections.ParallelOrthogonalProjection;
import projections.Proyectador;

import java.util.ArrayList;
import java.util.List;

public class AnimationElement {

    protected Figura figura;
    protected List<MatrizTransformacion> actions;
    protected Proyectador proyectador;

    public AnimationElement(Figura figura) {
        this(figura, new ParallelOrthogonalProjection());
    }

    public AnimationElement(Figura figura, Proyectador proyectador) {
        this.figura = figura;
        this.proyectador = proyectador;

        actions = new ArrayList<>();
    }

    public void addAction(MatrizTransformacion action) {
        actions.add(action);
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
    }

    public void setProyectador(Proyectador proyectador) {
        this.proyectador = proyectador;
    }

    public Figura getFigura() {
        return figura;
    }

    public int getNumberOfActions() {
        return actions.size();
    }

    public MatrizTransformacion getAction(int index) {
        return actions.get(index);
    }

    public Proyectador getProyectador() {
        return proyectador;
    }
}
