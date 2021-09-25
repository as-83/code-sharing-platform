package platform.repos;

import org.springframework.stereotype.Repository;
import platform.Code;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ArrayCodeRepo {

    private List<Code> codeList = new ArrayList<>();

    public List<Code> getAllCodes() {
        return  codeList;
    }

    public Code getCodeByNumber(int number) {
        if (number > 0 && number <= codeList.size()) {
            return codeList.get(number - 1);
        }
        return null;
    }

    public int addCode(Code code) {
        codeList.add(code);
        return codeList.indexOf(code);
    }

    public List<Code> getLatestCodes(int i) {
        List<Code> lastCodes = new ArrayList<>();
        for (int j = codeList.size() - 1; j >= 0 && j > codeList.size() - 11 ; j--) {
            lastCodes.add(codeList.get(j));
        }
        return lastCodes;
    }
}
