package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.repos.CodeRepo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeService {
    private CodeRepo codeRepo;

    public CodeService(@Autowired CodeRepo codeRepo) {
        this.codeRepo = codeRepo;
    }

    public List<Code> getAllCodes() {
        return (List<Code>) codeRepo.findAll();
    }

    public Optional<Code> getCodeById(long number) {
            return codeRepo.findById(number);
    }

    public Code addCode(Code newCode) {
        newCode.setDate(LocalDateTime.now());
        newCode.setUUID(UUID.randomUUID().toString());
        if (newCode.getTime() > 0) {
            if (newCode.getViews() > 0) {
                newCode.setRestriction(Restriction.ALL);
            } else {
                newCode.setRestriction(Restriction.TIME);
            }
        } else if (newCode.getViews() > 0) {
            newCode.setRestriction(Restriction.VIEW);
        } else {
            newCode.setRestriction(Restriction.NO);
        }
        return codeRepo.save(newCode);
    }

    public List<Code> getLatestTenCodes() {
        return ((List<Code>) codeRepo.findAll()).stream()
                .filter(c -> c.getRestriction() == Restriction.NO)
                .sorted(Comparator.comparing(Code::getId).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Code> getCodeByUUID(String uuid) {
        Optional<Code> code = codeRepo.findByUUID(uuid);
        List<Code> codeList  = Collections.emptyList();;
        if (code.isPresent()) {
            Code codeFromDb = code.get();
            Restriction restriction = code.get().getRestriction();
            switch (restriction) {
                case TIME: {
                    checkTime(codeFromDb);
                } break;
                case VIEW: {
                    checkViews(codeFromDb);
                } break;
                case ALL: {
                    checkTime(codeFromDb);
                    checkViews(codeFromDb);
                }; break;
            }
            if (codeFromDb.getRestriction() == Restriction.EXPIRED) {
                codeList = Collections.emptyList();
            } else {
                codeList = List.of(codeFromDb);
            }
            codeRepo.save(codeFromDb);
        }

        return  codeList;
    }

    private void checkViews(Code codeFromDb) {
        if (codeFromDb.getViews() > 0) {
            codeFromDb.setViews(codeFromDb.getViews() - 1);
        } else if (codeFromDb.getViews() < 1) {
            //codeFromDb.setViews(0);
            codeFromDb.setRestriction(Restriction.EXPIRED);
        }
    }

    private void checkTime(Code codeFromDb) {

        if (codeFromDb.getTime() <= 0) {
            codeFromDb.setRestriction(Restriction.EXPIRED);
        }
    }
}
