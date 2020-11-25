package eu.ase.ro.damapp.network.xml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;

public class PremiumParser {
    public static Premium fromXml(String xml) {
        if (xml == null) {
            return null;
        }
        Premium result = new Premium();
        int event;
        String text = null;
        try {
            Item item = new Item();
            ArrayList<Product> products = new ArrayList<>();
            Product product = null;
            String type = null;
            //creare parser
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(new StringReader(xml));
            event = xmlParser.getEventType();
            //aceasta librarie interpreteaza fiecare componenta in parte a xml-ului. Astfel putem intalni urmatoarele date: tag de start (Ex <item ...>),
            // tag de sfarsit (Ex: </item>), atribute de la nivelul tag-urilor (Ex: <item type="Bronze">) si valoare (Ex: Pizza Diavola);
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = xmlParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        //este accesat in momentul in care ne aflam pe un tag de start. Ex: <item type="...">
                        //daca tag-ul este item, atunci ar trebui sa initializam un nou element de acest tip. Metoda noastra intoarce o lista de astfel de obiecte,
                        //Prin urmare, acest tag se intalneste o singura data pentru fiecare obiect in parte.
                        if (name.equals("item")) {
                            //fiecare tag de tipul "item" are un atribut numit 'type'. Aici se face preluarea valorii pe care acesta o retine.
                            type = xmlParser.getAttributeValue(null, "type");
                            products = new ArrayList<>();
                            item = new Item();
                        } else if (name.equals("product")) {// daca identificam un tag pentru produs, atunci ar trebui sa il initializam, astfel incat sa-l populam cu valori mai tarziu.
                            product = new Product();
                        }
                        break;
                    case XmlPullParser.TEXT:// in acest caz ne aflam atunci cand accesam o valoare dintr-un tag frunza. Ex: <categoruy>Food</category>
                        text = xmlParser.getText();
                        break;
                    case XmlPullParser.END_TAG: //tag-ul de end, va da informatiile necesare, pentru construirea obiectelor java.
                        // Cand va aflati in aceasta situatie puteti stiti ce valoare este salvata in variabila 'text' in tag-ul XmlPullParser.Text
                        if (name.equals("category")) {
                            //Ex de tag: </category>. Adaugam valoare in obiectul item.
                            item.setCategory(text);
                        } else if (name.equals("name") && product != null) {//nodul 'name' se afla doar la nivelul produsului, prin urmare il adaugam.
                            product.setName(text);
                        } else if (name.equals("price") && text != null && !text.isEmpty() && product != null) {
                            product.setPrice(Double.parseDouble(text));
                        } else if (name.equals("product")) {// daca avem tag-ul </product> atunci inseamna ca obiectul de tip produs ar trebui adaugat in lista de produse, ale item-ului curent.
                            products.add(product);
                        } else if (name.equals("item")) {//daca tag-ul este </item> atunci ar trebui sa adaugam lista de produse in item, iar item-ul format se adauga in lista de rezultate.
                            item.setProducts(products);
                            if (type != null && type.equalsIgnoreCase("bronze")) {
                                result.setBronze(item);
                            } else if (type != null && type.equalsIgnoreCase("silver")) {
                                result.setSilver(item);
                            } else if (type != null && type.equalsIgnoreCase("gold")) {
                                result.setGold(item);
                            }
                        }
                }
                //se acceseaza urmatoarea componenta a xml.
                event = xmlParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
