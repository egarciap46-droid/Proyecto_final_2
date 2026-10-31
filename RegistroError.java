package mycompany;

public class RegistroError {
    private String archivoOrigen;
    private String registroOriginal;
    private String motivoRechazo;

    public RegistroError(String archivoOrigen, String registroOriginal, String motivoRechazo) {
        this.archivoOrigen = archivoOrigen;
        this.registroOriginal = registroOriginal;
        this.motivoRechazo = motivoRechazo;
    }

    public String getArchivoOrigen() { return archivoOrigen; }
    public String getRegistroOriginal() { return registroOriginal; }
    public String getMotivoRechazo() { return motivoRechazo; }
}