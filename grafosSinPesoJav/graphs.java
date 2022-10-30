public String caminoYCostoMinimoEntreVerticesDijsktra(int verticeInicial, int verticeDestino) {
    UtilRecorridos control = new UtilRecorridos(this.grafo.cantidadVertices());
    String s="";
    List<Double> costos=inicializarCostos(verticeInicial);
    List<Boolean> marcados=inicializarMarcados();
    List<Integer> pred=inicializarPredecesores();
    int noMarcadoDeMenorCosto=verticeInicial;
    while(!control.estaMarcado(verticeDestino) && costos.get(noMarcadoDeMenorCosto) != INFINITO) {
    control.marcarVertice(noMarcadoDeMenorCosto);
    List<AdyacenteConPeso> adyacentes = adyacentesDeVertice(noMarcadoDeMenorCosto);
    int verticeActual=listaDeVertices.get(noMarcadoDeMenorCosto);
    for(int i=0; i<adyacentes.size(); i++) {
    if(!control.estaMarcado(i)) {
    double costoAdyacente=costos.get(i);
    double costoActual=costos.get(noMarcadoDeMenorCosto);
    if(costoAdyacente>(costoActual+peso(verticeActual, i))) {
    costos.set(i, costoActual+peso(verticeActual, i));
    pred.set(i, noMarcadoDeMenorCosto);
    }
    }
    }
    noMarcadoDeMenorCosto=posicionMenorCostoNoMarcado(costos, control);
    }
    if(costos.get(verticeDestino) != INFINITO){
    s+="Camino entre "+verticeInicial+" y "+verticeDestino+": "+encontrarCaminoDijsktra(pred,verticeDestino)
    +" Costo: "+costos.get(verticeDestino);
    }else {
    s+="Camino entre "+verticeInicial+" y "+verticeDestino+": "+"No hay camino entre los vertices Costo: --";
    }
    return s;
    }
    private String encontrarCaminoDijsktra(List<Integer> pred, int posicionDestino) {
    List<Integer> camino = new ArrayList<>();
    camino.add(posicionDestino);
    int elemento=pred.get(posicionDestino);
    int pos=posicionDestino;
    while(elemento != -1 && camino.size()<=cantidadDeVertices()) {
    camino.add(elemento);
    pos=elemento;
    elemento=pred.get(pos);
    }
    String s=""+camino.get(camino.size()-1);
    for(int i=camino.size()-2; i>=0; i--) {
    s+="->"+camino.get(i);
    }
    return s;
    }
    private int posicionMenorCostoNoMarcado(List<Double> costos, UtilRecorridos control) {
    int men=0;
    double comp=INFINITO;
    for(int i=0; i<costos.size(); i++) {
    if(!control.estaMarcado(i)) {
    if(costos.get(i)<=comp) {
    comp=costos.get(i);
    men=i;
    }
    }
    }
    return men;
    }
    private List<Double> inicializarCostos(int verticeInicial){
    List<Double> costos = new ArrayList<>();
    for(int i=0; i<cantidadDeVertices(); i++) {
    if(i != verticeInicial) {
    costos.add(INFINITO);
    }else {
    costos.add(0.0);
    }
    }
    return costos;
    }
    private List<Integer> inicializarPredecesores(){
    List<Integer> pred = new ArrayList<>();
    for(int i=0; i<cantidadDeVertices(); i++) {
    pred.add(-1);
    }
    return pred;
    }