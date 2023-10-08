import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ParsingXml {

    // 파싱할 데이터가 있는 위치
    String path;

    // xml을 읽어주는 메소드
    ReadXml readXml = new ReadXml();

    // 파싱한 값을 넣어줄 배열
    ArrayList<String[]> CORPCODE = new ArrayList<>();

    // 객체를 생성할 때, 파싱을 하게 만듬
    ParsingXml(String path) throws ParserConfigurationException, IOException, SAXException {
        this.path = path;
        ParsingXml();
    }

    // private로 만들어서 객체를 생성할 때만 실행하게 만들어줌
    // 파싱해주는 메소드
    private void ParsingXml() throws ParserConfigurationException, IOException, SAXException {
        Document document = readXml.readXml(path);
        NodeList nList = document.getElementsByTagName("list");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                if (!getTagValue("stock_code", eElement).equals(" ")) {
                    String[] tmpStringArr = {getTagValue("corp_code", eElement), getTagValue("corp_name", eElement), getTagValue("stock_code", eElement)};
                    CORPCODE.add(tmpStringArr);
                }
            }
        }
    }

    // CORPCODE를 얻어주는 메소드로 계속 파일을 읽고 파싱하면 시간이 오래걸릴 것 같아서, 한번만 만들고 그냥 계속 가져오는 방향으로 코딩해봄
    ArrayList<String[]> getCORPCODE() {
        return CORPCODE;
    }

    // 값을 가져와주는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
