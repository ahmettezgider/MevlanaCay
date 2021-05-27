package utils;

public enum SubMenuLinks {

    SHOP ("Produkte", "Shop", "Ürünler"),
    ABOUTUS ("Über uns", "About Us", "Hakkımızda"),
    IMPRESSUM ("Impressum", "Impressum", "Künye"),
    FAQ ("Häufig gestellte Fragen", "Frequently Asked Questions", "Sıkça Sorulan Sorular"),
    TVADS ("Tv Spots", "Tv Ads", "Tv Reklamları"),
    BLOG ("Blog", "Blog", "Blog"),
    CONTACTUS ("Kontakt", "Contact Us", "İletişim")
    ;

    private String de;
    private String en;
    private String tr;

    SubMenuLinks(String de, String en, String tr) {
        this.de = de;
        this.en = en;
        this.tr = tr;
    }

    public String getsubTitle(String lang){
        lang = lang.toUpperCase();
        if (lang.equals("DE")) return de;
        if (lang.equals("EN")) return en;
        if (lang.equals("TR")) return tr;
        return de;
    }
}
