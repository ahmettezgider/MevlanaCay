package utils;

public enum MenuLinks {

    HOME ("HOME","HOME", "ANASAYFA"),
    SHOP("PRODUKTE", "SHOP", "ÜRÜNLER"),
    MEVLANATEA("MEVLANA TEE", "MEVLANA TEA", "MEVLANA ÇAY"),
    ABOUTUS("ÜBER UNS", "ABOUT US", "HAKKIMIZDA", MEVLANATEA),
    IMPRESSUM("IMPRESSUM", "MEVLANA TEA", "KÜNYE", MEVLANATEA),
    FAQ("HÄUFIG GESTELLTE FRAGEN", "FAQ", "SIKÇA SORULAN SORULAR", MEVLANATEA),
    TVADS("TV SPOTS", "TV ADS", "TV REKLAMLARI", MEVLANATEA),
    BLOG("BLOG", "BLOG", "BLOG"),
    CONTACTUS("KONTAKT", "CONTACT US", "İLETİŞİM"),
    DE(" DE", " DE", " DE", 1),
    EN(" EN", " EN", " EN", 1),
    TR(" TR", " TR", " TR", 1)
    ;

    private Object[] vals;

    MenuLinks(Object...vals) {
        this.vals = vals;
    }

    public String getLang(int index){
        return vals[index].toString();
    }

    public boolean isSubMenu(){
        return vals.length>3;
    }

    public MenuLinks getBaseMenu(){
        return (MenuLinks) vals[3];
    }

    public boolean isLanguage(){
        return vals[0]==" DE" || vals[0]==" EN" || vals[0]==" TR";
    }

}
