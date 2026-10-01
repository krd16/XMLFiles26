package controllers;

import models.Car;
import models.Dealership;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadXML {
    public Dealership readDealership(Path path) throws SAXException, IOException, NumberFormatException {
        Dealership dealership = new Dealership();
        Document document = null;
        DocumentBuilder documentBuilder = null;

        // Library W3C DOM works creating a document with the following objects
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error while parsing file");
        }

        document = documentBuilder.parse(path.toFile());

        Element root = document.getDocumentElement();

        NodeList carList = root.getElementsByTagName("coche");
        NodeList brandList = root.getElementsByTagName("marca");
        NodeList modelList = root.getElementsByTagName("modelo");
        NodeList engineDisplacementList = root.getElementsByTagName("cilindrada");

        Car auxCar;
        List<Car> auxList = new ArrayList<>();

        for (int index = 0; index < carList.getLength(); index++) {
            auxCar = new Car();

            auxCar.setBrand(brandList.item(index).getTextContent());
            auxCar.setModel(modelList.item(index).getTextContent());
            auxCar.setEngineDisplacement(Float.parseFloat(engineDisplacementList.item(index).getTextContent()));
            auxCar.setId(Integer.parseInt(((Element) carList.item(index)).getAttribute("id")));

            auxList.add(auxCar);
        }
        dealership.setCars(auxList);

        return dealership;
    }
}
