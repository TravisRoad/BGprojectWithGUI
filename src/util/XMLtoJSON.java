package util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XMLtoJSON {

    public static String convert(String xmlString) {
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        String jsonPrettyPrintString = null;
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
            jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        return jsonPrettyPrintString;
    }

    public static void main(String[] args) {
        int PRETTY_PRINT_INDENT_FACTOR = 4;
        String TEST_XML_STRING =
                "<?xml version=\"1.0\" ?><test attrib=\"moretest\">Turn this to JSON</test>";
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
    }
}

