package platform;

public class HtmlHolder {
    static final String htmlWrapper = "<html>\n" +
            "<head>\n" +
            "    <title>%s</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<span id=\"load_date\">%s</span>" +
            "    <pre id=\"code_snippet\">\n" +
            "%s" +
            "</pre>\n" +
            "%s" +
            "</body>\n" +
            "</html>";

    static final String htmlWrapperInput = "<html>\n" +
            "<head>\n" +
            "    <title>%s</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<span id=\"load_date\">%s</span>" +
            "%s" +
            "\n" +
            "%s" +
            "</body>\n" +
            "</html>";
    public static final String textArea = "<textarea id=\"code_snippet\" placeholder=\"// Write new code\">" +
            "  </textarea>\n" +
            "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>";
    public static final String script = "<script type=\"text/javascript\"> function send() {\n" +
            "    let str = document.getElementById(\"code_snippet\").value;\n" +
            "    let object = {\n" +
            //"        \"code\": document.getElementById(\'code_snippet\').value\n" +
            "        \"code\": str" +
            "    };\n" +
            "    \n" +
            "    let json = JSON.stringify(object);\n" +
            "    \n" +
            "    let xhr = new XMLHttpRequest();\n" +
            "    xhr.open(\'POST\', '/api/code/new', false);\n" +
            "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
            "    xhr.send(json);\n" +
            "    \n" +
            "// console.log(object);" +
            "// console.log(json);" +
            "    if (xhr.status == 200) {\n" +
            "      alert(\"Success!\");\n" +
            "    }\n" +
            "else{alert(\"UnSuccess!\");}" +
            "" +
            "}" +
            "</script>";


}
