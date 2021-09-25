package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CodeController {
    private CodeService codeService;

    public CodeController(@Autowired CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code")
    public String getCodes(Model model) {
        model.addAttribute("codes", codeService.getAllCodes());
        model.addAttribute("title", "Code");
        return "code";
    }

    /*@GetMapping("code/{N}")
    public String getCodeByNumber(@PathVariable("N") long number, Model model) {
        List<Code> oneCodeList = List.of(codeService.getCodeById(number).get());/////////
        model.addAttribute("codes", oneCodeList);
        model.addAttribute("title", "Code");
        return "code";
    }*/

    @GetMapping("code/{uuid}")
    public String getCodeByNumber(@PathVariable("uuid") String uuid, Model model) {
        List<Code> oneCodeList = codeService.getCodeByUUID(uuid);/////////
        if (oneCodeList.size() > 0) {
            model.addAttribute("codes", oneCodeList);
            model.addAttribute("title", "Code");
            return "code";
        } else {
            return "redirect:api/code/{uuid}";
        }

    }

    @GetMapping("/code/new")
    public String getCodeNewInHtml() {
        return "addCode";
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        model.addAttribute("codes", codeService.getLatestTenCodes());
        model.addAttribute("title", "Latest");
        return "code";
    }

}
