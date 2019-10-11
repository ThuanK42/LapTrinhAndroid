package com.thuannluit.baitaptuan5;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.thuannluit.models.Employee;
import com.thuannluit.models.Employees;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Button btnDOM, btnSAX;
    Spinner spn;
    ListView lvEmployee;

    private final String fileName = "file.xml";
    private final String TAG = getClass().getSimpleName();

    // cap doi tuong dung trong spinner
    ArrayList<Employees> arraySpinner = new ArrayList<Employees>();
    ArrayAdapter<Employees> adapterSpinner = null;
    // cap doi tuong danh cho ListView
    ArrayList<Employee> arrayListview = new ArrayList<Employee>();
    ArrayAdapter<Employee> adapterListview = null;

    static ArrayList<Employee> fakeData = new ArrayList<Employee>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidgetsControl();
        addEventsForFormWidgets();
        saveData();
    }

    //quyền đọc ghi file
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    //Kiểm tra thiết bị có bộ nhớ ngoài và có thể ghi file được không?
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Hàm lấy các control theo Id
     */
    private void getWidgetsControl() {
        spn = findViewById(R.id.spnNgheNghiep);
        btnDOM = findViewById(R.id.btnDOM);
        btnSAX = findViewById(R.id.btnSAX);
        lvEmployee = findViewById(R.id.lvDanhSachNhanVien);

        arraySpinner = listDataEmployees();

        // arrayListview = domParser();

        //Cấu hình cho Spinner
        adapterSpinner = new ArrayAdapter<Employees>(this,
                android.R.layout.simple_spinner_item,
                arraySpinner);

        adapterSpinner.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapterSpinner);

        adapterSpinner.notifyDataSetChanged();

        //Cấu hình cho ListView
        adapterListview = new ArrayAdapter<Employee>(this,
                android.R.layout.simple_list_item_1,
                arrayListview);
        lvEmployee.setAdapter(adapterListview);

        btnDOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveData();
                domParser();
            }
        });
        btnSAX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //readData();
                saxParser();
            }
        });
    }

    public void saveData() {
        if (isExternalStorageReadable()) {
            String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
            String content2 = "<employees>\n";
            String content3 = "</employees>\n";

            String e1 = "<employee id=\"1\" title=\"ProjectManager\">\n" +
                    "      <name>Tran Viet Son</name>\n" +
                    "      <phone>0932191045</phone>\n" +
                    "   </employee>\n";

            String e2 = "<employee id=\"2\" title=\"TeamLeader\">\n" +
                    "      <name>Le Van Thuan</name>\n" +
                    "      <phone>0983172229</phone>\n" +
                    "   </employee>\n";

            String e3 = "<employee id=\"3\" title=\"Marketing\">\n" +
                    "      <name>Bui Thi Thanh Tam</name>\n" +
                    "      <phone>14021998</phone>\n" +
                    "   </employee>\n";

            String e4 = "<employee id=\"4\" title=\"Accountant\">\n" +
                    "      <name>Ho Thi My Trang</name>\n" +
                    "      <phone>21031998</phone>\n" +
                    "   </employee>\n";

            String e5 = "<employee id=\"5\" title=\"TourGuide\">\n" +
                    "      <name>Ha Tran</name>\n" +
                    "      <phone>09051998</phone>\n" +
                    "   </employee>\n";

            String e6 = "<employee id=\"6\" title=\"Architect\">\n" +
                    "      <name>Le Thi Le Huyen</name>\n" +
                    "      <phone>04081998</phone>\n" +
                    "   </employee>\n";
            String e7 = "<employee id=\"7\" title=\"Architect\">\n" +
                    "      <name>Kieu Dung</name>\n" +
                    "      <phone>09011998</phone>\n" +
                    "   </employee>\n";
            String e8 = "<employee id=\"8\" title=\"Designer\">\n" +
                    "      <name>Nguyen Thi Phuong Thao</name>\n" +
                    "      <phone>0983172229</phone>\n" +
                    "   </employee>\n";
            String e9 = "<employee id=\"9\" title=\"CEO\">\n" +
                    "      <name>Nguyen Van Quang</name>\n" +
                    "      <phone>0968104244</phone>\n" +
                    "   </employee>\n";
            String e10 = "<employee id=\"10\" title=\"ProjectManager\">\n" +
                    "      <name>To Thanh Sang</name>\n" +
                    "      <phone>0349103131</phone>\n" +
                    "   </employee>\n";
            String e11 = "<employee id=\"11\" title=\"TeamLeader\">\n" +
                    "      <name>Lam Cong Hau</name>\n" +
                    "      <phone>0946743260</phone>\n" +
                    "   </employee>\n";
            String e12 = "<employee id=\"12\" title=\"TeamLeader\">\n" +
                    "      <name>Nguyen Hieu</name>\n" +
                    "      <phone>0344704400</phone>\n" +
                    "   </employee>\n";
            String e13 = "<employee id=\"13\" title=\"CEO\">\n" +
                    "      <name>Le Hoang</name>\n" +
                    "      <phone>0354851515</phone>\n" +
                    "   </employee>\n";
            String e14 = "<employee id=\"14\" title=\"BusinessAnalyst\">\n" +
                    "      <name>Le Vo Dong Hoi</name>\n" +
                    "      <phone>0342372233</phone>\n" +
                    "   </employee>\n";
            String e15 = "<employee id=\"15\" title=\"CEO\">\n" +
                    "      <name>Tan Thien Long</name>\n" +
                    "      <phone>0984466888</phone>\n" +
                    "   </employee>\n";

            File file;
            FileOutputStream outputStream;
            try {
                file = new File(Environment.getExternalStorageDirectory(), fileName);
                Log.d("MainActivity", Environment.getExternalStorageDirectory().getAbsolutePath());
                outputStream = new FileOutputStream(file);
                outputStream.write(content.getBytes());
                outputStream.write(content2.getBytes());

                outputStream.write(e1.getBytes());
                outputStream.write(e2.getBytes());
                outputStream.write(e3.getBytes());
                outputStream.write(e4.getBytes());
                outputStream.write(e5.getBytes());
                outputStream.write(e6.getBytes());
                outputStream.write(e7.getBytes());
                outputStream.write(e8.getBytes());
                outputStream.write(e9.getBytes());
                outputStream.write(e10.getBytes());
                outputStream.write(e11.getBytes());
                outputStream.write(e12.getBytes());
                outputStream.write(e13.getBytes());
                outputStream.write(e14.getBytes());
                outputStream.write(e15.getBytes());

                outputStream.write(content3.getBytes());
                outputStream.close();
                Toast.makeText(MainActivity.this, "Luu thanh cong", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Cannot save file", Toast.LENGTH_LONG).show();
        }
    }

    public void readData() {
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            String str = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line + "\n");

            }
            // txtdsEmployee.setText(buffer.toString());
            Toast.makeText(this, "Read file success", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Employees> listDataEmployees() {
        ArrayList<Employees> list = new ArrayList<Employees>();
        list.add(new Employees("ProjectManager"));
        list.add(new Employees("TeamLeader"));
        list.add(new Employees("Marketing"));
        list.add(new Employees("Accountant"));
        list.add(new Employees("TourGuide"));
        list.add(new Employees("Architect"));
        list.add(new Employees("Designer"));
        list.add(new Employees("CEO"));
        list.add(new Employees("BusinessAnalyst"));
        return list;
    }

//    private ArrayList<Employee> listDataEmployee() {
//        ArrayList<Employee> list = new ArrayList<Employee>();
//        list.add(new Employee("1", "ProjectManager", "Luu bi", "098317229"));
//        list.add(new Employee("2", "TeamLeader", "gia cat luong", "098317229"));
//        list.add(new Employee("3", "TeamLeader", "Bang Thong", "098317229"));
//        list.add(new Employee("4", "ProjectManager", "Tao Thao", "098317229"));
//        list.add(new Employee("5", "TeamLeader", "Quach Gia", "098317229"));
//        list.add(new Employee("6", "TeamLeader", "Tu Ma Y", "098317229"));
//        return list;
//    }

    private void addEventsForFormWidgets() {
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // xoa het du lieu listview
                arrayListview.clear();
                for (int i = 0; i < fakeData.size(); i++) {
                    // so sanh title spinner co trung voi title employee ko
                    if (arraySpinner.get(position).toString().equalsIgnoreCase(fakeData.get(i).getTitle())) {
                        // trung add vao list view
                        arrayListview.add(fakeData.get(i));
                        //cap nhat thay doi
                        adapterListview.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void saxParser() {
        BufferedReader input = null;
        File file = null;
        try {
            //Tạo đối tượng parser từ class XmlPullParser
            XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
            XmlPullParser parser = fc.newPullParser();
            //Tạo FileInputStream từ xml source (XML để trong SD Card)
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            FileInputStream fIn = new FileInputStream(file);
            parser.setInput(fIn, "UTF-8");

            int eventType = -1;
            String nodeName;
            String id = "";
            String title = "";
            String name = "";
            String phone = "";

            // Tiến hành duyệt
            while (eventType != XmlPullParser.END_DOCUMENT)//chưa kết thúc tài liệu
            {
                eventType = parser.next();// bắt đầu duyệt để
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG://là tag mở
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {// kiểm tra đúng tag mình muốn hay không
                            //datashow += parser.getAttributeValue(0) + "-";//lấy giá trị của thuộc tính
                            id = parser.getAttributeValue(0);
                            // datashow += parser.getAttributeValue(1) + "-";
                            title = parser.getAttributeValue(1);
                        } else if (nodeName.equals("name")) {
                            // datashow += parser.nextText() + "-";//lấy nội dung tag element
                            name = parser.nextText();
                        } else if (nodeName.equals("phone")) {
                            //  datashow += parser.nextText() + "-";
                            phone = parser.nextText();
                        }

                        break;
                    case XmlPullParser.END_TAG://là tag đóng
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            //  datashow += "\n----------------\n";

                        }
                        break;

                }
                arrayListview.add(new Employee(id, title, name, phone));
                fakeData.add(new Employee(id, title, name, phone));
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public ArrayList<Employee> domParser() {
        BufferedReader input = null;
        File file = null;
        try {
            //Tạo đối tượng DocumentBuilder (builder )
            DocumentBuilderFactory fac =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =
                    fac.newDocumentBuilder();
            // Tạo FileInputStream từ tập tin XML nguồn (trong sd card)
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            FileInputStream fIn = new FileInputStream(file);
            //Dùng phương thức parse của đối tượng builder để tạo Document
            Document doc = builder.parse(fIn);
            //lấy tag Root ra
            Element root = doc.getDocumentElement();
            // lấy toàn bộ node con của Root
            NodeList list = root.getChildNodes();
            //biến để lưu thông tin
            // duyệt từ node đầu tiên cho tới node cuối cùng
            for (int i = 0; i < list.getLength(); i++) {
                // mỗi lần duyệt thì lấy ra 1 node
                Node node = list.item(i);
                // kiểm tra xem node đó có phải là Element hay không, vì ta dựa vào element để lấy dữ liệu bên trong
                if (node instanceof Element) {
                    // lấy được tag Employee ra
                    Element employee = (Element) node;
                    //id là thuộc tính của tag Employee
                    String id = employee.getAttribute("id");
                    //title là thuộc tính của tag employee
                    String title = employee.getAttribute("title");
                    // lấy tag name bên trong của tag Employee
                    NodeList listChild = employee
                            .getElementsByTagName("name");
                    //lấy nội dung của tag name
                    String name = listChild.item(0).getTextContent();
                    // lấy tag phone bên trong của tag Employee
                    listChild = employee.getElementsByTagName("phone");
                    // lấy nội dung của tag phone</span>
                    String phone = listChild.item(0).getTextContent();
                    // theo vao mang arraylistEmployee
                    arrayListview.add(new Employee(id, title, name, phone));
                    fakeData.add(new Employee(id, title, name, phone));
                    // cap nhat thay doi du lieu cho list view
                    adapterListview.notifyDataSetChanged();
                }
            }
            Toast.makeText(this, "Doc File thanh cong", Toast.LENGTH_LONG).show();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayListview;
    }
}


