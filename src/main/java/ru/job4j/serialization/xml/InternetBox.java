package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;

@XmlRootElement(name = "InternetBox")
@XmlAccessorType(XmlAccessType.FIELD)
public class InternetBox {
    @XmlAttribute
    private boolean delivered;

    @XmlAttribute
    private int weight;

    @XmlAttribute
    private String postCompany;
    private Addr addr;

    @XmlElementWrapper(name = "descriptions")
    @XmlElement(name = "description")
    private String[] descriptions;

    public InternetBox() {

    }
    public InternetBox(
            boolean delivered,
            int weight,
            String postCompany,
            Addr addr,
            String... descriptions) {
        this.delivered = delivered;
        this.weight = weight;
        this.postCompany = postCompany;
        this.addr = addr;
        this.descriptions = descriptions;
    }

    public boolean getDelivered() {
        return delivered;
    }

    public int getWeight() {
        return weight;
    }

    public String getPostCompany() {
        return postCompany;
    }

    public Addr getAddr() {
        return addr;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPostCompany(String postCompany) {
        this.postCompany = postCompany;
    }

    public void setAddr(Addr addr) {
        this.addr = addr;
    }

    public void setDescriptions(String[]  descriptions) {
        this.descriptions = descriptions;
    }

    public static void main(String[] args)  throws Exception {
        InternetBox internetBox = new InternetBox(false,
                3123,
                "Почта России",
                new Addr("Россия", "Урюпинск", "Ленина", 1),
                "Заказное", "Срочное");
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(InternetBox.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(internetBox, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            InternetBox result = (InternetBox) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}