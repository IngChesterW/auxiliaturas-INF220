public class DFS {
    private List<Integer> recorrido;
    private Grafo grafo;
    protected UtilRecorridos control;
    public DFS(Grafo g){
    this.grafo = g;
    this.recorrido = new ArrayList<>();
    control = new UtilRecorridos(grafo.cantidadVertices());
    control.desmarcarTodos();
    }
    public void realizarDFS(int vertice){
    grafo.validarVertice(vertice);
    control.marcarVertice(vertice);
    recorrido.add(vertice);
    Iterable<Integer> adyacentes = this.grafo.adyacentesDeVertice(vertice);
    for (Integer adyacente : adyacentes) {
    if(!control.estaMarcado(adyacente)){
    realizarDFS(adyacente);
    }
    }
    }
    public boolean hayCaminoA(int vertice) {
    this.grafo.validarVertice(vertice);
    return this.control.estaMarcado(vertice);
    }
    public boolean hayCaminoATodos(){
    return this.control.estanTodosMarcados();
    }
    public Iterable<Integer> getRecorrido(){
    return this.recorrido;
    }
    public UtilRecorridos getControl(){
    return this.control;
    }
    }
    public class DebilmenteConexo {
    private DFS dfs;
    private Digrafo digrafo;
    private int SIN_VERTICE_NO_MARCADO = -1;
    public DebilmenteConexo(Digrafo unDigrafo) {
    this.digrafo = unDigrafo;
    DFS dfs = new DFS(digrafo);
    }
    public boolean esDebilmenteConexo() {
    dfs.realizarDFS(0);
    int proximoVerticeNoMarcado = 0;
    while (proximoVerticeNoMarcado != SIN_VERTICE_NO_MARCADO) {
    proximoVerticeNoMarcado = verticeNoMarcadoConAdyacenteMarcado();
    if(proximoVerticeNoMarcado != SIN_VERTICE_NO_MARCADO)
    dfs.realizarDFS(proximoVerticeNoMarcado);
    }
    return dfs.hayCaminoATodos();
    }
    private int verticeNoMarcadoConAdyacenteMarcado() {
    boolean existeVertice = false;
    int proximoVerticeNoMarcado = 0;
    while (proximoVerticeNoMarcado < digrafo.cantidadVertices() && !existeVertice) {
    if (!dfs.hayCaminoA(proximoVerticeNoMarcado)) {
    Iterator<Integer> adyacentesVertice =
    digrafo.adyacentesDeVertice(proximoVerticeNoMarcado).iterator();
    while (adyacentesVertice.hasNext() && !existeVertice) {
    existeVertice = dfs.hayCaminoA(adyacentesVertice.next());
    }
    if (!existeVertice)
    proximoVerticeNoMarcado++;
    } else
    proximoVerticeNoMarcado++;
    }
    if (proximoVerticeNoMarcado >= digrafo.cantidadVertices())
    return SIN_VERTICE_NO_MARCADO;
    else return proximoVerticeNoMarcado;
    }
    }