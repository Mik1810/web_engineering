package it.univaq.webmarket.model.Categorie;

public class CategoriaFiglio extends Categoria{

    private CategoriaPadre categoriaPadre;

    public CategoriaFiglio(String nome, Integer id, CategoriaPadre categoriaPadre) {
        super(nome, id);
        this.categoriaPadre = categoriaPadre;
    }

    public CategoriaPadre getCategoriaGenitore() { return this.categoriaPadre; }

    public void setCategoriaGenitore(CategoriaPadre categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }
}
