package com.example.dataservise.ServiceD;

import com.example.dataservise.DasturchiRepozitory.DasturchiRepo;
import com.example.dataservise.Entity.DasturchiEntity;
import com.example.dataservise.Payload.ApiResponsD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DasturchiServise {
    @Autowired
    DasturchiRepo dasturchiRepo;

    public ApiResponsD addDasturchi(DasturchiEntity dasturchi) {
        boolean b = dasturchiRepo.existsByEmail(dasturchi.getEmail());
        if(b)
        return new ApiResponsD("Bunday dasturchi allaqachon ro'yhatdan o'tgan",false);
        dasturchiRepo.save(dasturchi);
        return new ApiResponsD("dasturchi ma'lumotlari saqlandi",true);
    }
    public ApiResponsD editDasturchi(Integer id, DasturchiEntity dasturchi) {
        Optional<DasturchiEntity> byId = dasturchiRepo.findById(id);
        if (byId.isPresent()){
            DasturchiEntity dasturchi1=byId.get();
            dasturchi1.setIsm(dasturchi.getIsm());
            dasturchi1.setFamiliya(dasturchi.getFamiliya());
            dasturchi1.setEmail(dasturchi.getEmail());
            dasturchi1.setMaosh(dasturchi.getMaosh());
            dasturchi1.setLavozim(dasturchi.getLavozim());
            dasturchiRepo.save(dasturchi1);
            return new ApiResponsD("Ma'lumotlar tahrirlandi", true);
        }
        return new ApiResponsD("Bazada bunday idli xodim mavjud emas", false);
    }

    public ApiResponsD idreadDasturchi(Integer id) {
        Optional<DasturchiEntity> byId = dasturchiRepo.findById(id);
        if (byId.isPresent()){
            List<DasturchiEntity> list=dasturchiRepo.findAll();
            for (DasturchiEntity dasturchi : list) {
                String[] matn=dasturchi.toString().split(", ");
                String ss="";
                for (String s : matn) {
                    if (s.indexOf("(")>0){
                        s=s.substring(s.indexOf("(")+1);
                    }
                    if (s.indexOf(")")>0){
                        s=s.substring(0,s.indexOf(")"));
                    }
                    ss+=s+"\n";
                }
                if (dasturchi.getId()==id) return new ApiResponsD(ss,true);
            }
        }
        return new ApiResponsD("Bazada bunday idli dasturchi mavjud emas!",false);
    }

    public ApiResponsD readDasturchi() {
        List<DasturchiEntity> list=dasturchiRepo.findAll();
        String ss="";
        for (DasturchiEntity dasturchi : list) {
            String[] matn=dasturchi.toString().split(", ");
            for (String s : matn) {
                if (s.indexOf("(")>0){
                    s=s.substring(s.indexOf("(")+1);
                }
                if (s.indexOf(")")>0){
                    s=s.substring(0,s.indexOf(")"));
                }
                ss+=s+"\n";
            }
            ss+="\n";
        }
        return new ApiResponsD(ss,true);
    }

    public ApiResponsD delDasturchi(Integer id) {
        Optional<DasturchiEntity> byId = dasturchiRepo.findById(id);
        if (byId.isPresent()){
            dasturchiRepo.deleteById(id);
            return new ApiResponsD("Muvoffaqiyatli o'chirildi", true);
        }
        return new ApiResponsD("Bazada buday idli hodim mavjud emas!!!", false);
    }
}
