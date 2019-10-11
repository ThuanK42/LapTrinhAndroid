package com.thuannluit.elements;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class CreateFileXML {
    public static void main(String[] args) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element employees; // Tao tag employees
            employees = document.createElement("employees");

            //========================Tao doi tuong===============================

            ArrayList<Employee> list = new ArrayList<>();

            Employee e1 = new Employee("1", "ProjectManager", "Trần Viết Sơn", "0932191045");
            Employee e2 = new Employee("2", "TeamLeader", "Lê Văn Thuận", "0983172229");
            Employee e3 = new Employee("3", "Marketing", "Bùi Thị Thanh Tâm", "14021998");
            Employee e4 = new Employee("4", "Accountant", "Hồ Thị Mỹ Trang", "21031998");
            Employee e5 = new Employee("5", "TourGuide", "Hà Trần", "09051998");
            Employee e6 = new Employee("6", "Architect", "Lê Thị Lệ Huyền", "04081998");
            Employee e7 = new Employee("7", "Architect", "Kiều Dung", "09011998");
            Employee e8 = new Employee("8", "Designer", "Nguyễn Thị Phương Thảo", "0983172229");
            Employee e9 = new Employee("9", "CEO", "Nguyễn Văn Quang", "0968104244");
            Employee e10 = new Employee("10", "ProjectManager", "Tô Thanh Sang", "0349103131");
            Employee e11 = new Employee("11", "TeamLeader", "Lâm Công Hậu", "0946743260");
            Employee e12 = new Employee("12", "TeamLeader", "Nguyễn Hiếu", "0344704400");
            Employee e13 = new Employee("13", "CEO", "Lê Hoàng", "0354851515");
            Employee e14 = new Employee("14", "BusinessAnalyst", "Lê Võ Đông Hội", "0342372233");
            Employee e15 = new Employee("15", "CEO", "Triệu Thiên Long", "0984466888");

            list.add(e1);
            list.add(e2);
            list.add(e3);
            list.add(e4);
            list.add(e5);
            list.add(e6);
            list.add(e7);
            list.add(e8);
            list.add(e9);
            list.add(e10);
            list.add(e11);
            list.add(e12);
            list.add(e13);
            list.add(e14);
            list.add(e15);

            // cho nay co the dung arraylisst cho no mau nhe, thoi gian ko co de toi uu code

            addEmployee(document, employees, e1);
            addEmployee(document, employees, e2);
            addEmployee(document, employees, e3);
            addEmployee(document, employees, e4);
            addEmployee(document, employees, e5);
            addEmployee(document, employees, e6);
            addEmployee(document, employees, e7);
            addEmployee(document, employees, e8);
            addEmployee(document, employees, e9);
            addEmployee(document, employees, e10);
            addEmployee(document, employees, e11);
            addEmployee(document, employees, e12);
            addEmployee(document, employees, e13);
            addEmployee(document, employees, e14);
            addEmployee(document, employees, e15);

            //==================================================================================
            document.appendChild(employees);

            // ghi file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // dat thuoc tinh cho phep moi phan tu xml nam tren mot dong
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //dat thuoc tinh cho phep moi phan tu xml lui ra thut vao so voi le tuy theo level cua no
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            // Chuyen doi tu doc -> file hoac text can nguon va dich, nguon la DOMSource, dich la Streamresult
            DOMSource nguon = new DOMSource(document);
            // duong dan chua file xml
            StreamResult dich = new StreamResult("F:\\Nam 4\\LapTrinhAndroid\\ThayToan\\Thuc hanh\\Tuan_5\\app\\src\\main\\res\\values\\file.xml");
            transformer.transform(nguon, dich);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Document document, Element employees, Employee e) {
        Element employee = document.createElement("employee");
        // thiet lap thuoc tinh cho <tag> employee : id voi title
        employee.setAttribute("id", e.getId());
        employee.setAttribute("title", e.getTitle());

        // tao <name>
        Element name = document.createElement("name");
        // tag name co chua text nen dung settextcontent de them text vao
        name.setTextContent(e.getName());
        // tao <name>
        Element phone = document.createElement("phone");
        // tag phone co chua text nen dung settextcontent de them text vao
        phone.setTextContent(e.getPhone());

        // Phan tu employee co cac con la name va phone dung appendChild den gan 2 thang do vao tag employee
        employee.appendChild(name);
        employee.appendChild(phone);

        // tag employee lai nam trong tag employees, dung appendchild de gan con employee cho thang cha employees
        employees.appendChild(employee);

    }
}